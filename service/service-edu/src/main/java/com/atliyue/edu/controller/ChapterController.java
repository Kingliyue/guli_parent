package com.atliyue.edu.controller;


import com.atliyue.edu.entity.Chapter;
import com.atliyue.edu.service.ChapterService;
import com.atliyue.edu.vo.ChapterVo;
import com.liyue.result.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@CrossOrigin
public class ChapterController {
    @Autowired
    private ChapterService chapterService;
    //获取章节小节的信息
    @GetMapping("getChapterAndVideo")
    public Result getChapterAndVideo(@RequestParam("courseId") String courseId){
        List<ChapterVo> chapterAndVideoList = chapterService.getChapterAndVideoList(courseId);
        return Result.ok().data("list",chapterAndVideoList);
    }
    @PostMapping("saveChapter")
    public Result saveChapter(@RequestBody Chapter chapter){
        chapterService.saveChaperAndVideo(chapter);
        return Result.ok();
    }
    @PutMapping("updateChapter")
    public Result updateChapter(@RequestBody Chapter chapter){
        chapterService.updateChapter(chapter);
        return Result.ok();
    }
}

