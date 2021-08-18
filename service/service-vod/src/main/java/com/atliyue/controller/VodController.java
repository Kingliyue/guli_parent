package com.atliyue.controller;

import com.liyue.result.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("edu/vod")
public class VodController {

    @PostMapping("upload")
    public Result videoUpload(MultipartFile file){

        return Result.ok();
    }

}
