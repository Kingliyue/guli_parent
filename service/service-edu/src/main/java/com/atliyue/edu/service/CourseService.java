package com.atliyue.edu.service;

import com.atliyue.edu.entity.Course;
import com.atliyue.edu.vo.CourseVo;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
