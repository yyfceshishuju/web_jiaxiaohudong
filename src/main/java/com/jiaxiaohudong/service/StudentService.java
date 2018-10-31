package com.jiaxiaohudong.service;

import com.jiaxiaohudong.entity.CommonStudent;

import java.util.List;

public interface StudentService {
    List<CommonStudent> getStudents(int tearcherID);

    List<CommonStudent> searchStudents(CommonStudent stu);
}
