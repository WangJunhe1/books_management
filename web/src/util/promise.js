/**
 * promise是解决异步的方法，本质上是一个构造函数，可以用它实例化一个对象
 * 构造函数上有resove reject 方法  对象上有then catch方法
 *
 * */

const p = new Promise(function (resove, reject) {
    // 一段耗时的异步操作
    setTimeout(() => {
        // resove('成功的data')
        reject('error message')
    }, 2000)
})

// 使用异步对象
p.then(res => {
    console.log(res)
}).catch(error => {
        console.log(error)
})
