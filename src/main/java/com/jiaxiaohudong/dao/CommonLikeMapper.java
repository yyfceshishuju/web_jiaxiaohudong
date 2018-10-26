package com.jiaxiaohudong.dao;

import com.jiaxiaohudong.entity.CommonLike;

public interface CommonLikeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommonLike record);

    int insertSelective(CommonLike record);

    CommonLike selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommonLike record);

    int updateByPrimaryKey(CommonLike record);
}