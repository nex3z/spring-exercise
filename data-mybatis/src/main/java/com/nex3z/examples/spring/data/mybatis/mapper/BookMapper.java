package com.nex3z.examples.spring.data.mybatis.mapper;

import com.nex3z.examples.spring.data.mybatis.model.Book;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface BookMapper {

    @Insert("insert into t_book (title, price, create_time, update_time) values (#{title}, #{price}, now(), now())")
    @Options(useGeneratedKeys = true)
    Long save(Book book);

    @Select("select * from t_book where id = #{id}")
    @Results(value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "create_time", property = "createTime"),
            // map-underscore-to-camel-case = true
            // @Result(column = "update_time", property = "updateTime"),
    })
    Book findById(@Param("id") Long id);

    @Select("select * from t_book order by id")
    List<Book> findAllWithRowBounds(RowBounds rowBounds);

    @Select("select * from t_book order by id")
    List<Book> findAllWithParam(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

}
