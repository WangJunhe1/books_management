<script>
import '@/assets/font/iconfont.css'
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
        if( res.data === null ) {
          this.$message({
            message: res.message,
            type: 'warning'
          });
          return false;
        }
        this.user = res.data;
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
          <span class="form_span">登录</span>
          <input type="text" class="form_input" placeholder="Username" v-model="user.username"/>
          <input type="password" class="form_input" placeholder="Password" v-model="user.password" @keyup.enter="formCheck"/>
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
</template>

<style>
*{
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  /* 字体无法选中 */
  user-select: none;
}

.body{
  width: 100vw;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 12px;
  background-color: #ecf0f3;
  color: #a0a5a8;
}

#login {
  position: relative;
  width: 1000px;
  max-width: 1000px;
  min-width: 600px;
  height: 600px;
  padding: 25px;
  background-color: #ecf0f3;
  box-shadow: 10px 10px 10px #d1d9e6, -10px -10px 10px #f9f9f9;
  border-radius: 12px;
  overflow: hidden;
}
/* 设置响应式 */
@media (max-width: 1200px) {
  #login {
    transform: scale(0.7);
  }
}
@media (max-width: 1000px) {
  #login {
    transform: scale(0.6);
  }
}
@media (max-width: 800px) {
  #login {
    transform: scale(0.5);
  }
}
@media (max-width: 600px) {
  #login {
    transform: scale(0.4);
  }
}

.container{
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  top: 0;
  width: 600px;
  height: 100%;
  padding: 25px;
  background-color: #ecf0f3;
  transition: 1.25s;
}

.form {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  width: 100%;
  height: 100%;
}
.iconfont {
  margin: 0 5px;
  border: rgba(0, 0, 0, 0.5) 2px solid;
  border-radius: 50%;
  font-size: 25px;
  padding: 3px;
  opacity: 0.5;
  transition: 0.1s;
}
.iconfont:hover {
  opacity: 1;
  transition: 0.15s;
  cursor: pointer;
}
.form_input{
  width: 350px;
  height: 40px;
  margin: 4px 0;
  padding-left: 25px;
  font-size: 13px;
  letter-spacing: 0.15px;
  border: none;
  outline: none;
  background-color: #ecf0f3;
  transition: 0.25s ease;
  border-radius: 8px;
  box-shadow: inset 2px 2px 4px #d1d9e6, inset -2px -2px 4px #f9f9f9;
}
.form_input:focus {
  box-shadow: inset 4px 4px 4px #d1d9e6, inset -4px -4px 4px #f9f9f9;
}
.form_span {
  margin-top: 30px;
  margin-bottom: 12px;
}

.form_link {
  color: #181818;
  font-size: 15px;
  margin-top: 25px;
  border-bottom: 1px solid #a0a5a8;
  line-height: 2px;
}

.title {
  font-size: 34px;
  font-weight: 700;
  line-height: 3;
  color: #181818;
}
.description {
  font-size: 14px;
  letter-spacing: 0.25px;
  text-align: center;
  line-height: 1.6;
}
.button {
  width: 180px;
  height: 50px;
  border-radius: 25px;
  margin-top: 50px;
  font-size: 14px;
  font-weight: 700;
  letter-spacing: 1.15px;
  background-color: #4b70e2;
  color: #f9f9f9;
  box-shadow: 8px 8px 16px #d1d9e6, -8px -8px 16px #f9f9f9;
  border: none;
  outline: none;
}
.login-container{
  z-index: 100;
  left: calc(100% - 600px);
}
.switch {
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  top: 0;
  left: 0;
  height: 100%;
  width: 400px;
  padding: 50px;
  z-index: 200;
  transition: 1.25s;
  background-color: #ecf0f3;
  overflow: hidden;
  box-shadow: 4px 4px 10px #d1d9e6, -4px -4px 10px #f9f9f9;
}

.switch-circle {
  position: absolute;
  width: 500px;
  height: 500px;
  border-radius: 50%;
  background-color: #ecf0f3;
  box-shadow: inset 8px 8px 12px #d1d9e6, inset -8px -8px 12px #f9f9f9;
  bottom: -60%;
  left: -60%;
  transition: 1.25s;
}
.switch-circle-t {
  top: -30%;
  left: 60%;
  width: 300px;
  height: 300px;
}
.switch-container {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  position: relative;
  width: 400px;
  padding: 50px 55px;
  transition: 1.25s;
}
.switch-button {
  cursor: pointer;
}

.switch-button:hover,
.submit:hover {
  box-shadow: 6px 6px 10px #d1d9e6, -6px -6px 10px #f9f9f9;
  transform: scale(0.985);
  transition: 0.25s;
}
.switch-button:active,
.switch-button:focus {
  box-shadow: 2px 2px 6px #d1d9e6, -2px -2px 6px #f9f9f9;
  transform: scale(0.97);
  transition: 0.25s;
}

.switch-button a{
  text-decoration: none;
  color: #ecf0f3;
}



#tablet{
  background: #4ac4aa;
  -webkit-transform:rotateY(0deg) scale(1, 1);
  transform:rotateY(0deg) scale(1, 1);
}
#wifi{
  background: #ea5634;
  -webkit-transform:rotateY(180deg) scale(0, 0);
  transform:rotateY(180deg) scale(0, 0);
}
#tablet.move{
  -webkit-transform:rotateY(-180deg) scale(0, 0);
  transform:rotateY(-180deg) scale(0, 0);
}
#wifi.move{
  -webkit-transform:rotateY(0deg) scale(1, 1);
  transform:rotateY(0deg) scale(1, 1);
}

</style>