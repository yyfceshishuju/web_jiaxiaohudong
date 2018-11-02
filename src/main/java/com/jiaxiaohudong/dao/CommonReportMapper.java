package com.jiaxiaohudong.dao;

import com.jiaxiaohudong.entity.CommonReport;

public interface CommonReportMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommonReport record);

    int insertSelective(CommonReport record);

    CommonReport selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommonReport record);

    int updateByPrimaryKey(CommonReport record);
}