package com.example.emp.service;

import com.example.emp.entity.User;

/**
 * 用户服务接口
 */
public interface UserService {
    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 登录成功返回用户信息，失败返回null
     */
    User login(String username, String password);
    
    /**
     * 根据用户名查询用户
     */
    User findByUsername(String username);
    
    /**
     * 新增用户
     */
    int add(User user);
    
    /**
     * 更新用户
     */
    int update(User user);
    
    /**
     * 删除用户
     */
    int deleteById(Integer id);
}