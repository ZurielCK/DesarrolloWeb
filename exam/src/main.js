import Vue from 'vue'
import App from './App.vue'


import router from './router/index'
import './assets/main.css'

import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'


import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

import Vuelidate from 'vuelidate'


Vue.use(BootstrapVue)
Vue.use(IconsPlugin)
Vue.use(Vuelidate)

new Vue({
  router,
  render: (h) => h(App)
}).$mount('#app')
