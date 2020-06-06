package org.zongf.test.tools.ognl;

import ognl.InappropriateExpressionException;
import ognl.OgnlException;
import org.junit.Assert;
import org.junit.Test;
import org.zongf.tools.util.OgnlUtil;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * @author zongf
 * @date 2020-06-03
 */
public class OgnlUtilTest {

    /** 常量(字面量)测试 */
    @Test
    public void testLiteral(){
        // 字符串(双引号)
        Assert.assertTrue(String.class.equals(OgnlUtil.getValue(null, "\"hello, ognl\"").getClass()));
        // 字符串(单引号)
        Assert.assertTrue(String.class.equals(OgnlUtil.getValue(null, "'hello, ognl'").getClass()));
        // 字符(单引号)
        Assert.assertTrue(Character.class.equals(OgnlUtil.getValue(null, "'h'").getClass()));
        // 整数, 默认Integer
        Assert.assertTrue(Integer.class.equals(OgnlUtil.getValue(null, "123").getClass()));
        // 浮点数, 默认Double
        Assert.assertTrue(Double.class.equals(OgnlUtil.getValue(null, "123.0").getClass()));
        // 浮点数, Float
        Assert.assertTrue(Float.class.equals(OgnlUtil.getValue(null, "123.0f").getClass()));
        // 浮点数, 指定BigDecimal
        Assert.assertTrue(BigDecimal.class.equals(OgnlUtil.getValue(null, "123.0b").getClass()));
        // 浮点数, 指定Double
        Assert.assertTrue(Double.class.equals(OgnlUtil.getValue(null, "123.0D").getClass()));
        // 整数, 指定BigInteger
        Assert.assertTrue(BigInteger.class.equals(OgnlUtil.getValue(null, "123h").getClass()));
        // 布尔型
        Assert.assertTrue(Boolean.class.equals(OgnlUtil.getValue(null, "true").getClass()));
        // 空值类型
        Assert.assertNull(OgnlUtil.getValue(null, "null"));
    }

    /** 变量测试 */
    @Test
    public void testVariable(){
        List<Integer> scores = Arrays.asList(99, 80, 70, 50);
        Map<String, Object> root = new HashMap<>();
        root.put("scores", scores);

        // 自定义变量, 用逗号分割多个表达式
        System.out.println("平均分: " + OgnlUtil.getValue(root, "#scoreAvg = (scores[0] + scores[1] + scores[2] + scores[3])/scores.size , #scoreAvg > 60 ? '及格' : '不及格'"));
        // 不适用变量
        System.out.println("平均分: " + OgnlUtil.getValue(root, "(scores[0] + scores[1] + scores[2] + scores[3])/scores.size > 60 ? '及格' : '不及格'"));

    }

    /** this变量测试 */
    @Test
    public void testThis(){
        List<Integer> scores = Arrays.asList(99, 80, 70, 50);

        // 自定义变量
        System.out.println("size:" + OgnlUtil.getValue(scores, "#this.size()"));
        System.out.println(OgnlUtil.getValue(scores, "#first = #this[0] , #first > 60 ? '及格' : '不及格'"));
    }

    /** 集合内置属性 */
    @Test
    public void testInsideVariable() {

        Map<String, String> map = new HashMap<>();
        map.put("A", "aaa"); map.put("B", "bbb"); map.put("C", "ccc");

        Set<String> set = new HashSet<>();
        set.add("java"); set.add("linux"); set.add("js");

        Map<String, Object> root = new HashMap<>();
        root.put("map", map);
        root.put("list", Arrays.asList("java", "linux", "js"));
        root.put("set", set);

        System.out.println("map.isEmpty:" + OgnlUtil.getValue(root, "map.isEmpty"));
        System.out.println("map.size:" + OgnlUtil.getValue(root, "map.size"));
        System.out.println("map.keys:" + OgnlUtil.getValue(root, "map.keys"));
        System.out.println("map.values:" + OgnlUtil.getValue(root, "map.values"));

        System.out.println("set.isEmpty:" + OgnlUtil.getValue(root, "set.isEmpty"));
        System.out.println("set.size:" + OgnlUtil.getValue(root, "set.size"));
        System.out.println("set.iterator:" + OgnlUtil.getValue(root, "set.iterator").getClass());
        System.out.println("set.hasNext:" + OgnlUtil.getValue(root, "set.iterator.hasNext"));

        // iterator 遍历
        Iterator iterator = (Iterator) OgnlUtil.getValue(root, "set.iterator");
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
    }


