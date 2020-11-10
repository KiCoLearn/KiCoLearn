import Vue from 'vue';
import Vuex from 'vuex';

import createPersistedState from 'vuex-persistedstate';

import auth from '@/store/modules/auth';
import quiz from '@/store/modules/quiz';
import fcm from '@/store/modules/fcm';

Vue.use(Vuex);

export default new Vuex.Store({
    modules: {
        auth,
        quiz,
        fcm,
    },
    plugins: [createPersistedState({
        storage: window.sessionStorage,
        paths: [
            'auth',
            'quiz',
            'fcm',
        ],
    })],
});
