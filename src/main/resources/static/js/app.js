// 全局API基础URL
const API_BASE_URL = '/api';

// Vue实例
new Vue({
    el: '#app',
    data() {
        return {
            // 用户信息
            userInfo: null,
            // 员工列表数据
            employees: [],
            // 部门列表
            departments: [],
            // 职位列表
            positions: [],
            // 加载状态
            loading: false,
            // 分页参数
            currentPage: 1,
            pageSize: 10,
            total: 0,
            // 搜索表单
            searchForm: {
                name: '',
                departmentId: '',
                positionId: ''
            },
            // 员工表单
            employeeForm: {
                id: null,
                name: '',
                gender: 1,
                age: 25,
                idCard: '',
                phone: '',
                email: '',
                address: '',
                departmentId: '',
                positionId: '',
                entryDate: '',
                status: 1
            },
            // 表单验证规则
            rules: {
                name: [
                    { required: true, message: '请输入姓名', trigger: 'blur' },
                    { min: 2, max: 20, message: '长度在 2 到 20 个字符', trigger: 'blur' }
                ],
                gender: [
                    { required: true, message: '请选择性别', trigger: 'change' }
                ],
                phone: [
                    { pattern: /^1[3-9]\d{9}$/, message: '请输入正确的手机号码', trigger: 'blur' }
                ],
                email: [
                    { type: 'email', message: '请输入正确的邮箱地址', trigger: 'blur' }
                ],
                idCard: [
                    { pattern: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/, message: '请输入正确的身份证号码', trigger: 'blur' }
                ],
                departmentId: [
                    { required: true, message: '请选择部门', trigger: 'change' }
                ],
                positionId: [
                    { required: true, message: '请选择职位', trigger: 'change' }
                ],
                entryDate: [
                    { required: true, message: '请选择入职日期', trigger: 'change' }
                ]
            },
            // 对话框相关
            dialogVisible: false,
            dialogTitle: '新增员工',
            isEdit: false
        };
    },
    created() {
        // 检查登录状态
        this.checkLoginStatus();
        
        // 页面创建时加载数据
        this.fetchEmployees();
        this.fetchDepartments();
        this.fetchPositions();
    },
    methods: {
        // 检查登录状态
        checkLoginStatus() {
            // 从localStorage获取用户信息
            const userInfoStr = localStorage.getItem('userInfo');
            if (!userInfoStr) {
                // 未登录，跳转到登录页
                window.location.href = 'login.html';
                return;
            }
            
            // 解析用户信息
            this.userInfo = JSON.parse(userInfoStr);
        },
        
        // 退出登录
        logout() {
            // 清除本地存储的用户信息
            localStorage.removeItem('userInfo');
            // 跳转到登录页
            window.location.href = 'login.html';
        },
        
        // 获取员工列表
        fetchEmployees() {
            this.loading = true;
            axios.get(`${API_BASE_URL}/employees`)
                .then(response => {
                    this.employees = response.data;
                    this.total = response.data.length;
                    this.loading = false;
                })
                .catch(error => {
                    console.error('获取员工列表失败:', error);
                    this.$message.error('获取员工列表失败');
                    this.loading = false;
                });
        },
        
        // 获取部门列表
        fetchDepartments() {
            axios.get(`${API_BASE_URL}/departments`)
                .then(response => {
                    this.departments = response.data;
                })
                .catch(error => {
                    console.error('获取部门列表失败:', error);
                    this.$message.error('获取部门列表失败');
                });
        },
        
        // 获取职位列表
        fetchPositions() {
            axios.get(`${API_BASE_URL}/positions`)
                .then(response => {
                    this.positions = response.data;
                })
                .catch(error => {
                    console.error('获取职位列表失败:', error);
                    this.$message.error('获取职位列表失败');
                });
        },
        
        // 搜索员工
        searchEmployees() {
            this.loading = true;
            axios.get(`${API_BASE_URL}/employees/search`, {
                params: this.searchForm
            })
                .then(response => {
                    this.employees = response.data;
                    this.total = response.data.length;
                    this.loading = false;
                })
                .catch(error => {
                    console.error('搜索员工失败:', error);
                    this.$message.error('搜索员工失败');
                    this.loading = false;
                });
        },
        
        // 重置搜索表单
        resetSearch() {
            this.$refs.searchForm.resetFields();
            this.fetchEmployees();
        },
        
        // 显示添加员工对话框
        showAddDialog() {
            this.isEdit = false;
            this.dialogTitle = '新增员工';
            this.employeeForm = {
                id: null,
                name: '',
                gender: 1,
                age: 25,
                idCard: '',
                phone: '',
                email: '',
                address: '',
                departmentId: '',
                positionId: '',
                entryDate: new Date().toISOString().split('T')[0],
                status: 1
            };
            this.dialogVisible = true;
            // 在下一个DOM更新周期后重置表单验证
            this.$nextTick(() => {
                if (this.$refs.employeeForm) {
                    this.$refs.employeeForm.clearValidate();
                }
            });
        },
        
        // 显示编辑员工对话框
        showEditDialog(row) {
            this.isEdit = true;
            this.dialogTitle = '编辑员工';
            // 复制一份数据，避免直接修改表格数据
            this.employeeForm = JSON.parse(JSON.stringify(row));
            this.dialogVisible = true;
            // 在下一个DOM更新周期后重置表单验证
            this.$nextTick(() => {
                if (this.$refs.employeeForm) {
                    this.$refs.employeeForm.clearValidate();
                }
            });
        },
        
        // 提交表单
        submitForm() {
            this.$refs.employeeForm.validate(valid => {
                if (valid) {
                    if (this.isEdit) {
                        // 更新员工
                        this.updateEmployee();
                    } else {
                        // 添加员工
                        this.addEmployee();
                    }
                } else {
                    return false;
                }
            });
        },
        
        // 添加员工
        addEmployee() {
            axios.post(`${API_BASE_URL}/employees`, this.employeeForm)
                .then(() => {
                    this.$message.success('添加员工成功');
                    this.dialogVisible = false;
                    this.fetchEmployees();
                })
                .catch(error => {
                    console.error('添加员工失败:', error);
                    this.$message.error('添加员工失败');
                });
        },
        
        // 更新员工
        updateEmployee() {
            axios.put(`${API_BASE_URL}/employees/${this.employeeForm.id}`, this.employeeForm)
                .then(() => {
                    this.$message.success('更新员工成功');
                    this.dialogVisible = false;
                    this.fetchEmployees();
                })
                .catch(error => {
                    console.error('更新员工失败:', error);
                    this.$message.error('更新员工失败');
                });
        },
        
        // 删除员工
        handleDelete(id) {
            this.$confirm('确认删除该员工?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                axios.delete(`${API_BASE_URL}/employees/${id}`)
                    .then(() => {
                        this.$message.success('删除员工成功');
                        this.fetchEmployees();
                    })
                    .catch(error => {
                        console.error('删除员工失败:', error);
                        this.$message.error('删除员工失败');
                    });
            }).catch(() => {
                // 取消删除操作
            });
        },
        
        // 分页大小变化
        handleSizeChange(val) {
            this.pageSize = val;
            this.fetchEmployees();
        },
        
        // 当前页变化
        handleCurrentChange(val) {
            this.currentPage = val;
            this.fetchEmployees();
        }
    }
});