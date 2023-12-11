const book = {
    namespaced:true,
    state: {
        book: JSON.parse(localStorage.getItem('book')) || null,
    },
    mutations: {
        setBook (state, book) {
            state.book = book;
        }
    },
    actions: {
        setBookAction (context, book) {
            context.commit('setBook', book);
        }
    }
}

export default book;