package com.example.emp.service.impl;

import com.example.emp.entity.User;
import com.example.emp.mapper.UserMapper;
import com.example.emp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * 用户服务实现类
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    
    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 登录成功返回用户信息，失败返回null
     */
    @Override
    public User login(String username, String password) {
        // 根据用户名查询用户
        User user = userMapper.selectByUsername(username);
        if (user == null) {
            return null;
        }
        
        // 密码加密后比较
        String encryptedPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!user.getPassword().equals(encryptedPassword)) {
            return null;
        }
        
        // 登录成功，清除密码后返回用户信息
        user.setPassword(null);
        return user;
    }
    
    /**
     * 根据用户名查询用户
     */
    @Override
    public User findByUsername(String username) {
        return userMapper.selectByUsername(username);
    }
    
    /**
     * 新增用户
     */
    @Override
    public int add(User user) {
        // 密码加密
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        return userMapper.insert(user);
    }
    
    /**
     * 更新用户
     */
    @Override
    public int update(User user) {
        // 如果密码不为空，则进行加密
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        }
        return userMapper.update(user);
    }
    
    /**
     * 删除用户
     */
    @Override
    public int deleteById(Integer id) {
        return userMapper.deleteById(id);
    }
}