<script>

export default {
  data() {
    return {
      page: 1,
      input: "",
      myBorrowBooks: [
        {
          id: 1,
          name: "《JavaScript权威指南》",
          author: "Nicholas C. Zakas",
          desc: "JavaScript权威指南，适合初步学习js的小白和复习使用",
          img: "https://img3.doubanio.com/lpic/s1167614.jpg",
        },
        {
          id: 2,
          name: "《JavaScript权威指南》",
          author: "Nicholas C. Zakas",
          desc: "JavaScript权威指南，适合初步学习js的小白和复习使用",
          img: "https://img3.doubanio.com/lpic/s1167614.jpg",
        },
        {
          id: 3,
          name: "《JavaScript权威指南》",
          author: "Nicholas C. Zakas",
          desc: "JavaScript权威指南，适合初步学习js的小白和复习使用",
          img: "https://img3.doubanio.com/lpic/s1167614.jpg",
        },
        {
          id: 4,
          name: "《JavaScript权威指南》",
          author: "Nicholas C. Zakas",
          desc: "JavaScript权威指南，适合初步学习js的小白和复习使用",
          img: "https://img3.doubanio.com/lpic/s1167614.jpg",
        }
      ],
      recommendBooks: [
        {
          id: 1,
          name: "《JavaScript权威指南》",
          author: "Nicholas C. Zakas",
          desc: "JavaScript权威指南，适合初步学习js的小白和复习使用",
          img: "https://img3.doubanio.com/lpic/s1167614.jpg",
        },
        {
          id: 2,
          name: "《JavaScript权威指南》",
          author: "Nicholas C. Zakas",
          desc: "JavaScript权威指南，适合初步学习js的小白和复习使用",
          img: "https://img3.doubanio.com/lpic/s1167614.jpg",
        },
        {
          id: 3,
          name: "《JavaScript权威指南》",
          author: "Nicholas C. Zakas",
          desc: "JavaScript权威指南，适合初步学习js的小白和复习使用",
          img: "https://img3.doubanio.com/lpic/s1167614.jpg",
        },
        {
          id: 4,
          name: "《JavaScript权威指南》",
          author: "Nicholas C. Zakas",
          desc: "JavaScript权威指南，适合初步学习js的小白和复习使用",
          img: "https://img3.doubanio.com/lpic/s1167614.jpg",
        }
      ],
      classifyList: []
    }
  },
  methods: {
    nextPage() {
      this.page++;
      this.$axios.get(`http://localhost:5000/book/nextPage/${this.page}`).then(res => {
        this.recommendBooks = res.data.data;
      })
    },
    bookDetails(id) {
      this.$router.push(`/index/bookDetails/${id}`);
    }
  },
  mounted() {
    this.$axios.get('http://localhost:5000/borrow/myBorrow',
        {
          headers: {
            'token': this.$store.state.User.token
          }
        }
    ).then(res => {
      this.myBorrowBooks = res.data.data;
      this.loading = false;
    })
    this.$axios.get('http://localhost:5000/bookType/getBookType').then(res => {
      this.classifyList = res.data.data;
    });

    this.$axios.get(`http://localhost:5000/book/nextPage/${this.page}`).then(res => {
      this.recommendBooks = res.data.data;
    })

  }
}
</script>

<template>
  <div id="bookQuery" style="margin-top: 220px">
    <div class="bookQuery">
      <div class="container">
        <div class="header">
          <div class="input">
            <el-input
                class="search-input"
                placeholder="请输入内容"
                prefix-icon="el-icon-search"
                v-model="input"
                clearable>
            </el-input>
          </div>
        </div>
        <div class="banner">
          <div class="myBorrow">
            <h2 class="header-borrow bookshelf-title">
              我的借阅
              <router-link class="right-link" to="/user/comment">
                查看我的借阅&gt
              </router-link>
            </h2>
            <div class="borrow-list">
              <div class="borrow-bookshelf" v-for="item in myBorrowBooks" :key="item.bookId" v-if="myBorrowBooks !== null">
                <span class="borrow-bookshelf-turn" @click="bookDetails(item.bookId)"></span>
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
              <div class="borrow-nothing" v-else>

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
              <div class="recommend-item" v-for="item in recommendBooks.rows" :key="item.bookId">
                <span class="recommend-item-turn" @click="bookDetails(item.bookId)"></span>
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

#bookQuery .header {
  padding: 10px;
  width: 100%;
}

#bookQuery .header .input {
  margin: 30px auto;
  width: 60%;
}

#bookQuery .header .el-input__inner {
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
  color: white;
}

.header-borrow .right-link {
  position: absolute;
  text-decoration: none;
  right: 0;
}

#bookQuery .banner {
  width: 100%;
  height: 100%;
}

#bookQuery .myBorrow {
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
  color: white;
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
  color: #ffffff;
  font-weight: 500;
}

.myBorrow .borrow-bookshelf .book-info .book-author {
  margin-top: 10px;
  font-size: 14px;
  color: white;
}

.recommend {
  width: 100%;
  color: white;
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
  color: #eef0f4;
}

.item-allInfo .book-info .book-author {
  margin-top: 12px;
  font-size: 14px;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  word-break: break-all;
  word-wrap: normal;
  color: #8a8c90;
}

.item-allInfo .book-info .book-desc {
  padding: 0 10px;
  margin-top: 10px;
  font-size: 13px;
  font-family: DIN-Regular,PingFang SC,-apple-system,SF UI Text,Lucida Grande,STheiti,Microsoft YaHei,sans-serif;
  color: #8a8c90;
  line-height: 21px;
  overflow: hidden;
  max-height: 42px;
}
.bookQuery{
  background-color: black;
}
.myBorrow .borrow-bookshelf {
  position: relative;
  height: 169px;
  overflow: hidden;
  border-radius: 12px;
  width: 23.5%;
  transition: all .2s ease-in-out;
  background-color: black;
}
/*书鼠标悬浮变色*/
.myBorrow .borrow-bookshelf:hover {
  background-color: rgb(38, 38, 40);
}

.recommend .recommend-list .recommend-item {
  position: relative;
  height: 352px;
  border-radius: 12px;
  width: 23.5%;
  transition: all .2s ease-in-out;
  background-color: black;
}
/*书鼠标悬浮变色*/
.recommend .recommend-list .recommend-item:hover{
  background-color: rgb(38, 38, 40);
}

.book-classify{
  margin: 30px auto;
  width: 79%;
}
.book-classify h2{
  color: white;
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
  color: white;
  height: 100%; /* 让a标签高度100%填充li元素 */
  display: flex; /* 使用flex布局 */
  align-items: center; /* 实现垂直居中 */
}

.book-classify ul li:hover{
  background-color:rgb(38, 38, 40);
}

</style>