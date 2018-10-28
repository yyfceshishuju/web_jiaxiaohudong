package com.jiaxiaohudong.service.impl;

import com.jiaxiaohudong.dao.CommonUserMapper;
import com.jiaxiaohudong.entity.CommonUser;
import com.jiaxiaohudong.service.CommonUserService;
import com.jiaxiaohudong.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lcf12307 on 2018/10/28.
 */
@Service
public class CommonUserServiceImpl implements CommonUserService {
    @Autowired
    private CommonUserMapper mapper;

    public R insert(CommonUser record) {
        return null;
    }

    public R selectByOpenId(String openid) {
        return null;
    }

    public R selectByPhone(long phone) {
        return null;
    }
}
