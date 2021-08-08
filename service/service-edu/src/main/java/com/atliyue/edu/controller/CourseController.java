package com.atliyue.edu.controller;


import com.atliyue.edu.entity.Course;
import com.atliyue.edu.service.CourseService;
import com.atliyue.edu.vo.CourseInfoVo;
import com.atliyue.edu.vo.CourseVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liyue.result.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
@Slf4j
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping("addCourse")
    public Result addCourse(@RequestBody CourseVo courseVo) {
        log.info("{},{},{},{}", "CourseController", "addCourse", "传入的值:", courseVo);
        String id = courseService.saveCourseAndCorseDesc(courseVo);
        return Result.ok().data("courseId", id);
    }

    @GetMapping("/getCourse/{courseId}")
    public Result getCourse(@PathVariable("courseId") String courseId) {
        CourseVo courseVo = courseService.selectCourseAndCorseDesc(courseId);
        return Result.ok().data("courseVo", courseVo);
    }

    @PutMapping("update")
    public Result updateCourse(@RequestBody CourseVo courseVo) {
        courseService.updateCourseAndCorseDesc(courseVo);
        return Result.ok();
    }

    @GetMapping("select")
    public Result select(@RequestParam String id) {
        Course course = courseService.selectCourse(id);
        return Result.ok().data("course", course);
    }

    @GetMapping("getCourseInfo/{courseId}")
    public Result getCourseInfo(@PathVariable String courseId) {
        CourseInfoVo courseInfo = courseService.getCourseInfo(courseId);
        return Result.ok().data("coursePublish",courseInfo);
    }
    //发布
    @PutMapping("updateStatus/{courseId}")
        public Result updateStatus(@PathVariable String courseId){
        Course course = new Course();
        course.setId(courseId);
        course.setStatus("Normal");
        courseService.updateById(course);
        return Result.ok();

    }
    @GetMapping("getCourseList/{page}/{limit}")
    public Result getCourseList(@PathVariable long page, @PathVariable long limit,
                                Course courseQuery){
        Page page1 = new Page(page,limit);
        QueryWrapper queryWrapper = new QueryWrapper<>(courseQuery);
        if(queryWrapper!= null && StringUtils.isNoneEmpty(courseQuery.getTitle())){
            queryWrapper.select("title");
        }
        if (queryWrapper != null && StringUtils.isNoneEmpty(courseQuery.getStatus())){
            queryWrapper.select("status");
        }
        IPage iPage = courseService.page(page1, queryWrapper);

        return Result.ok().data("page",iPage);
    }
}
