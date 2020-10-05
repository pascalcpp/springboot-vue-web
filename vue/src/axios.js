import axios from 'axios'
import ElementUI from 'element-ui';
import el from "element-ui/src/locale/lang/el";
import router from './router'
import store from './store'


axios.defaults.baseURL = 'http://localhost:8081'

axios.interceptors.request.use(config => {
  return config
})

axios.interceptors.response.use(response => {

  // status 正常时 进入

  if(response.data.code === 200){

    return response

  }else {

    // console.log(response)
    // console.log('status 正常')
    ElementUI.Message.error(response.data.msg, {duration : 3 * 1000})
    return Promise.reject(response.data.msg)

  }

},
  error => {
    // console.log('status 异常')
    if(error.response.data){
      error.message = error.response.data.msg
    }

    if(error.response.status === 401){
      store.commit('REMOVE_INFO')
      router.push('/login')

    }

    ElementUI.Message.error(error.message, {duration : 3 * 1000})

    return Promise.reject(error.message)
  }

)
