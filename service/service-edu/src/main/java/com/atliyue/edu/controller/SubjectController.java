package com.atliyue.edu.controller;


import com.atliyue.edu.entity.Subject;
import com.atliyue.edu.service.SubjectService;
import com.liyue.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    @PostMapping("/down")
    public Result downExcel(){

        List<Subject> subjectAll = subjectService.getSubjectAll();
        return Result.ok().data("list",subjectAll);
    }

}

