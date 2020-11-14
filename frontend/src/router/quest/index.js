import KidQuest from '@/views/quest/KidQuest';
import ParentQuest from '@/views/quest/ParentQuest';

export default[
    {
        path: '/kid/quest',
        name: 'KidQuest',
        component:KidQuest,
        meta: { requireAuth: true, kidOnly: true },
    },
    {
        path: '/parents/quest',
        name: 'ParentQuest',
        component:ParentQuest,
        meta: { requireAuth: true, parentsOnly: true },
    },
];