package com.atliyue.edu.controller.front;

import com.atliyue.edu.service.CourseService;
import com.atliyue.edu.service.SubjectService;
import com.atliyue.edu.vo.CourseFrontVo;
import com.atliyue.edu.vo.SubjectVo;
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
    @PostMapping("getCourseListPage/{page}/{size}")
    public Result getCourseListPage(CourseFrontVo courseFrontVo, @PathVariable("page")long page, @PathVariable("size")long size){
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

        return  Result.ok();
    }

    /**
     * 回去一二級分類
     * @return
     */
    @GetMapping("getSubject")
    public Result getSubject(){
        List<SubjectVo> subjectAll = subjectService.getSubjectAll();
        return Result.ok().data("subject",subjectAll);
    }
}
