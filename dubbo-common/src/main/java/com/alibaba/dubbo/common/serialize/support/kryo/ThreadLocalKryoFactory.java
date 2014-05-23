package com.alibaba.dubbo.common.serialize.support.kryo;

import com.esotericsoftware.kryo.Kryo;

/**
 * @author lishen
 */
public class ThreadLocalKryoFactory extends KryoFactory {

    private final ThreadLocal<Kryo> holder  = new ThreadLocal<Kryo>() {
        @Override
        protected Kryo initialValue() {
            return createKryo();
        }
    };

    public Kryo getKryo() {
        return holder.get();
    }
}
