const book = {
    namespaced:true,
    state: {
        book: JSON.parse(localStorage.getItem('book')) || null,
        commentState: localStorage.getItem('commentState') || null,
    },
    mutations: {
        setBook (state, book) {
            state.book = book;
        },
        commentState (state, commentState) {
            state.commentState = commentState;
        }
    },
    actions: {
        setBookAction (context, book) {
            context.commit('setBook', book);
        },
        setCommentStateAction (context, commentState) {
            context.commit('commentState', commentState);
        },
    }
}

export default book;