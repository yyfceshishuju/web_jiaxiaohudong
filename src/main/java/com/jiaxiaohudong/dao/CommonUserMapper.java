package com.jiaxiaohudong.dao;

import com.jiaxiaohudong.entity.CommonUser;

public interface CommonUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommonUser record);

    int insertSelective(CommonUser record);

    CommonUser selectByPrimaryKey(Integer id);

    CommonUser selectByPhone(Long phone);

    CommonUser selectByOpenId(Long phone);

    int updateByPrimaryKeySelective(CommonUser record);

    int updateByPrimaryKey(CommonUser record);
}