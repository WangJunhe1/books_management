<script>
import '@/assets/font/iconfont.css'
export default {
  data() {
    return {
      commentState: this.$store.state.Book.commentState,
      commentContent: '',
      book: this.$store.state.Book.book || {
        bookName: "test",
      },
    }
  },
  methods: {
    changeCommentState(index) {
      let bookFooterLink = document.querySelectorAll('.book-footer-link');
      for (let i = 0; i < bookFooterLink.length; i++) {
        if (index !== i) {
          bookFooterLink[i].style.backgroundColor = 'yellow';
        } else {
          bookFooterLink[i].style.backgroundColor = '#0099ff';
          this.commentState = index;
          localStorage.setItem('commentState', index);
          this.$store.dispatch('Book/setCommentStateAction', index);
        }
      }
    },
    submitComment() {
      this.$axios.post('http://localhost:5000/comment/postComment', {
        bookId: this.book.bookId,
        commentContent: this.commentContent,
        commentStatus: this.commentState,
      }, {
        headers: {
          'token': this.$store.state.User.token,
        }
      }).then((res) => {
        if (res.data.code === 0) {
          this.$message({
            message: '评论失败',
            type: 'success'
          })
        }
        if (res.data.code === 1) {
          this.$message({
            message: '评论成功，即将返回到图书详情页面',
            type: 'success'
          })
          this.$router.push(`/index/bookDetails/${this.book.bookId}`)
        }
      })
    }
  },
  mounted() {
    let bookFooterLink = document.querySelectorAll('.book-footer-link');
    this.commentState = localStorage.getItem('commentState');
    this.book = JSON.parse(localStorage.getItem('book'));
    console.log(this.commentState);
    bookFooterLink[this.commentState].style.backgroundColor = '#0099ff';
  }
}
</script>

<template>
  <div class="bookComment">
    <div class="container" style="background-color:whitesmoke;">
      <div class="comment-header">
        <div class="book-info">
          <div class="book-from">
            点评"{{book.bookName}}"
          </div>
          <div class="comment-score">
            <div class="book-footer-link" @click="changeCommentState(0)">
              <div class="book-footer-icon">
                <i class="iconfont icon-Outline_fuben11"></i>
                推荐
              </div>
            </div>
            <div class="book-footer-link" @click="changeCommentState(1)">
              <div class="book-footer-icon">
                <i class="iconfont icon-Outline_fuben5"></i>
                一般
              </div>
            </div>
            <div class="book-footer-link" @click="changeCommentState(2)">
              <div class="book-footer-icon">
                <i class="iconfont icon-Outline_fuben24"></i>
                不行
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="comment-banner">
        <textarea class="comment-value" v-model="commentContent"></textarea>
      </div>
      <div class="comment-footer">
        <el-button type="primary" class="comment-submit" @click="submitComment()">发 表</el-button>
      </div>
    </div>
  </div>
</template>

<style>
.comment-header {
  width: 100%;
  height: 100px;
}

.comment-header .book-from {
  text-align: center;
  margin: 20px auto;
  font-size: 30px;
}

.comment-header .comment-score {
  margin: 10px auto;
  display: flex;
  width: 800px;
  justify-content: space-between;
}

.comment-header .comment-score .book-footer-link{
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
  cursor: pointer;
  user-select: none;
}

.comment-banner {
  width: 100%;
  margin-top: 20px;
  border-top: 1px solid #cccccc;
}

.comment-banner .comment-value{
  width: 100%;
  height: 500px;
  border: none;
  background-color: whitesmoke;
}

.comment-footer {
  width: 100%;
  border-top: 1px solid #cccccc;
  height: 70px;
  line-height: 70px;
  text-align: center;
}

.comment-footer .comment-submit {
  width: 100px;
  height: 40px;
  border: none;
  border-radius: 20px;
  background-color: #0099ff;
  color: white;
  font-size: 14px;
  font-family: PingFangSC-Medium,PingFang SC,-apple-system,SF UI Text,Lucida Grande,STheiti,Microsoft YaHei,sans-serif;
}

.comment-footer .comment-submit:hover {
  background-color: #0080ff;
}

</style>