package com.atliyue.controller;

import com.atliyue.service.VodService;
import com.atliyue.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("edu/vod")
@CrossOrigin
public class VodController {
    /**
     * 上传视频
     * @param file
     * @return
     */
    @Autowired
    private VodService vodService;
    @PostMapping("videoUpload")
    public Result videoUpload(MultipartFile file){
        String videoId = vodService.getVideoId(file);
        return Result.ok().data("videoId",videoId);
    }
    @DeleteMapping("delete/{videoSourceId}")
    public Result deleteVideo(@PathVariable String videoSourceId){
        vodService.deleteVideoById(videoSourceId);
        return Result.ok();
    }
}
