<script>
export default {
  data() {
    return {
      activeIndex: '1',
      isLogin: this.$store.state.User.isLogin,
      user: {
        ...this.$store.state.User.user
      },
    };
  },
  methods: {

  },
}
</script>

<template>
  <div id="header">
      <el-menu
          :default-active="activeIndex"
          :router="true"
          class="el-menu-demo"
          mode="horizontal"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b">
        <h2 class="el-menu-title">图书管理系统</h2>
        <el-menu-item index="/index" class="menu-first-item">首页</el-menu-item>
        <el-menu-item index="/index/bookDistribution">馆藏分布</el-menu-item>
        <el-menu-item index="/index/bookQuery">图书查询</el-menu-item>
        <el-menu-item index="/bookBorrow">图书借阅</el-menu-item>
        <el-submenu class="menu-right" index="5" v-if="isLogin">
          <template slot="title">
            <el-row class="demo-avatar demo-basic">
              <el-col :span="12">
                <div class="demo-basic--circle">
                  <div class="block">
                    <el-avatar :size="30" :src="user.img"></el-avatar>
                    {{user.username}}
                  </div>
                </div>
              </el-col>
            </el-row>
          </template>
          <el-menu-item index="/user/userInfo">
            <i class="el-icon-user-solid"></i>
            个人信息
          </el-menu-item>
          <el-menu-item index="/user/borrow">
            <i class="el-icon-reading"></i>
            我的借阅
          </el-menu-item>
          <el-menu-item index="/user/evaluation">
            <i class="el-icon-chat-dot-round"></i>
            我的评论
          </el-menu-item>
          <el-menu-item index="/login">
            <i class="el-icon-arrow-left"></i>
            退出登录
          </el-menu-item>
        </el-submenu>
        <el-menu-item class="menu-right" v-else><router-link to="/login">登录</router-link></el-menu-item>
      </el-menu>
  </div>
</template>

<style>

.el-menu-item a{
  display: block;
  color: #ffffff;
  text-decoration: none;
}

.el-menu-demo {
  position: relative;
  overflow: hidden;
}

.el-menu-demo a {
  display: block;
  text-decoration: none;
}



.el-menu-demo .el-menu-title {
  margin-left: 20px;
  height: 60px;
  font-size: 20px;
  color: #ffffff;
  position: absolute;
  left: 0;
  float: left;
}

.menu-first-item {
  margin-left: 150px;
}

.el-menu-demo .menu-right{
  position: absolute;
  right: 0;
  float: right;
}
</style>