package com.atliyue.edu.service;

import com.atliyue.edu.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author liYue
 * @since 2021-04-01
 */

@Service
public interface TeacherService extends IService<Teacher> {

    List getTeacherList();

    Map<String, Object> getTeacherListPage(long page, long size);
}
