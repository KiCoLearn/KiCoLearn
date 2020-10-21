import Vue from 'vue';
import VueRouter from 'vue-router';
//import Home from '../views/Home.vue';
import Sign from '../views/Sign.vue';

Vue.use(VueRouter);

const routes = [
    {
        path: '/sign',
        name: 'Sign',
        component:Sign
    }
];

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes,
});

export default router;
