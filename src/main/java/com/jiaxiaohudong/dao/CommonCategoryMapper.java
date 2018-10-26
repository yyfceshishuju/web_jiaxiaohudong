package com.jiaxiaohudong.dao;

import com.jiaxiaohudong.entity.CommonCategory;

public interface CommonCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommonCategory record);

    int insertSelective(CommonCategory record);

    CommonCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommonCategory record);

    int updateByPrimaryKey(CommonCategory record);
}