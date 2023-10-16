package com.bobo.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author boboking
 * @date 2023/6/2
 * @description application.yml 配置文件中 自定义的阿里云OSS配置信息对应的 实体类
 */
// prefix 通过该注解指定阿里云OSS配置信息的属性 前缀归属，
// aliyun.oss下的 4个子配置项对应的参数值就会自动的注入到该类的对应属性中来
// 然后通过该类的getter方法拿到其属性值，也就拿到了配置信息

/* 在其它类中的使用方法、步骤
    @Autowired //通过注解进行依赖注入
    private AliOSSProperties aliOSSProperties; //定义一个该类对象

    调用该类的 aliOSSProperties.getEndpoint() 方法，就可以拿到其属性值： https://oss-cn-hangzhou.aliyuncs.com
*/
/** 当使用 @ConfigurationProperties 注解时，该java文件上方可能会发出红色警告提示框
 * Spring Boot Configuration Annotation Processor not configured 这句话
 * 此时需要在maven项目管理的 pom.xml 配置文件中引入如下依赖即可
 *          <dependency>
 *             <groupId>org.springframework.boot</groupId>
 *             <artifactId>spring-boot-configuration-processor</artifactId>
 *          </dependency>
 * */
@Data  //lombok 自动生成 setter、getter方法.....
@Component //将该类交给IOC容器管理，要使用该类对象时，只需要通过依赖注入就可以从容器中获取到该类的实例bean对象
@ConfigurationProperties(prefix = "aliyun.oss") //属性自动注入，且必须指定前缀归属
public class AliOSSProperties {
    //注意这里的属性名一定要和 application.yml 配置文件中的 阿里云OSS配置信息一致
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;

    /* application.yml 配置文件中 自定义的阿里云OSS配置信息格式如下
    aliyun:
      oss:
        endpoint: https://oss-cn-hangzhou.aliyuncs.com
        accessKeyId: LTAI5tGsMz9g82AHrvDDGWWv
        accessKeySecret: 8tlJqG7aV30JgaOZJV6iOOQENAwuUo
        bucketName: bobo-tlias
    */
}
