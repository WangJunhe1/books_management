<script>
import "@/assets/css/BookQuery.css";

export default {
  data() {
    return {
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
  mounted() {
    this.$axios.get('http://localhost:5000/bookType/getBookType').then(res => {
      this.classifyList = res.data.data;
    });

    // this.$axios.post('http://localhost:5000/book/myBorrowBooks', null,{
    //   headers: {
    //     'token': this.$store.state.User.token
    //   }
    // }).then(res => {
    //   console.log(res.data)
    //   this.myBorrowBooks = res.data;
    // });


  }
}
</script>

<template>
  <div id="bookQuery">
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
            <router-link class="right-link" to="{name: 'userBorrow'}">
              查看我的借阅&gt
            </router-link>
          </h2>
          <div class="borrow-list">
            <div class="borrow-bookshelf" v-for="item in myBorrowBooks" :key="item.id" v-if="myBorrowBooks !== null">
              <router-link :to="{name: 'bookDetails', params: {book: item}}"></router-link>
              <div class="book-allInfo">
                <div class="book-img">
                  <img :src="item.img" :alt="item.name" class="borrow-img">
                </div>
                <div class="book-info">
                  <div class="book-name">{{item.name}}</div>
                  <div class="book-author">{{item.author}}</div>
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
            <span class="right-link">
              换一批
            </span>
          </h2>
          <div class="recommend-list">
            <div class="recommend-item" v-for="item in recommendBooks" :key="item.id">
              <router-link :to="{name: 'bookDetails', params: {book: item}}"></router-link>
              <div class="item-allInfo">
                <div class="book-img">
                  <img :src="item.img" :alt="item.name" class="borrow-img">
                </div>
                <div class="book-info">
                  <div class="book-name">{{item.name}}</div>
                  <div class="book-author">{{item.author}}</div>
                  <div class="book-desc">{{item.desc}}</div>
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
              <router-link :to="{name: 'bookBorrow', params: {bookTypeID: item.bookTypeId}}">{{item.bookType}} * {{item.bookNumber}}</router-link>
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

.myBorrow .borrow-bookshelf a {
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

.recommend .recommend-list .recommend-item a{
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

</style>