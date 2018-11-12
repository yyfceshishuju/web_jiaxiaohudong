package com.jiaxiaohudong.dao;

import com.jiaxiaohudong.entity.CommonReport;

import java.util.List;

public interface CommonReportMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommonReport record);

    int insertSelective(CommonReport record);

    CommonReport selectByPrimaryKey(Integer id);

    List<CommonReport> selectByUidAStuid(Integer uid, Integer stuid);

    List<CommonReport> selectByUidAStuidAYear(Integer uid, Integer stuid, Integer year);

    int updateByPrimaryKeySelective(CommonReport record);

    int updateByPrimaryKey(CommonReport record);

    List<CommonReport> selectAll();

    List<CommonReport> selectByTid(Integer tid);

    CommonReport selectBySid(Long sid, Long thisTime);

}