package com.jiaxiaohudong.baidu_service;

import com.jiaxiaohudong.entity.CommonQuestion;
import org.springframework.beans.factory.annotation.Autowired;


public class QuestionAndImage {
    @Autowired
    private String imagePath;
    @Autowired
    private CommonQuestion question;

    public CommonQuestion getQuestion() {
        return question;
    }

    public void setQuestion(CommonQuestion question) {
        this.question = question;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    public QuestionAndImage(String imagePath, CommonQuestion question){
        this.imagePath = imagePath;
        this.question = question;
    }
}
