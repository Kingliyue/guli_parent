package com.atliyue.edu.controller;


import com.liyue.result.Result;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
public class VideoController {
    public Result getVideo(){
        return Result.ok();
    }

}

