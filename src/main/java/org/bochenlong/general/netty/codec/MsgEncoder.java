package org.bochenlong.general.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import org.bochenlong.general.netty.msg.bean.NettyMsg;

import java.util.List;

/**
 * Created by bochenlong on 16-11-4.
 */
public class MsgEncoder extends MessageToMessageEncoder<NettyMsg> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, NettyMsg msg, List<Object> list) throws Exception {
        if (msg == null || msg.getHeader() == null) {
            throw new Exception("The encode message is null");
        }
    
        ByteBuf byteBuf = MsgCodecUtil.encode(msg);
    
        list.add(byteBuf);
    }
}
