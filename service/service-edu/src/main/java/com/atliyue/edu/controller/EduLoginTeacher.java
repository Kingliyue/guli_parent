package com.atliyue.edu.controller;

import com.atliyue.edu.service.TeacherService;
import com.atliyue.edu.vo.EduLoginVo;
import com.atliyue.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("edu/teacher")
@CrossOrigin//跨域
public class EduLoginTeacher {
    @Autowired
    private TeacherService teacherService;
    @PostMapping("user/login")
    public Result login(@RequestBody EduLoginVo eduLoginVo){
        return Result.ok().data("token","admin");
    }
    @GetMapping ("user/info/{token}")
        public Result getInfo(@PathVariable("token") String token){
        System.out.println(token);
        return Result.ok().data("roles","[admin]")
                .data("name","admin")
                .data("avatar","https://online-teach-file.oss-cn-beijing.aliyuncs.com/teacher/2019/10/30/de47ee9b-7fec-43c5-8173-13c5f7f689b2.png");

    }
}
