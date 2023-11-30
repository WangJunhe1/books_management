<script>
import '@/assets/font/iconfont.css'
import '@/assets/css/login.css';
export default {
  name: "Login",
  data() {
    return {
      user: {
        username: '',
        password: ''
      },
      data: null
    }
  },
  methods: {
    formCheck() {
      if (this.user.username === '') {
        this.$message({
          message: '用户名不能为空',
          type: 'warning'
        });
        return false;
      }
      if (this.user.password === '') {
        this.$message({
          message: '密码不能为空',
          type: 'warning'
        });
        return false;
      };
      // this.$store.dispatch('User/setDataAction', this.data);
      // debugger
      this.$axios.post('http://localhost:5000/user/login', this.user).then(res => {
        console.log(res.data);
        this.data = res.data;
        if( this.data.object === null ) {
          this.$message({
            message: this.data.message,
            type: 'warning'
          });
          return false;
        }
        localStorage.setItem('data', JSON.stringify(this.data));
        this.$store.dispatch('User/setDataAction', this.data);
        this.user = this.data.data;
        localStorage.setItem('user', JSON.stringify(this.user));
        this.$store.dispatch('User/setUserAction', this.user);
        localStorage.setItem('isLogin', true);
        this.$store.dispatch('User/setIsLoginAction', true);
        localStorage.setItem('token', this.user.token);
        this.$store.dispatch('User/setTokenAction', this.user.token);
        this.$router.push('/index');
        return true;
      }).catch(err => {
        console.log(err);
        return false;
      })
    }
  }
}

</script>

<template>
  <div  class="animate__animated  animate__flipInY">
  <div class="body">
    <div id="login">
      <div class="container login-container" id="login-container">
        <transition name="fade">
        <form method="post" class="form" id="login-form">
          <h2 class="form_title title">登入账号</h2>
          <div class="from_icons">
            <i class="iconfont icon-QQ"></i>
            <i class="iconfont icon-weixin"></i>
            <i class="iconfont icon-github"></i>
            <i class="iconfont icon-bilibili-line"></i>
          </div>
          <span class="form_span">选择登录方式或电子邮箱注册</span>
          <input type="text" class="form_input" placeholder="Username" v-model="user.username"/>
          <input type="password" class="form_input" placeholder="Password" v-model="user.password"/>
          <router-link to="/register" class="form_link">忘记密码?</router-link>
          <el-button :plain="true" class="form_button button submit" @click="formCheck()">SIGN IN</el-button>
        </form>
      </transition>
      </div>

      <div class="switch" id="switch-login">
        <div class="switch-circle"></div>
        <div class="switch-circle switch-circle-t"></div>

        <transition name="fade">
        <div class="switch-container" id="switch-container-login">
          <h2 class="switch-title title" style="letter-spacing: 0;">Hello Friend!</h2>
          <p class="switch-description description">去注册一个账号，成为尊贵的粉丝会员让我们抬入奇妙的旅途</p>
          <router-link to="/register"><button class="switch-button button switch-btn">SIGN UP</button></router-link>
        </div>
        </transition>
      </div>
    </div>
  </div>
</div>
</template>

<style>


</style>