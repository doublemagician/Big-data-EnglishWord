package com.muc.service;

import com.muc.bean.OutlineVocabularyKey;
import com.muc.mapper.OutlineVocabularyMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
@Service
public class VocabularyServiceImp implements VocabularyService{
    @Resource
    OutlineVocabularyMapper vocabularyMapper;
    @Override
    public ArrayList<OutlineVocabularyKey> getVocabulary(int type) {
        return vocabularyMapper.selectByType(type);
    }

    @Override
    public int insert(OutlineVocabularyKey record) {
        return vocabularyMapper.insert(record);
    }

    @Override
    public int delete() {
        return vocabularyMapper.deleteByWord();
    }
}
