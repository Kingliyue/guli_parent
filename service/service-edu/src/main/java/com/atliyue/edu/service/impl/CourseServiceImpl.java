package com.atliyue.edu.service.impl;

import com.atliyue.edu.entity.Course;
import com.atliyue.edu.entity.CourseDescription;
import com.atliyue.edu.mapper.CourseDescriptionMapper;
import com.atliyue.edu.mapper.CourseMapper;
import com.atliyue.edu.service.CourseService;
import com.atliyue.edu.vo.CourseInfoVo;
import com.atliyue.edu.vo.CourseVo;
import com.atliyue.exception.MyException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {


    @Autowired
    private CourseDescriptionMapper descriptionMapper;
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public Course selectCourse(String courseId) {
        return courseMapper.selectCourseById(courseId);
    }

    @Override
    @Transactional
    public CourseVo selectCourseAndCorseDesc(String courseId) {
        //获取课程描述
        CourseDescription courseDescription = descriptionMapper.selectById(courseId);
        Course course = courseMapper.selectCourseById(courseId);
        if(courseDescription == null || course == null ){
            throw new  MyException(20001,"查询课程信息异常");
        }
        CourseVo courseVo = new CourseVo();
        BeanUtils.copyProperties(course,courseVo);
        log.info(String.format("CourseServiceImpl#selectCourseAndCorseDesc#%s",course));
        BeanUtils.copyProperties(courseDescription,courseVo);
        log.info(String.format("CourseServiceImpl#selectCourseAndCorseDesc#%s",courseDescription));
        return  courseVo;

    }

    @Override
    @Transactional
    public void updateCourseAndCorseDesc(CourseVo courseVo) {
        CourseDescription courseDescription = new CourseDescription();
        BeanUtils.copyProperties(courseVo,courseDescription);
        Course course =new Course();
        BeanUtils.copyProperties(courseVo,course);
        course.setId(courseDescription.getId());
        int i = baseMapper.updateById(course);
        if(i ==0){
            throw  new MyException(200001,"修改信息异常");
        }
        descriptionMapper.updateById(courseDescription);
    }

    @Override
    @Transactional
    public String saveCourseAndCorseDesc(CourseVo courseVo) {
        Course course =new Course();
        BeanUtils.copyProperties(courseVo,course);
        //1.保存到课程表中
        int insert =courseMapper.insert(course);
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
    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        CourseInfoVo courseInfo = courseMapper.getCourseInfo(courseId);
        return courseInfo;

    }
}
