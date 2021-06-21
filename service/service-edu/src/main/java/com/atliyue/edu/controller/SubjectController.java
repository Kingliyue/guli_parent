package com.atliyue.edu.controller;


import com.atliyue.edu.service.SubjectService;
import com.atliyue.edu.vo.SubjectVo;
import com.liyue.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author liYue
 * @since 2021-05-30
 */
@RestController
@RequestMapping("/edu/subject")
@Api
@CrossOrigin
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @PostMapping("/upload")
    @ApiOperation("分析文件")
    public Result uploadExcel(MultipartFile file) {

        subjectService.saveSubject(file, subjectService);
        return Result.ok();
    }
    @GetMapping("/list")
    public Result downExcel(){

        List<SubjectVo> subjectAll = subjectService.getSubjectAll();
        System.out.println(subjectAll);
        return Result.ok().data("list",subjectAll);
    }

}

