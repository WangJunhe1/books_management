<script>
export default {
  data() {
    return {
      isSelected: false,
      page: 1,
      input: "",
      newInput: "",
      myBorrowBooks: [],
      recommendBooks: [],
      classifyList: [],
      searchBooks: []
    }
  },
  methods: {
    //  点击换一批
    nextPage() {
      this.page++;
      this.$axios.get(`http://localhost:5000/book/nextPage/${this.page}`).then(res => {
        this.recommendBooks = res.data.data;
        console.log(this.recommendBooks);
      })
    },
    // 跳转图书详情页面
    bookDetails(item) {
      localStorage.setItem('book',JSON.stringify(item));
      this.$store.dispatch('Book/setBookAction', localStorage.setItem('book',JSON.stringify(item)));
      this.$router.push(`/index/bookDetails/${item.bookId}`);
    },
    getSearchBooks() {
      if (this.input === ""){
        this.$message({
          message: "搜索内容不能为空",
          type: "warning"
        });
      } else {
        this.$axios.post('http://localhost:5000/book/searchPage', {
          searchKey: this.input
        }).then(res => {
          console.log(res.data)
          if (res.data.length === 0) {
            console.log(1)
            this.$message({
              message: "没有找到相关书籍内容",
              type: "warning"
            })
          }
          for (let i = 0; i < res.data.length; i++) {
            // 使用更安全的方法替换字符串
            const safeReplace = (str, search, replace) => {
              if (str) {
                return str.replace(new RegExp(search, 'g'), replace);
              }
              return str;
            };

            res.data[i].bookName = safeReplace(res.data[i].bookName, this.input, `<span style="color: deepskyblue">${this.input}</span>`);
            res.data[i].bookAuthor = safeReplace(res.data[i].bookAuthor, this.input, `<span style="color: deepskyblue">${this.input}</span>`);
            res.data[i].bookDesc = safeReplace(res.data[i].bookDesc, this.input, `<span style="color: deepskyblue">${this.input}</span>`);
          }
          this.searchBooks = res.data;
        })
      }
    },
    changeIsSelectedTure() {
      this.isSelected = true;
    },
    changeIsSelectedFalse() {
      this.isSelected = false;
    }
  },
  mounted() {
    if (this.$store.state.User.token !== null) {
      this.$axios.get('http://localhost:5000/borrow/myBorrow',
          {
            headers: {
              'token': this.$store.state.User.token
            }
          }
      ).then(res => {
        this.myBorrowBooks = res.data;
        this.loading = false;
      })
    } else {

    }
    this.$axios.get('http://localhost:5000/bookType/getBookType').then(res => {
      this.classifyList = res.data;
    });

    this.$axios.get(`http://localhost:5000/book/nextPage/${this.page}`).then(res => {
      console.log(res)
      this.recommendBooks = res.data.rows;
      this.searchBooks = this.recommendBooks;
    })

  }
}
</script>

<template>
  <div id="home">
    <div class="home">
      <div class="container">
        <div class="header">
          <div class="input"
               @mouseleave="changeIsSelectedFalse()"
               @mouseenter="changeIsSelectedTure()">
            <el-input
                class="search-input"
                placeholder="请输入内容"
                prefix-icon="el-icon-search"
                v-model="input"
                clearable>
            </el-input>
            <el-button type="primary" @click="getSearchBooks()" icon="el-icon-arrow-right" class="search-input-button"></el-button>
            <div class="search_result_global_list" v-if="isSelected === true">
              <div class="search_result_global_item" v-for="item in searchBooks" :key="item.bookId">
                <span class="search_result_global_bookLink" @click="bookDetails(item)">
                  <div class="search_result_global_bookBlock">
                    <div class="search_result_global_bookCover">
                      <img :src="item.bookImg" :alt="item.bookName" class="wr_bookCover_img" >
                    </div>
                    <div class="search_result_global_bookInfo">
                      <p class="search_result_global_bookTitle" v-html="item.bookName"></p>
                      <p class="search_result_global_bookAuthor" v-html="item.bookAuthor"></p>
                      <p class="search_result_global_bookContent" v-html="item.bookDesc"></p>
                    </div>
                  </div>
                </span>
              </div>
            </div>
          </div>
        </div>
        <div class="banner">
          <div class="myBorrow">
            <h2 class="header-borrow bookshelf-title" v-if="myBorrowBooks.length !== 0">
              我的借阅
              <router-link class="right-link" to="/user/comment">
                查看我的借阅&gt
              </router-link>
            </h2>
            <div class="borrow-list">
              <div class="borrow-bookshelf" v-for="item in myBorrowBooks" :key="item.bookId">
                <span class="borrow-bookshelf-turn" @click="bookDetails(item)"></span>
                <div class="book-allInfo">
                  <div class="book-img">
                    <img :src="item.bookImg" :alt="item.bookName" class="borrow-img">
                  </div>
                  <div class="book-info">
                    <div class="book-name">{{item.bookName}}</div>
                    <div class="book-author">{{item.bookAuthor}}</div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="recommend">
            <h2 class="header-recommend bookshelf-title">
              猜你喜欢
              <span class="right-link" @click="nextPage()">
                换一批
              </span>
            </h2>
            <div class="recommend-list">
              <div class="recommend-item" v-for="item in recommendBooks" :key="item.bookId">
                <span class="recommend-item-turn" @click="bookDetails(item)"></span>
                <div class="item-allInfo">
                  <div class="book-img">
                    <img :src="item.bookImg" :alt="item.bookName" class="borrow-img">
                  </div>
                  <div class="book-info">
                    <div class="book-name">{{item.bookName}}</div>
                    <div class="book-author">{{item.bookAuthor}}</div>
                    <div class="book-desc">{{item.bookDesc}}</div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="footer">
        <div class="book-classify">
          <h2 class="book-classify-title bookshelf-title">
            分类
          </h2>
          <ul class="classify-list-body">
            <li class="classify-list-item" v-for="item in classifyList">
              <router-link :to="{name: 'bookBorrow', params: {bookTypeID: item.bookTypeId}}">{{item.bookType}} * {{item.bookNumber}}本</router-link>
            </li>
            <li class="classify-list-item">
              <router-link to="/index/bookBorrow">查看全部 * 19个</router-link>
            </li>
          </ul>

        </div>
      </div>

    </div>
  </div>

