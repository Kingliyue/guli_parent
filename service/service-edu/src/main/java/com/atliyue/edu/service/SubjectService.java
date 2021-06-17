package com.atliyue.edu.service;

import com.atliyue.edu.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author liYue
 * @since 2021-05-30
 */
public interface SubjectService extends IService<Subject> {

    void saveSubject(MultipartFile file,SubjectService subjectService);

    List<Subject> getSubjectAll();

}
