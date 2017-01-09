package org.bochenlong.general.netty.server.auth.impl;

import io.netty.channel.ChannelHandlerContext;
import org.bochenlong.general.netty.NettyHelper;
import org.bochenlong.general.netty.server.auth.IAuth;
import org.bochenlong.general.netty.util.EmptyJudgeUtil;

import java.util.HashSet;

/**
 * Created by bochenlong on 16-12-22.
 */
public class WhiteList implements IAuth {
    private static HashSet whiteList = new HashSet() {{
        add("10.1.1.9");
    }};
    
    @Override
    public boolean auth(ChannelHandlerContext ctx) {
        String ip = NettyHelper.getIp(ctx.channel().remoteAddress());
        if (!EmptyJudgeUtil.isNotNullAndEmpty(ip)) return false;
        if (whiteList.contains(ip)) return true;
        return false;
    }
    
    @Override
    public boolean remove(ChannelHandlerContext ctx) {
        return true;
    }
}
