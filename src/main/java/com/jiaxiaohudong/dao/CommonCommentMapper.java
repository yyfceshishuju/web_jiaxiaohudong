package com.jiaxiaohudong.dao;

import com.jiaxiaohudong.entity.CommonComment;

public interface CommonCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommonComment record);

    int insertSelective(CommonComment record);

    CommonComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommonComment record);

    int updateByPrimaryKey(CommonComment record);
}