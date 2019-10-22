import Vue from 'vue'
import Router from 'vue-router'

import homeView       from '@/component/view/homeView'
import loginView      from '@/component/view/loginView'
import logoutView     from '@/component/view/logoutView'
import registerView   from '@/component/view/registerView'
import auctionView    from '@/component/view/auctionView'
import mypageView     from '@/component/view/mypageView'

Vue.use(Router)

const requireAuth = () => (from, to, next) => {
  const isAuthenticated = false
  if (isAuthenticated) return next()
  next('/login?returnPath=mypage')
}

export default new Router({
  mode: 'history',
  routes: [{
      name: "home",
      path: '/',
      component: homeView
    }, {
      name: "login",
      path: '/login',
      component: loginView
    }, {
      name: "logout",
      path: '/logout',
      component: logoutView
    }, {
      name: "register",
      path: '/register',
      component: registerView
    },
    {
      name: "auction",
      path: '/auction',
      component: auctionView
    },
    {
      name: "mypage",
      path: '/mypage',
      component : mypageView
    }
  ]
})
