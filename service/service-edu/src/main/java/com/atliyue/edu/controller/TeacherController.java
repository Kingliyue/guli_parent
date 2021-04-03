package com.atliyue.edu.controller;


import com.atliyue.edu.entity.Teacher;
import com.atliyue.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author liYue
 * @since 2021-04-01
 */
@RestController
@RequestMapping("/edu/teacher")
@Api
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @GetMapping("/list")
    @ApiOperation("教师列表")
    public List<Teacher> getListTeacher(){
        List<Teacher> list = teacherService.list(null);
        return list;
    }
    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除教师")
    public boolean deleteTeacher(@PathVariable("id") String userId){
        boolean b = teacherService.removeById(userId);
        return b;
    }

}

