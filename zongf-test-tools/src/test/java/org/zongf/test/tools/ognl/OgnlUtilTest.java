package org.zongf.test.tools.ognl;

import ognl.OgnlException;
import org.junit.Test;
import org.zongf.tools.util.OgnlUtil;

import java.util.*;

/**
 * @author zongf
 * @date 2020-06-03
 */
public class OgnlUtilTest {

    @Test
    public void test1() throws OgnlException {

        Employee.Department department = new Employee.Department();
        department.setName("研发部");
        department.setManager("小张");

        Employee emp = new Employee();
        emp.setId(1001);
        emp.setName("zhangsan");
        emp.setEmpNo("EMP_1001");
        emp.setDepartment(department);

        Map<String, Object> root = new HashMap<>();
        root.put("emp", emp);

        // 拼接()内都表示当前对象
        System.out.println(OgnlUtil.getValue(root, "emp.(id + ':' +  name + ':' + empNo)"));

    }

    /** 字面量测试 */
    @Test
    public void testLiteral(){
        Object root = new Object();
        System.out.println("字符串(双引号):" + OgnlUtil.getValue(root, "\"hello, ognl\"").getClass());
        System.out.println("字符串(单引号):" + OgnlUtil.getValue(root, "'hello, ognl'").getClass());
        System.out.println("字符(单引号):" + OgnlUtil.getValue(root, "'h'").getClass());
        System.out.println("整数:" + OgnlUtil.getValue(root, "123").getClass());
        System.out.println("Float:" + OgnlUtil.getValue(root, "123.09f").getClass());
        System.out.println("Double:" + OgnlUtil.getValue(root, "123.09D").getClass());
        System.out.println("BigInteger:" + OgnlUtil.getValue(root, "123h").getClass());
        System.out.println("BigDecimal:" + OgnlUtil.getValue(root, "123b").getClass());
        System.out.println("布尔类型:" + OgnlUtil.getValue(root, "false").getClass());
        System.out.println("空值类型:" + OgnlUtil.getValue(root, "null"));
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

    @Test
    public void testList(){
        List<String> books = Arrays.asList("java", "linux", "js", "css");
        System.out.println("size:" + OgnlUtil.getValue(books, "#this.size"));
        System.out.println("books[1]:" + OgnlUtil.getValue(books, "#this[1]"));

    }

    /** 变量测试 */
    @Test
    public void testVariable(){
        List<String> books = Arrays.asList("java", "linux", "js", "css");
        Map<String, Object> root = new HashMap<>();
        root.put("books", books);

        // 自定义变量
        System.out.println(OgnlUtil.getValue(root, "#bookSize = books.size , #bookSize > 2 ? '多' : '少'"));

        // 内置this变量
        System.out.println("books size:" + OgnlUtil.getValue(books, "#this.size"));
        System.out.println("books[1]:" + OgnlUtil.getValue(books, "#this[1]"));
    }

    @Test
    public void setValue() {
        Map<String, Object> root = new HashMap<>();
        root.put("idx", 1001);

        System.out.println("idx before:" + OgnlUtil.getValue(root, "idx"));
        OgnlUtil.setValue(root, "idx", "200");
        System.out.println("idx after:" + OgnlUtil.getValue(root, "idx"));

        OgnlUtil.setValue(root, "idx + 1", "3001");
    }

    @Test
    public void testSpecial(){
        Map<String, Object> root = new HashMap<>();
        List<String> books = Arrays.asList("java", "linux", "js", "css");
        root.put("books", books);
        root.put("name", "js");

        // 测试in/not in 表达式
        System.out.println(OgnlUtil.getValue(root, "'book' in {'book', 'linux'}"));
        System.out.println(OgnlUtil.getValue(root, "books.contains('linux')"));
        System.out.println(OgnlUtil.getValue(root, "'js' not in books"));

    }

    /** 集合筛选 */
    @Test
    public void testCollection() {
        Map<String, Object> root = new HashMap<>();
        root.put("array", Arrays.asList("java", false, "linux", 20, "js", 20.3f));

        System.out.println("筛选全部字符串:" + OgnlUtil.getValue(root, "array.{? #this instanceof String}"));
        System.out.println("筛选第一个字符串:" + OgnlUtil.getValue(root, "array.{^ #this instanceof String}"));
        System.out.println("筛选最后一个字符串:" + OgnlUtil.getValue(root, "array.{$ #this instanceof String}"));

    }
}
