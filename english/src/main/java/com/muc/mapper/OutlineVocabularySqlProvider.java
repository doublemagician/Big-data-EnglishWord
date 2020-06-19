package com.muc.mapper;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;

import com.muc.bean.OutlineVocabularyKey;

public class OutlineVocabularySqlProvider {

    public String insertSelective(OutlineVocabularyKey record) {
        BEGIN();
        INSERT_INTO("outline_vocabulary");
        
        if (record.getWord() != null) {
            VALUES("word", "#{word,jdbcType=VARCHAR}");
        }
        
        if (record.getTestType() != null) {
            VALUES("test_type", "#{testType,jdbcType=INTEGER}");
        }
        
        return SQL();
    }
}