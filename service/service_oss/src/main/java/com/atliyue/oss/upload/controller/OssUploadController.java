package com.atliyue.oss.upload.controller;

import com.atliyue.exception.MyException;
import com.atliyue.oss.upload.service.OssUploadService;
import com.liyue.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


/**
 * 上传到oss
 */
@RestController
@Api(tags="上传文件")
@CrossOrigin
@RequestMapping("/oss")
public class OssUploadController {
    @Autowired
    private OssUploadService ossUploadService;
    @PostMapping("/upload")
    @ApiOperation("上传文件")
    public Result uploadOss(MultipartFile file){
        String url = "";
        try {
             url = ossUploadService.ossUploadFile(file);
        }catch (MyException e){
            Result.error().setMessage("上传头像失败");
        }
        return Result.ok().data("url",url);
    }

}
