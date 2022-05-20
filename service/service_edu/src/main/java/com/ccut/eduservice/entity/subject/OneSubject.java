package com.ccut.eduservice.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OneSubject {

    private String id;
    private String title;

    //一个一级分类有多个二级分类集合表示
    private List<TwoSubject> children = new ArrayList<>();


}
