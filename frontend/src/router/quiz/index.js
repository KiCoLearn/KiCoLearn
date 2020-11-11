export default [
    {
        path: '/quiz',
        name: 'QuizTest',
        component: () => import(/* webpackChunkName: "about" */ '@/views/quiz/QuizTest'),
        meta: { requireAuth: true, kidOnly: true },
    },
    {
        path: '/admin/quiz',
        name: 'QuizManage',
        component: () => import(/* webpackChunkName: "about" */ '@/views/quiz/QuizManage'),
        meta: { admin: true },
    },
];