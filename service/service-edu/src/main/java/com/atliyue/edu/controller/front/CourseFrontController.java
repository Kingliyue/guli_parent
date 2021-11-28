package com.atliyue.edu.controller.front;

import com.atliyue.edu.service.ChapterService;
import com.atliyue.edu.service.CourseService;
import com.atliyue.edu.service.SubjectService;
import com.atliyue.edu.vo.*;
import com.atliyue.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("eduService/courseFront")
public class CourseFrontController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private ChapterService chapterService;
    @PostMapping("getCourseListPage/{page}/{size}")
    public Result getCourseListPage(@RequestBody CourseFrontVo courseFrontVo, @PathVariable("page")long page, @PathVariable("size")long size){
        Map<String,Object> map = courseService.getCourseListPage(page,size,courseFrontVo);
        return Result.ok().data(map);
    }

    /**
     * 根據課程id獲取課程信息
     * @param courseId
     * @return
     */
    @GetMapping("getCourseInfo/{courseId}")
    public Result getCourseInfo(@PathVariable("courseId") String courseId){

        CourseWebVo courseInfoWeb = courseService.getCourseInfoWeb(courseId);
        List<ChapterVo> chapterAndVideoList = chapterService.getChapterAndVideoList(courseId);
        return  Result.ok().data("course",courseInfoWeb).data("chapterList",chapterAndVideoList);
    }

    /**
     * 获取一二级分类
     * @return
     */
    @GetMapping("getSubject")
    public Result getSubject(){
        List<SubjectVo> subjectAll = subjectService.getSubjectAll();
        return Result.ok().data("subject",subjectAll);
    }
}
