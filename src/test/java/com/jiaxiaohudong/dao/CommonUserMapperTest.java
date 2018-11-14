package com.jiaxiaohudong.dao;

import com.jiaxiaohudong.entity.CommonUser;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class CommonUserMapperTest {

    @Autowired
    CommonUserMapper commonUserMapper;
    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
    }

    @Test
    public void insertSelective() {
    }

    @Test
    public void selectByPrimaryKey() {
    }

    @Test
    public void selectByPhone() {
    }

    @Test
    public void selectByOpenId() {
    }

    @Test
    public void updateByPrimaryKeySelective() {
        CommonUser user = new CommonUser();
        user.setOpenid("123");
        user.setId(678687234);
        commonUserMapper.selectByPrimaryKey(user.getId());
    }

    @Test
    public void updateByPrimaryKey() {
    }
}