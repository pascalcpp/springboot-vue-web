// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import axios from 'axios'
import router from './router'
import store from './store'
import './axios'
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
import './permission'



Vue.use(mavonEditor)
Vue.prototype.$axios = axios
Vue.use(ElementUI)




Vue.config.productionTip = false


/* eslint-disable no-new */
new Vue({
  router,
  render: h => h(App),
  store
}).$mount('#app')
