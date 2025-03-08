App({
  globalData: {
    userInfo: null,
    baseUrl: 'http://localhost:8080/api',
    token: ''
  },
  onLaunch: function() {
    // 获取本地存储的用户信息和token
    const token = wx.getStorageSync('token');
    const userInfo = wx.getStorageSync('userInfo');
    
    if (token && userInfo) {
      this.globalData.token = token;
      this.globalData.userInfo = userInfo;
    } else {
      // 如果没有登录信息，跳转到登录页
      wx.redirectTo({
        url: '/pages/login/login'
      });
    }
  },
  
  // 封装请求方法
  request: function(options) {
    const that = this;
    const token = that.globalData.token;
    
    return new Promise((resolve, reject) => {
      wx.request({
        url: that.globalData.baseUrl + options.url,
        method: options.method || 'GET',
        data: options.data || {},
        header: {
          'Content-Type': 'application/json',
          'Authorization': token ? 'Bearer ' + token : ''
        },
        success: function(res) {
          // 请求成功
          if (res.statusCode === 200) {
            resolve(res.data);
          } else if (res.statusCode === 401) {
            // 未授权，跳转登录页
            wx.removeStorageSync('token');
            wx.removeStorageSync('userInfo');
            wx.redirectTo({
              url: '/pages/login/login'
            });
            reject('未授权或登录已过期');
          } else {
            reject(res.data || '请求失败');
          }
        },
        fail: function(err) {
          reject(err || '请求失败');
          wx.showToast({
            title: '网络错误',
            icon: 'none'
          });
        }
      });
    });
  }
})