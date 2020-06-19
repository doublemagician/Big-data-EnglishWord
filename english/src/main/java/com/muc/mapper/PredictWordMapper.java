package com.muc.mapper;

import com.muc.bean.PredictWord;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

import java.util.ArrayList;

public interface PredictWordMapper {
    @Delete({
        "delete from predict_word",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);
    @Delete({
            "delete from predict_word where word in (\"a\",\"b\",\"c\",\"d\",\"e\",\"f\",\"g\",\"h\",\"i\",\"j\",\"k\",\"l\",\"m\",\"n\",\"o\",\"p\",\"q\",\"r\",\"s\",\"t\",\"u\",\"v\",\"w\",\"x\",\"y\",\"z\",\"was\",\"been\",\"have\",\"has\",\"had\",\"ever\",\"about\",\"the\",\"a\",\"an\",\"will\",\"and\",\"after\",\"not\",\"only\",\"what\",\"where\",\"who\",\"when\",\"how\",\"can\",\n" +
                    "            \"do\",\"to\",\"out\",\"go\",\"us\",\"we\",\"our\",\"it\",\"you\",\"your\",\"yours\",\"theirs\",\"its\",\"of\",\"ours\",\"hers\",\"mine\",\"this\",\"that\",\"these\",\"those\",\"such\",\"which\",\n" +
                    "            \"whose\",\"whom\",\"as\",\"if\",\"many\",\"much\",\"eRach\",\"both\",\"either\",\"one\",\"none\",\"nothing\",\"other\",\"another\",\"then\",\"yourself\",\"myself\",\n" +
                    "            \"itself\",\"ourselves\",\"herself\",\"himself\",\"themselves\",\"be\",\"off\",\"good\",\"bad\",\"ok\",\"over\",\"so\",\"up\",\"too\",\"two\",\"three\",\"four\",\"five\",\"six\",\n" +
                    "            \"seven\",\"eight\",\"nine\",\"ten\",\"once\",\"would\",\"having\",\"doing\",\"just\",\"now\",\"does\",\"get\",\"could\",\"dont\",\"couldnt\",\"no\",\"wont\",\"in\",\"on\",\"her\",\"at\",\"she\")"
    })
    int deleteByWord();

    @Insert({
        "insert into predict_word (id, word, ",
        "probability, test_type)",
        "values (#{id,jdbcType=INTEGER}, #{word,jdbcType=VARCHAR}, ",
        "#{probability,jdbcType=DECIMAL}, #{testType,jdbcType=INTEGER})"
    })
    int insert(PredictWord record);

    @InsertProvider(type=PredictWordSqlProvider.class, method="insertSelective")
    int insertSelective(PredictWord record);

    @Select({
        "select",
        "id, word, probability, test_type",
        "from predict_word",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="word", property="word", jdbcType=JdbcType.VARCHAR),
        @Result(column="probability", property="probability", jdbcType=JdbcType.DECIMAL),
        @Result(column="test_type", property="testType", jdbcType=JdbcType.INTEGER)
    })
    PredictWord selectByPrimaryKey(Integer id);
    @Select({
            "select",
            "id, word, probability, test_type",
            "from predict_word",
            "where test_type = #{type,jdbcType=INTEGER} order by probability desc"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="word", property="word", jdbcType=JdbcType.VARCHAR),
            @Result(column="probability", property="probability", jdbcType=JdbcType.DECIMAL),
            @Result(column="test_type", property="testType", jdbcType=JdbcType.INTEGER)
    })
    ArrayList<PredictWord> selectByType(Integer type);

    @UpdateProvider(type=PredictWordSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(PredictWord record);

    @Update({
        "update predict_word",
        "set word = #{word,jdbcType=VARCHAR},",
          "probability = #{probability,jdbcType=DECIMAL},",
          "test_type = #{testType,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(PredictWord record);
}