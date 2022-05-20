package com.ccut.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.ccut.oss.service.OssService;
import com.ccut.oss.utils.ConstandPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = ConstandPropertiesUtils.END_POIND;
// 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = ConstandPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstandPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstandPropertiesUtils.BUCKET_NAME;

        try {
            // 创建OSS实例。
            OSS ossClient = new OSSClientBuilder().build( endpoint, accessKeyId, accessKeySecret );

              //获取上传文件输入流
            InputStream inputStream = file.getInputStream();
            // InputStream inputStream = new FileInputStream("D:\\localpath\\examplefile.txt");
            //获取文件名称
            String fileName = file.getOriginalFilename();

            //文件名称加随机值防止上传文件相同名字间的id相同被覆盖
            //replaceAll把生成字符串中有”-“的替换成”空“
            String uuid = UUID.randomUUID().toString().replaceAll( "-","" );
            fileName = uuid+fileName;


            /*把文件按照日期进行分类
            2021//3/12/01.jpg
            1、获取当日期这个关键字是因为引入
             <dependency>
            <groupId>joda-time</groupId>
            <artifactId>joda-time</artifactId>
        </dependency>*/
            String datePath = new DateTime().toString( "yyyy/MM/dd" );
            //拼接
            fileName = datePath+"/"+fileName;

          /* 调用oss方法实现上传
            第一个参数 Bucket名称
            第二个参数 上传到oss文件路径和文件名称
            第三个参数 上传文件的输入流
            */
            ossClient.putObject( bucketName, fileName, inputStream );
           // 关闭OSSClient。
            ossClient.shutdown();
            //把上传之后文件路径返回
            //需要阿里oss路径手动拼接出来并返回
            //https://edu-2016.oss-cn-hangzhou.aliyuncs.com/%E7%8C%AA.jpg
            String url = "https://"+bucketName+"."+endpoint+"/"+fileName;
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
