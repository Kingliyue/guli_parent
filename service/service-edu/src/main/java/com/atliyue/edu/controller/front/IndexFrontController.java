package com.atliyue.edu.controller.front;

import com.atliyue.edu.service.CourseService;
import com.atliyue.edu.service.TeacherService;
import com.liyue.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 */
@RestController
@RequestMapping("eduService/indexFront")
public class IndexFrontController {
    @Autowired
    private CourseService courseService;
    private TeacherService teacherService;
    //1.查询最热门的八个课程
    @GetMapping("getCourserList")
    public Result getCourseList(){

        List courseList = courseService.getCourseList();

        List teacherList = teacherService.getTeacherList();
        return Result.ok().data("courseList",courseList).data("teacherList",teacherList);
    }

}
