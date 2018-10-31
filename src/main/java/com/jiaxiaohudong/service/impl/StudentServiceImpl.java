package com.jiaxiaohudong.service.impl;

import com.jiaxiaohudong.dao.CommonStudentMapper;
import com.jiaxiaohudong.entity.CommonStudent;
import com.jiaxiaohudong.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private CommonStudentMapper commonStudentMapper;
    public List<CommonStudent> getStudents(int tearcherID) {

        List<CommonStudent> commonStudents = commonStudentMapper.selectByTid(tearcherID);

        return commonStudents;

    }

    public List<CommonStudent> searchStudents(CommonStudent stu) {
        stu.setName("%"+stu.getName()+"%");
        List<CommonStudent> commonStudents = commonStudentMapper.selectByTidAndName(stu);
        return commonStudents;
    }
}
