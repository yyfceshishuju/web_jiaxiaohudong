package com.jiaxiaohudong.dao;

import com.jiaxiaohudong.entity.CommonQuestion;

public interface CommonQuestionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommonQuestion record);

    int insertSelective(CommonQuestion record);

    CommonQuestion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommonQuestion record);

    int updateByPrimaryKey(CommonQuestion record);
}