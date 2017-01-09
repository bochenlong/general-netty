package org.bochenlong.general.netty.msg.bean;

import org.bochenlong.general.netty.common.CommonHeaderKey;
import org.bochenlong.general.netty.msg.MsgType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bochenlong on 16-11-4.
 */
public class Header {
    private byte crcCode = 0x01;
    private int length;
    private long requestId;
    private short type; /** see {@link MsgType}*/
    private byte requestProp;
    private byte priority;
    private Map<String, Object> systemic = new HashMap<>();
    private Map<String, Object> customized = new HashMap<>();
    
    public byte getCrcCode() {
        return crcCode;
    }
    
    public void setCrcCode(byte crcCode) {
        this.crcCode = crcCode;
    }
    
    public int getLength() {
        return length;
    }
    
    public void setLength(int length) {
        this.length = length;
    }
    
    public long getRequestId() {
        return requestId;
    }
    
    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }
    
    public short getType() {
        return type;
    }
    
    public void setType(short type) {
        this.type = type;
    }
    
    public byte getRequestProp() {
        return requestProp;
    }
    
    public void setRequestProp(byte requestProp) {
        this.requestProp = requestProp;
    }
    
    public byte getPriority() {
        return priority;
    }
    
    public void setPriority(byte priority) {
        this.priority = priority;
    }
    
    public String getRemote() {
        return (String) systemic.get(CommonHeaderKey.FR_HOST);
    }
    
    public void setRemote(String host) {
        systemic.put(CommonHeaderKey.FR_HOST, host);
    }
    
    public Map<String, Object> getCustomized() {
        return customized;
    }
    
    public void setCustomized(Map<String, Object> customized) {
        this.customized = customized;
    }
    
    @Override
    public String toString() {
        return "Header{" +
                "crcCode=" + crcCode +
                ", length=" + length +
                ", requestId=" + requestId +
                ", type=" + type +
                ", requestProp=" + requestProp +
                ", priority=" + priority +
                ", systemic=" + systemic +
                ", customized=" + customized +
                '}';
    }
}
