<script>
export default {
  data() {
    return {
      tableData: []
    }
  },
  mounted() {
    this.$axios.get("http://localhost:5000/bookType/getBookType").then(res => {
      this.tableData = res.data;
    })
  },
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
            prop="bookType"
            label="种类"
            width="570px">
        </el-table-column>
        <el-table-column
            prop="bookTypeLocation"
            label="位置"
            width="570px">
        </el-table-column>

      </el-table>
    </div>
  </div>
</template>

<style>

</style>