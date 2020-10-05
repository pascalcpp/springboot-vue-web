import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login'
import Blogs from '../views/Blogs'
import BlogEdit from '../views/BlogEdit'
import BlogDetail from '../views/BlogDetail'


Vue.use(VueRouter)

const routes = [
  {
    path: "/",
    name: "Index",
    redirect: {name: 'Blogs'}
  },
  {
    path: "/login",
    name: "Login",
    component: Login
  },
  {
    path: "/blogs",
    name: "Blogs",
    component: Blogs
  },
  {
    path: "/blog/add",
    name: "BlogAdd",
    component: BlogEdit,
    meta: {
      requireAuth: true
    }
  },
  {
    path: "/blog/:blogId",
    name: "BlogDetail",
    component: BlogDetail
  },
  {
    path: "/blog/edit/:blogId",
    name: "BlogEdit",
    component: BlogEdit,
    meta: {
      requireAuth: true
    }
  }
]


const router = new VueRouter({
  mode: 'history',
  routes
})

export default router
