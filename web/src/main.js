import Vue from 'vue'
import App from './App.vue'
import router from "@/router/index";
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import axios from 'axios'
import store from "@/store";
import service from "@/interceptor/request.js";

// 动画 animate.css
import 'animate.css';


Vue.use(ElementUI);
Vue.prototype.$axios = service;
Vue.config.productionTip = false

new Vue({
  render: h => h(App),
  router,
  store,
}).$mount('#app')
