package com.jiaxiaohudong.dao;

import com.jiaxiaohudong.entity.CommonClass;

public interface CommonClassMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommonClass record);

    int insertSelective(CommonClass record);

    CommonClass selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommonClass record);

    int updateByPrimaryKey(CommonClass record);
}