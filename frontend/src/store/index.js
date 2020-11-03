import Vue from 'vue';
import Vuex from 'vuex';

import createPersistedState from 'vuex-persistedstate';

import auth from '@/store/modules/auth';
import quiz from '@/store/modules/quiz';

Vue.use(Vuex);

export default new Vuex.Store({
    modules: {
        auth,
        quiz,
    },
    plugins: [createPersistedState({
        storage: window.sessionStorage,
        paths: [
            'auth',
            'quiz',
        ],
    })],
});
