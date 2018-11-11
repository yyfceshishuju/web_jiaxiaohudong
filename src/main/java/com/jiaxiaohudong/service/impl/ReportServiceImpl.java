package com.jiaxiaohudong.service.impl;

import com.jiaxiaohudong.dao.CommonReportMapper;
import com.jiaxiaohudong.entity.CommonReport;
import com.jiaxiaohudong.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    CommonReportMapper commonReportMapper;
    public List<CommonReport> showALl() {
        return commonReportMapper.selectAll();
    }
}
