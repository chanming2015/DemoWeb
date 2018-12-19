package com.github.chanming.concurrent.executor;

/**
 * Description: Create Date:2018年12月19日
 *
 * @author XuMaoSen Version:1.0.0
 */
public class TaskResult<R> {
    private final TaskResultType resultType;
    private final R result;
    private final String message;

    /**
     * @author XuMaoSen
     */
    public TaskResult(TaskResultType resultType, R result, String message) {
        this.resultType = resultType;
        this.result = result;
        this.message = message;
    }

    /**
     * @author XuMaoSen
     */
    public TaskResult(TaskResultType resultType, R result) {
        this(resultType, result, TaskResultType.Success.toString());
    }

    public TaskResultType getResultType() {
        return resultType;
    }

    public R getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    /**
     * @author XuMaoSen
     */
    @Override
    public String toString() {
        return String.format("TaskResult [resultType=%s, returnValue=%s, message=%s]", resultType, result, message);
    }
}
