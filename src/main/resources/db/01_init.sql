-- 创建数据库
CREATE DATABASE IF NOT EXISTS emp_management DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

USE emp_management;

-- 部门表
CREATE TABLE IF NOT EXISTS `department` (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '部门ID',
    name VARCHAR(50) NOT NULL COMMENT '部门名称',
    description VARCHAR(200) COMMENT '部门描述',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT '部门表';

-- 职位表
CREATE TABLE IF NOT EXISTS `position` (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '职位ID',
    name VARCHAR(50) NOT NULL COMMENT '职位名称',
    description VARCHAR(200) COMMENT '职位描述',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT '职位表';

-- 员工表
CREATE TABLE IF NOT EXISTS `employee` (
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT '员工ID',
    name VARCHAR(50) NOT NULL COMMENT '员工姓名',
    gender TINYINT DEFAULT 0 COMMENT '性别：0-未知，1-男，2-女',
    age INT COMMENT '年龄',
    id_card VARCHAR(18) COMMENT '身份证号',
    phone VARCHAR(11) COMMENT '手机号',
    email VARCHAR(50) COMMENT '邮箱',
    address VARCHAR(200) COMMENT '地址',
    department_id INT COMMENT '部门ID',
    position_id INT COMMENT '职位ID',
    entry_date DATE COMMENT '入职日期',
    status TINYINT DEFAULT 1 COMMENT '状态：0-离职，1-在职',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT '员工表';

-- 插入初始数据
INSERT INTO department (name, description) VALUES
('技术部', '负责公司技术研发'),
('市场部', '负责公司市场营销'),
('人事部', '负责公司人事管理'),
('财务部', '负责公司财务管理');

INSERT INTO position (name, description) VALUES
('工程师', '负责技术开发工作'),
('经理', '负责部门管理工作'),
('主管', '负责团队管理工作'),
('专员', '负责具体业务工作');