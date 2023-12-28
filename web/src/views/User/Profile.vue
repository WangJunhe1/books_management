<script>
  export default {
    data() {
      return {
        fileImg: null,
        student: {
          studentName: "",
          studentNumber: "",
          studentSex: "",
          description: "",
          birthday: "",
          region: "",
          portrait: "",
        },
        isChange: false,
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
      }
    },
    methods: {
      changeIsTrue() {
        this.isChange = true;
        this.student.region = this.student.region.split("/");
        console.log(this.student.region);
      },
      changeIsFalse() {
        this.isChange = false;
        console.log(this.student.region);
        this.student.region = this.student.region.join("/");
        console.log(this.student.birthday)
      },
      saveStudent() {
        console.log(this.student.region)
        this.student.region = this.student.region.join("/");
        console.log(this.student.birthday)
        if (this.fileImg !== null) {
          this.$axios.post('http://localhost:5000/image/upload',
              {
                "studentNumber": this.student.studentNumber,
                "file": this.fileImg
              },
              {
                headers: {
                  'token': this.$store.state.User.token,
                  'Content-Type': 'multipart/form-data'
                }
              }
          )
        }
        this.$axios.put('http://localhost:5000/student/update',
            {
              userId: this.student.userId,
              studentNumber: this.student.studentNumber,
              studentName: this.student.studentName,
              studentSex: this.student.studentSex,
              region: this.student.region,
              description: this.student.description,
              birthday: this.student.birthday,
            }, {
              headers: {
                'token': this.$store.state.User.token,
              }
            }
        ).then(() => {
          //  数据回显
          this.$axios.get('http://localhost:5000/student/getStudent', {
            headers: {
              'token': this.$store.state.User.token,
            }
          }).then(res => {
            this.student = res.data;

            console.log(this.student.birthday)
            this.student.birthday = this.student.birthday.substring(0, 10);
            console.log(this.student.birthday)

            setTimeout(() => {
              this.fileImg = null;
              this.isChange = false;
            }, 2000)
          })
        })
      },
      changeDisplayBlock() {
        let icon = document.querySelector('.iconfont');
        icon.style.display = 'flex';
      },
      changeDisplayNone() {
        let icon = document.querySelector('.iconfont');
        icon.style.display = 'none';
      },
      fileInputClick() {
        let fileInput = document.querySelector('.fileInput');
        fileInput.click();
      },
      fileInputChange(event) {
        const files = event.target.files;
        if (files.length > 1) {
          // 如果文件数组长度大于 1，则取消选择
          const input = event.target;
          input.value = "";
          input.type = "file";
        }
        this.fileImg = event.target.files[0];
        console.log(this.fileImg);
        const reader = new FileReader;
        reader.readAsDataURL(this.fileImg);
        reader.onload = () => {
          let fileImg = document.querySelector('.fileImg');
          fileImg.src = reader.result;
        }
      },
    },
    mounted() {
      this.$axios.get('http://localhost:5000/student/getStudent', {
        headers: {
          'token': this.$store.state.User.token,
        }
      }).then(res => {
        this.student = res.data;
        console.log(this.student.birthday)
        this.student.birthday = this.student.birthday.substring(0, 10);
        console.log(this.student.birthday)
      })
    }
  }
</script>

<template>
  <div id="profile">
    <el-descriptions title="个人资料" :column="1" style="font-size: 20px; margin-top: 30px" v-if="!isChange">
      <template slot="extra">
        <el-button type="primary" size="small" @click="changeIsTrue()">修改</el-button>
      </template>
      <el-descriptions-item label="头像">
        <img class="studentImg" :src="student.portrait" :alt="student.studentName" style="width: 100px; height: 100px; border-radius: 50%"></img>
      </el-descriptions-item> <br/>
      <el-descriptions-item label="用户昵称">{{student.studentName}}</el-descriptions-item>
      <el-descriptions-item label="用户ID">{{student.studentNumber}}</el-descriptions-item>
      <el-descriptions-item label="性别">{{student.studentSex}}</el-descriptions-item>
      <el-descriptions-item label="所在地区">{{student.region}}</el-descriptions-item>
      <el-descriptions-item label="出生日期">{{student.birthday}}</el-descriptions-item>
      <el-descriptions-item label="个人简介">{{student.description}}</el-descriptions-item>
    </el-descriptions>

    <el-form ref="form" :model="student" label-width="80px" v-else>
      <el-form-item label="头像">
        <input type="file" class="fileInput" accept="image/*" @change="fileInputChange"/>
        <div class="fileBox">
          <img class="fileImg" :src="student.portrait" alt="头像图标">
          <div class="change"
               @mouseenter="changeDisplayBlock()"
               @mouseleave="changeDisplayNone()"
               @click="fileInputClick()">
            <span class="iconfont"> + </span>
          </div>
        </div>
      </el-form-item>
      <el-form-item label="用户昵称">
        <el-input v-model="student.studentName" placeholder="请输入用户名"></el-input>
      </el-form-item>
      <el-form-item label="用户ID">
        <el-input v-model="student.studentNumber" disabled></el-input>
      </el-form-item>
      <el-form-item label="性别">
        <el-select v-model="student.studentSex" placeholder="请选择性别">
          <el-option label="男" value="男"></el-option>
          <el-option label="女" value="女"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="所在地区">
        <el-cascader
            placeholder="请选择所在地区"
            v-model="student.region"
            :options="options"
            filterable></el-cascader>
      </el-form-item>
      <el-form-item label="出生日期">
        <el-col :span="11">
          <el-date-picker type="date" placeholder="选择日期" value-format="yyyy-MM-dd" v-model="student.birthday" style="width: 100%;"></el-date-picker>
        </el-col>
      </el-form-item>
      <el-form-item label="个人简介">
        <el-input type="textarea" placeholder="请输入个人简介" v-model="student.description"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="saveStudent()">保存</el-button>
        <el-button  @click="changeIsFalse()">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<style>
.fileInput {
  display: none;
}
.fileBox {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  position: relative;
}
.fileImg {
  width: 100%;
  height: 100%;
  border-radius: 50%;
  user-select: none;
  pointer-events: none;
}
.change {
  position: absolute;
  width: 100%;
  height: 100%;
  line-height: 100px;
  border-radius: 50%;
  top: 0;
  left: 0;
  z-index: 10;
}

.change:hover {
  background-color: rgb(0, 0, 0, 0.5);
  z-index: 10000;
}

.change .iconfont {
  color: white;
  display: none;
  font-size: 20px;
  justify-content: center;
  align-items: center;
  user-select: none;
  pointer-events: none;
}

</style>