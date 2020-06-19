package com.muc.service;

import com.muc.bean.OutlineVocabularyKey;

import java.util.ArrayList;

public interface VocabularyService {
    public ArrayList<OutlineVocabularyKey> getVocabulary(int type);
    public int insert(OutlineVocabularyKey record);
    public int delete();
}
