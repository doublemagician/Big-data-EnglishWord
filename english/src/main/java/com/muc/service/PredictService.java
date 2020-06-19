package com.muc.service;

import com.muc.bean.OutlineVocabularyKey;
import com.muc.bean.PredictWord;

import java.util.ArrayList;

public interface PredictService {
    public int pridict(ArrayList<OutlineVocabularyKey> words,int type);
    public ArrayList<PredictWord> getPredcitWord(int type);
    public int delete();
}
