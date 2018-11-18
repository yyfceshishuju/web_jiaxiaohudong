package com.jiaxiaohudong.service.impl;

import com.jiaxiaohudong.dao.CommonBaiduUserMapper;
import com.jiaxiaohudong.entity.CommonBaiduUser;
import com.jiaxiaohudong.service.BaiduUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaiduUserServiceImpl implements BaiduUserService {

    @Autowired
    private CommonBaiduUserMapper commonBaiduUserMapper;

    public List<CommonBaiduUser> getAll() {
        return commonBaiduUserMapper.selectAll();
    }
}
