package com.alibaba.dubbo.common.serialize.support.kryo;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.serialize.ObjectInput;
import com.alibaba.dubbo.common.serialize.ObjectOutput;
import com.alibaba.dubbo.common.serialize.OptimizedSerialization;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * TODO for now kryo serialization doesn't deny classes that don't implement the serializable interface
 *
 * @author lishen
 */
public class KryoSerialization implements OptimizedSerialization {

    public byte getContentTypeId() {
        return 8;
    }

    public String getContentType() {
        return "x-application/kryo";
    }

    public ObjectOutput serialize(URL url, OutputStream out) throws IOException {
        return new KryoObjectOutput(out);
    }

    public ObjectInput deserialize(URL url, InputStream is) throws IOException {
        return new KryoObjectInput(is);
    }
}