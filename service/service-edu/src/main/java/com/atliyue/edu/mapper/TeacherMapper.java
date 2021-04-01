package com.atliyue.edu.mapper;

import com.atliyue.edu.entity.Teacher;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 Mapper 接口
 * </p>
 *
 * @author liYue
 * @since 2021-04-01
 */
@Mapper
public interface TeacherMapper extends BaseMapper<Teacher> {

}
