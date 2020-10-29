import Vue from 'vue';
import VueRouter from 'vue-router';
//account
import Sign from '@/views/account/Sign';
import Main from '@/views/account/Main';
import Kidsconnect from '@/views/account/Kidsconnect';
import Kidslogin from '@/views/account/Kidslogin';
//kids
import Kidsmain from '@/views/Kids/Kidsmain';
Vue.use(VueRouter);

const routes = [
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
];

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes,
});

export default router;
