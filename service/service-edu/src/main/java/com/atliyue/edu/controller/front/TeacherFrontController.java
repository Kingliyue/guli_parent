package com.atliyue.edu.controller.front;

import com.atliyue.result.Result;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeacherFrontController {
    public Result getTeacherListPage(){
        return Result.ok();
    }
}
