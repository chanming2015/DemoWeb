package com.github.chanming.concurrent.executor;

/**
 * Description: 任务处理接口，业务调用者需要实现 Create Date:2018年12月19日
 *
 * @author XuMaoSen Version:1.0.0
 */
public interface TaskProcesser<R, D> {
    TaskResult<R> taskExecute(D data);
}
