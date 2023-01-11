
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
import moment from 'moment'
import App from './app.vue'
import 'vue-orgchart/dist/style.min.css'
// import "@/node_modules/chart.js"
// import "@/node_modules/hchs-vue-charts"

// eslint-disable-next-line import/no-relative-parent-imports


// Globally register all `_base`-prefixed components
import '@components/_globals'

// Vue.use(window.VueCharts)
Vue.use(Cookie)
Vue.use(FontAwesomeIcon)
axios.defaults.baseURL = 'http://localhost:8888'
Vue.use(axios)
// Vue.use(Toast);
Vue.use(Toast, {
  type: 'center',
  duration: 3000,
  wordWrap: true,
  width: '250px'
});
Vue.config.productionTip = false
// format date time vue 2
Vue.filter('formatDate', function(value) {
  if (value) {
    return moment(String(value)).format('MM/DD/YYYY hh:mm:ss')
  }
});

Vue.use( CKEditor );
// Install BootstrapVue
Vue.use(BootstrapVue)
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin)
// Don't warn about using the dev version of Vue in development.
Vue.config.productionTip = process.env.NODE_ENV === 'production'

// If running inside Cypress...
if (process.env.VUE_APP_TEST === 'e2e') {
  // Ensure tests fail when Vue emits an error.
  Vue.config.errorHandler = window.Cypress.cy.onUncaughtException
}
export const bus = new Vue()
const app = new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount('#app')

// If running e2e tests...
if (process.env.VUE_APP_TEST === 'e2e') {
  // Attach the app to the window, which can be useful
  // for manually setting state in Cypress commands
  // such as `cy.logIn()`.
  window.__app__ = app
}
