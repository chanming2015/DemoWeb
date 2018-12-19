package com.github.chanming.concurrent.executor;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description: Create Date:2018年12月19日
 *
 * @author XuMaoSen Version:1.0.0
 */
public class PendingJobPool {
    private PendingJobPool() {}

    public static PendingJobPool getInstance() {
        return PoolHolder.poll;
    }

    private static final class PoolHolder {
        private static final PendingJobPool poll = new PendingJobPool();
    }

    private static final int THREAD_COUNTS = Runtime.getRuntime().availableProcessors();
    private static BlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<>(5000);
    private static final ExecutorService taskExecutor = new ThreadPoolExecutor(THREAD_COUNTS, THREAD_COUNTS, 60, TimeUnit.SECONDS, taskQueue);
    private static final ConcurrentHashMap<String, JobInfo<?, ?>> jobInfoMap = new ConcurrentHashMap<>();
    private static CheckJobProcesser checkJob = CheckJobProcesser.getInstance();

    public static Map<String, JobInfo<?, ?>> getJobinfomap() {
        return jobInfoMap;
    }

    public <R, D> void registerJob(String jobName, int taskLength, TaskProcesser<R, D> taskProcesser, long expireTime) {
        JobInfo<R, D> jobInfo = new JobInfo<R, D>(jobName, taskLength, taskProcesser, expireTime);
        if (jobInfoMap.putIfAbsent(jobName, jobInfo) != null) {
            throw new RuntimeException(jobName + "已经注册了！");
        }
    }

    public <R, D> void putTask(String jobName, D data) {
        JobInfo<R, D> jobInfo = getJob(jobName);
        PendingTask<R, D> task = new PendingTask<R, D>(jobInfo, data, checkJob);
        taskExecutor.execute(task);
    }

    public <R, D> List<TaskResult<R>> getTaskDetails(String jobName) {
        JobInfo<R, D> jobInfo = getJob(jobName);
        return jobInfo.getTaskDetails();
    }

    public <R, D> Map<String, Integer> getJobInfos(String jobName) {
        Map<String, Integer> map = new HashMap<>(4);
        JobInfo<R, D> jobInfo = getJob(jobName);
        map.put("totalCount", jobInfo.getTotalCount());
        map.put("successCount", jobInfo.getSuccessCount());
        map.put("failCount", jobInfo.getFailCount());
        return map;
    }

    @SuppressWarnings("unchecked")
    private <R, D> JobInfo<R, D> getJob(String jobName) {
        JobInfo<R, D> jobInfo = (JobInfo<R, D>) jobInfoMap.get(jobName);
        if (null == jobInfo) {
            throw new RuntimeException(jobName + "是个非法任务。");
        }
        return jobInfo;
    }

    private final class JobInfo<R, D> {
        private final String jobName;
        private final int taskLength;
        private final TaskProcesser<R, D> taskProcesser;
        private final AtomicInteger successCount;
        private final AtomicInteger totalCount;
        private final BlockingQueue<TaskResult<R>> taskDetailQueue;
        // 工作的完成保存的时间，超过这个时间从缓存中清除
        private final long expireTime;

        /**
         * @author XuMaoSen
         */
        public JobInfo(String jobName, int taskLength, TaskProcesser<R, D> taskProcesser, long expireTime) {
            this.jobName = jobName;
            this.taskLength = taskLength;
            this.taskProcesser = taskProcesser;
            this.successCount = new AtomicInteger(0);
            this.totalCount = new AtomicInteger(0);
            this.taskDetailQueue = new LinkedBlockingQueue<>(this.taskLength);
            this.expireTime = expireTime;
        }

        public String getJobName() {
            return jobName;
        }

        public TaskProcesser<R, D> getTaskProcesser() {
            return taskProcesser;
        }

        public int getSuccessCount() {
            return successCount.get();
        }

        public int getTotalCount() {
            return totalCount.get();
        }

        public int getFailCount() {
            return getTotalCount() - getSuccessCount();
        }

        public List<TaskResult<R>> getTaskDetails() {
            List<TaskResult<R>> taskList = new LinkedList<>();
            TaskResult<R> taskResult;
            // 循环从阻塞队列中拿任务的结果，
            while ((taskResult = taskDetailQueue.poll()) != null) {
                taskList.add(taskResult);
            }
            return taskList;
        }

        public void putTaskResult(TaskResult<R> result, CheckJobProcesser checkJob) {
            if (TaskResultType.Success.equals(result.getResultType())) {
                successCount.incrementAndGet();
            }
            totalCount.incrementAndGet();
            taskDetailQueue.add(result);
            if (totalCount.get() == taskLength) {
                checkJob.putJob(jobName, expireTime);
            }
        }
    }

    private final class PendingTask<R, D> implements Runnable {
        private final JobInfo<R, D> jobInfo;
        private final D processData;
        private final CheckJobProcesser checkJob;

        /**
         * @author XuMaoSen
         */
        public PendingTask(JobInfo<R, D> jobInfo, D processData, CheckJobProcesser checkJob) {
            this.jobInfo = jobInfo;
            this.processData = processData;
            this.checkJob = checkJob;
        }

        /**
         * @author XuMaoSen
         */
        @Override
        public void run() {
            TaskProcesser<R, D> taskProcesser = jobInfo.getTaskProcesser();
            String jobName = jobInfo.getJobName();
            TaskResult<R> result = null;
            try {
                result = taskProcesser.taskExecute(processData);
            }
            catch (Exception e) {
                result = new TaskResult<R>(TaskResultType.Exception, null, String.format("jobName:%s: %s", jobName, e.getMessage()));
            }
            // 要做检查，防止开发人员处理不当
            if (result == null) {
                result = new TaskResult<R>(TaskResultType.Exception, null, String.format("jobName:%s: %s", jobName, "result is null"));
            }
            else if (result.getResultType() == null) {
                if (result.getMessage() == null) {
                    result = new TaskResult<R>(TaskResultType.Exception, result.getResult(), String.format("jobName:%s: %s", jobName,
                            "message is null"));
                }
                else {
                    result = new TaskResult<R>(TaskResultType.Exception, result.getResult(), String.format("jobName:%s: %s", jobName,
                            "result is not null,but message:", result.getMessage()));
                }
            }
            jobInfo.putTaskResult(result, checkJob);
        }
    }
}
