package com.atliyue.edu.service.impl;

import com.atliyue.edu.entity.Chapter;
import com.atliyue.edu.mapper.ChapterMapper;
import com.atliyue.edu.mapper.ChapterVoMapper;
import com.atliyue.edu.mapper.VideoMapper;
import com.atliyue.edu.service.ChapterService;
import com.atliyue.edu.vo.ChapterVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author liYue
 * @since 2021-06-23
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Autowired
    private ChapterVoMapper chapterVoMapper;
    @Autowired
    private VideoMapper videoMapper;
    @Override
    public String saveChaperAndVideo(Chapter chapter) {
        baseMapper.insert(chapter);
        return null;
    }

    @Override
    public List<ChapterVo> getChapterAndVideoList(String courseId) {
        List<ChapterVo> chapterAndVideoList = chapterVoMapper.getChapterAndVideoList(courseId);
        return chapterAndVideoList;
    }

    @Override
    public String updateChapter(Chapter chapter) {
        baseMapper.updateById(chapter);
        return null;
    }
}
