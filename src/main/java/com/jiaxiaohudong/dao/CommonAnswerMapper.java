package com.jiaxiaohudong.dao;

import com.jiaxiaohudong.entity.CommonAnswer;

public interface CommonAnswerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommonAnswer record);

    int insertSelective(CommonAnswer record);

    CommonAnswer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommonAnswer record);

    int updateByPrimaryKey(CommonAnswer record);
}