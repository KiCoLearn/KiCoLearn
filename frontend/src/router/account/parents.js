import Certification from '@/views/parents/Certification';

export default[
    {
        path: '/parents/certification',
        name: 'Certification',
        component: Certification,
        meta: { requireAuth: true, parentsOnly: true },
    }, 
];


