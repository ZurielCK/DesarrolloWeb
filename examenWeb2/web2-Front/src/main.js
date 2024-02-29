import Vue from 'vue'
import App from './App.vue'
import router from './router/index'

import '@asika32764/vue-animate/dist/vue-animate.css'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import './assets/main.css'

import { BootstrapVue, IconsPlugin, FormPlugin } from 'bootstrap-vue'


Vue.use(BootstrapVue)
Vue.use(IconsPlugin)
Vue.use(FormPlugin)

new Vue({
  router,
  render: (h) => h(App)
}).$mount('#app')