export default [
    {
        path: '/kid/quiz',
        name: 'KidQuiz',
        component: () => import(/* webpackChunkName: "about" */ '@/views/quiz/KidQuiz'),
        meta: { requireAuth: true, kidOnly: true },
    },
    {
        path: '/parents/quiz',
        name: 'ParentsQuiz',
        component: () => import(/* webpackChunkName: "about" */ '@/views/quiz/ParentsQuiz'),
        meta: { requireAuth: true, parentsOnly: true },
    },
    {
        path: '/admin/quiz',
        name: 'QuizManage',
        component: () => import(/* webpackChunkName: "about" */ '@/views/quiz/QuizManage'),
        meta: { adminOnly: true },
    },
];