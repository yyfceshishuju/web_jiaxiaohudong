package com.jiaxiaohudong.dao;

import com.jiaxiaohudong.entity.CommonUser;

public interface CommonUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommonUser record);

    int insertSelective(CommonUser record);

    CommonUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommonUser record);

    int updateByPrimaryKey(CommonUser record);
}