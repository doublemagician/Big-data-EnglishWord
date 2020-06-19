package com.muc.service;

import com.muc.bean.TestType;
import com.muc.bean.YearType;

import java.util.ArrayList;

public interface TypeService {
public ArrayList<TestType> getTestType();
public ArrayList<YearType> getYearType(Integer testType);

}
