package com.muc.bean;

import java.io.Serializable;

public class OutlineVocabularyKey implements Serializable {
    private String word;

    private Integer testType;

    private static final long serialVersionUID = 1L;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word == null ? null : word.trim();
    }

    public Integer getTestType() {
        return testType;
    }

    public void setTestType(Integer testType) {
        this.testType = testType;
    }
}