package com.jiaxiaohudong.dao;

import com.jiaxiaohudong.entity.CommonStudent;

import java.util.List;

public interface CommonStudentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommonStudent record);

    int insertSelective(CommonStudent record);

    CommonStudent selectByPrimaryKey(Integer id);

    CommonStudent selectByStudentId(String stuId);

    CommonStudent selectByStudentIdAPhNum(String stuId, Long phNum);

    List<CommonStudent> selectByPid(Integer pid);

    int updateByPrimaryKeySelective(CommonStudent record);

    int updateByPrimaryKey(CommonStudent record);
}