import ParentStore from '@/views/shop/ParentStore';
import KidStore from '@/views/shop/KidStore';

export default[
    {
        path: '/parents/store',
        name: 'ParentStore',
        component:ParentStore,
        meta: { requireAuth: true, parentsOnly: true },
    },
    {
        path: '/kid/store',
        name: 'KidStore',
        component:KidStore,
        meta: { requireAuth: true, kidOnly: true },
    },

];