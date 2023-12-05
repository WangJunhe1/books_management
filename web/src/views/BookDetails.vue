<script>
import '@/assets/font/iconfont.css'
export default {
  data() {
    return {
      book: {
        id: 1,
        name: "《JavaScript权威指南》",
        author: "Nicholas C. Zakas",
        desc: "JavaScript权威指南，适合初步学习js的小白和复习使用",
        img: "https://img3.doubanio.com/lpic/s1167614.jpg",
      },
      bookDetail: {
        bookName: '《JavaScript权威指南》',
        count: 92,
        number: 73.2,
        ratings: [
          {
            user: {
              ...this.$store.state.User.user,
              img: 'https://avatars.githubusercontent.com/u/122709756?v=4'
            },
            score: 4.5,
            time: '2019-08-08 12:12',
            commentType: 1,
            text: "评价内容",
          },
          {
            user: {
              ...this.$store.state.User.user,
              img: 'https://avatars.githubusercontent.com/u/122709756?v=4'
            },
            score: 4.5,
            time: '2019-08-08 12:12',
            commentType: 1,
            text: "评价内容",
          },
        ],
        ratingsSum: [
          {
            name: "推荐",
            number: 72,
          },
          {
            name: "一般",
            number: 22,
          },
          {
            name: "不行",
            number: 5,
          },
        ]
      }
    }
  },
  methods: {
    format(percentage) {
      return percentage;
    }
  },
  mounted() {
    this.book = this.$route.params.book;
  }
}
</script>

<template>
  <div class="details">
    <div class="container">
      <div class="book-info">
        <div class="reader-book-info">
          <div class="reader-book-info-header">
            <div class="book-info-cover">
              <img :src="book.img" :alt="book.name" class="book-info-img">
            </div>
            <div class="book-info-right">
              <div class="book-info-right-header">
                <div class="book-info-right-header-title">
                  <div class="book-info-name">{{book.name}}</div>
                  <div class="book-info-author">{{book.author}}</div>
                </div>
                <div class="book-info-right-header-wrapper">
                  <button class="book-info-right-button" v-if="book !== null">借阅</button>
                  <button class="book-info-right-button" v-else>已借阅</button>
                </div>
              </div>
              <div class="book-info-right-desc">
                {{book.desc}}
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
                  {{bookDetail.number}}
                  <span style="font-size: 20px">%</span>
                </div>
                <div class="rating-container-detail-count">
                  {{bookDetail.count}}人点评
                </div>
              </div>
              <div class="book-rating-bar-container">
                <div class="book-rating-bar"
                     v-for="(item, index) in bookDetail.ratingsSum"
                     :key="index">
                  <span>{{item.name}}</span>
                  <div class="book-sating-item-bar-bgc">
                    <div class="book-sating-item-bar" :style="{width: item.number+'%'}"></div>
                  </div>
                </div>
              </div>
            </div>
            <div class="ratings-footer">
              <router-link to="" class="book-footer-link">
                <div class="book-footer-icon">
                  <i class="iconfont icon-Outline_fuben11"></i>
                  推荐
                </div>
              </router-link>
              <router-link to="" class="book-footer-link">
                <div class="book-footer-icon">
                  <i class="iconfont icon-Outline_fuben5"></i>
                  一般
                </div>
              </router-link>
              <router-link to="" class="book-footer-link">
                <div class="book-footer-icon">
                  <i class="iconfont icon-Outline_fuben24"></i>
                  不行
                </div>
              </router-link>
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
          <div class="toReview-banner">
            <div class="toReview-banner-item"
                 v-for="(item, index) in bookDetail.ratings"
                 :key="index">
              <div class="toReview-banner-item-title">
                <div class="banner-title-user-info">
                  <img class="banner-title-user-img" :src="item.user.img" :alt="item.user.username">
                </div>
                <span class="banner-title-user-name">{{item.user.username}}</span>
                <div class="banner-title-user-comment" v-if="item.commentType === 1">
                  <i class="iconfont icon-Outline_fuben11"></i>
                  <div class="iconfont-text">推荐</div>
                </div>
                <div class="banner-title-user-comment" v-if="item.commentType === 2">
                  <i class="iconfont icon-Outline_fuben5"></i>
                  <div class="iconfont-text">一般</div>
                </div>
                <div class="banner-title-user-comment" v-if="item.commentType === 3">
                  <i class="iconfont icon-Outline_fuben24"></i>
                  <div class="iconfont-text">不行</div>
                </div>
              </div>
              <p class="toReview-banner-item-comment">
                {{item.text}}
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style>
.details .container {
  width: 60%;
  margin: 0 auto;
  background-color: pink;
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
  color: #eef0f4;
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
  color: #8a8c90;
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
  color: #8a8c90;
  line-height: 16px;
}

.book-sating-item-bar-bgc {
  flex: 1;
  background-color: blue;
  border-radius: 3px;
  margin-left: 8px;
  height: 5px;
}

.book-sating-item-bar {
  height: 100%;
  background-color: black;
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
  color: #eef0f4;
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

.toReview-banner-item .toReview-banner-item-content {
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

</style>