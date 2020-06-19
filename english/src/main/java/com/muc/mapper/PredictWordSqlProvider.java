package com.muc.mapper;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.muc.bean.PredictWord;

public class PredictWordSqlProvider {

    public String insertSelective(PredictWord record) {
        BEGIN();
        INSERT_INTO("predict_word");
        
        if (record.getId() != null) {
            VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getWord() != null) {
            VALUES("word", "#{word,jdbcType=VARCHAR}");
        }
        
        if (record.getProbability() != null) {
            VALUES("probability", "#{probability,jdbcType=DECIMAL}");
        }
        
        if (record.getTestType() != null) {
            VALUES("test_type", "#{testType,jdbcType=INTEGER}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(PredictWord record) {
        BEGIN();
        UPDATE("predict_word");
        
        if (record.getWord() != null) {
            SET("word = #{word,jdbcType=VARCHAR}");
        }
        
        if (record.getProbability() != null) {
            SET("probability = #{probability,jdbcType=DECIMAL}");
        }
        
        if (record.getTestType() != null) {
            SET("test_type = #{testType,jdbcType=INTEGER}");
        }
        
        WHERE("id = #{id,jdbcType=INTEGER}");
        
        return SQL();
    }
}