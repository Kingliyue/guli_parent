package com.atliyue.edu.controller;


import com.atliyue.edu.entity.Teacher;
import com.atliyue.edu.service.TeacherService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping(value = "/list")
    @ApiOperation("教师列表")
    public List<Teacher> getLiistTearche( Wrapper<Teacher> queryWrapper){
        List<Teacher> list = teacherService.list(queryWrapper);
        return list;
    }

}

