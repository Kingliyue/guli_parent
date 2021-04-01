package com.atliyue.edu.service.impl;

import com.atliyue.edu.entity.Teacher;
import com.atliyue.edu.mapper.TeacherMapper;
import com.atliyue.edu.service.TeacherService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author liYue
 * @since 2021-04-01
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
    @Resource
    private  TeacherMapper teacherMapper ;
    @Override
    public List<Teacher> list(Wrapper<Teacher> queryWrapper) {
        List<Teacher> teachers = teacherMapper.selectList(queryWrapper);
        return teachers;
    }
}