    /** 静态方法,静态常量 测试 */
    @Test
    public void testStatic(){
        Map<String, Object> root = new HashMap<>();
        root.put("time", new Date());

        /** 静态变量 与 静态方法 */
        System.out.println("静态变量:" + OgnlUtil.getValue(root, "@org.zongf.ognl.DateUtil@DATE_FORMAT"));

        /** 无参静态方法 */
        System.out.println("无参静态方法:" + OgnlUtil.getValue(root, "@org.zongf.ognl.DateUtil@now()"));

        /** 静态方法 */
        System.out.println("静态方法+ognl常量:" + OgnlUtil.getValue(root, "@org.zongf.ognl.DateUtil@format(time,'yyyy-MM-dd HH:mm:ss')"));

        /** 静态变量 与 静态方法 */
        System.out.println("静态方法+静态变量:" + OgnlUtil.getValue(root, "@org.zongf.ognl.DateUtil@format(time,@org.zongf.ognl.DateUtil@DATE_FORMAT)"));

        // 当存在方法重载时,会随机选择一个
        System.out.println("方法重载, 随机:" + OgnlUtil.getValue(root, "@org.zongf.ognl.DateUtil@sayHello(154d)"));
    }


    /** 测试类集合 */
    @Test
    public void testLikeCollection() {

        String[] array = new String[]{"java", "linux", "js"};
        List<String> list =  Arrays.asList("java", "linux", "js");
        Set<String> set = new HashSet<>(Arrays.asList("java", "linux", "js"));
        Map<String, String> map = new HashMap<>();
        map.put("A", "aaa"); map.put("B", "bbb"); map.put("C", "ccc");

        Map<String, Object> root = new HashMap<>();
        root.put("array", array);
        root.put("set", set);
        root.put("list", list);
        root.put("map", map);

        // 数组 可使用下标进行获取值
        System.out.println("array.length:" + OgnlUtil.getIntValue(root, "array.length"));
        System.out.println("array[0]:" + OgnlUtil.getStringValue(root, "array[0]"));

        // list 可使用下标进行获取值
        System.out.println("list.size:" + OgnlUtil.getIntValue(root, "list.size"));
        System.out.println("list.get(1):" + OgnlUtil.getStringValue(root, "list[1]"));

        // set 只能通过iterator 获取元素
        System.out.println("set.size:" + OgnlUtil.getIntValue(root, "set.size"));
        System.out.println("set.first:" + OgnlUtil.getStringValue(root, "set.iterator.next"));

        // map 可通过key获取对应的元素
        System.out.println("map.size" + OgnlUtil.getIntValue(root, "map.size"));
        System.out.println("map['A']:" + OgnlUtil.getIntValue(root, "map['A']"));

    }




    /** 集合包含 */
    @Test
    public void testInAndNotIn(){
        Map<String, Object> root = new HashMap<>();
        List<String> books = Arrays.asList("java", "linux", "js", "css");
        root.put("books", books);
        root.put("name", "js");

        // 测试in/not in 表达式
        System.out.println(OgnlUtil.getValue(root, "'book' in {'book', 'linux'}"));
        System.out.println(OgnlUtil.getValue(root, "'js' not in books"));
        System.out.println(OgnlUtil.getValue(root, "books.contains('linux')"));
        System.out.println(OgnlUtil.getValue(root, "!books.contains('linux')"));
    }

    /** 集合筛选 */
    @Test
    public void testCollection() {
        Map<String, Object> root = new HashMap<>();
        root.put("list", Arrays.asList("java", false, "linux", 20, "js", 20.3f));

        // 集合筛选
        System.out.println("筛选全部字符串:" + OgnlUtil.getValue(root, "list.{? #this instanceof String}"));
        System.out.println("筛选第一个字符串:" + OgnlUtil.getValue(root, "list.{^ #this instanceof String}"));
        System.out.println("筛选最后一个字符串:" + OgnlUtil.getValue(root, "list.{$ #this instanceof String}"));

        // 集合判断
        System.out.println(OgnlUtil.getValue(root, "'linux' in {'book', 'linux'}"));
        System.out.println(OgnlUtil.getValue(root, "books.contains('linux')"));
        System.out.println(OgnlUtil.getValue(root, "'linux' not in books"));
    }

    /** 测试修改值*/
    @Test
    public void setValue() {
        Map<String, Object> root = new HashMap<>();
        root.put("idx", 1001);

        // 测试set
        System.out.println("idx before:" + OgnlUtil.getValue(root, "idx"));
        OgnlUtil.setValue(root, "idx", "200");
        System.out.println("idx after:" + OgnlUtil.getValue(root, "idx"));

        // 不是set表达式, 抛出异常: ognl.InappropriateExpressionException
        OgnlUtil.setValue(root, "idx + 1", "3001");
    }


}
