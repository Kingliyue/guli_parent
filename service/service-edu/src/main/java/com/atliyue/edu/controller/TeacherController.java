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
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@CrossOrigin //跨域
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @PostMapping("user/login")
    @ApiOperation("登录的接口")
    public Result login(@RequestParam("username") String username,@RequestParam("password") String password){

        return Result.ok().data("admin","admin");

    }


    @GetMapping("/list")
    @ApiOperation("教师列表")
    public Result getListTeacher() {
        List<Teacher> list = teacherService.list(null);
        log.info(String.format("查询返回的结果%s",list));
        return Result.ok().data("item", list);

    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除教师")
    @Transactional
    public Result deleteTeacher(@PathVariable("id") String userId) {
        boolean b = true;
        try {
            b = teacherService.removeById(userId);
            Result.ok();
        } catch (Exception e) {
            log.info("查询返回的结果 %s",e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new MyException(ResultCode.sucese, e.getMessage());
        }
        return null;

    }

    @PostMapping("edu/teacher/")
    @ApiOperation("添加老师")

    public Result addTeacher(QueryTeacher teacher) {

        return Result.ok();
    }

    //有条件的查询教师列表分页
    @PostMapping("/edu/teacher/query")
    @ApiOperation("有条件的查询老师列表")
    public Result queryTeacher(@RequestParam("current") long current, @RequestParam("size") long size, QueryTeacher queryTeacher) {
        //current当前第几页  、size每页大小
        IPage<Teacher> teacherPage = null;
        try {
            Page page = new Page(current, size);

            QueryWrapper wrapper = new QueryWrapper(queryTeacher);
            if (queryTeacher != null && "".equals(queryTeacher.getId())) {
                wrapper.select("id");
            }
            if (queryTeacher != null && "".equals(queryTeacher.getName())) {
                wrapper.select("name");
            }
            if (queryTeacher != null && "".equals(queryTeacher.getGmtCreate())) {
                wrapper.select("gmtCreate");
            }
            if (queryTeacher != null && "".equals(queryTeacher.getGmtModified())) {
                wrapper.select("gmtModified");
            }
            teacherPage = teacherService.page(page, wrapper);

        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException(ResultCode.error, e.getMessage());
        }


        return Result.ok().code(ResultCode.sucese).data("ippage", teacherPage);
    }

}

