import Vue from 'vue'

/* common */
import nav              from '@/component/common/nav'
import breadcrumb       from '@/component/common/breadcrumb'
import logo             from '@/component/common/logo'

Vue.component('v-nav'         , nav)
Vue.component('v-breadcrumb'  , breadcrumb)
Vue.component('logo'          , logo)

/* external component */
import VueDaumPostcode  from "vue-daum-postcode"
Vue.use(VueDaumPostcode);
