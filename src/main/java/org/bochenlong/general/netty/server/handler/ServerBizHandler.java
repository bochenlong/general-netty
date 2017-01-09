package org.bochenlong.general.netty.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.bochenlong.general.netty.msg.bean.NettyMsg;
import org.bochenlong.general.netty.NettyHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerBizHandler extends ChannelInboundHandlerAdapter {
    private static Logger logger = LoggerFactory.getLogger(ServerBizHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.info("read {}", ctx);
        NettyHelper.deliveryMsg(ctx, (NettyMsg) msg);

        // 释放
        ReferenceCountUtil.release(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        logger.debug("read complete {}", ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.warn("{} exception {}", ctx, cause);
        cause.printStackTrace();
        ctx.close();
    }

}