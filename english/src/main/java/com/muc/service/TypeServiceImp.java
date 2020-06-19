package com.muc.service;

import com.muc.bean.TestType;
import com.muc.bean.YearType;
import com.muc.mapper.TestTypeMapper;
import com.muc.mapper.TestWordCountMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class TypeServiceImp implements TypeService {
    @Resource
    TestTypeMapper testTypeMapper;
    @Resource
    TestWordCountMapper testWordCountMapper;
    @Override
    public ArrayList<TestType> getTestType() {
        return testTypeMapper.selectAll();
    }

    @Override
    public ArrayList<YearType> getYearType(Integer testType) {
        return testWordCountMapper.getYearType(testType);
    }
}
