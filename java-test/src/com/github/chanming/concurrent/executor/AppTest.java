package com.github.chanming.concurrent.executor;

import java.util.List;
import java.util.Random;

/**
 * Description:
 * Create Date:2018年12月19日
 * @author XuMaoSen
 * Version:1.0.0
 */
public class AppTest {
    private final static String JOB_NAME = "计算数值";
    private final static int JOB_LENGTH = 100;

    // 查询任务进度的线程
    private static class QueryResult implements Runnable {
        private final PendingJobPool pool;

        public QueryResult(PendingJobPool pool) {
            this.pool = pool;
        }

        @Override
        public void run() {
            int i = 0;// 查询次数
            while (i < 100) {
                List<TaskResult<String>> taskDetail = pool.getTaskDetails(JOB_NAME);
                if (!taskDetail.isEmpty()) {
                    System.out.println(pool.getJobInfos(JOB_NAME));
                    System.out.println(taskDetail);
                }
                try {
                    Thread.sleep(100);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
            }
        }
    }

    public static void main(String[] args) {
        TestTask myTask = new TestTask();
        // 拿到框架的实例
        PendingJobPool pool = PendingJobPool.getInstance();
        // 注册job
        pool.registerJob(JOB_NAME, JOB_LENGTH, myTask, 1000 * 10);
        Random r = new Random();
        for (int i = 0; i < JOB_LENGTH; i++) {
            // 依次推入Task
            pool.putTask(JOB_NAME, r.nextInt(1000));
        }
        Thread t = new Thread(new QueryResult(pool));
        t.start();
    }
}
