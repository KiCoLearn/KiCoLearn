import Vue from 'vue';
import VueRouter from 'vue-router';

import Main from '@/views/account/Main';
import KidMain from '@/views/kid/KidMain';
import ParentsMain from '@/views/parents/ParentsMain';

import AccountRoutes from '@/router/account';
import ManageQuizRoutes from '@/router/quiz';
import ShopRoutes from '@/router/shop';
import ReportRoutes from '@/router/report';
/* todo : 지울것 */
import DummyRoutes from '@/router/dummy';

import QuestRoutes from '@/router/quest';

import Error from '@/views/Page404';

Vue.use(VueRouter);

const routes = [
    ...AccountRoutes,
    ...ManageQuizRoutes,
    ...ShopRoutes,
    ...DummyRoutes,
    ...QuestRoutes,
    ...ReportRoutes,
    
    {
        path: '/kid',
        name: 'KidMain',
        component: KidMain,
        meta: { requireAuth: true, kidOnly: true },
    },
  
    {
        path: '/parents',
        name: 'ParentsMain',
        component:ParentsMain,
        meta: { requireAuth: true, parentsOnly: true },
    },
  
    {
        path: '/',
        component: Main,
        name: 'Main',
        beforeEnter: (to, from, next) => {
            switch (store.getters['auth/role']) {
            case 'parents':
                next({
                    name: 'ParentsMain'
                });
                break;
            case 'kid':   
                next({
                    name : 'KidMain'
                });
                break;
            default : 
                next();
            }
            
        },
    },
    {
        path: '/*',
        name: 'Error',
        component: Error,
    },
];

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes,
});

import store from '@/store';

router.beforeEach((to, from, next) => {
    if (to.matched.some(record => record.meta.adminOnly)) {
        if (store.getters['auth/isAdmin'] !== true) {
            next({
                name: 'Main',
            });
        } else {
            next();
        }
    }   
    if (to.matched.some(record => record.meta.parentsOnly)) {
        if (store.getters['auth/isParents'] !== true) {
            next({
                name: 'Main'
            });
        }
    } else if (to.matched.some(record => record.meta.kidOnly)) {
        if (store.getters['auth/isKid'] !== true) {
            next({
                name: 'Main'
            });
        }
    } 
    
    next();
});

export default router;
