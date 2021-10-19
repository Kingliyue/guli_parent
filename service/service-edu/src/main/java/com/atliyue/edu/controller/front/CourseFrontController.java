package com.atliyue.edu.controller.front;

import com.atliyue.edu.service.CourseService;
import com.atliyue.edu.vo.CourseFrontVo;
import com.atliyue.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("eduService/courseFront")
public class CourseFrontController {
    @Autowired
    private CourseService courseService;
    @PostMapping("getCourseListPage")
    public Result getCourseListPage(@RequestBody CourseFrontVo courseFrontVo, @PathVariable("page")long page, @PathVariable("size")long size){
        Map<String,Object> map = courseService.getCourseListPage(page,size,courseFrontVo);
        return Result.ok().data(map);
    }
}
