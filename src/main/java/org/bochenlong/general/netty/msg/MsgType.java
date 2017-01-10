package org.bochenlong.general.netty.msg;

/**
 * Created by bochenlong on 16-12-22.
 * <p>
 * 系统消息类型，取值范围为0 - 99
 */
public enum MsgType {
    UNKNOWN((short) 0),
    HEART((short) 1),
    AUTH((short) 2),;
    
    private short type;
    
    MsgType(short type) {
        this.type = type;
    }
    
    public short getType() {
        return type;
    }
    
    public static boolean isSystemType(MsgType type) {
        return type.getType() >= 0 && type.getType() < 100;
    }
}
