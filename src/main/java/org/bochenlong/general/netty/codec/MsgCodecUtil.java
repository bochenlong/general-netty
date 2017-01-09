package org.bochenlong.general.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.bochenlong.general.netty.msg.bean.NettyMsg;

/**
 * Created by bochenlong on 16-11-4.
 */
public class MsgCodecUtil {
    
    public static ByteBuf encode(NettyMsg msg) {
        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeInt(1);
        byteBuf.writeBytes(MsgCodec.toBytes(msg));
        byteBuf.setInt(0, byteBuf.readableBytes());
        return byteBuf;
    }
    
    public static NettyMsg decode(ByteBuf in) {
        int length = in.readInt();
        int msgLength = in.readableBytes();
        byte[] bytes= new byte[msgLength];
        in.readBytes(bytes);
        NettyMsg msg = MsgCodec.toObject(bytes, NettyMsg.class);
        msg.getHeader().setLength(length);
        return msg;
    }

}
