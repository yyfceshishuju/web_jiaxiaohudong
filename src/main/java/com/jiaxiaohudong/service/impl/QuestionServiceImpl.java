package com.jiaxiaohudong.service.impl;

import com.jiaxiaohudong.dao.CommonQuestionMapper;
import com.jiaxiaohudong.entity.CommonQuestion;
import com.jiaxiaohudong.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private CommonQuestionMapper commonQuestionMapper;

    public int removerByPrimaryKey(Integer id) {
        return 0;
    }

    public int add(CommonQuestion record) {
        int insert = commonQuestionMapper.insert(record);
        return insert;
    }

    public int addSelective(CommonQuestion record) {
        return 0;
    }

    public CommonQuestion searchByPrimaryKey(Integer id) {
        return commonQuestionMapper.selectByPrimaryKey(id);
    }

    public int modifyByPrimaryKeySelective(CommonQuestion record) {
        return 0;
    }

    public int modifyByPrimaryKey(CommonQuestion record) {
        return 0;
    }

    public List<CommonQuestion> searchByCommonQuestion(CommonQuestion req) {
        List<CommonQuestion> commonQuestions = commonQuestionMapper.selectByCommonQuestion(req);
        return commonQuestions;
    }

    public List<CommonQuestion> searchByPage(Integer sid, Integer start, Integer pageSize) {
        return  commonQuestionMapper.selectByPage(sid, start, pageSize);
    }
}
