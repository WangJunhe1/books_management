import axios from 'axios'

//初始化axios
const service = axios.create({
    baseURL: 'http://localhost:5000',
    headers: {
        'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    // timeout: 10000 // 请求超时
})

//请求拦截器
service.interceptors.request.use(
    config => {
        // 判断是否存在token,把token添加点请求头中，每次请求携带token传给后端
        if (this.$store.state.User.token) {
            config.headers['token'] = this.$store.state.User.token
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
        const res = response.data
        if (res.code !== 200) {
            //请求失败（包括token失效，302，404...根据和后端约定好的状态码做出不同的处理）
            return res
        }else{
            //请求成功
            return res
        }
    },error => {
        //处理错误响应
        return Promise.reject(error)
    }
)

export default service