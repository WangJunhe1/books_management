import Vue from 'vue'
import App from './App.vue'
import router from "@/router/index";
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import axios from 'axios'
import VueAxios from 'vue-axios'
import store from "@/store";

Vue.use(ElementUI);
Vue.prototype.$axios = axios;
// Vue.use(VueAxios, axios)
Vue.config.productionTip = false

new Vue({
  render: h => h(App),
  router,
  store,
}).$mount('#app')
