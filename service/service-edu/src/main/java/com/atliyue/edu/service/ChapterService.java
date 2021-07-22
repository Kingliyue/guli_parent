package com.atliyue.edu.service;

import com.atliyue.edu.entity.Chapter;
import com.atliyue.edu.vo.ChapterVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author liYue
 * @since 2021-06-23
 */
public interface ChapterService extends IService<Chapter> {
    List<ChapterVo> getChapterAndVideoList(String courseId);

    String  saveChapter(Chapter chapter);

    String updateChapter(Chapter chapter);

    Chapter getChapter(String chapterId);


}
