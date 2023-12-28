<script>
import "@/assets/css/BookBorrow.css";
export default {
  data() {
    return {
      bookTypeList: [],
      books: [],
      type: "个人成长",
    }
  },
  methods: {
    bookDetails(item) {
      localStorage.setItem('book',JSON.stringify(item));
      this.$store.dispatch('Book/setBookAction', this.book);
      this.$router.push('/index/bookDetails/'+item.bookId);
    },
    changeBorrowPage(item, index) {
      console.log(1)
      this.type = item.bookType;
      console.log(this.type);
      let rankingList = document.querySelectorAll('.ranking-list-item');
      for (let i = 0; i < rankingList.length; i++) {
        rankingList[i].classList.remove('ranking-list-item-active');
        if (index - 1 === i) {
          rankingList[i].classList.add('ranking-list-item-active');
        }
      }
      this.$axios.get(`http://localhost:5000/book/${item.bookTypeId}`).then(res => {
          this.books = res.data;
      })
    }
  },
  mounted() {
    this.$axios.get('http://localhost:5000/bookType/getBookType').then(res => {
      this.bookTypeList = res.data;
    });
    this.$axios.get(`http://localhost:5000/book/1`).then(res => {
      this.books = res.data;
    })
    let rankingList = document.querySelectorAll('.ranking-list-item');
    rankingList[0].classList.add('ranking-list-item-active');
  },
}
</script>

<template>
  <div id="bookBorrow">
    <div class="container" style="background-color:#fff;">
      <el-container>
        <el-aside width="200px" class="el-aside" style="background-color:whitesmoke;">
          <div class="nav-slide">
            <ul class="ranking-list">
              <li class="ranking-list-item" v-for="item in bookTypeList" :key="item.bookTypeId">
                <span class="ranking-list-item-link" @click="changeBorrowPage(item, item.bookTypeId)">{{item.bookType}}</span>
              </li>
            </ul>
          </div>
        </el-aside>
        <el-container class="main-container">
          <el-header class="main-header" style="background-color:whitesmoke;">
            <span class="header-title" v-html="type"></span>
          </el-header>
          <el-main style="padding: 0">
            <div class="ranking-content-list">
              <div class="ranking-content-list-item" v-for="(item, index) in books" :key="item.id">
                <span class="font-book" @click="bookDetails(item)" ></span>
                <div class="content-list-item-info">
                  <p class="content-list-item-info-index">{{index + 1}}</p>
                  <div class="content-list-item-info-cover">
                    <img :src="item.bookImg" :alt="item.bookName">
                  </div>
                  <div class="content-list-item-info-info">
                    <p class="title">{{item.bookName}}</p>
                    <p class="author">{{item.bookAuthor}}</p>
                    <p class="desc">{{item.bookDesc}}</p>
                  </div>
                </div>
              </div>
            </div>
          </el-main>
        </el-container>
      </el-container>
    </div>
  </div>
</template>

<style>
.el-aside {
  height: 580px;
}
.main-container{
  height: 580px;
}
.nav-slide {
  padding: 0;
  height: 100%;
  width: 100%;
  box-sizing: border-box;
  border-radius: 0;
  border-right: 1px solid black;
}

.nav-slide .ranking-list {
  padding: 0;
  height: 100%;
  width: 100%;
  overflow: auto;
  text-align: left;
}

.nav-slide .ranking-list .ranking-list-item{
  margin: 0;
  padding: 0 18px;
  height: 56px;
  display: block;
  font-size: 18px;
  font-family: "SourceHanSerifCN-Bold",PingFang SC,-apple-system,SF UI Text,Lucida Grande,STheiti,Microsoft YaHei,sans-serif;
  line-height: 56px;
  color: #000;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  word-break: break-all;
  word-wrap: normal;
  border-radius: 4px 0 0 4px;
}


.nav-slide .ranking-list .ranking-list-item:hover {
  background-color: #4a7ab0;
}

.ranking-list-item-active {
  background-color: #4a7ab0;
}

.nav-slide .ranking-list .ranking-list-item .ranking-list-item-link {
  display: block;
  text-decoration: none;
  color: black;
}

.main-container .main-header {
  width: 100%;
  height: 60px;
  border-bottom: 1px solid black;
}

.main-container .main-header .header-title {
  height: 60px;
  line-height: 60px;
  color: #000;
  font-size: 25px;
  font-weight: bolder;
}

.ranking-content-list {
  width: 100%;
  padding: 0 0 20px;
  flex-direction: row;
  position: relative;
}

.ranking-content-list-item {
  background-color: whitesmoke;
  position: relative;
}

.ranking-content-list-item:hover {
  background-color: #4a7ab0;
}

.ranking-content-list-item .font-book {
  position: absolute;
  width: 100%;
  height: 100%;
  z-index: 1;
}

.content-list-item-info {
  display: flex;
  align-items: center;
  padding: 20px 0;
  overflow: hidden;
}

.content-list-item-info-index{
  flex-shrink: 0;
  width: 76px;
  font-size: 20px;
  font-family: DIN-Medium,PingFang SC,-apple-system,SF UI Text,Lucida Grande,STheiti,Microsoft YaHei,sans-serif;
  text-align: center;
  color: #b2b4b8;
}

.content-list-item-info-cover {
  flex-shrink: 0;
  width: 108px!important;
  height: 156px!important;
}

.content-list-item-info-cover img {
  vertical-align: top;
  width: 100%;
  height: 100%;
  background-color: #d8d8d8;
  -o-object-fit: cover;
  object-fit: cover;
}

.content-list-item-info-info {
  flex: 1;
  padding: 0 32px;
}

.content-list-item-info-info .title {
  padding-right: 90px;
  font-size: 18px;
  font-family: "SourceHanSerifCN-Bold",PingFang SC,-apple-system,SF UI Text,Lucida Grande,STheiti,Microsoft YaHei,sans-serif;
  color: #000;
  line-height: 26px;
  overflow: hidden;
  height: 52px;
  display: -webkit-box;
  display: -moz-box;
  text-overflow: ellipsis;
  -webkit-line-clamp: 2;
  -moz-line-clamp: 2;
  line-clamp: 2;
  -webkit-box-orient: vertical;
  -webkit-text-size-adjust: none;
  box-orient: vertical;
  height: auto;
  max-height: 52px;
}

.content-list-item-info-info .author {
  margin-top: 10px;
  font-size: 14px;
  color: #000;
  line-height: 21px;
  overflow: hidden;
  height: 21px;
  display: -webkit-box;
  display: -moz-box;
  text-overflow: ellipsis;
  -webkit-line-clamp: 1;
  -moz-line-clamp: 1;
  line-clamp: 1;
  -webkit-box-orient: vertical;
  -webkit-text-size-adjust: none;
  box-orient: vertical;
  height: auto;
  max-height: 21px;
}

.content-list-item-info-info .desc {
  margin-top: 8px;
  font-size: 14px;
  color: #000;
  line-height: 22px;
  overflow: hidden;
  height: 44px;
  display: -webkit-box;
  display: -moz-box;
  text-overflow: ellipsis;
  -webkit-line-clamp: 2;
  -moz-line-clamp: 2;
  line-clamp: 2;
  -webkit-box-orient: vertical;
  -webkit-text-size-adjust: none;
  box-orient: vertical;
  height: auto;
  max-height: 44px;
}



</style>