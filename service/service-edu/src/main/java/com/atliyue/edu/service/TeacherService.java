package com.atliyue.edu.service;

import com.atliyue.edu.entity.Teacher;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author liYue
 * @since 2021-04-01
 */
@Transactional(rollbackFor = RuntimeException.class)
public interface TeacherService extends IService<Teacher> {

}
