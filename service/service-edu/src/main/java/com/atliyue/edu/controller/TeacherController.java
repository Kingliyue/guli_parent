package com.atliyue.edu.controller;


import com.atliyue.edu.entity.Teacher;
import com.atliyue.edu.service.TeacherService;
import com.atliyue.exception.MyException;
import com.liyue.result.Reselt;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
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
    public Reselt getListTeacher(){
        List<Teacher> list = teacherService.list(null);
        return  Reselt.ok().data("item",list);

    }
    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除教师")
    @Transactional
    public Reselt deleteTeacher(@PathVariable("id") String userId) {
        boolean b =true;
        try {
            int cc =2/0;
            b = teacherService.removeById(userId);
            Reselt.ok();
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
           throw new MyException(12234,e.getMessage());
        }
        return null;

    }
    @PostMapping
    @ApiOperation("添加老师")
     public Reselt addTeacher(){

        return Reselt.ok();
    }


}

