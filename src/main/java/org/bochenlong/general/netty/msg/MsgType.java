package org.bochenlong.general.netty.msg;

/**
 * Created by bochenlong on 16-12-22.
 * <p>
 * MsgType 0-99 为系统保留 // 100 - Short.MAX_VALUE 为业务可取类型
 */
public enum MsgType {
    HEART((short) 0),
    AUTH((short) 1),
    BUSINESS((short) 100);
    
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
