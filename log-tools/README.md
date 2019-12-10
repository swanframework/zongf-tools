## log-tools

## 1. 默认行为
* 使用logback 日志框架, 版本号为1.2.3
* 日志格式: [%d{yyyy-MM-dd HH:mm:ss.SSS}][%thread][%-5level][%logger{36}]-%msg%n
* 默认只配置了控制台输出

## 2. 集成方式

### 2.1 引入依赖
引入依赖后，便拥有了默认输出控制台的功能

```xml
<dependency>
    <groupId>org.zongf</groupId>
    <artifactId>log-tools</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

### 2.2 自定义日志输出格式
resources 目录下创建自定义logback.xml配置, 覆盖模块儿中的logback.xml 配置

```xml
<?xml version="1.0"?>
<configuration debug="false">

    <!-- 输出到控制台 -->
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <!-- 定义日志输出格式 -->
            <pattern>[%d{yyyy-MM-dd HH:mm:ss.SSS}][%thread][%-5level][%logger{36}]-%msg%n</pattern>
        </encoder>
    </appender>

    <!-- 配置根logger -->
    <root level="DEBUG">
        <appender-ref ref="STDOUT"/>
    </root>
</configuration>
```

### 2.3 修改logback版本号


```xml

```