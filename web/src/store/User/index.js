const user = {
    namespaced:true,
    state: {
        isLogin: localStorage.getItem('isLogin') || false,
        user: JSON.parse(localStorage.getItem('user')) || null,
        data: JSON.parse(localStorage.getItem('data')) || null,
    },
    mutations: {
        setUser(state, user) {
            state.user = user
        },
        setData(state, data) {
            this.data = data
        },
        setIsLogin(state, isLogin) {
            state.isLogin = isLogin
        }
    },
    actions: {
        setUserAction(context, user) {
            context.commit('setUser', user);
        },
        setDataAction(context, data) {
            context.commit('setData', data);
        },
        setIsLoginAction(context, isLogin) {
            context.commit('setIsLogin', isLogin);
        }
    },
}

export default user;