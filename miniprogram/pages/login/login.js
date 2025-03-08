// pages/login/login.js
Page({
  data: {
    username: '',
    password: '',
    loading: false
  },

  // 输入用户名
  inputUsername: function(e) {
    this.setData({
      username: e.detail.value
    });
  },

  // 输入密码
  inputPassword: function(e) {
    this.setData({
      password: e.detail.value
    });
  },

  // 登录
  login: function() {
    const that = this;
    const { username, password } = this.data;
    
    // 表单验证
    if (!username || !password) {
      wx.showToast({
        title: '用户名和密码不能为空',
        icon: 'none'
      });
      return;
    }
    
    // 显示加载中
    this.setData({ loading: true });
    
    // 调用登录接口
    const app = getApp();
    app.request({
      url: '/users/login',
      method: 'POST',
      data: {
        username: username,
        password: password
      }
    }).then(res => {
      // 登录成功
      wx.setStorageSync('token', res.token);
      wx.setStorageSync('userInfo', res.user);
      
      // 更新全局数据
      app.globalData.token = res.token;
      app.globalData.userInfo = res.user;
      
      // 跳转到首页
      wx.switchTab({
        url: '/pages/index/index'
      });
    }).catch(err => {
      // 登录失败
      wx.showToast({
        title: '用户名或密码错误',
        icon: 'none'
      });
    }).finally(() => {
      // 隐藏加载中
      that.setData({ loading: false });
    });
  },
  
  // 微信一键登录
  wxLogin: function() {
    wx.showToast({
      title: '功能开发中...',
      icon: 'none'
    });
  }
})