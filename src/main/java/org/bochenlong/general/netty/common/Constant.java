package org.bochenlong.general.netty.common;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by bochenlong on 16-11-15.
 */
public final class Constant {
    private static Properties properties = init();
    
    private static Properties init() {
        try {
            new Properties().load(Constant.class.getClassLoader().getResourceAsStream("netty.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new NoSuchFieldError("netty.properties not exist");
    }
    
    
    public static final byte YES = 1;
    public static final byte NO = 0;
    
    public static final long dataCenterId = Long.valueOf(properties.getProperty("IdWorker.dataCenterId"));
    public static final long workerId = Long.valueOf(properties.getProperty("IdWorker.workerId"));
}
