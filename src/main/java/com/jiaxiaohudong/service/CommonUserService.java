package com.jiaxiaohudong.service;

import com.jiaxiaohudong.entity.CommonUser;
import com.jiaxiaohudong.entity.Userinfo;
import com.jiaxiaohudong.util.R;

/**
 * Created by lcf12307 on 2018/10/28.
 */
public interface CommonUserService {
    R insert(CommonUser record);
    R update(CommonUser record);
    CommonUser selectByOpenId(String openid);
    CommonUser selectByPhone(long phone);
}
