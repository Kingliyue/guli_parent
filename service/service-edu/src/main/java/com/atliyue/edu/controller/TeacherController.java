package com.atliyue.edu.controller;


import com.atliyue.edu.entity.Teacher;
import com.atliyue.edu.service.TeacherService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
@ApiModel
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @GetMapping("/list")
    @ApiOperation("教师列表")
    public List<Teacher> getListTeacher(){
        List<Teacher> list = teacherService.list(null);
        return list;
    }
    @DeleteMapping("/delete")
    public String deleteTeacher(){
        teacherService.removeById("1192327476087115778");
        return "ok";
    }

}

