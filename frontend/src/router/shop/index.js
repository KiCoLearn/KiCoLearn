import ParentStore from '@/views/shop/ParentStore';
import KidStore from '@/views/shop/KidStore';

export default[
    {
        path: '/parentstore',
        name: 'ParentStore',
        component:ParentStore,
        meta: { requireAuth: true, parentsOnly: true },
    },
    {
        path: '/kidstore',
        name: 'KidStore',
        component:KidStore,
        meta: { requireAuth: true, kidOnly: true },
    },

];