package org.zongf.tools.common.utils;

import org.zongf.tools.common.enums.CharsetEnum;
import org.zongf.tools.common.exception.URLCoderException;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/** URL编码解码工具类. URL编码可解决web传参时特殊符号问题.
 * @author zongf
 * @date 2019-12-04
 */
public class URLCoderUtil {

    /** 对字符串做url编码， 默认字符编码格式为UTF-8
     * @param str 任意字符串
     * @return String 编码后的字符串。如果字符串为空或编码过程发生异常，则返回null。
     * @author zongf
     * @date 2019-12-04
     */
    public static String encode(String str){
        return encode(str, CharsetEnum.UTF8);
    }

    /** 对字符串做url编码
     * @param str  任意字符串
     * @param charset 指定使用的字符串集。无特殊情况，建议使用UTF-8即可
     * @return String 编码后的字符串。如果字符串为空或编码过程发生异常，则返回null。
     * @author zongf
     * @date 2019-12-04
     */
    public static String encode(String str, CharsetEnum charset){

        // 如果字符串为null, 则返回null
        if(TestUtil.isNull(str)) return str;

        try {
            return URLEncoder.encode(str, charset.toString());
        } catch (UnsupportedEncodingException e) {
            throw new URLCoderException(e, "url 编码异常, 字符串:%s", str);
        }
    }

    /** 对字符串做url解码， 默认使用UTF-8编码
     * @param str 加密的字符串
     * @return String 正常返回解码后的字符串。若传入字符串为null，或解码过程出现异常，则返回null.
     * @author zongf
     * @date 2019-12-04
     */
    public static String decode(String str){
        return decode(str, CharsetEnum.UTF8);
    }

    /** 对字符串做url解码
     * @param str  加密的字符串
     * @param charset  指定解析使用的字符集
     * @return String  String 正常返回解码后的字符串。若传入字符串为null，或解码过程出现异常，则返回null.
     * @author zongf
     * @date 2019-12-04
     */
    public static String decode(String str, CharsetEnum charset){

        // 如果字符串为null, 则返回null
        if(TestUtil.isNull(str)) return str;

        try {
            return URLDecoder.decode(str, charset.toString());
        } catch (UnsupportedEncodingException e) {
            throw new URLCoderException(e, "url 解码异常, 字符串:%s", str);
        }
    }

}
