package org.zongf.tools.util;

import ognl.Ognl;
import ognl.OgnlException;
import org.zongf.tools.ognl.OgnlMemberAccess;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Ognl 工具类
 *
 * @author zongf
 * @return Object
 */
public final class OgnlUtil {

    private static final OgnlMemberAccess MEMBER_ACCESS = new OgnlMemberAccess();

    // 缓存表达式解析
    private static final Map<String, Object> expressionCache = new ConcurrentHashMap<>();

    private OgnlUtil() {
    }

    /** 获取String类型的值
     * @see #getValue(Object, String)
     * @throws java.lang.ClassCastException
     */
    public static String getStringValue(Object root, String expression) {
        return (String) getValue(root, expression);
    }

    /** 获取Integer类型的值
     * @see #getValue(Object, String)
     * @throws java.lang.ClassCastException
     */
    public static Integer getIntValue(Object root, String expression) {
        return (Integer) getValue(root, expression);
    }

    /** 获取Float类型的值
     * @see #getValue(Object, String)
     * @throws java.lang.ClassCastException
     */
    public static Float getFloatValue(Object root, String expression) {
        return (Float) getValue(root, expression);
    }

    /** 获取Double类型的值
     * @see #getValue(Object, String)
     * @throws java.lang.ClassCastException
     */
    public static Double getDoubleValue(Object root, String expression) {
        return (Double) getValue(root, expression);
    }

    /** 获取Boolean类型的值
     * @see #getValue(Object, String)
     * @throws java.lang.ClassCastException
     */
    public static Boolean getBooleanValue(Object root, String expression) {
        return (Boolean) getValue(root, expression);
    }

    /** 获取BigInteger类型的值
     * @see #getValue(Object, String)
     * @throws java.lang.ClassCastException
     */
    public static BigInteger getBigIntegerValue(Object root, String expression) {
        return (BigInteger) getValue(root, expression);
    }

    /** 获取BigDecimal类型的值
     * @see #getValue(Object, String)
     * @throws java.lang.ClassCastException
     */
    public static BigDecimal getBigDecimalValue(Object root, String expression) {
        return (BigDecimal) getValue(root, expression);
    }

    /**
     * 获取表达式值, 开启表达式缓存
     *
     * @param expression 表达式
     * @param root       根节点
     * @return Object
     * @author zongf
     * @date 2020-06-03
     */
    public static Object getValue(Object root, String expression) {
        return getValue(root, expression, true);
    }

    /**
     * 获取表达式值
     * @param expression      表达式
     * @param root            根节点
     * @param cacheExpression 是否缓存表达式
     * @return Object
     * @author zongf
     * @date 2020-06-03
     */
    public static Object getValue(Object root, String expression, boolean cacheExpression) {
        try {
            // 基于root创建上下文, 不允许修改context
            Map context = Ognl.createDefaultContext(root, MEMBER_ACCESS);

            // 解析表达式
            Object exp = parseExpression(expression, cacheExpression);

            // 返回
            return Ognl.getValue(exp, context, root);
        } catch (OgnlException e) {
            throw new RuntimeException("ognl 表达式解析异常, 表达式:" + expression + "", e);
        }
    }

    /**
     * 解析表达式
     *
     * @param expression      表达式
     * @param cacheExpression
     * @return Object
     * @author zongf
     * @date 2020-06-03
     */
    private static Object parseExpression(String expression, boolean cacheExpression) throws OgnlException {
        Object parsedExpression = null;
        if (cacheExpression) { // 开启表达式缓存
            parsedExpression = expressionCache.get(expression);
            if (parsedExpression == null) {
                parsedExpression = Ognl.parseExpression(expression);
                expressionCache.put(expression, parsedExpression);
            }
        } else {
            parsedExpression = Ognl.parseExpression(expression);
        }
        return parsedExpression;
    }

    /** 为表达式赋值 <br/>
     *  当表达式不能表示赋值时, 会抛出异常
     * @see #setValue(Object, String, String, Boolean)
     * @throws ognl.InappropriateExpressionException
     */
    public static void setValue(Object root, String expression, String value) {
        setValue(root, expression, value, true);
    }

    /** 表达式赋值 <br/>
     *  当表达式不能表示赋值时, 会抛出异常
     * @param root 起始对象
     * @param expression 表达式
     * @param value 值
     * @param cacheExpression 缓存表达式
     * @throws ognl.InappropriateExpressionException
     * @author zongf
     * @date 2020-06-03
     */
    public static void setValue(Object root, String expression, String value, Boolean cacheExpression) {

        try {
            // 基于root创建上下文, 不允许修改context
            Map context = Ognl.createDefaultContext(root, MEMBER_ACCESS);

            // 解析表达式
            Object exp = parseExpression(expression, cacheExpression);

            // 赋值
            Ognl.setValue(exp, context, root, value);
        } catch (OgnlException e) {
            throw new RuntimeException("ognl 表达式解析异常, 表达式:" + expression + "", e);
        }
    }

}
