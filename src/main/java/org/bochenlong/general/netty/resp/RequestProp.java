package org.bochenlong.general.netty.resp;

/**
 * Created by bochenlong on 17-1-9.
 */
public enum RequestProp {
    ASYNC_REQUEST((byte) 1),
    SYNC_REQUEST((byte) 2),
    RESPONSE((byte) 3);
    
    private byte prop;
    
    private RequestProp(byte prop) {
        this.prop = prop;
    }
    
    public byte getProp() {
        return this.prop;
    }
}
