package com.ccut.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ccut.eduservice.entity.EduSubject;
import com.ccut.eduservice.entity.excel.SubjectData;
import com.ccut.eduservice.entity.subject.OneSubject;
import com.ccut.eduservice.entity.subject.TwoSubject;
import com.ccut.eduservice.listener.SubjectExcelListener;
import com.ccut.eduservice.mapper.EduSubjectMapper;
import com.ccut.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-03-23
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    //添加课程分类
    @Override
    public void saveSubject(MultipartFile file,EduSubjectService subjectService) {

        try {
            //文件输入流
            InputStream in = file.getInputStream();
            //调用方法读取
            EasyExcel.read(in, SubjectData.class,new SubjectExcelListener(subjectService)).sheet().doRead();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //课程分类列表（树形）
    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        //1、查询所有的一级分类 parent_id=0
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq( "parent_id","0" );
        List<EduSubject> oneSubjects = baseMapper.selectList( wrapper );
        //2、查询所有的二级分类
        QueryWrapper<EduSubject> wrappertwo = new QueryWrapper<>();
        wrappertwo.ne( "parent_id","0" );
        List<EduSubject> twoSubjects = baseMapper.selectList( wrappertwo );
        //创建一个集合，用于存储最终的数据
        List<OneSubject> finalSubjectList = new ArrayList<>();

        //3、封装一级分类
        for (int i = 0; i < oneSubjects.size(); i++) {
            EduSubject eduSubject = oneSubjects.get( i );
            OneSubject oneSubject = new OneSubject();
            BeanUtils.copyProperties( eduSubject,oneSubject );
            finalSubjectList.add( oneSubject );
            //在一级分类里面获取二级分类

        //4、封装二级分类
        ArrayList<TwoSubject> twoFinalSubjectList = new ArrayList<>();

            for (int m = 0; m < twoSubjects.size(); m++) {
            EduSubject tSubject = twoSubjects.get( m );
            if (tSubject.getParentId().equals( oneSubject.getId())){
                TwoSubject twoSubject = new TwoSubject();
                BeanUtils.copyProperties( tSubject,twoSubject );
                twoFinalSubjectList.add( twoSubject );
            }
        }

        oneSubject.setChildren( twoFinalSubjectList );
        }
        return finalSubjectList;
    }
}
