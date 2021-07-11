package com.atliyue.edu.controller;


import com.atliyue.edu.service.CourseService;
import com.atliyue.edu.vo.CourseVo;
import com.liyue.result.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@Api
@CrossOrigin
public class CourseController {
    @Autowired
    private CourseService courseService;
    @PostMapping("addCourse")
    public Result addCourse(@RequestBody CourseVo courseVo){

        String id = courseService.saveCourseAndCorseDesc(courseVo);

        return Result.ok().data("courseId",id);
    }
    @GetMapping("getCourse")
    public Result getCourse(@RequestParam("courseId")String courseId){
        CourseVo courseVo = courseService.selectCourseAndCorseDesc(courseId);
        return Result.ok().data("courseVo",courseVo);
    }
    @PutMapping("update")
    public Result updateCourse(@RequestBody CourseVo courseVo){
        courseService.updateCourseAndCorseDesc(courseVo);
        return  Result.ok();
    }

}

