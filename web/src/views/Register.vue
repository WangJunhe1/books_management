<script>
import '@/assets/font/iconfont.css'
export default {
  name: "Register",
  data() {
    return {
      user: {
        username: '',
        email: '',
        password: ''
      }
    };
  },
}
</script>

<template>
  <div class="animate__animated animate__flipInY">
  <div class="body">
    <div id="register">
      <div class="container register-container" id="register-container">
        <form action="/login" method="post" class="form" id="register-form">
          <h2 class="form_title title">创建账号</h2>
          <div class="from_icons">
            <i class="iconfont icon-QQ"></i>
            <i class="iconfont icon-weixin"></i>
            <i class="iconfont icon-github"></i>
            <i class="iconfont icon-bilibili-line"></i>
          </div>
          <span class="form_span">选择注册方式或电子邮箱注册</span>
          <input type="text" class="form_input" placeholder="Name" v-model="user.username" />
          <input type="email" class="form_input" placeholder="Email" v-model="user.email" />
          <input type="password" class="form_input" placeholder="Password" v-model="user.password" />
          <button type="submit" class="form_button button submit">SIGN UP</button>
        </form>
        
      </div>

      <div class="switch" id="switch-register">
        <div class="switch-circle"></div>
        <div class="switch-circle switch-circle-t"></div>
        <div class="switch-container" id="switch-container-register">
          <h2 class="switch-title title" style="letter-spacing: 0;">Welcome Back!</h2>
          <p class="switch-description description">已经有账号了嘛，去登录账号进入奇妙的世界吧！！！</p>
          <router-link to="/login"><button class="switch-button button switch-btn">SIGN IN</button></router-link>
        </div>
      </div>
    </div>
  </div>
</div>
</template>

<style>
@import "F:/Web前端框架/web_project/Km/Km/src/assets/css/registe.css";
</style>