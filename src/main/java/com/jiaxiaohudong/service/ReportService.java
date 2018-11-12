package com.jiaxiaohudong.service;

import com.jiaxiaohudong.entity.CommonReport;

import java.util.List;

public interface ReportService {
    List<CommonReport> showALl();
    CommonReport selectById(Long sid, Long time);
}
