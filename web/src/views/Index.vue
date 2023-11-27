<script>
import Header from "@/components/Header.vue";
export default {
  name: "Index",
  data() {
    return {
    };
  },
  components: {
    Header
  },
  methods: {
    getUser() {
      this.$axios.get("http://localhost:5000/user/getUser", {
        method: "post",
        params: {
          username: "seven",
          password: "12345678"
        }
      }).then(res => {
        console.log(res.data);
      });
    }
  }
}
</script>

<template>
  <div id="index">
    <Header></Header>
    <router-view></router-view>
    <el-button type="success" @click="getUser()">查看数据</el-button>
  </div>
</template>

<style>

</style>