package com.atliyue.edu.controller.front;

import com.atliyue.edu.entity.Teacher;
import com.atliyue.edu.service.TeacherService;
import com.atliyue.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TeacherFrontController {
    @Autowired
    private TeacherService teacherService;
    @RequestMapping("getTeacherListPage/{page}/{size}")
    public Result getTeacherListPage(@PathVariable("page") long page,@PathVariable("size") long size){
      Map<String,Object> map= teacherService.getTeacherListPage(page,size);
        return Result.ok();
    }

    /**
     * 根据id获取Teacher的信息
     * @return
     */
    @RequestMapping("getTeacherInfo/{teacherId}")
    public Result getTeacherInfo(@PathVariable("teacherId")String teacherId){
        Teacher teacher = teacherService.getById(teacherId);
        return Result.ok().data("teacher",teacher);
    }
}
