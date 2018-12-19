package com.github.chanming.concurrent.executor;

import java.util.Random;

/**
 * Description: Create Date:2018年12月19日
 *
 * @author XuMaoSen Version:1.0.0
 */
public class TestTask implements TaskProcesser<Integer, Integer> {
    /**
     * @author XuMaoSen
     */
    @Override
    public TaskResult<Integer> taskExecute(Integer data) {
        int flag = new Random().nextInt(500);
        try {
            Thread.sleep(flag);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (flag <= 300) {// 正常处理的情况
            Integer returnValue = data.intValue() + flag;
            return new TaskResult<Integer>(TaskResultType.Success, returnValue);
        }
        else if ((flag > 301) && (flag <= 400)) {// 处理失败的情况
            return new TaskResult<Integer>(TaskResultType.Failure, -1, "Failure");
        }
        else {// 发生异常的情况
            throw new RuntimeException("异常发生了！！");
        }
    }
}
