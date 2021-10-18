package com.atliyue.edu.service.impl;

import com.atliyue.edu.entity.Teacher;
import com.atliyue.edu.mapper.TeacherMapper;
import com.atliyue.edu.service.TeacherService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author liYue
 * @since 2021-04-01
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
   @Autowired
   @Resource
    private  TeacherMapper teacherMapper;
    @Override
    @Cacheable(value = "getTeacherList",key = "'teacher'")
    public List getTeacherList() {
        return teacherMapper.getTeacherList();
    }

    @Override
    public Map<String, Object> getTeacherListPage(long page, long size) {
        IPage iPage = new Page(page, size);
        IPage selectPage = teacherMapper.selectPage(iPage, null);
        Map<String,Object> map = new HashMap<>();
        map.put("total",selectPage.getTotal());
        map.put("records",selectPage.getRecords());
        map.put("current",selectPage.getCurrent());
        map.put("size",selectPage.getSize());
        map.put("pages",selectPage.getPages());

        return map;
    }
}
