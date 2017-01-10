package org.bochenlong.general.netty.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.bochenlong.general.netty.NettyHelper;
import org.bochenlong.general.netty.msg.MsgType;
import org.bochenlong.general.netty.msg.bean.NettyMsg;
import org.bochenlong.general.netty.server.auth.AuthHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by bochenlong on 16-11-4.
 */
public class ServerAuthInHandler extends ChannelInboundHandlerAdapter {
    private static Logger logger = LoggerFactory.getLogger(ServerAuthInHandler.class);
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.debug("read {}", ctx);
        if (AuthHelper.auth(ctx)) {
            logger.info("Server auth success {}", NettyHelper.getIp(ctx.channel().remoteAddress()));
            ctx.fireChannelRead(msg);
        } else {
            logger.info("Server auth fail {}", NettyHelper.getIp(ctx.channel().remoteAddress()));
            ReferenceCountUtil.release(msg);
            ctx.close();
        }
    }
    
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        logger.debug("read complete {}", ctx);
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("{} exception {}", ctx, cause.getMessage());
        cause.printStackTrace();
    }
    
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.debug("inactive {}", ctx);
        AuthHelper.remove(ctx);
        logger.info("Server auth remove {}", ctx);
        NettyHelper.removeChannel(ctx);
        logger.info("Server channel remove {}", ctx);
    }
}
