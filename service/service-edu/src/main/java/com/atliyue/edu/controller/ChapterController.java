package com.atliyue.edu.controller;


import com.atliyue.edu.service.ChapterService;
import com.atliyue.edu.vo.ChapterVo;
import com.liyue.result.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author liYue
 * @since 2021-06-23
 */
@RestController
@RequestMapping("/edu/chapter")
@Api
public class ChapterController {
    @Autowired
    private ChapterService chapterService;
    //获取章节小节的信息
    @GetMapping("getChapterAndVideo")
    public Result getChapterAndVideo(){
        List<ChapterVo> chapterAndVideoList = chapterService.getChapterAndVideoList();
        return Result.ok().data("list",chapterAndVideoList);
    }

}

