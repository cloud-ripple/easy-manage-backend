package com.bobo.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.UUID;

/**
 * 阿里云 OSS 工具类
 */

@Component  //将该类交给IOC容器管理，通过依赖注入从IOC中来获取 bean对象，在使用该类实例的时候就不需要通过 new 对象方式来创建了
public class AliOSSUtils {
    /** 以下是通过硬编码方式把配置信息写死在java程序中 ，不便于维护修改，不推荐使用
     * 由于是springboot项目，而springboot的配置信息统一由规定的
     * application.properties / application.yml / application.yaml 等各类配置文件进行管理
     * 所以可以将如下的 阿里云OSS配置信息 放在配置文件中
     * 通过springboot提供的注解   @Value  根据指定其配置文件中的 key键 拿到对应的 value值
     * @Value 注解通常用于外部配置的属性注入，具体用法为： @Value("${配置文件中的key}")
     * */
    /** 获取阿里云OSS配置信息参数方式 1 */
//    private String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";
//    private String accessKeyId = "LTAI5tGsMz9g82AHrvDDGWWv"; //    //注意改成自己的密钥、Bucket名称
//    private String accessKeySecret = "8tlJqG7aV30JgaOZJV6iOOQENAwuUo";
//    private String bucketName = "bobo-tlias";

    /** 获取阿里云OSS配置信息参数方式 2 */
    /** 注意：这里的 @Value 注解是spring框架中的，不要选择 lombok提供的 */
    @Value("${aliyun.oss.endpoint}")    //注意确保 key是否对应一致 ，把指定key对应的value值  赋值给 endpoint
    private String endpoint;

    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliyun.oss.bucketName}")
    private String bucketName;

    /** 获取阿里云OSS配置信息参数方式 3  （推荐使用） */
    //通过 application.yml 配置文件中 自定义的阿里云OSS配置信息对应的 实体类 AliOSSProperties
    @Autowired //依赖注入，从IOC容器中拿到 AliOSSProperties 类的实例bean对象，并完成赋值给 aliOSSProperties
    private AliOSSProperties aliOSSProperties;

    /** 方式 2 和 方式 3 都是用来注入外部配置文件中的属性的
     * 两者区别：
     * @Value 注解只能一个一个的进行外部属性的注入
     * @ConfigurationProperties 注解可以批量的将外部的属性注入到 bean对象的各个队形属性中 */

    /**
     * 实现上传图片到阿里云对象存储 OSS Bucket
     */
    public String upload(MultipartFile file) throws IOException {
        /** 获取阿里云OSS配置信息参数方式 3 推荐使用 */
//        String  endpoint3 = aliOSSProperties.getEndpoint();
//        String  accessKeyId3 = aliOSSProperties.getAccessKeyId();
//        String accessKeySecret3 = aliOSSProperties.getAccessKeySecret();
//        String bucketName3 = aliOSSProperties.getBucketName();

        // 获取上传的文件的输入流
        InputStream inputStream = file.getInputStream();

        // 避免文件覆盖
        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));

        //上传文件到 OSS
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(bucketName, fileName, inputStream);

        //文件访问路径
        String url = endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" + fileName;
        // 关闭ossClient
        ossClient.shutdown();

        return url;// 把上传到oss的路径返回  举例：https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/45376bcf-6fae-40a7-a8f0-950cc5a75acb.jpeg
    }

}
