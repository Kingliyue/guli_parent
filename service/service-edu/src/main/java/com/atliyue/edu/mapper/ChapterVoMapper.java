package com.atliyue.edu.mapper;

import com.atliyue.edu.vo.ChapterVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author liYue
 * @since 2021-06-23
 */
public interface ChapterVoMapper extends BaseMapper<ChapterVo> {

    List<ChapterVo> getChapterAndVideoList();
}
