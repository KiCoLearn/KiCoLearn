export default [
    {
        path: '/quiz',
        name: 'QuizTest',
        component: () => import(/* webpackChunkName: "about" */ '@/views/quiz/QuizTest'),
        meta: { authorized: true },
    },
    {
        path: '/admin/quiz',
        name: 'QuizManage',
        component: () => import(/* webpackChunkName: "about" */ '@/views/quiz/QuizManage'),
        meta: { admin: true },
    },
];