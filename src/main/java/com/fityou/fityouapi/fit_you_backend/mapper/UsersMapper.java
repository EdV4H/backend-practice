package com.fityou.fityouapi.fit_you_backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.openapi.fityou.model.NewUser;
import org.openapi.fityou.model.User;

@Mapper
public interface UsersMapper {
  
  @Insert("insert into users (id, name) values (0, #{name})")
    public boolean create(NewUser newUser);

  @Select("select * from users")
    public List<User> read();

  @Select("select * from users where id = #{id}")
    public List<User> findById(int id);

  @Update("update users set name = #{name} where id = #{id}")
    public boolean update(User user);

  @Delete("delete from users where id = #{id}")
    public boolean delete(int id);

  @Select("select last_insert_id()")
    public int lastInsertId();
}
