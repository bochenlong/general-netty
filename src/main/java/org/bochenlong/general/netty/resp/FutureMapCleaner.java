package org.bochenlong.general.netty.resp;

import org.bochenlong.general.netty.NettyChannel;

import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by bochenlong on 16-11-14.
 */
public class FutureMapCleaner {
    public static void clean() {
        Runnable runnable = () ->
                NettyChannel.futures.entrySet()
                        .stream()
                        .filter(a -> a.getValue().getStatus() != NettyFuture.RUNNING)
                        .map(Map.Entry::getKey)
                        .collect(Collectors.toList())
                        .stream()
                        .forEach(NettyChannel.futures::remove);
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
        service.scheduleAtFixedRate(runnable, 120, 60, TimeUnit.SECONDS);
    }
}
