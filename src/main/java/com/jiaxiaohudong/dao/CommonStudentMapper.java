package com.jiaxiaohudong.dao;

import com.jiaxiaohudong.entity.CommonStudent;

public interface CommonStudentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommonStudent record);

    int insertSelective(CommonStudent record);

    CommonStudent selectByPrimaryKey(Integer id);

    CommonStudent selectByStudentId(String stuId);

    int updateByPrimaryKeySelective(CommonStudent record);

    int updateByPrimaryKey(CommonStudent record);
}