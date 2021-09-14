package com.atliyue.edu.controller.front;

import com.atliyue.edu.service.CourseService;
import com.liyue.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping("eduService/indexFront")
public class IndexFrontController {
    @Autowired
    private CourseService courseService;
    //1.查询最热门的八个课程
    @GetMapping("getCourserList")
    public Result getCourseList(){
        courseService.getCourseList();

        return  Result.ok();
    }
    //2.查询最受欢迎四个老师

}
