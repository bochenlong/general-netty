package org.bochenlong.general.netty.resp;

import java.util.concurrent.*;

/**
 * Created by bochenlong on 16-11-14.
 */
public class NettyFuture<V> implements Future<V> {
    private CountDownLatch countDownLatch = new CountDownLatch(1);

    private volatile int status;

    public static final int RUNNING = 1;
    private static final int CANCELLED = 2;
    private static final int COMPLETED = 10;

    private volatile V result;

    public NettyFuture() {
        status = RUNNING;
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return true;
    }

    @Override
    public boolean isCancelled() {
        return status == CANCELLED;
    }

    @Override
    public boolean isDone() {
        return status == COMPLETED;
    }

    @Override
    public V get() throws InterruptedException, ExecutionException {
        countDownLatch.await();
        if (this.status == COMPLETED) {
            return this.result;
        } else if (this.status == CANCELLED) {
            throw new InterruptedException("P2pFuture have be interrupted");
        }
        return this.result;
    }

    @Override
    public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        if (countDownLatch.await(timeout, unit)) {
            if (this.status == COMPLETED) {
                return this.result;
            } else if (this.status == CANCELLED) {
                throw new InterruptedException("P2pFuture have be interrupted");
            }
        }
        throw new TimeoutException("P2pFuture get the result timeout");
    }

    public void set(V v) {
        this.result = v;
        changeStatus(COMPLETED);

    }

    public void cancel() {
        changeStatus(CANCELLED);
    }

    public int getStatus() {
        return this.status;
    }

    private synchronized void changeStatus(int status) {
        if (this.status != RUNNING) {
            return;
        }
        this.status = status;
        this.countDownLatch.countDown();
    }
}

