package com.atliyue.edu.controller.front;

import com.atliyue.edu.entity.Course;
import com.atliyue.edu.entity.Teacher;
import com.atliyue.edu.service.CourseService;
import com.atliyue.edu.service.TeacherService;
import com.atliyue.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("eduService/teacherFront")
public class TeacherFrontController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private CourseService courseService;
    /**
     * 获取teacher分页信息
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("getTeacherListPage/{page}/{size}")
    public Result getTeacherListPage(@PathVariable("page") long page,@PathVariable("size") long size){
      Map<String,Object> map= teacherService.getTeacherListPage(page,size);
        return Result.ok().data(map);
    }

 /**
     * 根据id获取Teacher的信息
     * @return
     */
    @GetMapping("getTeacherInfo/{teacherId}")
    public Result getTeacherInfo(@PathVariable("teacherId")String teacherId){
        Teacher teacher = teacherService.getById(teacherId);
        List<Course> courses = courseService.queryCourse(teacherId);

        return Result.ok().data("teacher",teacher).data("course",courses);
    }
}
