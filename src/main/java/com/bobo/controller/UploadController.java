package com.bobo.controller;

import com.bobo.pojo.Result;
import com.bobo.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author boboking
 * @date 2023/6/1
 * @description 文件上传管理
    * 1.接收前端上传的图片
    * 2.将图片存储起来，放到阿里云存储 OOS
    * 3.返回响应 图片的访问的 url
    * 4.前端拿到图片的 url 后会自动的给 阿里云对象存储服务OOS发送请求，最终获取到对应图片，然后在浏览器页面展示出来
    *
    * 步骤：
    * 引入阿里云OOS上传文件工具类(由官方的示例代码改造而来)
    * 上传图片接口开发
 */
@Slf4j
@RestController
public class UploadController {
    /* 前端表单信息如下：
    <form action="/upload" method="post" enctype="multipart/form-data">
        姓名: <input type="text" name="username"><br>
        年龄: <input type="text" name="age"><br>
        头像: <input type="file" name="image"><br>
        <input type="submit" value="提交">
    </form>
    */
    @Autowired  //依赖注入 阿里云 OSS 工具类，从IOC容器中得到一个其bean实例对象
    private AliOSSUtils aliOSSUtils;

    /** 本地存储文件方法 */
    @PostMapping("/localUpload")  //注意这里的接收参数名要和前端请求表单项的参数名一致，也可以通过 @RequestParam注解可以把前端请求参数image绑定到files参数名
    public Result uploadAtLocal(String username, Integer age, @RequestParam("image") @RequestBody List<MultipartFile> files) throws IOException {
        // List<MultipartFile> 可以接收多个文件
        ArrayList<String> names = new ArrayList<>();
        files.stream().forEach(file -> {
            log.info("文件上传：{}, {}, {}", username, age, file);
            String originalFilename = file.getOriginalFilename(); //获取接收文件的原始文件名 比如 1.jpg
            //如果接收过来的文件其原始文件名相同，则会覆盖替换之前同名的文件，
            // 所以需要构造唯一的文件名(不能重复) -- 采用UUID 通用唯一识别码，这样才能把同名不同内容的文件保存下来
            //String uuid = UUID.randomUUID().toString();
            //System.out.println(uuid); //比如： 7f120200-e31e-4fa3-8f86-2558f86cec50
            int index = originalFilename.lastIndexOf(".");
            String extName = originalFilename.substring(index);//从 . 开始截取全部，拿到文件扩展名 .jpg
            //构建一个新的文件名
            String newFileName = UUID.randomUUID().toString() + extName; //拼接
            log.info("新的文件名：{}", newFileName); //打印日志

            names.add(newFileName);

            //将接收到的文件存储在本地磁盘目录下
            /** 注意：如果将图片存储在服务器的本地磁盘目录中，那么这些文件是没有办法在前端页面浏览器中访问到的
             * 如果上传大文件，音频、视频等，是不可能存储在本地的一个服务器上，而开发中是推荐使用云存储 */
            try {
                file.transferTo(new File("D:\\upload\\" + newFileName)); //注意：要保证D盘下有这个 upload文件夹
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        /**
         * 在springboot中，文件上传，默认单个文件允许最大大小为 1M ，如果需要上传大文件，可以在 application.properties 文件中进行如下配置：
         * #配置单个文件最大上传大小
         * spring.servlet.multipart.max-file-size=10MB
         * #配置单个请求最大上传大小(一次请求可以上传多个文件)
         * spring.servlet.multipart.max-request-size=100MB
         * */
        return Result.success(names); //响应文件名数据
    }

    /** 阿里云对象存储 OSS Bucket 上传文件 */
    @PostMapping("/upload")   //MultipartFile 接收单个上传的文件，并封装到该对象中
    public Result uploadAliYunOSS(MultipartFile file) throws IOException {
        /** 注意这里的接收参数名 file 要和前端请求表单项中的属性 name="参数名" 一致，也可以通过 @RequestParam注解可以把前端请求的参数名绑定到此处的 file 形参 */
        log.info("文件上传...文件名：{}", file.getOriginalFilename()); //打印日志 原始文件名
        //调用阿里云OSS工具类进行文件上传，并接收 已经存储在阿里云对象存储OSS Bucket 中的图片的 url
        String url = aliOSSUtils.upload(file);
        log.info("文件上传完成，文件访问的url: {}", url);
        /** 文件（Object）上传至存储空间（Bucket）后，OSS会自动生成文件URL，
         * 您可以直接通过文件URL（即Bucket外网访问域名）访问该文件。
         * 若您希望通过自定义域名（自有域名）访问这些文件，需要在阿里云官网操作 将自定义域名绑定至文件所在的Bucket。*/

        return Result.success(url); //给前端响应数据
    }

}
