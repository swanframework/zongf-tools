package org.zongf.tools.common.utils;

import org.zongf.tools.common.exception.SerializableException;

import java.io.*;

/** 序列化工具
 * @since 1.0
 * @author zongf
 * @created 2019-07-24
 */
public class SerializableUtil {

    /** 序列化对象为字节数组
     * @param object 目标对象
     * @return 对象的字节数组. 对象为空时返回null
     * @since 1.0
     * @author zongf
     * @created 2019-07-24
     */
    public static byte[] serialize(Serializable object) {

        if(object == null) return null;

        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;

        try {

            // 序列化
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(object);

            return bos.toByteArray();

        } catch (Exception ex) {
            throw new SerializableException(ex);
        }finally {
            CloseUtil.close(bos, oos);
        }
    }

    /** 反序列化字节数组为java对象
     * @param bytes 字节数组
     * @param t 要转换的对象类型
     * @return 目标类型对象或null.
     * @since 1.0
     * @author zongf
     * @created 2019-07-24
     */
    public static <T> T unSerialize(byte[] bytes, Class<? extends Serializable> t) {

        if(t == null) return null;

        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;

        try {

            // 反序列化
            bis = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bis);

            return  (T) ois.readObject();

        } catch (Exception ex) {
            throw new SerializableException(ex);
        }finally {
            CloseUtil.close(ois, bis);
        }
    }

}
