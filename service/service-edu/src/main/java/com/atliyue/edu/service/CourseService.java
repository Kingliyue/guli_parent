package com.atliyue.edu.service;

import com.atliyue.edu.entity.Course;
import com.atliyue.edu.vo.CourseInfoVo;
import com.atliyue.edu.vo.CourseVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.awt.*;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author liYue
 * @since 2021-06-23
 */
public interface CourseService extends IService<Course> {

    String  saveCourseAndCorseDesc(CourseVo courseVo);

    CourseVo selectCourseAndCorseDesc(String courseId);

    void updateCourseAndCorseDesc(CourseVo courseVo);

    Course selectCourse(String courseId);

    CourseInfoVo getCourseInfo(String courseId);
    //
    void deleteCourse(String courseId);

    List getCourseList();
    //获取课程的全部信息

}
