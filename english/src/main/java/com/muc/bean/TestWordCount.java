package com.muc.bean;

import java.io.Serializable;

public class TestWordCount implements Serializable {
    private Integer id;

    private String word;

    private Integer count;

    private Integer testType;

    private String testTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word == null ? null : word.trim();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getTestType() {
        return testType;
    }

    public void setTestType(Integer testType) {
        this.testType = testType;
    }

    public String getTestTime() {
        return testTime;
    }

    public TestWordCount(String word, Integer count, Integer testType, String testTime) {
        this.word = word;
        this.count = count;
        this.testType = testType;
        this.testTime = testTime;
    }
    public TestWordCount()
    {}
    public void setTestTime(String testTime) {
        this.testTime = testTime == null ? null : testTime.trim();
    }
}