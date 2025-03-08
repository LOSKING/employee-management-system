-- 用户表
CREATE TABLE IF NOT EXISTS `user` (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '用户ID',
    username VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名',
    password VARCHAR(100) NOT NULL COMMENT '密码',
    role VARCHAR(20) NOT NULL COMMENT '角色：ADMIN-管理员，EMPLOYEE-员工',
    employee_id INT COMMENT '关联的员工ID',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    FOREIGN KEY (employee_id) REFERENCES employee(id)
) COMMENT '用户表';

-- 插入初始管理员账号
INSERT INTO user (username, password, role) VALUES
('admin', 'e10adc3949ba59abbe56e057f20f883e', 'ADMIN'); -- 密码：123456