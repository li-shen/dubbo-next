package com.alibaba.dubbo.demo;

import com.alibaba.dubbo.common.serialize.support.SerializableClassRegistry;

/**
 * @author lishen
 */
public class SerializationOptimizer {

    public void init() {
        System.out.println(">>>>>>>>>>> optimizing serialization...");

        SerializableClassRegistry.registerClass(BidRequest.class);
        SerializableClassRegistry.registerClass(BidResponse.class);
        SerializableClassRegistry.registerClass(Device.class);
        SerializableClassRegistry.registerClass(Geo.class);
        SerializableClassRegistry.registerClass(Impression.class);
        SerializableClassRegistry.registerClass(SeatBid.class);
    }
}
