<script>
export default {
  data() {
    return {
      count: 0,
      tableData: []
    }
  },
  methods: {
    renewBook(row) {
      console.log(row);

      this.$axios.put(`http://localhost:5000/borrow/${row.bookId}`,
          null,
          {
            headers: {
              'token': this.$store.state.User.token,
            }
          }
      ).then((res) => {
        if (res.data.code === 0) {
          this.$message({
            message: res.data.message,
            type: 'error'
          });
        }
        if (res.data.code === 1) {
          this.$message({
            message: '恭喜你，续借成功',
            type: 'success'
          });
          this.$axios.get('http://localhost:5000/borrow/myBorrow',
              {
                headers: {
                  'token': this.$store.state.User.token
                }
              }
          ).then(res => {
            this.tableData = res.data;
          })
        }
      })
    },
    returnBook(row) {
      console.log(row);
      this.$axios.put(`http://localhost:5000/borrow/return?bookId=${row.bookId}`,
          null,
          {
            headers: {
              'token': this.$store.state.User.token,
            }
          }
      ).then((res) => {
        if (res.data.code === 0) {
          this.$message({
            message: res.data.message,
            type: 'error'
          });
        }
        this.$axios.get('http://localhost:5000/borrow/myBorrow',
            {
              headers: {
                'token': this.$store.state.User.token
              }
            }
        ).then(res => {
          this.tableData = res.data;
          this.$message({
            message: '恭喜你，归还成功',
            type: 'success'
          });
        });
      })
    },
    load() {
      this.count += 2
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
      for (let i = 0; i < res.data.length; i++) {
        res.data[i].borrowStartTime = res.data[i].borrowStartTime.replace('T', ' ');
        res.data[i].borrowEndTime = res.data[i].borrowEndTime.replace('T', ' ');
      }
      this.tableData = res.data;
    })
  }
}
</script>

<template>
  <div class="bookComment">
    <el-table
        :data="tableData"
        height="500"
        border
        style="width: 100%;">
      <el-table-column
          fixed
          prop="borrowStartTime"
          label="借阅日期"
          width="180">
      </el-table-column>
      <el-table-column
          fixed
          prop="borrowEndTime"
          label="归还日期"
          width="180"
          color="red">
      </el-table-column>
      <el-table-column
          prop="bookImg"
          label="图书图片"
          width="200">
        <template slot-scope="scope">
          <img :src="scope.row.bookImg"
               :alt="scope.row.bookName"
               style="width: 70%; height: 70%;"/>
        </template>
      </el-table-column>
      <el-table-column
          prop="bookName"
          label="图书名称"
          width="120">
      </el-table-column>
      <el-table-column
          prop="bookType"
          label="图书类别"
          width="120">
      </el-table-column>
      <el-table-column
          prop="bookAuthor"
          label="作者"
          width="120">
      </el-table-column>
      <el-table-column
          prop="bookPublish"
          label="出版社"
          width="120">
      </el-table-column>
      <el-table-column
          fixed="right"
          label="操作"
          width="200">
        <template slot-scope="scope">
          <el-button @click="renewBook(scope.row)" type="primary" size="small">续借</el-button>
          <el-button @click="returnBook(scope.row)" type="danger" size="small">归还</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<style>
::-webkit-scrollbar {
  display: none;
}
</style>