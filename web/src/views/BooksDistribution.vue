<script>
export default {
  data() {
    return {
      loading: true,
      tableData: []
    }
  },
  mounted() {
    this.$axios.get("http://localhost:5000/bookType/getBookType").then(res => {
      this.tableData = res.data.data;
    })
  },
  updated() {
    this.loading = false;
  }
}
</script>

<template>
  <div id="bookDistribution" style="margin-top: 220px">
    <div class="container">
      <el-table
          v-loading="loading"
          element-loading-text="拼命加载中"
          element-loading-spinner="el-icon-loading"
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