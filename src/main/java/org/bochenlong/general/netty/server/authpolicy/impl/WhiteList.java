package org.bochenlong.general.netty.server.authpolicy.impl;

import io.netty.channel.ChannelHandlerContext;
import org.bochenlong.general.netty.NettyHelper;
import org.bochenlong.general.netty.util.EmptyJudgeUtil;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created by bochenlong on 16-12-22.
 */
public class WhiteList {
    private static class Holder {
        private static WhiteList auth = new WhiteList();
    }
    
    public static WhiteList me() {
        return Holder.auth;
    }
    
    private Set<String> whiteList = Collections.synchronizedSet(new HashSet<>());
    private Predicate<ChannelHandlerContext> predicate = (ChannelHandlerContext ctx) -> {
        String ip = NettyHelper.getIp(ctx.channel().remoteAddress());
        if (!EmptyJudgeUtil.isNotNullAndEmpty(ip)) return false;
        if (whiteList.contains(ip)) return true;
        return false;
    };
    
    private Consumer<ChannelHandlerContext> consumer = (ChannelHandlerContext ctx) -> {
        whiteList.remove(NettyHelper.getIp(ctx.channel().remoteAddress()));
    };
    
    
    public Set<String> getWhiteList() {
        return me().whiteList;
    }
    
    public void setWhiteList(Set<String> whiteList) {
        me().whiteList = whiteList;
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
    
    public static void setConsumer(Consumer<ChannelHandlerContext> consumer) {
        me().consumer = consumer;
    }
}
