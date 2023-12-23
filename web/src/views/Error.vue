<script>
export default {
  name: 'Error',
  data() {
    return {
      error_count_time: 3
    }
  },
  methods: {
    downCountTime() {
      setInterval(() => {
        this.error_count_time--;
      }, 1000)
    }
  },
  mounted() {
    this.downCountTime();
    setTimeout(() => {
      this.$router.push('/index')
    }, 3000)
  }
}
</script>

<template>
  <div id="error">
    <span>页面还有<span class="error-count-time">{{error_count_time}}</span>s跳转到主页面</span>
    <img src="@/assets/404.png" alt="404" class="error-img">
  </div>
</template>

<style>
#error {
  width: 100%;
  height: 100%;
}

.error-img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
</style>