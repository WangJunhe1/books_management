<script>
export default {
  name: 'userComment',
  data() {
    return {
      comments: []
    }
  },
  mounted() {
    this.$axios.get('http://localhost:5000/comment/getComment', {
      headers: {
        'token': this.$store.state.User.token,
      }
    }).then(res => {
      this.comments = res.data;
      console.log(this.comments)
    })
  }
}
</script>

<template>
  <div id="userComment">
    <div class="toReview-banner">
      <div class="toReview-banner-item"
           v-for="(item, index) in comments"
           :key="index">
        <div class="toReview-banner-item-title">
          <div class="banner-title-user-info">
            <span>{{item.bookName}}</span>
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
        <hr>
        <p class="toReview-banner-item-comment">
          {{item.commentContent}}
        </p>
      </div>
    </div>
  </div>
</template>

<style>
.toReview-banner {
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

.toReview-banner-item-comment {
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