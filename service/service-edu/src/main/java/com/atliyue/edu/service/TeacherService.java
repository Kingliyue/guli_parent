package com.atliyue.edu.service;

import com.atliyue.edu.entity.Teacher;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author liYue
 * @since 2021-04-01
 */
public interface TeacherService extends IService<Teacher> {
    @Override
    List<Teacher> list(Wrapper<Teacher> wrapper);
}
