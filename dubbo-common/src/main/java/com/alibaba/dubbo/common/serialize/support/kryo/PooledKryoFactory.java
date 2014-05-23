package com.alibaba.dubbo.common.serialize.support.kryo;

import com.esotericsoftware.kryo.Kryo;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author lishen
 */
public class PooledKryoFactory extends KryoFactory {

    private final Queue<Kryo> pool = new ConcurrentLinkedQueue<Kryo>();

    @Override
    public void returnKryo(Kryo kryo) {
        pool.offer(kryo);
    }

    @Override
    public void close() {
        pool.clear();
    }

    public Kryo getKryo() {
        Kryo kryo = pool.poll();
        if (kryo == null) {
            kryo = createKryo();
        }
        return kryo;
    }
}
