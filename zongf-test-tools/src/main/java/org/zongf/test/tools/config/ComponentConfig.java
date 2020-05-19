package org.zongf.test.tools.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

/** 组件配置
 * @author zongf
 * @date 2020-05-16
 */
@Configuration
@ComponentScans(
    @ComponentScan("org.zongf.tools.spring")    // spring-tools 模块扫描配置
)
public class ComponentConfig {

}
