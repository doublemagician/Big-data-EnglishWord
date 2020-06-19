package com.muc.mapper;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.muc.bean.TestWordCount;

public class TestWordCountSqlProvider {

    public String insertSelective(TestWordCount record) {
        BEGIN();
        INSERT_INTO("test_word_count");
        
        if (record.getId() != null) {
            VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getWord() != null) {
            VALUES("word", "#{word,jdbcType=VARCHAR}");
        }
        
        if (record.getCount() != null) {
            VALUES("count", "#{count,jdbcType=INTEGER}");
        }
        
        if (record.getTestType() != null) {
            VALUES("test_type", "#{testType,jdbcType=INTEGER}");
        }
        
        if (record.getTestTime() != null) {
            VALUES("test_time", "#{testTime,jdbcType=VARCHAR}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(TestWordCount record) {
        BEGIN();
        UPDATE("test_word_count");
        
        if (record.getWord() != null) {
            SET("word = #{word,jdbcType=VARCHAR}");
        }
        
        if (record.getCount() != null) {
            SET("count = #{count,jdbcType=INTEGER}");
        }
        
        if (record.getTestType() != null) {
            SET("test_type = #{testType,jdbcType=INTEGER}");
        }
        
        if (record.getTestTime() != null) {
            SET("test_time = #{testTime,jdbcType=VARCHAR}");
        }
        
        WHERE("id = #{id,jdbcType=INTEGER}");
        
        return SQL();
    }
}