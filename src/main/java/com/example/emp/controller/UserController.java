package com.example.emp.controller;

import com.example.emp.entity.User;
import com.example.emp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户控制器
 */
@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;
    
    /**
     * 用户登录
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginForm) {
        String username = loginForm.get("username");
        String password = loginForm.get("password");
        
        if (username == null || password == null) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "用户名和密码不能为空");
            return ResponseEntity.badRequest().body(response);
        }
        
        User user = userService.login(username, password);
        if (user == null) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "用户名或密码错误");
            return ResponseEntity.badRequest().body(response);
        }
        
        // 登录成功，返回用户信息
        Map<String, Object> response = new HashMap<>();
        response.put("user", user);
        response.put("message", "登录成功");
        return ResponseEntity.ok(response);
    }
    
    /**
     * 添加用户
     */
    @PostMapping
    public ResponseEntity<?> add(@RequestBody User user) {
        // 检查用户名是否已存在
        User existingUser = userService.findByUsername(user.getUsername());
        if (existingUser != null) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "用户名已存在");
            return ResponseEntity.badRequest().body(response);
        }
        
        int result = userService.add(user);
        if (result > 0) {
            return ResponseEntity.ok().build();
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "添加用户失败");
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    /**
     * 更新用户
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody User user) {
        user.setId(id);
        int result = userService.update(user);
        if (result > 0) {
            return ResponseEntity.ok().build();
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "更新用户失败");
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * 删除用户
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        int result = userService.deleteById(id);
        if (result > 0) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}