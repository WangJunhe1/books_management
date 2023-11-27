import Vuex from 'vuex'
import Vue from 'vue'

import User from "./User";
import Book from "./Book";

Vue.use(Vuex);

const store = new Vuex.Store({
    modules: {
        User,
        Book
    }
});

export default store;