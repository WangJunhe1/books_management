import VueRouter from "vue-router";
import Vue from "vue";

Vue.use(VueRouter);

const routes = new VueRouter({
    routes: [
        {
            path: "/",
            redirect: "/index",
        },
        {
            name: "index",
            path: "/index",
            component: () => import("@/views/Index.vue"),
            redirect: { name: "home" },
            children: [
                {
                    name: "home",
                    path: "/index/home",
                    component: () => import("@/views/Home.vue"),
                    meta: {
                        title: "首页"
                    }
                },
                {
                    name: "bookDistribution",
                    path: "/index/bookDistribution",
                    component: () => import("@/views/BooksDistribution.vue"),
                    meta: {
                        title: "馆藏分布"
                    }
                },
                {
                    name: "bookBorrow",
                    path: "/index/bookBorrow",
                    component: () => import("@/views/BookBorrow.vue"),
                    meta: {
                        title: "图书借阅"
                    }
                },
                {
                    name: "bookDetails",
                    path: "/index/bookDetails/:book",
                    props:true,
                    component: () => import("@/views/BookDetails.vue"),
                    meta: {
                        title: "图书信息"
                    }
                },
                {
                    name: "bookComment",
                    path: "/index/bookComment/",
                    props:true,
                    component: () => import("@/views/BookComment.vue"),
                    meta: {
                        title: "图书评论"
                    }
                },
                {
                    name: "user",
                    path: "/user",
                    redirect: {name: "userInfo"},
                    component: () => import("@/views/User/User.vue"),
                    children: [
                        {
                            name: "userInfo",
                            path: "/user/userInfo",
                            redirect: {name: "profile"},
                            component: () => import("@/views/User/UserInfo.vue"),
                            children: [
                                {
                                    name: "account",
                                    path: "/user/userInfo/account",
                                    component: () => import("@/views/User/Account.vue"),
                                    meta: {
                                        title: "账户信息"
                                    }
                                },
                                {
                                    name: "profile",
                                    path: "/user/userInfo/profile",
                                    component: () => import("@/views/User/Profile.vue"),
                                    meta: {
                                        title: "个人信息"
                                    }
                                },
                                {
                                    name: "collection",
                                    path: "/user/userInfo/collection",
                                    component: () => import("@/views/User/Collection.vue"),
                                    meta: {
                                        title: "我的收藏"
                                    }
                                },
                                {
                                    name: "history",
                                    path: "/user/userInfo/history",
                                    component: () => import("@/views/User/History.vue"),
                                    meta: {
                                        title: "借阅历史"
                                    }
                                },
                            ]
                        },
                        {
                            name: "userBorrow",
                            path: "/user/borrow",
                            component: () => import("@/views/User/UserBorrow.vue"),
                            meta: {
                                title: "我的借阅"
                            }
                        },
                        {
                            name: "userComment",
                            path: "/user/comment",
                            component: () => import("@/views/User/UserComment.vue"),
                            meta: {
                                title: "我的评论"
                            }
                        }
                    ]
                },
            ]
        },
        {
            name: "login",
            path: "/login",
            component: () => import("@/views/Login.vue"),
            meta: {
                title: "登录"
            }
        },
        {
            name: "register",
            path: "/register",
            component: () => import("@/views/Register.vue"),
            meta: {
                title: "注册"
            }
        },
        {
            name: "registerNext",
            path: "/registerNext",
            component: () => import("@/views/RegisterNext.vue"),
            meta: {
                title: "详细注册"
            }
        },
        {
            name: "404",
            path: "/*",
            component: () => import("@/views/Error.vue"),
            meta: {
                title: "404"
            }
        }
    ],
    mode: "history"
})

routes.beforeEach(async(to, form, next) => {
    if (to.meta.title) {
        document.title = to.meta.title
    } else {
        document.title = '404' //此处写默认的title
    }
    next();
})


export default routes;