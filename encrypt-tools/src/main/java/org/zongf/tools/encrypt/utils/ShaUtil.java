package org.zongf.tools.encrypt.utils;

import org.zongf.tools.common.enums.CharsetEnum;
import org.zongf.tools.encrypt.exception.EncryptException;
import sun.misc.BASE64Encoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/** SHA1 加密工具类
 * @author zongf
 * @date 2020-05-18
 */
public class ShaUtil {

    /** 使用HmacSHA1算法对字符串加密
     * @param str 原字符串
     * @param key 加密的key
     * @param charset 字符编码
     * @return String 返回SHA1加密后的Base64编码的字符串
     * @author zongf
     * @date 2020-05-18
     */
    public static String hmacEncrypt(String str, String key, String charset) {
        String algorithm = "HmacSHA1";
        try {
            //根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
            SecretKeySpec signinKey = new SecretKeySpec(key.getBytes(charset), algorithm);

            //生成一个指定 Mac 算法 的 Mac 对象
            Mac mac = Mac.getInstance(algorithm);

            //用给定密钥初始化 Mac 对象
            mac.init(signinKey);

            //完成 Mac 操作
            byte[] rawHmac = mac.doFinal(str.getBytes());

            // 64位编码
            return new BASE64Encoder().encode(rawHmac);
        } catch (Exception e) {
            throw new EncryptException(e, "SHA1加密异常");
        }
    }

    /** 使用SHA1算法对字符串加密
     * @param str 原字符串
     * @param key 加密的key
     * @return String 返回SHA1加密后的Base64编码的字符串
     * @author zongf
     * @date 2020-05-18
     */
    public static String hmacEncrypt(String str, String key) {
        return hmacEncrypt(str, key, CharsetEnum.UTF8.toString());
    }

}
