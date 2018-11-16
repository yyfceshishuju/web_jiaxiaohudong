package com.jiaxiaohudong.baidu_service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.BlockingQueue;

public class ImageTask implements Task {
    @Autowired
    private BlockingQueue<QuestionAndImage> questionAndImages;

    public void put(QuestionAndImage questionAndImage) throws InterruptedException {
        questionAndImages.put(questionAndImage);
    }


    public QuestionAndImage get() throws InterruptedException{
        return questionAndImages.take();
    }
    public ImageTask(BlockingQueue<QuestionAndImage> questionAndImages){
        this.questionAndImages = questionAndImages;
    }
}
