package org.zongf.test.tools.ognl;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zongf
 * @date 2020-06-03
 */
@Setter @Getter
public class Employee {

    private Integer id;

    private String empNo;

    private String name;

    private Department department;

    @Setter @Getter
    public static class Department{

        private String name;

        private String manager;

    }
}