</template>

<style>
#home .header {
  padding: 10px;
  width: 100%;
}

#home .header .input {
  margin: 30px auto;
  width: 60%;
  position: relative;
}

.input .search-input-button {
  position: absolute;
  height: 50px;
  right: 0;
  border-radius: 25px;
}

#home .header .el-input__inner {
  height: 50px;
  line-height: 50px;
  border-radius: 25px
}

.banner {
  margin: 40px auto;
}

.header-borrow {
  position: relative;
  font-size: 25px;
  color: brown;
}

.header-borrow .right-link {
  position: absolute;
  text-decoration: none;
  right: 0;
}

#home .banner {
  width: 100%;
  height: 100%;
}

#home .myBorrow {
  width: 100%;
}

.bookshelf-title {
  width: 100%;
  position: relative;
  font-size: 25px;
}

.right-link {
  position: absolute;
  right: 0;
  font-size: 20px;
  color: brown;
}

.right-link:hover {
  color: #409EFF;
}

.myBorrow .borrow-list {
  width: 100%;
  height: 100%;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
}

.myBorrow .borrow-bookshelf:hover {
  transform: scale(1.1);
}

.myBorrow .borrow-bookshelf .borrow-bookshelf-turn {
  position: absolute;
  top: 0;
  width: 100%;
  height: 100%;
  z-index: 1;
}

.myBorrow .borrow-bookshelf .book-allInfo {
  display: table;
  padding: 24px;
  box-sizing: border-box;
}

.myBorrow .borrow-bookshelf .borrow-img {
  display: table-cell;
  text-align: left;
  vertical-align: middle;
  width: 84px;
  height: 121px;
}
.myBorrow .borrow-bookshelf .book-info {
  padding: 0 0 0 24px;
  display: table-cell;
  vertical-align: middle;
}

.myBorrow .borrow-bookshelf .book-info .book-name {
  font-size: 16px;
  color: brown;
  font-weight: 500;
}

.myBorrow .borrow-bookshelf .book-info .book-author {
  margin-top: 10px;
  font-size: 14px;
  color: brown;
}

.recommend {
  width: 100%;
  color: brown;
}

.recommend .recommend-list {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
}




.recommend .recommend-list .recommend-item:hover {
  transform: scale(1.1);
}

.recommend .recommend-list .recommend-item .recommend-item-turn{
  position: absolute;
  top: 0;
  width: 100%;
  height: 100%;
  z-index: 1;
}

.recommend .recommend-list .recommend-item .item-allInfo {
  overflow: hidden;
  padding: 0 18px;
  box-sizing: content-box;
  text-align: center;
}

.item-allInfo .book-img {
  width: 96px;
  height: 139px;
  margin: 40px auto 0;
}

