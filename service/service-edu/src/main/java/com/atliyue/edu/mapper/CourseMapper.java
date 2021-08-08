package com.atliyue.edu.mapper;

import com.atliyue.edu.entity.Course;
import com.atliyue.edu.vo.CourseInfoVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

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

}
