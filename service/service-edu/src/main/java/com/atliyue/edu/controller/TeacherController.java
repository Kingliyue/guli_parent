package com.atliyue.edu.controller;


import com.atliyue.edu.entity.Teacher;
import com.atliyue.edu.service.TeacherService;
import com.atliyue.edu.vo.QueryTeacher;
import com.atliyue.exception.MyException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liyue.result.Result;
import com.liyue.result.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
@CrossOrigin
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
   
    @GetMapping("/list")
    @ApiOperation("教师列表")
    public Result getListTeacher() {
        QueryWrapper queryWrapper = new QueryWrapper();
        List<Teacher> list = teacherService.list(queryWrapper);
        return Result.ok().data("list", list);
    }
    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除教师")
    @Transactional
    public Result deleteTeacher(@PathVariable("id") String userId) {
        System.out.println(userId +"----------");
        boolean b = true;
        try {
            b = teacherService.removeById(userId);
            System.out.println(Result.ok());
            return  Result.ok();
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new MyException(ResultCode.error,"我错了！！");
        }


    }
    @PostMapping("/addTeacher")
    @ApiOperation("添加老师")
    @Transactional
    public Result addTeacher(Teacher queryTeacher) {
        System.out.println(queryTeacher);
        try {
            boolean save = teacherService.save(queryTeacher);
            if(save){
                return Result.ok();
            }else {
                return Result.error();
            }
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
            e.printStackTrace();
            throw  new MyException(ResultCode.error,e.getMessage());
        }
    }
    //修改教师列表
    @PutMapping("/updateTeacher")
    @ApiOperation("修改教师信息")
    public Result updateTeacher(Teacher queryTeacher){
        //todo
        System.out.println(queryTeacher+"111111");
        boolean b1 = teacherService.updateById(queryTeacher);
        if(b1){
            return  Result.ok();
        }else{
            return Result.error();
        }
    }
    //根据ID获取教师信息
    @GetMapping("/getTeacher/{id}")
    public Result getTeacher(@PathVariable("id")String userId){
        Teacher teacher = teacherService.getById(userId);
        return Result.ok().data("teacher",teacher);

    }
    //有条件的查询教师列表分页
    @PostMapping("/query/{current}/{size}")
    @ApiOperation("有条件的查询老师列表")
    public Result queryTeacher(@PathVariable("current") long current, @PathVariable("size") long size,
          @RequestBody QueryTeacher queryTeacher) {
        //current当前第几页  、size每页大小
        System.out.println(queryTeacher+"-------------");
        IPage<Teacher> teacherPage = null;
        try {
            Page page = new Page(current, size);

            QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
            if (StringUtils.isNotEmpty(queryTeacher.getIntro())) {
                wrapper.eq("intro",queryTeacher.getIntro());
            }
            if ( StringUtils.isNotEmpty(queryTeacher.getName())) {
                wrapper.eq("name",queryTeacher.getName());
            }
            if (queryTeacher.getGmtCreate() != null){
                wrapper.eq("gmt_create",queryTeacher.getGmtCreate());
            }
            if  ((queryTeacher.getGmtModified() !=null)) {
                wrapper.eq("gmt_create", queryTeacher.getGmtModified());
            }
            teacherPage = teacherService.page(page, wrapper);
            System.out.println(teacherPage);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException(ResultCode.error, e.getMessage());
        }


        return Result.ok().code(ResultCode.sucese).data("ippage", teacherPage);
    }

}

