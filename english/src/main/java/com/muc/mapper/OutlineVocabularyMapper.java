package com.muc.mapper;

import com.muc.bean.OutlineVocabularyKey;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.ArrayList;

public interface OutlineVocabularyMapper {
    @Delete({
        "delete from outline_vocabulary",
        "where word = #{word,jdbcType=VARCHAR}",
          "and test_type = #{testType,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(OutlineVocabularyKey key);
    @Delete({
            "delete from outline_vocabulary where word in (\"a\",\"b\",\"c\",\"d\",\"e\",\"f\",\"g\",\"h\",\"i\",\"j\",\"k\",\"l\",\"m\",\"n\",\"o\",\"p\",\"q\",\"r\",\"s\",\"t\",\"u\",\"v\",\"w\",\"x\",\"y\",\"z\",\"was\",\"been\",\"have\",\"has\",\"had\",\"ever\",\"about\",\"the\",\"a\",\"an\",\"will\",\"and\",\"after\",\"not\",\"only\",\"what\",\"where\",\"who\",\"when\",\"how\",\"can\",\n" +
                    "            \"do\",\"to\",\"out\",\"go\",\"us\",\"we\",\"our\",\"it\",\"you\",\"your\",\"yours\",\"theirs\",\"its\",\"of\",\"ours\",\"hers\",\"mine\",\"this\",\"that\",\"these\",\"those\",\"such\",\"which\",\n" +
                    "            \"whose\",\"whom\",\"as\",\"if\",\"many\",\"much\",\"eRach\",\"both\",\"either\",\"one\",\"none\",\"nothing\",\"other\",\"another\",\"then\",\"yourself\",\"myself\",\n" +
                    "            \"itself\",\"ourselves\",\"herself\",\"himself\",\"themselves\",\"be\",\"off\",\"good\",\"bad\",\"ok\",\"over\",\"so\",\"up\",\"too\",\"two\",\"three\",\"four\",\"five\",\"six\",\n" +
                    "            \"seven\",\"eight\",\"nine\",\"ten\",\"once\",\"would\",\"having\",\"doing\",\"just\",\"now\",\"does\",\"get\",\"could\",\"dont\",\"couldnt\",\"no\",\"wont\",\"in\",\"on\",\"her\",\"at\",\"she\")"
    })
    int deleteByWord();

    @Insert({
        "insert into outline_vocabulary (word, test_type)",
        "values (#{word,jdbcType=VARCHAR}, #{testType,jdbcType=INTEGER})"
    })
    int insert(OutlineVocabularyKey record);

    @InsertProvider(type=OutlineVocabularySqlProvider.class, method="insertSelective")
    int insertSelective(OutlineVocabularyKey record);
    @Select("select * from outline_vocabulary where test_type =#{type,jdbcType=INTEGER} "
            )
    @Results(
            {
          @Result(column = "word",property = "word",jdbcType = JdbcType.VARCHAR),
            @Result(column ="test_type",property = "testType", jdbcType = JdbcType.INTEGER)
            }
    )
    ArrayList<OutlineVocabularyKey> selectByType(int type);
}