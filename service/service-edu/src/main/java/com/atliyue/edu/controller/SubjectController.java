package com.atliyue.edu.controller;


import com.atliyue.edu.service.SubjectService;
import com.liyue.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @PostMapping("/read")
    @ApiOperation("分析文件")
    public Result uploadExcel(MultipartFile file) {
        try {

            subjectService.saveSubject(file, subjectService);
        } catch (Exception e) {
            return Result.error();
        }
        return Result.ok();
    }

}

