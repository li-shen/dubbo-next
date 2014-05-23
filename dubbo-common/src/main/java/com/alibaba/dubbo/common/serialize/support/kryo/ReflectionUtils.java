package com.alibaba.dubbo.common.serialize.support.kryo;

/**
 * @author lishen
 */
public abstract class ReflectionUtils {

    public static boolean checkZeroArgConstructor(Class clazz) {
        try {
            clazz.getDeclaredConstructor();
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }
}
