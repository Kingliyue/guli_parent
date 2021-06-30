package com.atliyue.edu.controller;


import com.atliyue.edu.service.CourseService;
import com.atliyue.edu.vo.CourseVo;
import com.liyue.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author liYue
 * @since 2021-06-23
 */
@RestController
@RequestMapping("/edu/course")
@CrossOrigin
public class CourseController {
    @Autowired
    private CourseService courseService;
    @PostMapping("addCourse")
    public Result addCourse(CourseVo courseVo){

        String id = courseService.saveCourseAndCorseDesc(courseVo);

        return Result.ok().data("courseId",id);
    }



}

