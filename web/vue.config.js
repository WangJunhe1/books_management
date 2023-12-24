module.exports = {

    // 反向代理（解决跨域问题）
    devServer:{
        open: true,
        host:'localhost',
        port: 8080,
        https: false,
        proxy:{ // 配置跨域
            '/api':{
                target:'http://localhost:5000', // 跨域请求地址
                changOrigin: true, // 是否允许跨域
                pathRewrite:{ // 路径重新，替换请求地址，将地址api替换为空
                    '^/api': ''
                }
            }
        }
    }

}
