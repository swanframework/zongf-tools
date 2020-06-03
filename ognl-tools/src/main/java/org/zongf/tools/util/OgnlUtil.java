package org.zongf.tools.util;

import ognl.Ognl;
import ognl.OgnlException;
import org.zongf.tools.ognl.OgnlMemberAccess;

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
     * @see #getValue(String, Object)
     * @throws java.lang.ClassCastException
     */
    public static String getStringValue(String expression, Object root) {
        return (String) getValue(expression, root);
    }

    /** 获取Integer类型的值
     * @see #getValue(String, Object)
     * @throws java.lang.ClassCastException
     */
    public static Integer getIntValue(String expression, Object root) {
        return (Integer) getValue(expression, root);
    }

    /** 获取Float类型的值
     * @see #getValue(String, Object)
     * @throws java.lang.ClassCastException
     */
    public static Float getFloatValue(String expression, Object root) {
        return (Float) getValue(expression, root);
    }

    /** 获取Double类型的值
     * @see #getValue(String, Object)
     * @throws java.lang.ClassCastException
     */
    public static Double getDoubleValue(String expression, Object root) {
        return (Double) getValue(expression, root);
    }

    /** 获取Boolean类型的值
     * @see #getValue(String, Object)
     * @throws java.lang.ClassCastException
     */
    public static Boolean getBooleanValue(String expression, Object root) {
        return (Boolean) getValue(expression, root);
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
    public static Object getValue(String expression, Object root) {
        return getValue(expression, root, true);
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
    public static Object getValue(String expression, Object root, boolean cacheExpression) {
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

}
