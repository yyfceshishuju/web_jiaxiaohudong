package com.jiaxiaohudong.dao;

import com.jiaxiaohudong.entity.CommonDivision;

public interface CommonDivisionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommonDivision record);

    int insertSelective(CommonDivision record);

    CommonDivision selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommonDivision record);

    int updateByPrimaryKey(CommonDivision record);
}