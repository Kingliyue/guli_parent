package com.atliyue.edu.service.impl;

import com.atliyue.edu.entity.Course;
import com.atliyue.edu.entity.CourseDescription;
import com.atliyue.edu.entity.Video;
import com.atliyue.edu.fegin.EduVodClient;
import com.atliyue.edu.mapper.ChapterMapper;
import com.atliyue.edu.mapper.CourseDescriptionMapper;
import com.atliyue.edu.mapper.CourseMapper;
import com.atliyue.edu.mapper.VideoMapper;
import com.atliyue.edu.service.CourseService;
import com.atliyue.edu.vo.CourseFrontVo;
import com.atliyue.edu.vo.CourseVo;
import com.atliyue.edu.vo.CourseWebVo;
import com.atliyue.entity.CourseInfoVo;
import com.atliyue.exception.MyException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Resource
    private CourseDescriptionMapper descriptionMapper;
    @Autowired
    @Resource
    private CourseMapper courseMapper;
    @Autowired
    @Resource
    private ChapterMapper chapterMapper;
    @Autowired
    @Resource
    private VideoMapper videoMapper;

    @Autowired
    private EduVodClient eduVodClient;

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
    @Override
    @Transactional
    public void deleteCourse(String courseId) {
        Map<String,Object> map=new HashMap<>();
        map.put("course_id",courseId);
        //1.删除课程的course表中的信息
        courseMapper.deleteById(courseId);
        //2.删除课程描述表中的信息
        descriptionMapper.deleteById(courseId);
        //3.删除章节表中的信息
        chapterMapper.deleteByMap(map);
        //4.删除video
        List<Video> videos = videoMapper.selectByMap(map);
        videoMapper.deleteByMap(map);
        //5删除远程的视频
        List list = new ArrayList();
        if(videos != null){
            for(int i = 0;i<videos.size();i++)
                list.add(videos.get(i).getVideoSourceId());
        }
        eduVodClient.deleteVideo(list);
    }
    @Override
    @Cacheable(value = "getCourseList",key = "'course'")
    public List getCourseList() {
        List courseList = courseMapper.getCourseList();
        return courseList;
    }

    @Override
    public List<Course> queryCourse(String teacherId) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teacher_Id",teacherId);
        queryWrapper.orderByDesc(true,"view_count");
        queryWrapper.last("limit 4");
        List<Course> courses = courseMapper.selectList(queryWrapper);
        return courses;
    }

    @Override
    public Map<String, Object> getCourseListPage(long page, long size, CourseFrontVo courseFrontVo) {
        IPage iPage = new Page(page,size);
        QueryWrapper queryWrapper = new QueryWrapper();
        if(courseFrontVo!= null&& StringUtils.isNotBlank(courseFrontVo.getSubjectParentId())){
            queryWrapper.eq("subject_parent_id",courseFrontVo.getSubjectParentId());
        }
        if(courseFrontVo!= null&& StringUtils.isNotBlank(courseFrontVo.getSubjectId())){
            queryWrapper.eq("subject_id",courseFrontVo.getSubjectId());
        }
        IPage selectPage = courseMapper.selectPage(iPage, queryWrapper);
        Map<String,Object> map = new HashMap<>(16);
        if(selectPage !=null){
            map.put("total",selectPage.getTotal());
            map.put("records",selectPage.getRecords());
            map.put("current",selectPage.getCurrent());
            map.put("size",selectPage.getSize());
            map.put("pages",selectPage.getPages());
        }

        return map;
    }

    @Override
    public CourseWebVo getCourseInfoWeb(String courseId) {
        return    courseMapper.getCourseInfoWeb(courseId);
    }
}
