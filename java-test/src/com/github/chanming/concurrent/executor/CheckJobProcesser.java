package com.github.chanming.concurrent.executor;

import java.util.concurrent.DelayQueue;

/**
 * Description: Create Date:2018年12月20日
 *
 * @author XuMaoSen Version:1.0.0
 */
public class CheckJobProcesser {
    private static final DelayQueue<ItemVo<String>> queue = new DelayQueue<ItemVo<String>>();// 存放已完成任务等待过期的队列
    private CheckJobProcesser() {}
    private static final class ProcesserHolder {
        public static CheckJobProcesser processer = new CheckJobProcesser();
    }
    public static CheckJobProcesser getInstance() {
        return ProcesserHolder.processer;
    }

    // 处理队列中到期任务的实行
    private static class FetchJob implements Runnable {
        @Override
        public void run() {
            ItemVo<String> item;
            while (true) {
                item = null;
                try {
                    // 拿到已经过期的任务
                    item = queue.take();
                }
                catch (InterruptedException e) {}
                if (item != null) {
                    String jobName = item.getData();
                    PendingJobPool.getJobinfomap().remove(jobName);
                    System.out.println(jobName + " is out of date,remove from map!");
                }
            }
        }
    }

    /* 任务完成后，放入队列，经过expireTime时间后，从整个框架中移除 */
    public void putJob(String jobName, long expireTime) {
        queue.add(new ItemVo<String>(expireTime, jobName));
        System.out.println("Job[" + jobName + "已经放入了过期检查缓存，过期时长：" + expireTime);
    }

    static {
        Thread thread = new Thread(new FetchJob());
        thread.setDaemon(true);
        thread.start();
        System.out.println("开启任务过期检查守护线程................");
    }
}
