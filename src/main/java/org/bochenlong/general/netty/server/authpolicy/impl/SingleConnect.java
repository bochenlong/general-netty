package org.bochenlong.general.netty.server.authpolicy.impl;

import io.netty.channel.ChannelHandlerContext;
import org.bochenlong.general.netty.NettyHelper;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created by bochenlong on 16-12-22.
 */
public class SingleConnect {
    private static class Holder {
        private static SingleConnect auth = new SingleConnect();
    }
    
    public static SingleConnect me() {
        return Holder.auth;
    }
    
    private Set<String> connSet = Collections.synchronizedSet(new HashSet<>());
    private Predicate<ChannelHandlerContext> predicate = (ChannelHandlerContext ctx) -> {
        if (connSet.contains(getPredicate())) return false;
        connSet.add(NettyHelper.getIp(ctx.channel().remoteAddress()));
        return true;
    };
    
    private Consumer<ChannelHandlerContext> consumer = (ChannelHandlerContext ctx) -> {
        connSet.remove(NettyHelper.getIp(ctx.channel().remoteAddress()));
    };
    
    public Set<String> getConnSet() {
        return me().connSet;
    }
    
    public void setConnSet(Set<String> connSet) {
        me().connSet = connSet;
    }
    
    public Predicate<ChannelHandlerContext> getPredicate() {
        return me().predicate;
    }
    
    public void setPredicate(Predicate<ChannelHandlerContext> predicate) {
        me().predicate = predicate;
    }
    
    public Consumer<ChannelHandlerContext> getConsumer() {
        return consumer;
    }
    
    public  void setConsumer(Consumer<ChannelHandlerContext> consumer) {
        me().consumer = consumer;
    }
}
