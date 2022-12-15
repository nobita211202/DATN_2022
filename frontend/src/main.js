
import router from '@router'
import store from '@state/store'
import Vue from 'vue'
import axios from '@/node_modules/axios/index'
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import CKEditor from 'ckeditor4-vue'
import Cookie from "vue-cookie"
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import 'vue2-toast/lib/toast.css';
import Toast from 'vue2-toast';

import App from './app.vue'
// eslint-disable-next-line import/no-relative-parent-imports

// Globally register all `_base`-prefixed components
import '@components/_globals'
Vue.use(Cookie)
Vue.use(FontAwesomeIcon)
axios.defaults.baseURL = 'http://localhost:8888'
Vue.use(axios)
Vue.use(Toast);
Vue.use( CKEditor );
// Install BootstrapVue
Vue.use(BootstrapVue)
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin)
// Don't warn about using the dev version of Vue in development.
export const bus = new Vue()
const app = new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount('#app')
