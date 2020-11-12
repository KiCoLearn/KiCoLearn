import KidLogin from '@/views/account/KidLogin';
import KidsRegist from '@/views/parents/KidRegist';
import KidDetail from '@/views/parents/KidDetail';
import KidsUpdate from '@/views/parents/KidUpdate';

export default [
    {
        path: '/kidlogin',
        name: 'KidLogin',
        component:KidLogin
    },
    {
        path: '/kidsregist',
        name: 'KidRegist',
        component:KidsRegist
    },
 
    {
        path: '/kidinfo',
        name: 'KidDetail',
        component:KidDetail
    },
    {
        path: '/kidsupdate',
        name: 'KidUpdate',
        component:KidsUpdate
    },
];