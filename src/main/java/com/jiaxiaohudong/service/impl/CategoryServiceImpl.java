package com.jiaxiaohudong.service.impl;

import com.jiaxiaohudong.dao.CommonCategoryMapper;
import com.jiaxiaohudong.entity.CommonCategory;
import com.jiaxiaohudong.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    public CommonCategoryMapper commonCategoryMapper;

    public CommonCategory searchById(Integer id) {
        return commonCategoryMapper.selectByPrimaryKey(id);
    }
}
