import Vue from 'vue';
import VueRouter from 'vue-router';

//account
import Sign from '@/views/account/Sign';
import Main from '@/views/account/Main';
import Kidsconnect from '@/views/account/Kidsconnect';
import Kidslogin from '@/views/account/Kidslogin';

//kids
import Kidsmain from '@/views/Kids/Kidsmain';
import Report from '@/views/Kids/Report';
import Quest from '@/views/Kids/Quest';
import Store from '@/views/Kids/Store';
import Quiz from '@/views/Kids/Quiz';

//parent
import KidsList from '@/views/Parent/KidsList';
import KidsRegist from '@/views/Parent/KidsRegist';

import QuizTestRoutes from '@/router/quiz';

import OAuthRoutes from '@/router/oauth';

Vue.use(VueRouter);

const routes = [
    ...OAuthRoutes,
    ...QuizTestRoutes,
    {
        path: '/',
        name: 'main',
        component:Main

    },
    {
        path: '/sign',
        name: 'Sign',
        component:Sign
    },
    {
        path: '/kidsconnect',
        name: 'Kidsconnect',
        component:Kidsconnect
    },
    {
        path: '/kidslogin',
        name: 'Kidslogin',
        component:Kidslogin
    },
    {
        path: '/kidsmain',
        name: 'Kidsmain',
        component:Kidsmain
    },
    {
        path: '/report',
        name: 'Report',
        component:Report
    },
    {
        path: '/quest',
        name: 'Quest',
        component:Quest
    },
    {
        path: '/store',
        name: 'Store',
        component:Store
    },
    {
        path: '/quiz',
        name: 'Quiz',
        component:Quiz
    },
    {
        path: '/kidslist',
        name: 'KidsList',
        component:KidsList
    },
    {
        path: '/kidsregist',
        name: 'KidsRegist',
        component:KidsRegist
    },
];

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes,
});

import store from '@/store';

router.beforeEach((to, from, next) => {
    if (to.matched.some(record => record.meta.admin)) {
        if (store.getters['auth/isAdmin'] !== true) {
            next({
                path: from.fullPath,
            });
        } else {
            next();
        }
    } else if (to.matched.some(record => record.meta.authorized)) {
        if (store.getters['auth/isAuthorized'] !== true) {
            next({
                path: from.fullPath,
            });
        } else {
            next();
        }
    } else {
        next();
    }
});

export default router;
