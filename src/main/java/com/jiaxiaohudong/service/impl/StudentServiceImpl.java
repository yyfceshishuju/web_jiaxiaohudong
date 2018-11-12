package com.jiaxiaohudong.service.impl;

import com.jiaxiaohudong.dao.CommonStudentMapper;
import com.jiaxiaohudong.entity.CommonStudent;
import com.jiaxiaohudong.service.StudentService;
import com.jiaxiaohudong.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private CommonStudentMapper commonStudentMapper;

    public R insert(CommonStudent record){
        R result = new R();
        result.put("msg", commonStudentMapper.insert(record));
        return result;
    }

    public List<CommonStudent> getStudents(int tearcherID) {

        List<CommonStudent> commonStudents = commonStudentMapper.selectByTid(tearcherID);

        return commonStudents;

    }

    public List<CommonStudent> searchStudents(CommonStudent stu) {
        stu.setName("%"+stu.getName()+"%");
        List<CommonStudent> commonStudents = commonStudentMapper.selectByTidAndName(stu);
        return commonStudents;
    }

    public List<CommonStudent> searchStudentsByNameAPhone(CommonStudent stu) {
        return commonStudentMapper.selectByTidAndNameAndPhone(stu);
    }

    public CommonStudent searchStrudent(Integer id) {
        return commonStudentMapper.selectByPrimaryKey(id);
    }


}
