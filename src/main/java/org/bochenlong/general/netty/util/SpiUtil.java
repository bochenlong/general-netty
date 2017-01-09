package org.bochenlong.general.netty.util;

import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by bochenlong on 17-1-9.
 */
public class SpiUtil {
    private static ConcurrentHashMap<Class, ServiceLoader> loaderMap =
            new ConcurrentHashMap<>();
    
    private static <T> ServiceLoader<T> loadService(Class<T> clazz) {
        return loaderMap.computeIfAbsent(clazz, key -> ServiceLoader.load(key));
    }
    
    public static <T> void reloadService(Class<T> clazz) {
        loaderMap.getOrDefault(clazz, loadService(clazz)).reload();
    }
    
    public static <T> T getServiceImpl(Class<T> clazz) {
        for (T service : loadService(clazz)) {
            return service;
        }
        return null;
    }
}
