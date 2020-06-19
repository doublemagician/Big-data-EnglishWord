package com.muc.service;

import com.muc.bean.TestWordCount;
import com.muc.bean.WordCloud;

import java.util.ArrayList;

public interface WordService {
    public ArrayList<WordCloud> getWordCloud(String year, String type,Integer limit);
    public int insert(TestWordCount testWordCount);
    public ArrayList<TestWordCount> wordCountPreYear(String word,Integer type);
    public int delete();
}
