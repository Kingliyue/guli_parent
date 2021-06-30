package com.atliyue.edu.service.impl;

import com.atliyue.edu.entity.Course;
import com.atliyue.edu.entity.CourseDescription;
import com.atliyue.edu.mapper.CourseDescriptionMapper;
import com.atliyue.edu.mapper.CourseMapper;
import com.atliyue.edu.service.CourseService;
import com.atliyue.edu.vo.CourseVo;
import com.atliyue.exception.MyException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

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
    @Resource
    @Autowired
    private CourseDescriptionMapper descriptionMapper;
    @Resource
    @Autowired
    private CourseMapper courseMapper;
    @Override
    @Transactional
    public CourseVo selectCourseAndCorseDesc(String courseId) {
        //获取课程描述
        CourseDescription courseDescription = descriptionMapper.selectById(courseId);
        Course course = courseMapper.selectById(courseId);
        CourseVo courseVo = new CourseVo();

        BeanUtils.copyProperties(course,courseVo);
        BeanUtils.copyProperties(courseDescription,courseVo);
        return  courseVo;

    }

    @Override
    @Transactional
    public String saveCourseAndCorseDesc(CourseVo courseVo) {
        Course course =new Course();
        BeanUtils.copyProperties(courseVo,course);
        //1.保存到课程表中
        int insert = baseMapper.insert(course);
        if (insert == 0){
            throw  new MyException(200001,"保存课程信息异常");
        }
        //1.1获取课程id
        String id =course.getId();
        //2.保存到课程描述表中
        CourseDescription courseDescription = new CourseDescription();
        BeanUtils.copyProperties(courseVo,courseDescription);
        courseDescription.setId(id);
        descriptionMapper.insert(courseDescription);
        return id;
    }
}
