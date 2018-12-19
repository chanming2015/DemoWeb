package com.github.chanming.concurrent.executor;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Description: Create Date:2018年12月20日
 *
 * @author XuMaoSen Version:1.0.0
 */
public class ItemVo<T> implements Delayed {
    private final long activeTime;// 到期时间，单位毫秒
    private final T data;

    // activeTime是个过期时长
    public ItemVo(long activeTime, T data) {
        this.activeTime = TimeUnit.NANOSECONDS.convert(activeTime, TimeUnit.MILLISECONDS) + System.nanoTime();// 将传入的时长转换为超时的时刻
        this.data = data;
    }

    public long getActiveTime() {
        return activeTime;
    }

    public T getData() {
        return data;
    }

    // 按照剩余时间排序
    @Override
    public int compareTo(Delayed o) {
        long d = getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS);
        return (d == 0) ? 0 : ((d > 0) ? 1 : -1);
    }

    // 返回元素的剩余时间
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.activeTime - System.nanoTime(), TimeUnit.NANOSECONDS);
    }
}
