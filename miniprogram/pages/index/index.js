// pages/index/index.js
Page({
  data: {
    userInfo: null,
    statistics: {
      totalEmployees: 0,
      departments: 0,
      positions: 0
    },
    recentEmployees: [],
    loading: true
  },

  onLoad: function() {
    // 获取用户信息
    const app = getApp();
    this.setData({
      userInfo: app.globalData.userInfo
    });
    
    // 加载统计数据
    this.loadStatistics();
    
    // 加载最近添加的员工
    this.loadRecentEmployees();
  },
  
  onShow: function() {
    // 页面显示时刷新数据
    if (this.data.userInfo) {
      this.loadStatistics();
      this.loadRecentEmployees();
    }
  },
  
  // 加载统计数据
  loadStatistics: function() {
    const app = getApp();
    this.setData({ loading: true });
    
    // 获取员工总数
    app.request({
      url: '/employees/count'
    }).then(res => {
      this.setData({
        'statistics.totalEmployees': res.count || 0
      });
    }).catch(err => {
      console.error('获取员工总数失败', err);
    });
    
    // 获取部门总数
    app.request({
      url: '/departments/count'
    }).then(res => {
      this.setData({
        'statistics.departments': res.count || 0
      });
    }).catch(err => {
      console.error('获取部门总数失败', err);
    });
    
    // 获取职位总数
    app.request({
      url: '/positions/count'
    }).then(res => {
      this.setData({
        'statistics.positions': res.count || 0,
        loading: false
      });
    }).catch(err => {
      console.error('获取职位总数失败', err);
      this.setData({ loading: false });
    });
  },
  
  // 加载最近添加的员工
  loadRecentEmployees: function() {
    const app = getApp();
    
    app.request({
      url: '/employees/recent'
    }).then(res => {
      this.setData({
        recentEmployees: res || []
      });
    }).catch(err => {
      console.error('获取最近员工失败', err);
    });
  },
  
  // 查看员工详情
  viewEmployee: function(e) {
    const id = e.currentTarget.dataset.id;
    wx.navigateTo({
      url: '/pages/employee/detail?id=' + id
    });
  },
  
  // 跳转到员工列表
  navigateToEmployees: function() {
    wx.switchTab({
      url: '/pages/employee/list'
    });
  },
  
  // 跳转到部门列表
  navigateToDepartments: function() {
    wx.navigateTo({
      url: '/pages/department/list'
    });
  },
  
  // 跳转到职位列表
  navigateToPositions: function() {
    wx.navigateTo({
      url: '/pages/position/list'
    });
  }
});