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

  }
}
</script>

<template>
  <div id="index">
    <Header></Header>
    <router-view></router-view>
  </div>
</template>

<style>
</style>