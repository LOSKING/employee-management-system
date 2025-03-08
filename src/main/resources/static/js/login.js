// 全局API基础URL
const API_BASE_URL = '/api';

// Vue实例
new Vue({
    el: '#app',
    data() {
        return {
            // 登录表单
            loginForm: {
                username: '',
                password: ''
            },
            // 表单验证规则
            rules: {
                username: [
                    { required: true, message: '请输入用户名', trigger: 'blur' }
                ],
                password: [
                    { required: true, message: '请输入密码', trigger: 'blur' }
                ]
            }
        };
    },
    created() {
        // 检查是否已登录
        this.checkLoginStatus();
    },
    methods: {
        // 检查登录状态
        checkLoginStatus() {
            // 从localStorage获取用户信息
            const userInfo = localStorage.getItem('userInfo');
            if (userInfo) {
                // 已登录，跳转到首页
                window.location.href = 'index.html';
            }
        },
        
        // 提交登录表单
        submitForm() {
            this.$refs.loginForm.validate(valid => {
                if (valid) {
                    // 发送登录请求
                    axios.post(`${API_BASE_URL}/users/login`, this.loginForm)
                        .then(response => {
                            // 登录成功
                            this.$message.success('登录成功');
                            
                            // 保存用户信息到localStorage
                            localStorage.setItem('userInfo', JSON.stringify(response.data.user));
                            
                            // 跳转到首页
                            setTimeout(() => {
                                window.location.href = 'index.html';
                            }, 1000);
                        })
                        .catch(error => {
                            // 登录失败
                            let errorMsg = '登录失败';
                            if (error.response && error.response.data && error.response.data.message) {
                                errorMsg = error.response.data.message;
                            }
                            this.$message.error(errorMsg);
                        });
                } else {
                    return false;
                }
            });
        }
    }
});