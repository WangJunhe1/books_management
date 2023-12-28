<script>
export default {
  name: 'Account',
  data() {
    return {
      student: {},
    }
  },
  methods: {
    changePassword() {
      this.$prompt('请输入密码', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        inputPattern: /\S/,
        inputErrorMessage: '密码不能为空'
      }).then(({ value }) => {
        this.$axios.put(`http://localhost:5000/user/updatePassword?studentNumber=${this.student.studentNumber}&password=${value}`,null,
        {
          headers: {
            'token': this.$store.state.User.token,
          }
        }).then(() => {
          this.$message({
            type: 'success',
            message: '你的密码是: ' + value
          });
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '取消输入'
        });
      });
    },
    changePhone() {
      this.$prompt('请输入手机号', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        inputPattern: /^1[3456789]\d{9}$/,
        inputErrorMessage: '手机号格式不正确'
      }).then(({ value }) => {
        this.$axios.put(`http://localhost:5000/user/updatePhone?studentNumber=${this.student.studentNumber}&studentPhone=${value}`,null,
        {
          headers: {
            'token': this.$store.state.User.token,
          }
        }).then(() => {
          this.$message({
            type: 'success',
            message: '你的手机号是: ' + value
          });
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '取消输入'
        });
      });
    },
    changeEmail() {
      this.$prompt('请输入邮箱', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        inputPattern: /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/,
        inputErrorMessage: '邮箱格式不正确'
      }).then(({ value }) => {
        this.$axios.put(`http://localhost:5000/user/updateEmail?studentNumber=${this.student.studentNumber}&studentEmail=${value}`,null,
        {
          headers: {
            'token': this.$store.state.User.token,
          }
        }).then(() => {
          this.$message({
            type: 'success',
            message: '你的邮箱是: ' + value
          });
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '取消输入'
        });
      });
    },
    cancelOut() {
      this.$confirm('此操作将永久删除该账号, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        inputPattern: "注销账号",
        type: 'warning'
      }).then(() => {
        this.$axios.delete(`http://localhost:5000/user/delete/${this.student.studentNumber}`,{
          headers: {
            'token': this.$store.state.User.token,
          }
        }).then(() => {
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
          localStorage.clear();
          this.$router.push('/login');
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
  },
  watch: {
    user: {
      handler(val, oldVal) {
        //  同步数据到后端
      },
      deep: true
    }
  },
  mounted() {
    this.$axios.get('http://localhost:5000/student/getStudent',{
      headers: {
        'token': this.$store.state.User.token
      }
    }).then(res => {
      this.student = res.data;
      console.log(this.student)
    })
  }
}
</script>

<template>
  <div id="account">
    <h2>账号设置</h2>
    <el-card class="box-card" shadow="hover" body-style="marign-top:30px">
      <div class="text item">
        密码
        <el-button type="text" @click="changePassword()" class="right-button">修改密码</el-button>
      </div>
      <el-divider></el-divider>
      <div class="text item">
        手机号
        <el-button type="text" @click="changePhone()" class="right-button">修改手机</el-button>
      </div>
      <el-divider></el-divider>
      <div class="text item">
        邮箱
        <el-button type="text" @click="changeEmail()" class="right-button">修改邮箱</el-button>
      </div>
      <el-divider></el-divider>
      <div class="text item">
        账号注销
        <el-button type="text" @click="cancelOut()" class="right-button">注销账号</el-button>
      </div>
    </el-card>
  </div>
</template>

<style>
.text {
  position: relative;
}
.right-button {
  position: absolute;
  top: -5px;
  right: 0;
  float: right;
}
</style>