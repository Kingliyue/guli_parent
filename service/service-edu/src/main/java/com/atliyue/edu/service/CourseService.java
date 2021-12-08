package com.atliyue.edu.service;

import com.atliyue.edu.entity.Course;
import com.atliyue.edu.vo.CourseFrontVo;
import com.atliyue.edu.vo.CourseVo;
import com.atliyue.edu.vo.CourseWebVo;
import com.atliyue.entity.CourseInfoVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

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


    List<Course> queryCourse(String teacherId);

    Map<String, Object> getCourseListPage(long page, long size, CourseFrontVo courseFrontVo);


    CourseWebVo getCourseInfoWeb(String courseId);
}
