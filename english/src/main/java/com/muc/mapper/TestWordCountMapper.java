package com.muc.mapper;

import com.muc.bean.TestWordCount;
import com.muc.bean.WordCloud;
import com.muc.bean.YearType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface TestWordCountMapper {
    @Delete({
        "delete from test_word_count",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);
    @Delete({
            "delete from test_word_count where word in (\"a\",\"b\",\"c\",\"d\",\"e\",\"f\",\"g\",\"h\",\"i\",\"j\",\"k\",\"l\",\"m\",\"n\",\"o\",\"p\",\"q\",\"r\",\"s\",\"t\",\"u\",\"v\",\"w\",\"x\",\"y\",\"z\",\"was\",\"been\",\"have\",\"has\",\"had\",\"ever\",\"about\",\"the\",\"a\",\"an\",\"will\",\"and\",\"after\",\"not\",\"only\",\"what\",\"where\",\"who\",\"when\",\"how\",\"can\",\n" +
                    "            \"do\",\"to\",\"out\",\"go\",\"us\",\"we\",\"our\",\"it\",\"you\",\"your\",\"yours\",\"theirs\",\"its\",\"of\",\"ours\",\"hers\",\"mine\",\"this\",\"that\",\"these\",\"those\",\"such\",\"which\",\n" +
                    "            \"whose\",\"whom\",\"as\",\"if\",\"many\",\"much\",\"eRach\",\"both\",\"either\",\"one\",\"none\",\"nothing\",\"other\",\"another\",\"then\",\"yourself\",\"myself\",\n" +
                    "            \"itself\",\"ourselves\",\"herself\",\"himself\",\"themselves\",\"be\",\"off\",\"good\",\"bad\",\"ok\",\"over\",\"so\",\"up\",\"too\",\"two\",\"three\",\"four\",\"five\",\"six\",\n" +
                    "            \"seven\",\"eight\",\"nine\",\"ten\",\"once\",\"would\",\"having\",\"doing\",\"just\",\"now\",\"does\",\"get\",\"could\",\"dont\",\"couldnt\",\"no\",\"wont\",\"in\",\"on\",\"her\",\"at\",\"she\")"
    })
    int deleteByword();

    @Insert({
        "insert into test_word_count (id, word, ",
        "count, test_type, ",
        "test_time)",
        "values (#{id,jdbcType=INTEGER}, #{word,jdbcType=VARCHAR}, ",
        "#{count,jdbcType=INTEGER}, #{testType,jdbcType=INTEGER}, ",
        "#{testTime,jdbcType=VARCHAR})"
    })
    int insert(TestWordCount record);

    @InsertProvider(type=TestWordCountSqlProvider.class, method="insertSelective")
    int insertSelective(TestWordCount record);

    @Select({
        "select",
        "id, word, count, test_type, test_time",
        "from test_word_count",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="word", property="word", jdbcType=JdbcType.VARCHAR),
        @Result(column="count", property="count", jdbcType=JdbcType.INTEGER),
        @Result(column="test_type", property="testType", jdbcType=JdbcType.INTEGER),
        @Result(column="test_time", property="testTime", jdbcType=JdbcType.VARCHAR)
    })
    TestWordCount selectByPrimaryKey(Integer id);
    @Select({
            "select",
            "word, count",
            "from wordcount_display",
            "where test_time like #{year,jdbcType=VARCHAR} and test_type like #{type,jdbcType=VARCHAR} order by count desc limit #{limit,jdbcType=INTEGER} "
    })
    @Results({
            @Result(column="word", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="count", property="value", jdbcType=JdbcType.INTEGER),
    })
    ArrayList<WordCloud> selectWordCloud(String year,String type,int limit);

    @Select({
            "select",
            "id, word, count, test_type, test_time",
            "from test_word_count",
            "where word like #{word,jdbcType=VARCHAR} and test_type= #{type,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="word", property="word", jdbcType=JdbcType.VARCHAR),
            @Result(column="count", property="count", jdbcType=JdbcType.INTEGER),
            @Result(column="test_type", property="testType", jdbcType=JdbcType.INTEGER),
            @Result(column="test_time", property="testTime", jdbcType=JdbcType.VARCHAR)
    })
    ArrayList<TestWordCount> selectByWord(String  word, Integer type);
    @Select({
        "select * from year_type where test_type= #{type,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="test_type", property="testType", jdbcType=JdbcType.INTEGER),
            @Result(column="test_time", property="yearType", jdbcType=JdbcType.VARCHAR),
    })
    ArrayList<YearType> getYearType(Integer type);
    @UpdateProvider(type=TestWordCountSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TestWordCount record);

    @Update({
        "update test_word_count",
        "set word = #{word,jdbcType=VARCHAR},",
          "count = #{count,jdbcType=INTEGER},",
          "test_type = #{testType,jdbcType=INTEGER},",
          "test_time = #{testTime,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(TestWordCount record);
}