<script>
export default {
  data() {
    return {
      student: {
        number: '',
        name: '',
        sex: '',
        email: '',
        birthDate: '',
        address: '',
        introduction: '',
      },
      rules: {
        name: [
          { required: true, message: '请输入学生姓名', trigger: 'blur' },
          { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ],
        number: [
          { required: true, message: '请输入学生学号', trigger: 'blur' },
          { min: 14, max: 14, message: '长度为14个字符', trigger: 'blur' }
        ],
        sex: [
          { required: true, message: '请输入性别', trigger: 'blur' },
          { min: 1, max: 1, message: '请输入\"男\"或\"女\"', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '请输入学生邮箱', trigger: 'blur' },
          { min: 14, max: 14, message: '长度为14个字符', trigger: 'blur' }
        ]
      },
      options: [],
      value: [],
      list: [],
      loading: false,
      states: ["Alabama", "Alaska", "Arizona",
        "Arkansas", "California", "Colorado",
        "Connecticut", "Delaware", "Florida",
        "Georgia", "Hawaii", "Idaho", "Illinois",
        "Indiana", "Iowa", "Kansas", "Kentucky",
        "Louisiana", "Maine", "Maryland",
        "Massachusetts", "Michigan", "Minnesota",
        "Mississippi", "Missouri", "Montana",
        "Nebraska", "Nevada", "New Hampshire",
        "New Jersey", "New Mexico", "New York",
        "North Carolina", "North Dakota", "Ohio",
        "Oklahoma", "Oregon", "Pennsylvania",
        "Rhode Island", "South Carolina",
        "South Dakota", "Tennessee", "Texas",
        "Utah", "Vermont", "Virginia",
        "Washington", "West Virginia", "Wisconsin",
        "Wyoming"]
    };
  },
  mounted() {
    this.list = this.states.map(item => {
      return { value: `value:${item}`, label: `label:${item}` };
    });
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          alert('submit!');
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm() {
      this.$router.go(-1);
    },
    remoteMethod(query) {
      if (query !== '') {
        this.loading = true;
        setTimeout(() => {
          this.loading = false;
          this.options = this.list.filter(item => {
            return item.label.toLowerCase()
                .indexOf(query.toLowerCase()) > -1;
          });
        }, 200);
      } else {
        this.options = [];
      }
    }
  }
}
</script>

<template>
  <div class="registerNext">
    <h2>注册详细信息</h2>
    <el-breadcrumb separator="/" style="font-size: 20px">
      <el-breadcrumb-item :to="{ path: '/register' }">注册</el-breadcrumb-item>
      <el-breadcrumb-item>详细信息注册</el-breadcrumb-item>
    </el-breadcrumb>
    <el-form :model="student" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm register-next-form">
      <el-form-item label="学生学号" prop="number">
        <el-input v-model="student.number" placeholder="请输入学生学号"></el-input>
      </el-form-item>
      <el-form-item label="学生姓名" prop="name">
        <el-input v-model="student.name" placeholder="请选择学生姓名"></el-input>
      </el-form-item>
      <el-form-item label="学生性别" prop="sex">
        <el-input v-model="student.sex" placeholder="请输入学生性别"></el-input>
      </el-form-item>
      <el-form-item label="学生邮箱" prop="email">
        <el-input v-model="student.email" placeholder="请输入学生邮箱"></el-input>
      </el-form-item>
      <el-form-item label="学生出生日期" prop="birthDate">
        <el-date-picker type="date" v-model="student.birthDate" placeholder="请输入个人日期" style="width: 100%;"></el-date-picker>
      </el-form-item>
      <el-form-item label="学生所在地区" prop="address">
        <el-select
            v-model="value"
            multiple
            filterable
            remote
            reserve-keyword
            placeholder="请输入关键词"
            :remote-method="remoteMethod"
            :loading="loading"
            style="width: 100%">
          <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="学生个人介绍" prop="introduction">
        <el-input type="textarea" v-model="student.introduction" placeholder="请输入个人介绍"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm()">立即创建</el-button>
        <el-button @click="resetForm()">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<style scoped>
.register-next-form {
  width: 400px;
  margin: 0 auto;
}
</style>