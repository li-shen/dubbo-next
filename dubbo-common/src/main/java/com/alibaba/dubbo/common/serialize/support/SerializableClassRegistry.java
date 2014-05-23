package com.alibaba.dubbo.common.serialize.support;

import java.util.LinkedList;
import java.util.List;

/**
 * @author lishen
 */
public abstract class SerializableClassRegistry {

    private static final List<Class> registrations = new LinkedList<Class>();

    /**
     * only supposed to be called at startup time
     */
    public static void registerClass(Class clazz) {
        registrations.add(clazz);
    }

    public static List<Class> getRegisteredClasses() {
        return registrations;
    }
}
