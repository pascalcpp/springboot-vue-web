import router from "./router";



router.beforeEach((to, from, next) => {

  console.log(to.matched.some(record => record.meta.requireAuth))
  if(to.matched.some(record => record.meta.requireAuth)){

    const token = localStorage.getItem('token')

    if(token){
      next()
    }else {
      next({
        path: '/login'
      })
    }
  }
  next()

})
