package com.atliyue.order.fegin;

import com.atliyue.edu.vo.CourseInfoVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@FeignClient(value = "service-edu" )
@RequestMapping("/edu/course/")
public interface EduCourseClient {
    @GetMapping("/getCourse/{courseId}")
    public CourseInfoVo getCourseInfoByCourseId(@PathVariable("courseId") String courseId);
}
