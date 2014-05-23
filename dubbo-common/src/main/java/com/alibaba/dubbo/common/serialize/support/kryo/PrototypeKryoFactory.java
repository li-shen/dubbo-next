package com.alibaba.dubbo.common.serialize.support.kryo;

import com.esotericsoftware.kryo.Kryo;

/**
 * @author lishen
 */
public class PrototypeKryoFactory extends KryoFactory {

    public Kryo getKryo() {
        return createKryo();
    }
}
