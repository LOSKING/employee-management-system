USE emp_management;

-- 插入部门测试数据
INSERT INTO department (name, description) VALUES
('人事部', '负责人员招聘与管理'),
('技术部', '负责产品研发与维护'),
('财务部', '负责公司财务管理');

-- 插入职位测试数据
INSERT INTO position (name, description) VALUES
('部门经理', '部门管理工作'),
('Java开发工程师', '后端服务开发'),
('前端开发工程师', '网页界面开发');

-- 插入员工测试数据
INSERT INTO employee (name, gender, age, id_card, phone, email, address, department_id, position_id, entry_date) VALUES
('张三', 1, 30, '110101199001011234', '13800138000', 'zhangsan@example.com', '北京市朝阳区', 1, 1, '2020-01-15'),
('李四', 2, 28, '110101199202021234', '13800138001', 'lisi@example.com', '上海市浦东新区', 2, 2, '2021-05-10'),
('王五', 1, 35, '110101198801011234', '13800138002', 'wangwu@example.com', '广州市天河区', 3, 3, '2019-09-01');