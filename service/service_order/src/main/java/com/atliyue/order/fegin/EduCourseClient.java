package com.atliyue.order.fegin;

import com.atliyue.entity.CourseInfoVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@FeignClient(value = "service-edu")
@RequestMapping("/edu/course/")
public interface EduCourseClient {
    @GetMapping("getCourseInfoFegin/{courseId}")
    CourseInfoVo getCourseInfoByCourseId(@PathVariable("courseId") String courseId);
}
