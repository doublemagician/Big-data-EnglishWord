package com.muc.mapper;

import com.muc.bean.TestType;
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

public interface TestTypeMapper {
    @Delete({
        "delete from test_type",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into test_type (id, val)",
        "values (#{id,jdbcType=INTEGER}, #{val,jdbcType=VARCHAR})"
    })
    int insert(TestType record);

    @InsertProvider(type=TestTypeSqlProvider.class, method="insertSelective")
    int insertSelective(TestType record);

    @Select({
        "select",
        "id, val",
        "from test_type",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="val", property="val", jdbcType=JdbcType.VARCHAR)
    })
    TestType selectByPrimaryKey(Integer id);
    @Select({
            "select",
            "id, val",
            "from test_type"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="val", property="val", jdbcType=JdbcType.VARCHAR)
    })
    ArrayList<TestType> selectAll();

    @UpdateProvider(type=TestTypeSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TestType record);

    @Update({
        "update test_type",
        "set val = #{val,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(TestType record);
}