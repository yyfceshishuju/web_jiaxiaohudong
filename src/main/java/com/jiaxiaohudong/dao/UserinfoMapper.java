package com.jiaxiaohudong.dao;

import com.jiaxiaohudong.entity.Userinfo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UserinfoMapper {
    int insert(Userinfo record);

    ArrayList<Userinfo> selectSelective(Userinfo record);
}