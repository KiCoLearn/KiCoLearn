export default [
    {
        path: '/quiz',
        name: 'QuizTest',
        component: () => import(/* webpackChunkName: "about" */ '@/views/quiz/QuizTest'),
    },
    {
        path: '/admin/quiz',
        name: 'QuizManage',
        component: () => import(/* webpackChunkName: "about" */ '@/views/quiz/QuizManage'),
    },
];