<script>
export default {
  data() {
    return {
      student: {
        studentNumber: '',
        studentName: '',
        studentSex: '',
        studentEmail: '',
        birthday: '',
        region: '',
        description: '',
      },
      rules: {
        studentName: [
          { required: true, message: '请输入学生姓名', trigger: 'blur' },
          { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ],
        studentNumber: [
          { required: true, message: '请输入学生学号', trigger: 'blur' },
          { min: 12, max: 12, message: '长度为12个字符', trigger: 'blur' }
        ],
        studentSex: [
          { required: true, message: '请输入性别', trigger: 'blur' },
          { min: 1, max: 1, message: '请输入\"男\"或\"女\"', trigger: 'blur' }
        ],
        studentEmail: [
          { required: true, message: '请输入学生邮箱', trigger: 'blur' },
        ]
      },
      value: [],
      list: [],
      loading: false,
      options: [
        {
          "value": "北京市",
          "label": "北京市",
          "children": [
            {
              "value": "东城区",
              "label": "东城区"
            },
            {
              "value": "西城区",
              "label": "西城区"
            },
            {
              "value": "朝阳区",
              "label": "朝阳区"
            },
            {
              "value": "丰台区",
              "label": "丰台区"
            },
            {
              "value": "石景山区",
              "label": "石景山区"
            },
            {
              "value": "海淀区",
              "label": "海淀区"
            },
            {
              "value": "门头沟区",
              "label": "门头沟区"
            },
            {
              "value": "房山区",
              "label": "房山区"
            },
            {
              "value": "通州区",
              "label": "通州区"
            },
            {
              "value": "顺义区",
              "label": "顺义区"
            },
            {
              "value": "昌平区",
              "label": "昌平区"
            },
            {
              "value": "大兴区",
              "label": "大兴区"
            },
            {
              "value": "怀柔区",
              "label": "怀柔区"
            },
            {
              "value": "平谷区",
              "label": "平谷区"
            },
            {
              "value": "密云区",
              "label": "密云区"
            },
            {
              "value": "延庆区",
              "label": "延庆区"
            }
          ]
        },
        {
          "value": "上海市",
          "label": "上海市",
          "children": [
            {
              "value": "黄浦区",
              "label": "黄浦区"
            },
            {
              "value": "徐汇区",
              "label": "徐汇区"
            },
            {
              "value": "长宁区",
              "label": "长宁区"
            },
            {
              "value": "静安区",
              "label": "静安区"
            },
            {
              "value": "普陀区",
              "label": "普陀区"
            },
            {
              "value": "虹口区",
              "label": "虹口区"
            },
            {
              "value": "杨浦区",
              "label": "杨浦区"
            },
            {
              "value": "闵行区",
              "label": "闵行区"
            },
            {
              "value": "宝山区",
              "label": "宝山区"
            },
            {
              "value": "嘉定区",
              "label": "嘉定区"
            },
            {
              "value": "浦东新区",
              "label": "浦东新区"
            },
            {
              "value": "金山区",
              "label": "金山区"
            },
            {
              "value": "松江区",
              "label": "松江区"
            },
            {
              "value": "青浦区",
              "label": "青浦区"
            },
            {
              "value": "奉贤区",
              "label": "奉贤区",
            }
          ]
        },
        {
          "value": "广东省",
          "label": "广东省",
          "children": [
            {
              "value": "广州市",
              "label": "广州市",
              "children": [
                {
                  "value": "天河区",
                  "label": "天河区"
                },
                {
                  "value": "越秀区",
                  "label": "越秀区"
                },
                {
                  "value": "荔湾区",
                  "label": "荔湾区"
                },
                {
                  "value": "海珠区",
                  "label": "海珠区"
                },
                {
                  "value": "白云区",
                  "label": "白云区"
                },
                {
                  "value": "黄埔区",
                  "label": "黄埔区"
                },
                {
                  "value": "番禺区",
                  "label": "番禺区"
                },
                {
                  "value": "花都区",
                  "label": "花都区"
                },
                {
                  "value": "南沙区",
                  "label": "南沙区"
                },
                {
                  "value": "萝岗区",
                  "label": "萝岗区"
                }
              ]
            },
            {
              "value": "深圳市",
              "label": "深圳市",
              "children": [
                {
                  "value": "福田区",
                  "label": "福田区"
                },
                {
                  "value": "罗湖区",
                  "label": "罗湖区"
                },
                {
                  "value": "南山区",
                  "label": "南山区"
                },
                {
                  "value": "宝安区",
                  "label": "宝安区"
                },
                {
                  "value": "龙岗区",
                  "label": "龙岗区"
                },
                {
                  "value": "盐田区",
                  "label": "盐田区"
                }
              ]
            }
          ]
        },
        {
          "value": "浙江省",
          "label": "浙江省",
          "children": [
            {
              "value": "杭州市",
              "label": "杭州市",
              "children": [
                {
                  "value": "上城区",
                  "label": "上城区"
                },
                {
                  "value": "下城区",
                  "label": "下城区"
                },
                {
                  "value": "西湖区",
                  "label": "西湖区"
                },
                {
                  "value": "拱墅区",
                  "label": "拱墅区"
                },
                {
                  "value": "滨江区",
                  "label": "滨江区"
                },
                {
                  "value": "萧山区",
                  "label": "萧山区"
                },
                {
                  "value": "余杭区",
                  "label": "余杭区"
                }
              ]
            },
            {
              "value": "宁波市",
              "label": "宁波市",
              "children": [
                {
                  "value": "海曙区",
                  "label": "海曙区"
                },
                {
                  "value": "江北区",
                  "label": "江北区"
                },
                {
                  "value": "北仑区",
                  "label": "北仑区"
                }
              ]
            }
          ]
        },
        {
          "value": "河南省",
          "label": "河南省",
          "children": [
            {
              "value": "郑州市",
              "label": "郑州市",
              "children": [
                {
                  "value": "中原区",
                  "label": "中原区"
                },
                {
                  "value": "二七区",
                  "label": "二七区"
                },
                {
                  "value": "管城回族区",
                  "label": "管城回族区"
                },
                {
                  "value": "金水区",
                  "label": "金水区"
                },
                {
                  "value": "上街区",
                  "label": "上街区"
                },
                {
                  "value": "惠济区",
                  "label": "惠济区"
                }
              ]
            },
            {
              "value": "开封市",
              "label": "开封市",
              "children": [
                {
                  "value": "龙亭区",
                  "label": "龙亭区"
                },
                {
                  "value": "鼓楼区",
                  "label": "鼓楼区"
                },
                {
                  "value": "禹王台区",
                  "label": "禹王台区"
                },
                {
                  "value": "顺河回族区",
                  "label": "顺河回族区"
                }
              ]
            },
            {
              "value": "洛阳市",
              "label": "洛阳市",
              "children": [
                {
                  "value": "西工区",
                  "label": "西工区"
                },
                {
                  "value": "老城区",
                  "label": "老城区"
                },
                {
                  "value": "瀍河回族区",
                  "label": "瀍河回族区"
                },
                {
                  "value": "涧西区",
                  "label": "涧西区"
                }
              ]
            }
          ]
        }
      ]
    };
  },
  mounted() {
  },
  methods: {
    submitForm() {
      if (this.student.region !== null) {
        this.student.region = this.student.region.join("/");
      }
      this.$axios.post('http://localhost:5000/student/register', {
        studentName: this.student.studentName,
        studentNumber: this.student.studentNumber,
        studentSex: this.student.studentSex,
        studentEmail: this.student.studentEmail,
        birthday: this.student.birthday,
        region: this.student.region,
        description: this.student.description,
      }).then(() => {
        this.$message({
          message: '学生信息注册成功，请重新注册账号',
          type: 'success'
        })
        this.$router.push('/register');
      })
    },
    resetForm() {
      this.$router.go(-1);
    },
  }
}
</script>

<template>
  <div id="registerNext">
    <div class="registerNext">
      <h2 class="registerNext-title">注册详细信息</h2>
      <el-form :model="student" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm register-next-form">
        <el-form-item label="学生学号" prop="studentNumber">
          <el-input v-model="student.studentNumber" placeholder="请输入学生学号"></el-input>
        </el-form-item>
        <el-form-item label="学生姓名" prop="studentName">
          <el-input v-model="student.studentName" placeholder="请选择学生姓名"></el-input>
        </el-form-item>
        <el-form-item label="学生性别" prop="studentSex">
          <el-input v-model="student.studentSex" placeholder="请输入学生性别"></el-input>
        </el-form-item>
        <el-form-item label="学生邮箱" prop="studentEmail">
          <el-input v-model="student.studentEmail" placeholder="请输入学生邮箱"></el-input>
        </el-form-item>
        <el-form-item label="学生出生日期" prop="birthDate">
          <el-date-picker type="date" v-model="student.birthday" value-format="yyyy-MM-dd" placeholder="请输入个人日期" style="width: 100%;"></el-date-picker>
        </el-form-item>
        <el-form-item label="学生所在地区" prop="address">
          <el-cascader :options="options" clearable style="width: 100%;" v-model="student.region"></el-cascader>
        </el-form-item>
        <el-form-item label="学生个人介绍" prop="introduction">
          <el-input type="textarea" v-model="student.description" placeholder="请输入个人介绍"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm()">立即创建</el-button>
          <el-button @click="resetForm()">取消</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<style>
#registerNext {
  width: 100vw;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 12px;
  background-color: #ecf0f3;
  color: #a0a5a8;
}

.registerNext {
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

.registerNext-title {
  font-size: 25px;
  color: black;
  text-align: center;
}

.register-next-form {
  width: 400px;
  margin: 0 auto;
}


</style>