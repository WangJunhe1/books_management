// 引入axios
import axios from 'axios'
import { Loading } from 'element-ui';
import { Message } from 'element-ui';

// 创建axios实例
const service = axios.create()

// 添加统一的请求状态

// 添加请求拦截器
let loadingInstance = null;
service.interceptors.request.use(function(config){

    loadingInstance = Loading.service();

    return config;
}, function(error){

    return Promise.reject(error);
})

// 添加响应拦截器
service.interceptors.response.use(function(response){

    let timer = setTimeout(()=>{
        loadingInstance.close();
        clearTimeout(timer);
    }, 2000)

    return response;
}, function(error){

    let timer = setTimeout(()=>{
        loadingInstance.close();
        clearTimeout(timer);
    }, 2000)

    // 统一设置错误提醒内容
    console.log(error.response);
    Message.error('网络请求异常！')

    return Promise.reject(error);
})

export default service;