<script>
import "@/assets/css/header.css";
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
    loginOut() {
      localStorage.clear();
      this.$router.push('/registerNext');
    }
  },
}
</script>
<template>
    <div>
      <div class="black">
          <a href="/index" title="首页">
            <img src="@/assets/css/logo.png" style="width: 400px;" alt="图书管理logo">
          </a>
  
          <div class="main">
            <ul class="main-ul">
              <li class="main-ul-li"><a href="/index" class="menu-link">首页</a></li>
              <li class="main-ul-li"><a href="/index/bookDistribution" class="menu-link">馆藏分布</a></li>
              <li class="main-ul-li"><a href="/index/bookQuery" class="menu-link">图书查询</a></li>
              <li class="main-ul-li"><a href="/index/bookBorrow" class="menu-link">图书借阅</a></li>
              <li class="main-ul-li" v-if="isLogin">
                <a href="/user/userInfo" class="menu-link">
                  <i class="el-icon-user-solid"></i>个人信息
                </a>
              </li>
              <li class="main-ul-li" v-if="isLogin">
                <a href="/user/borrow" class="menu-link">
                  <i class="el-icon-reading"></i>我的借阅
                </a>
              </li>
              <li class="main-ul-li" v-if="isLogin">
                <a href="/user/evaluation" class="menu-link">
                  <i class="el-icon-chat-dot-round"></i>我的评论
                </a>
              </li>
              <li class="main-ul-li" v-if="isLogin">
                <a href="/login" class="menu-link" @click="loginOut()">
                  <div class="el-icon-arrow-left"></div>退出登录
                </a href="/login">
              </li>
              <li class="main-ul-li" v-else>
                <a href="#" class="menu-link" @click="loginOut()">登录</a>
              </li>
            </ul>
          </div>     
      </div>
    </div>
  </template>
  



<style>

</style>