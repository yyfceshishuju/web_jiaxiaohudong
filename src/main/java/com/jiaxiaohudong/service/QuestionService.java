package com.jiaxiaohudong.service;

import com.jiaxiaohudong.entity.CommonQuestion;

import java.util.List;

public interface QuestionService {
    int removerByPrimaryKey(Integer id);

    int add(CommonQuestion record);

    int addSelective(CommonQuestion record);

    CommonQuestion searchByPrimaryKey(Integer id);

    int modifyByPrimaryKeySelective(CommonQuestion record);

    int modifyByPrimaryKey(CommonQuestion record);

    List<CommonQuestion> searchByCommonQuestion(CommonQuestion req);

    List<CommonQuestion> searchByPage(Integer sid, Integer start, Integer pageSize);
}
