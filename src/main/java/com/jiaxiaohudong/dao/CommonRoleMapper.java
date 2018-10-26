package com.jiaxiaohudong.dao;

import com.jiaxiaohudong.entity.CommonRole;

public interface CommonRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommonRole record);

    int insertSelective(CommonRole record);

    CommonRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommonRole record);

    int updateByPrimaryKey(CommonRole record);
}