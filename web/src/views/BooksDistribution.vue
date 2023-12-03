<script>
export default {
  data() {
    return {
      tableData: [
        {
          type: "文学",
          location: "二楼A区"
        },
        {
          type: "文学",
          location: "二楼A区"
        },
        {
          type: "文学",
          location: "二楼A区"
        },
        {
          type: "文学",
          location: "二楼A区"
        },
        {
          type: "文学",
          location: "二楼A区"
        }
      ]
    }
  },
  mounted() {
    this.$route.get("http://localhost:5000/book/getBookType").then(res => {
      this.tableData = res.data
    }).error(err => {
      console.log(err.response.data)
    })
  }
}
</script>

<template>
  <div id="bookDistribution">
    <div class="container">
      <el-table
          :data="tableData"
          stripe
          style="width: 100%">
        <el-table-column
            prop="type"
            label="种类"
            width="180">
        </el-table-column>
        <el-table-column
            prop="location"
            label="位置"
            width="180">
        </el-table-column>

      </el-table>
    </div>
  </div>
</template>

<style>

</style>