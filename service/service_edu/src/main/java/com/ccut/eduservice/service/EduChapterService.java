package com.ccut.eduservice.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.ccut.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ccut.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-04-19
 */
public interface EduChapterService extends IService<EduChapter> {


    //课程大纲列表,根据课程id进行查询
    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    //删除章节的方法
    boolean deleteChapter(String chapterId);
}
