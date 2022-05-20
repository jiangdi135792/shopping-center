package com.ccut.eduservice.mapper;

import com.ccut.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ccut.eduservice.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2021-04-19
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
public CoursePublishVo getPublishCourseInfo(String courseId);
}
