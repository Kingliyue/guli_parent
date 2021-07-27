package com.atliyue.edu.controller;


import com.atliyue.edu.entity.Video;
import com.atliyue.edu.service.VideoService;
import com.liyue.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author liYue
 * @since 2021-06-23
 */
@RestController
@RequestMapping("/edu/video")
@CrossOrigin
public class VideoController {
    @Autowired
    private VideoService videoService ;
    @GetMapping("getVideo/{videoId}")
    public Result getVideo(@PathVariable String videoId){
        Video video = videoService.getById(videoId);
        return Result.ok().data("video",video);
    }
    @PutMapping("updateVideo")
    public Result updateVideo(Video video){
        videoService.updateById(video);
        return  Result.ok();
    }
    @PostMapping("saveVideo")
    public Result saveVideo(Video video){
        videoService.save(video);
        return Result.ok();
    }
    @DeleteMapping("deleteVideo/{videoId}")
    public Result deleteVideo(@PathVariable String videoId){
        videoService.removeById(videoId);
        return Result.ok();
    }
}

