package com.jiaxiaohudong.dao;

import com.jiaxiaohudong.entity.CommonRepository;

public interface CommonRepositoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommonRepository record);

    int insertSelective(CommonRepository record);

    CommonRepository selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommonRepository record);

    int updateByPrimaryKey(CommonRepository record);
}