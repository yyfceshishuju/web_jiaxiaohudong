package com.jiaxiaohudong.dao;

import com.jiaxiaohudong.entity.CommonCategory;

import java.util.List;

public interface CommonCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommonCategory record);

    int insertSelective(CommonCategory record);

    CommonCategory selectByPrimaryKey(Integer id);

    List<CommonCategory> selectByType(Byte type);

    int updateByPrimaryKeySelective(CommonCategory record);

    int updateByPrimaryKey(CommonCategory record);
}