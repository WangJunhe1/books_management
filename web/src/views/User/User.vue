<script>
export default {
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    loginOut() {
      localStorage.clear();
      this.$router.push('/login');
    }
  }

}
</script>

<template>
  <div id="userInfo">
    <div class="container">
      <el-container>
        <el-aside width="200px">
          <el-menu
              default-active="/user/userInfo/profile"
              :router="true"
              class="el-menu-vertical-demo"
              @open="handleOpen"
              @close="handleClose"
              background-color="#545c64"
              text-color="#fff"
              active-text-color="#ffd04b">
            <el-submenu index="1">
              <template slot="title">
                <i class="el-icon-user-solid"></i>
                <span>个人中心</span>
              </template>
              <el-menu-item index="/user/userInfo/profile">个人资料</el-menu-item>
              <el-menu-item index="/user/userInfo/account">账号设置</el-menu-item>
            </el-submenu>
            <el-menu-item index="/user/borrow">
              <i class="el-icon-reading"></i>
              <span slot="title">我的借阅</span>
            </el-menu-item>
            <el-menu-item index="/user/comment">
              <i class="el-icon-chat-dot-round"></i>
              <span slot="title">我的评论</span>
            </el-menu-item>
            <el-menu-item index="/login" @click="loginOut()">
              <i class="el-icon-arrow-left"></i>
              <span slot="title">退出登录</span>
            </el-menu-item>
          </el-menu>
        </el-aside>
        <el-main style="background-color:#fff; margin-left: 10px">
          <router-view></router-view>
        </el-main>
      </el-container>
    </div>
  </div>
</template>

<style>

#userInfo {
  padding-top: 20px;
}

#userInfo a {
  display: block;
  color: #fff;
  text-decoration: none;
}

</style>