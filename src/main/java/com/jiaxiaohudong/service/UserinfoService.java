package com.jiaxiaohudong.service;

import com.jiaxiaohudong.entity.Userinfo;

import java.util.ArrayList;

/**
 * Created by yyf on 2018/10/17.
 */
public interface UserinfoService {
    int insert(Userinfo record);

    ArrayList<Userinfo> selectSelective(Userinfo record);
}
