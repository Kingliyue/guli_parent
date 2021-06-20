package com.atliyue.edu.mapper;

import com.atliyue.edu.vo.SubjectVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface SubjectVoMapper extends BaseMapper<SubjectVo> {
    List<SubjectVo> getSubjectAll();
}
