package com.atliyue.edu.mapper;

import com.atliyue.edu.entity.Teacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 讲师 Mapper 接口
 * </p>
 *
 * @author liYue
 * @since 2021-04-01
 */
public interface TeacherMapper extends BaseMapper<Teacher> {
    List getTeacherList();

}
