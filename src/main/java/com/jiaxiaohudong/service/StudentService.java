package com.jiaxiaohudong.service;

import com.jiaxiaohudong.entity.CommonStudent;
import com.jiaxiaohudong.util.R;

import java.util.List;

public interface StudentService {
    R insert(CommonStudent record);

    List<CommonStudent> getStudents(int tearcherID);

    List<CommonStudent> searchStudents(CommonStudent stu);

    List<CommonStudent> searchStudentsByNameAPhone(CommonStudent stu);

    CommonStudent searchStrudent(Integer id);
}
