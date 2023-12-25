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
       // 分页设置数据
      this.$axios.post('http://localhost:5000/user/register', {
        username: this.user.username,
        phone: this.user.phone,
        studentId: this.user.studentId,
        password: this.user.password
      }).then(() => {
        this.$router.push('/registerNext');
      });

    }
  }
}
</script>

<template>
  <div class="animate__animated animate__flipInY">
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
</div>
</template>

<style>

</style>