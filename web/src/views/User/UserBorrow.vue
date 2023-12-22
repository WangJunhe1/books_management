<script>
export default {
  methods: {
    handleClick(row) {
      console.log(row);
    }
  },

  data() {
    return {
      tableData: [
        {
          borrowDate: '2016-05-02',
          bookImg: 'http://localhost:5000/image/user/test.png',
          bookName: '王小虎',
          bookType: '上海',
          bookAuthor: '普陀区',
          bookPublish: '人民出版社'
        },
        {
          borrowDate: '2016-05-02',
          bookImg: 'http://localhost:5000/image/user/test.png',
          bookName: '王小虎',
          bookType: '上海',
          bookAuthor: '普陀区',
          bookPublish: '人民出版社'
        },
        {
          borrowDate: '2016-05-02',
          bookImg: 'http://localhost:5000/image/user/test.png',
          bookName: '王小虎',
          bookType: '上海',
          bookAuthor: '普陀区',
          bookPublish: '人民出版社'
        },
        {
          borrowDate: '2016-05-02',
          bookImg: 'http://localhost:5000/image/user/test.png',
          bookName: '王小虎',
          bookType: '上海',
          bookAuthor: '普陀区',
          bookPublish: '人民出版社'
        },
        {
          borrowDate: '2016-05-02',
          bookImg: 'http://localhost:5000/image/user/test.png',
          bookName: '王小虎',
          bookType: '上海',
          bookAuthor: '普陀区',
          bookPublish: '人民出版社'
        },
      ]
    }
  }
}
</script>

<template>
  <el-table
      :data="tableData"
      border
      style="width: 100%">
    <el-table-column
        fixed
        prop="borrowDate"
        label="借阅日期"
        width="150">
    </el-table-column>
    <el-table-column
        prop="bookImg"
        label="图书图片"
        width="200">
      <template slot-scope="scope">
        <img :src="scope.row.bookImg" :alt="scope.row.bookName" style="width: 70%; height: 70%;"/>
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
        <el-button @click="handleClick(scope.row)" type="primary" size="small">续借</el-button>
        <el-button type="warning" size="small">归还</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<style>

</style>