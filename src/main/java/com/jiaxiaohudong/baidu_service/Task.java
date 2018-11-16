package com.jiaxiaohudong.baidu_service;

public interface Task {
    void put(QuestionAndImage questionAndImage) throws InterruptedException;
    QuestionAndImage get() throws InterruptedException;
}
