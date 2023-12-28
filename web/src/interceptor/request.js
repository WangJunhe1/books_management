import axios from 'axios'
import { Message, Loading } from 'element-ui';
import router from '../router/index'
import store from '../store/index'

let loading        //定义loading变量

function startLoading() {    //使用Element loading-start 方法
    loading = Loading.service({
        lock: true,
        text: '页面正在加载中...',
        background: 'linear-gradient(to bottom , rgb(83,138,194), rgb(177,195,215))'
    })
}
function endLoading() {    //使用Element loading-close 方法
    loading.close()
}

//初始化axios
const service = axios.create({
    baseURL: 'http://localhost:5000',
    headers: {
        'Content-Type': 'application/json;charset=UTF-8'
    },
    // timeout: 10000 // 请求超时
})

//请求拦截器
service.interceptors.request.use(
    config => {
        startLoading();
        // 判断是否存在token,把token添加点请求头中，每次请求携带token传给后端
        if (store.state.User.token) {
            config.headers['token'] = store.state.User.token
        }
        return config
    },
    error => {
        //处理错误请求
        return Promise.reject(error)
    }
)
//响应拦截器
service.interceptors.response.use(
    response=> {
        endLoading()
        return response.data
    },error => {
        endLoading()

        const { status } = error.response
        if (status === 401) {
            Message.error(error.response.data.msg)
            // 清除token
            localStorage.clear()

            // 页面跳转
            router.push('/login')
        }
        //处理错误响应
        return Promise.reject(error)
    }
)

export default service