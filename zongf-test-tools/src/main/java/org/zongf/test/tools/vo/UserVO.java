package org.zongf.test.tools.vo;

/**
 * @author zongf
 * @date 2020-04-28
 */
public class UserVO {

    private String name;

    private Integer age;

    private String sex;

	public UserVO() {
        super();
    }

	public UserVO(String name, Integer age, String sex) {
        super();
		this.name = name;
		this.age = age;
		this.sex = sex;
    }

    public void setName(String name){
		this.name=name;
	}

	public String getName(){
		return this.name;
	}

    public void setAge(Integer age){
		this.age=age;
	}

	public Integer getAge(){
		return this.age;
	}

    public void setSex(String sex){
		this.sex=sex;
	}

	public String getSex(){
		return this.sex;
	}

    public String toString() {
		return getClass().getSimpleName() + "@" + hashCode() + ": {name:" + name + ", age:" + age + ", sex:" + sex  + "}";
	}

}
