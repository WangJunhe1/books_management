<script>
import "@/assets/css/header.css";
window.onscroll = function() {
    let img = document.getElementById('myImage');
    let blackDiv = document.getElementsByClassName('black')[0];
    let scrollTop = window.scrollY || document.documentElement.scrollTop;
      
      // 页面回到顶端时，照片放大
    if (scrollTop === 0) {
      img.style.width = '500px';
      blackDiv.style.height = '200px'; /* 高度变高 */
    } else { // 下滑时，照片变小，div高度恢复
      img.style.width = '400px';
      blackDiv.style.height = '100px'; /* 高度恢复 */
  }
};
export default {
  
  data() {
    return {
      activeIndex: '1',
      isLogin: this.$store.state.User.isLogin,
      user: {
        ...this.$store.state.User.user
      },
    };
  },
  methods: {
    loginOut() {
      localStorage.clear();
      this.$router.push('/login');
    }
  },
  
}
</script>
<template>
    <div class="head">
      <div class="black">
          <a href="/index" title="首页" class="img">
            <img id="myImage" src="@/assets/css/logo.png" style="width: 500px;" alt="Example Image">
          </a>
  
          <div class="main">
            <ul class="main-ul">
              <li class="main-ul-li"><a href="/index" class="menu-link">首页</a></li>
              <li class="main-ul-li"><a href="/index/bookDistribution" class="menu-link">馆藏分布</a></li>
              <li class="main-ul-li"><a href="/index/bookBorrow/1" class="menu-link">图书借阅</a></li>
              <li class="main-ul-li" v-if="isLogin">
                <a href="/user/userInfo" class="menu-link">
                  <i class="el-icon-user-solid"></i>个人信息
                </a>
              </li>
              <li class="main-ul-li" v-if="isLogin">
                <a href="/user/borrow" class="menu-link">
                  <i class="el-icon-reading"></i>我的借阅
                </a>
              </li>
              <li class="main-ul-li" v-if="isLogin">
                <a href="/user/comment" class="menu-link">
                  <i class="el-icon-chat-dot-round"></i>我的评论
                </a>
              </li>
              <li class="main-ul-li" v-if="isLogin">
                <a href="/login" class="menu-link" @click="loginOut()">
                  <div class="el-icon-arrow-left"></div>退出登录
                </a>
              </li>
              <li class="main-ul-li" v-else>
                <a href="/login" class="menu-link" @click="loginOut()">登录</a>
              </li>
            </ul>
          </div>     
      </div>
    </div>
  </template>
  



<style>
.main{
  flex-grow: 1; /* 使用flex-grow属性使得ul占据剩余空间 */
  text-align: right; /* 将整个main区域右对齐 */
  padding: 10px;
}
.main ul {
  text-align: right;
}

.main ul li {
  list-style-type: none;
  display: inline;
  font-size: 20px;
  margin-right: 30px;
  font-weight: bold;
  transition: font-size 0.3s, color 0.3s;
}

.main ul li:hover {
  font-size: 25px; /* 鼠标悬停时字体变大 */
}

.main a:hover {
  color: aqua; /* 鼠标悬停时字体颜色变为红色 */
}

.main a {
  color: white;
  text-decoration: none;
}


.head {
  position: relative;
  width: 100%;
  height: 500px;
  background-image: url('../assets/css/bgc.jpg');
  background-position: center;
  background-size: cover;
  background-repeat: no-repeat;
}

.black {
  position: absolute; /* 绝对定位，相对于父元素 .km */
  top: 0;
  left: 0;
  width: 100%;
  height: 200px; /* 初始高度 */
  display: flex;
  background: linear-gradient(to bottom, rgba(0, 0, 0, 0.8), transparent);
  z-index: 1000; /* 比 .km 更大的 z-index 值 */
  transition: height 0.5s;
  padding: 20px;
}
.content {
  padding-top: 240px;
}

img {
  width: 200px;
  transition: width 0.5s;
}
</style>