import Vue from 'vue'
import App from './App.vue'
import router from "@/router/index";
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import axios from 'axios'
import store from "@/store";

// 动画 animate.css
import animated from 'animate.css'
Vue.use(animated)


Vue.use(ElementUI);
Vue.prototype.$axios = axios;
Vue.config.productionTip = false

new Vue({
  render: h => h(App),
  router,
  store,
}).$mount('#app')
