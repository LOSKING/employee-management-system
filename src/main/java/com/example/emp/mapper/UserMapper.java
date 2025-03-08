package com.example.emp.mapper;

import com.example.emp.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户Mapper接口
 */
@Mapper
public interface UserMapper {
    /**
     * 根据用户名查询用户
     */
    User selectByUsername(String username);
    
    /**
     * 新增用户
     */
    int insert(User user);
    
    /**
     * 更新用户
     */
    int update(User user);
    
    /**
     * 删除用户
     */
    int deleteById(Integer id);
}