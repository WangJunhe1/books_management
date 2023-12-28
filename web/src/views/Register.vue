<script>
import '@/assets/font/iconfont.css'
import "@/assets/css/registe.css";
export default {
  name: "Register",
  data() {
    return {
      user: {
        username: '',
        studentId: '',
        phone: '',
        password: ''
      }
    }
  },
  methods: {
    register() {
      if(this.user.username.trim() === '') {
        this.$message({
          message: '用户名不能为空',
          type: 'error'
        })
        return false;
      }
      if(this.user.username.length < 3 || this.user.username.length > 12) {
        this.$message({
          message: '用户名长度为3-12位',
          type: 'error'
        })
        return false;
      }
      if(this.user.studentId.trim() === '') {
        this.$message({
          message: '学号不能为空',
          type: 'error'
        })
        return false;
      }
      if(this.user.studentId.length !== 12) {
        this.$message({
          message: '学号长度为12位',
          type: 'error'
        })
        return false;
      }
      if(this.user.phone.trim() === '') {
        this.$message({
          message: '手机号不能为空',
          type: 'error'
        })
        return false;
      }
      if(this.user.phone.length !== 11 ) {
        this.$message({
          message: '用户名长度为11位',
          type: 'error'
        })
        return false;
      }
      if(this.user.password.trim() === '') {
        this.$message({
          message: '密码不能为空',
          type: 'error'
        })
        return false;
      }
      if(this.user.password.length < 8 && this.user.password.length > 16) {
        this.$message({
          message: '密码长度为8-16位',
          type: 'error'
        })
        return false;
      }

      this.$axios.post('http://localhost:5000/user/register', {
        username: this.user.username,
        phone: this.user.phone,
        studentNumber: this.user.studentId,
        password: this.user.password
      }).then((res) => {
        console.log(res.data)
        if (res.data.code === 0) {
          this.$message({
            message: '注册失败，没有查询到对应到学生ID，您需要先进行学生注册',
            type: 'error'
          })
          this.$router.push('/registerNext');
        }
        if (res.data.code === 1) {
          this.$message({
            message: '注册成功',
            type: 'success'
          })
          this.$router.push('/login');
        }
      });
    }
  }
}
</script>

<template>
  <div class="body">
    <div id="register">
      <div class="container register-container" id="register-container">
        <form class="form" id="register-form">
          <h2 class="form_title title">创建账号</h2>
          <div class="from_icons">
            <i class="iconfont icon-QQ"></i>
            <i class="iconfont icon-weixin"></i>
            <i class="iconfont icon-github"></i>
            <i class="iconfont icon-bilibili-line"></i>
          </div>
          <span class="form_span">注册</span>
          <input type="text" class="form_input" placeholder="Name" v-model="user.username" />
          <input type="text" class="form_input" placeholder="StudentId" v-model="user.studentId" />
          <input type="text" class="form_input" placeholder="Phone" v-model="user.phone" />
          <input type="password" class="form_input" placeholder="Password" v-model="user.password" />
          <el-button  :plain="true" class="form_button button submit"  @click="register()">SIGN UP</el-button>
        </form>
        
      </div>

      <div class="switch" id="switch-register">
        <div class="switch-circle"></div>
        <div class="switch-circle switch-circle-t"></div>
        <div class="switch-container" id="switch-container-register">
          <h2 class="switch-title title" style="letter-spacing: 0;">Welcome Back!</h2>
          <p class="switch-description description">已经有账号了嘛，去登录账号进入奇妙的世界吧！！！</p>
          <router-link to="/login"><el-button class="switch-button button switch-btn submit">SIGN IN</el-button></router-link>
        </div>
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

#register {
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
  #register {
    transform: scale(0.7);
  }
}
@media (max-width: 1000px) {
  #register {
    transform: scale(0.6);
  }
}
@media (max-width: 800px) {
  #register {
    transform: scale(0.5);
  }
}
@media (max-width: 600px) {
  #register {
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
.register-container{
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
</style>