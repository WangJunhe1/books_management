<script>
import '@/assets/font/iconfont.css'
import '@/assets/css/csh.css'

export default {
  data() {
    return {
      isSuccessBorrow: 0,
      bookID: null,
      book: this.$store.state.Book.book || {
        bookId: null,
        bookName: null,
        bookAuthor: null,
        bookDetails: null,
      },
      bookDetails: [],
      rating: [
        {
          name: '推荐',
          value: null
        },
        {
          name: '一般',
          value: null
        },
        {
          name: '不行',
          value: null
        },
      ],
      ratingScore: 0,
    }
  },
  methods: {
    format(percentage) {
      return percentage;
    },
    borrowBook() {
      this.$axios.post(`http://localhost:5000/borrow/${this.book.bookId}`,
          null,
          {
            headers: {
              'token': this.$store.state.User.token,
            }
          }).then (res => {
            this.isSuccessBorrow = res.code;
      })
    },
    bookComment(index) {
      localStorage.setItem('commentState', index);
      this.$store.dispatch('setCommentState', index)
      this.$router.push('/index/bookComment');
    }
  },
  mounted() {
    this.bookID = this.$route.path.split('/').pop();
    this.$axios.get(`http://localhost:5000/book/getBook/${this.bookID}`).then(res => {
      this.book = res.data;
      this.$axios.get(`http://localhost:5000/comment/${this.bookID}`).then(res => {
        this.bookDetails = res.data;
        let a = 0, b = 0, c = 0;
        for (let i =0; i < this.bookDetails.length; i++) {
          if (this.bookDetails[i].commentStatus === 0) {
            a++;
          }
          if (this.bookDetails[i].commentStatus === 1) {
            b++;
          }
          if (this.bookDetails[i].commentStatus === 2) {
            c++;
          }
        }
        this.rating[0].value = a / this.bookDetails.length * 100;
        this.rating[1].value = b / this.bookDetails.length * 100;
        this.rating[2].value = c / this.bookDetails.length * 100;
        this.ratingScore = ((a * 2 + b - c * 0.5) / (2 * this.bookDetails.length)) * 100 || 0;
      })
    })
  },
  
}
</script>

<template>
<div class="details">
    <div class="container">
      <div class="book-info">
        <div class="reader-book-info">
          <div class="reader-book-info-header">
            <div class="book-info-cover">
              <img :src="book.bookImg" :alt="book.bookName" class="book-info-img">
            </div>
            <div class="book-info-right">
              <div class="book-info-right-header">
                <div class="book-info-right-header-title">
                  <div class="book-info-name">{{book.bookName}}</div>
                  <div class="book-info-author">{{book.bookAuthor}}</div>
                </div>
                <div class="book-info-right-header-wrapper">
                  <button class="book-info-right-button , km" v-if="this.isSuccessBorrow === 0" @click="borrowBook()">借阅</button>
                  <button class="book-info-right-button" v-else>已借阅</button>
                </div>
              </div>
              <div class="book-info-right-desc">
                {{book.bookDesc}}
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="book-banner">
        <div class="book-ratings">
          <div class="ratings-header">
            <div class="ratings-header-title">
              微信读书推荐值
            </div>
            <div class="ratings-container">
              <div class="rating-container-detail">
                <div class="rating-container-detail-number">
                  {{this.ratingScore.toFixed(1)}}
                  <span style="font-size: 20px">%</span>
                </div>
                <div class="rating-container-detail-count">
                  {{this.bookDetails.length}}人点评
                </div>
              </div>
              <div class="book-rating-bar-container">
                <div class="book-rating-bar"
                     v-for="(item, index) in this.rating"
                     :key="index">
                  <span>{{item.name}}</span>
                  <div class="book-sating-item-bar-bgc">
                    <div class="book-sating-item-bar" :style="{width: item.value+'%'}"></div>
                  </div>
                </div>
              </div>
            </div>
            <div class="ratings-footer">
              <span @click="bookComment(0)" class="book-footer-link , km">
                <div class="book-footer-icon">
                  <i class="iconfont icon-Outline_fuben11"></i>
                  推荐
                </div>
              </span>
              <span @click="bookComment(1)" class="book-footer-link , km">
                <div class="book-footer-icon">
                  <i class="iconfont icon-Outline_fuben5"></i>
                  一般
                </div>
              </span>
              <span @click="bookComment(2)" class="book-footer-link , km">
                <div class="book-footer-icon">
                  <i class="iconfont icon-Outline_fuben24"></i>
                  不行
                </div>
              </span>
            </div>
          </div>
        </div>
      </div>
      <div class="book-comments">
        <div class="lineBox"></div>
        <div class="comments-toReview-container">
          <div class="toReview-header">
            <p class="toReview-header-title">精彩点评</p>
          </div>
          <div class="nothing-comment" v-if="this.bookDetails.length === 0">没有评论</div>
          <div class="toReview-banner" v-else>
            <div class="toReview-banner-item"
                 v-for="(item, index) in bookDetails"
                 :key="index">
              <div class="toReview-banner-item-title">
                <div class="banner-title-user-info">
                  <img class="banner-title-user-img" :src="item.portrait" :alt="item.userName">
                </div>
                <span class="banner-title-user-name">{{item.userName}}</span>
                  <div class="banner-title-user-comment" v-if="item.commentStatus === 0">
                  <i class="iconfont icon-Outline_fuben11"></i>
                    <div class="iconfont-text">推荐</div> 
                </div>
         
                <div class="banner-title-user-comment" v-if="item.commentStatus === 1">
                  <i class="iconfont icon-Outline_fuben5"></i>
                  <div class="iconfont-text">一般</div>
                </div>
                <div class="banner-title-user-comment" v-if="item.commentStatus === 2">
                  <i class="iconfont icon-Outline_fuben24"></i>
                  <div class="iconfont-text">不行</div>
                </div>
              </div>
              <p class="toReview-banner-item-comment">
                {{item.commentContent}}
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
     <!-- 右部导航 -->
     <div class="youbu">
            <div class="youbu2 r">

                <div><a href=""><span></span>
                        <h5>WeChat</h5></a>
                </div>

                <div><a href=""><span></span>
                    <h5>QQ客服1</h5></a>
                </div>
                <div><a href=""><span></span>
                    <h5>QQ客服2</h5></a>
                </div>
                <div><a href="" class="gb"><span></span>
                    <h5>Tel</h5></a>
                </div>
            </div>
        </div>
        <!-- 二 -->
        <div class="sxb">
            <div class="sb">售前咨询</div>
            <div class="xb">网站续费</div>
        </div>
  </div>
