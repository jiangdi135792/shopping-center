package com.ccut.oss.controller;






import com.ccut.commonutils.R;
import com.ccut.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
public class OssController {

    @Autowired
    private OssService ossService;
    //上产头像方法
    @PostMapping
    public R uploadOssFile(MultipartFile file){
        //获取上传文件MultipartFile以流的方式
        String url = ossService.uploadFileAvatar(file);
        return R.ok().data( "url",url );
    }
}
