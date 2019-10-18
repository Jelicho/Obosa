import Vue from 'vue'
import Router from 'vue-router'

import homeView from '@/view/homeView'
import loginView from '@/view/loginView'
import logoutView from '@/view/logoutView'
import registerView from '@/view/registerView'
import auctionView from '@/view/auctionView'

import myUpdateView from '@/mypage/update'
import myChangePasswordView from '@/mypage/changePassword'
import myProductView from '@/mypage/product'

import auctionsRegisterVuew from '@/auction/register'
import auctionDetailView from '@/auction/detail'
import auctionBidView from '@/auction/bid'

import explorerAuctionView from '@/explorer/auctionList'
import explorerAuctionDetailView from '@/explorer/auctionDetail'
import explorerAddressSearchView from '@/explorer/addressSearch'

import addressPopUp from '@/api/addressPopUp'


Vue.use(Router)

export default new Router({
  mode:'history',
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
      name: "mypage.update",
      path: "/mypage/update",
      component: myUpdateView
    },
    {
      name: "mypage.change_password",
      path: "/mypage/change_password",
      component: myChangePasswordView
    },
    {
      name: "mypage.product",
      path: "/mypage/product",
      component : myProductView
    },
    {
      name: "auction.register",
      path: "/auction/register",
      component: auctionsRegisterVuew
    }, {
      name: "auction.detail",
      path: "/auction/detail/:id",
      component: auctionDetailView
    },
    {
      name: "auction.bid",
      path: "/auction/bid/:id",
      component: auctionBidView
    },
    {
      name: "explorer.auction",
      path: "/explorer/auction",
      component: explorerAuctionView
    }, {
      name: "explorer.auction.detail",
      path: "/explorer/auction/detail/:contractAddress",
      component: explorerAuctionDetailView
    },
    {
      name: "explorer.search",
      path: "/explorer/search",
      component: explorerAddressSearchView
    },{
      name: "address.popup",
      path:"/address/popup",
      component: addressPopUp
    }
  ]
})
