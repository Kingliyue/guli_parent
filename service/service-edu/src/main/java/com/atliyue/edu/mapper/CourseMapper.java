package com.atliyue.edu.mapper;

import com.atliyue.edu.entity.Course;
import com.atliyue.edu.vo.CourseWebVo;
import com.atliyue.entity.CourseInfoVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author liYue
 * @since 2021-06-23
 */
public interface CourseMapper extends BaseMapper<Course> {
    Course selectCourseById(String courseId);

    CourseInfoVo getCourseInfo(String courseId);

    List getCourseList();
    //前台系统获取课程的基本信息
    CourseWebVo getCourseInfoWeb(String courseId);
}
