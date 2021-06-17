package com.atliyue.edu.mapper;

import com.atliyue.edu.entity.Subject;
import com.atliyue.edu.vo.SubjectVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 课程科目 Mapper 接口
 * </p>
 *
 * @author liYue
 * @since 2021-05-30
 */

public interface SubjectMapper extends BaseMapper<Subject> {

    List<SubjectVo> getSubjectAll();
    List<Subject> selectSubject();
}