</template>

<style>
  @keyframes animate__pulse {
    0% {
        transform: scale(1);
    }
    50% {
        transform: scale(1.1);
    }
    100% {
        transform: scale(1);
    }
}
.km:hover {
    animation-name: animate__pulse;
    animation-duration: 0.8s;
    animation-timing-function: ease-out;
    animation-fill-mode: forwards;
}

/* .details {
   添加背景图片 
  background-image: url('../assets/css/');
  设置背景大小和重复方式 
  background-size: cover;
  background-repeat: no-repeat;
} */

.details .container {
  width: 60%;
  margin: 0 auto;
  background-color: rgb(224,221,216);
}

.details .container .book-info {
  height: 100%;
  box-sizing: border-box;
  border-bottom: 1px solid rgb(0, 0, 0, 0.5);
}

.details .container .book-info .reader-book-info {
  margin-left: 100px;
  margin-right: 100px;
  margin-bottom: 30px;
  padding-bottom: 36px;
}

.book-info .reader-book-info-header {
  display: flex;
  padding-top: 60px;
}

.book-info .reader-book-info-header .book-info-cover {
  flex-shrink: 0;
  width: 160px;
  height: 232px;
}

.book-info .reader-book-info-header .book-info-cover .book-info-img {
  vertical-align: top;
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.book-info .reader-book-info-header .book-info-right {
  flex: 1;
  margin-left: 40px;
}

.book-info .reader-book-info-header .book-info-right .book-info-right-header {
  display: flex;
  justify-content: space-between;
}

.book-info-right-header-title {
  display: block;
}

.book-info-right-header-title .book-info-name {
  margin-top: 4px;
  color: #000a12;
  font-family: "SourceHanSerifCN-Bold",PingFang SC,-apple-system,SF UI Text,Lucida Grande,STheiti,Microsoft YaHei,sans-serif;
  font-size: 24px;
  line-height: 34px;
  font-weight: 500;
}

.book-info-right-header-title .book-info-author {
  margin-top: 2px;
  color: #0097ff;
  font-family: "SourceHanSerifCN-Bold",PingFang SC,-apple-system,SF UI Text,Lucida Grande,STheiti,Microsoft YaHei,sans-serif;
  font-size: 18px;
  line-height: 26px;
}

.book-info-right-header-wrapper {
  display: flex;
  flex-direction: row;
}

.book-info-right-header-wrapper .book-info-right-button {
  margin: 10px 0 0;
  background-image: linear-gradient(90deg,#0087fc,#28b7ff);
  color: #fff;
  flex-shrink: 0;
  display: flex;
  width: 107px;
  justify-content: center;
  align-items: center;
  height: 40px;
  line-height: 40px;
  border-radius: 20px;
  font-size: 14px;
  border: 0;
  font-weight: 500;
  cursor: pointer;
}

.book-info-right .book-info-right-desc{
  position: relative;
  margin-top: 21px;
  cursor: pointer;
  text-align: justify;
  color: #8c8c8e;
  line-height: 25px;
  overflow: hidden;
  height: 100px;
  display: -webkit-box;
  display: -moz-box;
  text-overflow: ellipsis;
  -webkit-line-clamp: 4;
  -moz-line-clamp: 4;
  line-clamp: 4;
  -webkit-box-orient: vertical;
  -webkit-text-size-adjust: none;
  box-orient: vertical;
  font-size: 14px;
}

.book-banner {
  margin-bottom: 10px;
  width: 100%;
}

.book-ratings {
  padding-top: 30px;
  padding-bottom: 20px;
  margin-left: 100px;
  margin-right: 100px;
}
.book-ratings .ratings-header-title {
  display: flex;
  font-family: DIN-Medium,PingFang SC,-apple-system,SF UI Text,Lucida Grande,STheiti,Microsoft YaHei,sans-serif;
  font-size: 16px;
  line-height: 24px;
  font-weight: 500;
  color:orangered;
}

.book-ratings .ratings-container {
  display: flex;
  flex-direction: row;
  margin-top: 10px;
  align-items: center;
}

.book-ratings .ratings-container .rating-container-detail {
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.book-ratings .ratings-container .rating-container-detail .rating-container-detail-number {
  font-family: DIN-Bold,PingFang SC,-apple-system,SF UI Text,Lucida Grande,STheiti,Microsoft YaHei,sans-serif;
  font-size: 36px;
  color: #0097ff;
}

.book-ratings .ratings-container .rating-container-detail .rating-container-detail-count{
  display: flex;
  flex-direction: row;
  align-items: center;
  font-family: DIN-Medium,PingFang SC,-apple-system,SF UI Text,Lucida Grande,STheiti,Microsoft YaHei,sans-serif;
  font-size: 12px;
  padding-top: 10px;
  color: black;
  line-height: 16px;
}

.book-rating-bar-container {
  flex: 1;
  margin: 11px 0 0 20px;
}

.book-rating-bar-container .book-rating-bar {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  font-size: 12px;
  font-family: PingFangSC-Medium,PingFang SC,-apple-system,SF UI Text,Lucida Grande,STheiti,Microsoft YaHei,sans-serif;
  color:black;
  font-weight: bold;
  line-height: 16px;
}

.book-sating-item-bar-bgc {
  flex: 1;
  background-color: white;
  border-radius: 3px;
  margin-left: 8px;
  height: 5px;
}

.book-sating-item-bar {
  height: 100%;
  background-color: rgb(128, 128, 128);
  border-radius: 3px;
}

.ratings-footer {
  display: flex;
  justify-content: space-between;
  width: 100%;
  margin-top: 24px;
}

.ratings-footer .book-footer-link{
  font-family: PingFangSC-Medium,PingFang SC,-apple-system,SF UI Text,Lucida Grande,STheiti,Microsoft YaHei,sans-serif;
  font-size: 14px;
  color: green;
  width: calc((100% - 40px)/3);
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  height: 44px;
  border-radius: 10px;
  border: 1px solid transparent;
  background-color: yellow;
  box-sizing: border-box;
  text-decoration: none;
}

.book-comments {
  display: block;
}

.book-comments .lineBox {
  width: 100%;
  height: 6px;
  background: #fff;
}

.book-comments .comments-toReview-container {
  padding-top: 16px;
  margin-left: 100px;
  margin-right: 100px;
}

.book-comments .comments-toReview-container .toReview-header {
  overflow: hidden;
  margin-bottom: 20px;
}

.book-comments .comments-toReview-container .toReview-header .toReview-header-title {
  font-size: 16px;
  font-family: PingFang SC,-apple-system,SF UI Text,Lucida Grande,STheiti,Microsoft YaHei,sans-serif;
  font-weight: 500;
  color: orangered;
}

.book-comments .comments-toReview-container .toReview-banner {
  display: block;
  padding-bottom: 10px;
}

.toReview-banner .toReview-banner-item {
  cursor: pointer;
  background: beige;
  padding: 20px;
  border-radius: 12px;
  margin-bottom: 20px;
}

.toReview-banner .toReview-banner-item .toReview-banner-item-title {
  display: flex;
  align-items: center;
}

.toReview-banner-item-title .banner-title-user-img {
  width: 20px!important;
  height: 20px!important;
  display: inline-block!important;
  vertical-align: middle!important;
  margin-right: 6px;
  border-radius: 50%;
}

.toReview-banner-item-title .banner-title-user-name {
  font-size: 14px;
  font-weight: 500;
  display: inline-block;
  vertical-align: middle;
  margin-right: 6px;
  line-height: 20px;
  color: #000;
}

.banner-title-user-comment {
  display: flex;
  align-items: center;
}

.banner-title-user-comment .iconfont{
  width: 14px;
  height: 14px;
  background-repeat: no-repeat;
  background-size: 100%;
}

.banner-title-user-comment .iconfont-text{
  margin-left: 3px;
  color: #78787a;
  line-height: 20px;
  font-size: 14px;
  font-weight: 500;
}

.toReview-banner-item .toReview-banner-item-comment {
  position: relative;
  line-height: 25px;
  overflow: hidden;
  height: 125px;
  display: -webkit-box;
  display: -moz-box;
  text-overflow: ellipsis;
  -webkit-line-clamp: 5;
  -moz-line-clamp: 5;
  line-clamp: 5;
  -webkit-box-orient: vertical;
  -webkit-text-size-adjust: none;
  box-orient: vertical;
  height: auto;
  max-height: 125px;
}

.nothing-comment {
  height: 125px;
  line-height: 125px;
  color: #999;
  text-align: center;
}

</style>