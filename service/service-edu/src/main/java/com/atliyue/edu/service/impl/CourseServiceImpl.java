package com.atliyue.edu.service.impl;

import com.atliyue.edu.entity.Course;
import com.atliyue.edu.entity.CourseDescription;
import com.atliyue.edu.mapper.CourseDescriptionMapper;
import com.atliyue.edu.mapper.CourseMapper;
import com.atliyue.edu.service.CourseService;
import com.atliyue.edu.vo.CourseVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author liYue
 * @since 2021-06-23
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
    @Autowired
    private CourseDescriptionMapper descriptionMapper;
    @Override
    @Transactional
    public void saveCourseAndCorseDesc(CourseVo courseVo) {
        Course course =new Course();
        BeanUtils.copyProperties(courseVo,course);
        //1.保存到课程表中
        baseMapper.insert(course);
        //2.保存到课程描述表中
        CourseDescription courseDescription = new CourseDescription();
        BeanUtils.copyProperties(courseVo,courseDescription);
        descriptionMapper.insert(courseDescription);
    }
}