.item-allInfo .book-img img {
  vertical-align: top;
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.item-allInfo .book-info {
  text-align: center;
  display: block;
}
.item-allInfo .book-info .book-name {
  margin-top: 24px;
  font-size: 18px;
  font-family: "SourceHanSerifCN-Bold",PingFang SC,-apple-system,SF UI Text,Lucida Grande,STheiti,Microsoft YaHei,sans-serif;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  word-break: break-all;
  word-wrap: normal;
  color: brown;
}

.item-allInfo .book-info .book-author {
  margin-top: 12px;
  font-size: 14px;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  word-break: break-all;
  word-wrap: normal;
  color: saddlebrown;
}

.item-allInfo .book-info .book-desc {
  padding: 0 10px;
  margin-top: 10px;
  font-size: 13px;
  font-family: DIN-Regular,PingFang SC,-apple-system,SF UI Text,Lucida Grande,STheiti,Microsoft YaHei,sans-serif;
  color: brown;
  line-height: 21px;
  overflow: hidden;
  max-height: 42px;
}
.home{
  background-color: whitesmoke;
}
.myBorrow .borrow-bookshelf {
  position: relative;
  height: 169px;
  overflow: hidden;
  border-radius: 12px;
  width: 23.5%;
  transition: all .2s ease-in-out;
  background-color: whitesmoke;
  color: brown;
}
/*书鼠标悬浮变色*/
.myBorrow .borrow-bookshelf:hover {
  background-color: white;
}

.recommend .recommend-list .recommend-item {
  position: relative;
  height: 352px;
  border-radius: 12px;
  width: 23.5%;
  transition: all .2s ease-in-out;
  background-color: whitesmoke;
}
/*书鼠标悬浮变色*/
.recommend .recommend-list .recommend-item:hover{
  background-color: white;
}

.book-classify{
  margin: 30px auto;
  width: 79%;
}
.book-classify h2{
  color: brown;
}
.book-classify ul{
  width: 100%;
  padding: 0;
  list-style: none;
  text-align: left;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
}
.book-classify ul li {
  padding-left: 10px;
  width: 24%;
  height: 50px;
  border-radius: 0;
  border: solid hsla(0,0%,100%,.05);
  border-width: 0 0 1px;

}

.book-classify a {
  text-decoration: none;
  color: brown;
  height: 100%; /* 让a标签高度100%填充li元素 */
  display: flex; /* 使用flex布局 */
  align-items: center; /* 实现垂直居中 */
}

.book-classify ul li:hover{
  background-color: white;
}

.search_result_global_list {
  position: absolute;
  float: right;
  z-index: 100;
  display: block;
  border-radius: 12px;
  background-color: white;
  height: 500px;
  overflow-y: scroll;
  width: 100%;
  margin-bottom: 20px;
}

::-webkit-scrollbar {
  display: none;
}

.search_result_global_item:first-child {
  border-top-left-radius: 12px;
  border-top-right-radius: 12px;
}

.search_result_global_item:last-child {
  border-bottom-left-radius: 12px;
  border-bottom-right-radius: 12px;
}

.search_result_global_item {
  padding: 24px;
  cursor: pointer;
  border-radius: 0;
  border: solid hsla(0,0%,100%,.05);
  border-width: 0 0 1px;
}

.search-none {
  width: 100%;
  height: 100%;
  color: #4a7ab0;
  text-align: center;
  align-items: center;
  z-index: 10000;
}

.search_result_global_bookLink {
  display: block;
  user-select: none;
}

.search_result_global_item {
  padding: 24px;
  cursor: pointer;
  border-radius: 0;
  border: solid hsla(0,0%,100%,.05);
  border-width: 0 0 1px;
}

.search_result_global_item:hover {
  background-color: wheat;
}

.search_result_global_bookCover {
  width: 72px!important;
  height: 103px!important;
  float: left;
  display: block;
  width: 120px;
  height: 174px;
  box-shadow: 0 2px 16px rgba(0,0,0,.08);
  background: #d8d8d8;
  position: relative;
}

.wr_bookCover_img {
  vertical-align: top;
  width: 100%;
  height: 100%;
  background-color: #d8d8d8;
  -o-object-fit: cover;
  object-fit: cover;
}

.search_result_global_bookInfo {
  padding: 0 0 0 94px;
}

.search_result_global_bookTitle {
  padding-top: 3px;
  font-size: 16px;
  font-family: "SourceHanSerifCN-Bold",PingFang SC,-apple-system,SF UI Text,Lucida Grande,STheiti,Microsoft YaHei,sans-serif;
  color: brown;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  word-break: break-all;
  word-wrap: normal;
}

.search_result_global_bookAuthor {
  font-size: 13px;
  margin-top: 6px;
  color: saddlebrown;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  word-break: break-all;
  word-wrap: normal;
}

.search_result_global_bookContent {
  margin-top: 6px;
  font-size: 14px;
  color: brown;
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