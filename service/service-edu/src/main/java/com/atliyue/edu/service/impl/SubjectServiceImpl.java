package com.atliyue.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atliyue.edu.config.EasyExcelListen;
import com.atliyue.edu.entity.Subject;
import com.atliyue.edu.mapper.SubjectMapper;
import com.atliyue.edu.service.SubjectService;
import com.atliyue.edu.vo.SubjectData;
import com.atliyue.exception.MyException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author liYue
 * @since 2021-05-30
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {
    @Override
    public void saveSubject(MultipartFile file, SubjectService subjectService) {
        try {
            EasyExcel.read(file.getInputStream(), SubjectData.class, new EasyExcelListen(subjectService)).sheet().doRead();

        } catch (Exception e) {
            throw new MyException(20001, e.getMessage());
        }

    }
}
