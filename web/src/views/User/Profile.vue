<script>
  export default {
    data() {
      return {
        user: {
          username: "seven",
          userId: "seven",
          sex: "男",
          introduction: "在慢慢学习中",
          password: "",
          birthday: "2004-04-14",
          area: "河南省/郑州市/金水区",
          img: "https://avatars0.githubusercontent.com/u/22588905?s=460&v=4",
        },
        isChange: false,
        options: [
          {
            value: '河南省',
            label: '河南省',
            children: [
              {
                value: '郑州市',
                label: '郑州省',
                children: [
                  {
                    value: '金水区',
                    label: '金水区'
                  },
                  {
                    value: '管城回族区',
                    label: '管城回族区'
                  },
                ]
              }
            ]
          }
        ],
      }
    },
    methods: {
      changeUser() {
        this.isChange = !this.isChange;
      },
    },
    watch: {
      'user.area': {
        handler() {
          this.user.area = this.user.area.join("/");
        },
      },
      'user.birthday': {
        handler() {
          this.user.birthday = this.user.birthday.toLocaleDateString();
        },
      }
    },
    mounted() {
      this.$axios.get('http://localhost:5000//student/getStduent', {
        headers: {
          'token': this.$store.state.User.token,
        }
      }).then(res => {
        this.student = res.data.data;
      })
    }
  }
</script>

<template>
  <div id="profile">
    <el-descriptions title="个人资料" :column="1" style="font-size: 20px; margin-top: 30px" v-if="!isChange">
      <template slot="extra">
        <el-button type="primary" size="small" @click="changeUser()">修改</el-button>
      </template>
      <el-descriptions-item label="头像">
        <el-avatar :src="user.img" size="large" style="width: 100px; height: 100px;"></el-avatar>
      </el-descriptions-item> <br/>
      <el-descriptions-item label="用户昵称">{{user.username}}</el-descriptions-item>
      <el-descriptions-item label="用户ID">{{user.userId}}</el-descriptions-item>
      <el-descriptions-item label="性别">{{user.sex}}</el-descriptions-item>
      <el-descriptions-item label="所在地区">{{user.area}}</el-descriptions-item>
      <el-descriptions-item label="出生日期">{{user.birthday}}</el-descriptions-item>
      <el-descriptions-item label="个人简介">{{user.introduction}}</el-descriptions-item>
    </el-descriptions>

    <el-form ref="form" :model="user" label-width="80px" v-else>
      <el-form-item label="用户昵称">
        <el-input v-model="user.username" placeholder="请输入用户名"></el-input>
      </el-form-item>
      <el-form-item label="用户ID">
        <el-input v-model="user.userId" disabled></el-input>
      </el-form-item>
      <el-form-item label="性别">
        <el-select v-model="user.sex" placeholder="请选择性别">
          <el-option label="男" value="男"></el-option>
          <el-option label="女" value="女"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="所在地区">
        <el-cascader
            placeholder="请选择所在地区"
            v-model="user.area"
            :options="options"
            filterable></el-cascader>
      </el-form-item>
      <el-form-item label="出生日期">
        <el-col :span="11">
          <el-date-picker type="date" placeholder="选择日期" v-model="user.birthday" style="width: 100%;"></el-date-picker>
        </el-col>
      </el-form-item>
      <el-form-item label="个人简介">
        <el-input type="textarea" placeholder="请输入个人简介" v-model="user.introduction"></el-input>
      </el-form-item>
      <el-form-item label="头像"> <!-- 没有解决 -->
        <el-upload
            class="upload-demo"
            drag
            action="https://jsonplaceholder.typicode.com/posts/"
            multiple>
          <i class="el-icon-upload"></i>
          <div class="el-upload__text">将图片拖到此处，或<em>点击上传</em></div>
          <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>
        </el-upload>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="changeUser()">保存</el-button>
        <el-button  @click="changeUser()">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<style>

</style>