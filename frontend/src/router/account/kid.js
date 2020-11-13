import KidLogin from '@/views/account/KidLogin';
import KidsRegist from '@/views/parents/KidRegist';
import KidDetail from '@/views/parents/KidDetail';
import KidsUpdate from '@/views/parents/KidUpdate';

export default [
    {
        path: '/kid/login',
        name: 'KidLogin',
        component:KidLogin
    },
    {
        path: '/parents/regist',
        name: 'KidRegist',
        component:KidsRegist,
        meta: { requireAuth: true, parentsOnly: true },
    },
 
    {
        path: '/parents/detail',
        name: 'KidDetail',
        component:KidDetail,
        meta: { requireAuth: true, parentsOnly: true },
    },
    {
        path: '/parents/update',
        name: 'KidUpdate',
        component:KidsUpdate,
        meta: { requireAuth: true, parentsOnly: true },
    },
];