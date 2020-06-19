package com.muc.service;

import com.muc.bean.TestWordCount;
import com.muc.bean.WordCloud;
import com.muc.mapper.TestWordCountMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class WordServiceImp implements WordService{
    @Resource
    TestWordCountMapper testWordCountMapper;
    @Override
    public ArrayList<WordCloud> getWordCloud(String year, String type,Integer limit) {
        return testWordCountMapper.selectWordCloud(year,type,limit);
    }

    @Override
    public int insert(TestWordCount testWordCount) {
        return testWordCountMapper.insert(testWordCount);
    }

    @Override
    public ArrayList<TestWordCount> wordCountPreYear(String word,Integer type) {
        return testWordCountMapper.selectByWord(word,type);
    }

    @Override
    public int delete() {
        return testWordCountMapper.deleteByword();
    }
}
