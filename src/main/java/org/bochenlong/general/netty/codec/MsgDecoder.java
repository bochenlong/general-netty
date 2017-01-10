package org.bochenlong.general.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.util.ReferenceCountUtil;
import org.bochenlong.general.netty.msg.bean.NettyMsg;

/**
 * Created by bochenlong on 16-11-4.
 */
public class MsgDecoder extends LengthFieldBasedFrameDecoder {
    
    // maxFrameLength 最大长度
    // lengthFieldOffset 偏移量
    // lengthFieldLength 表示长度的位置
    // lengthAdjustment 弥补长度
    public MsgDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength, int lengthAdjustment) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, 0);
    }
    
    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        ByteBuf frame = (ByteBuf) super.decode(ctx, in);
        
        if (frame == null) {
            return null;
        }
        
        int length = frame.readInt();
        int remainLength = frame.readableBytes();
        byte[] bytes = new byte[remainLength];
        frame.readBytes(bytes);
        NettyMsg msg = MsgCodec.toObject(bytes, NettyMsg.class);
        msg.getHeader().setLength(length);
        
        frame.release();
        return msg;
    }
}
