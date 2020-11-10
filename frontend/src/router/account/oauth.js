import OAuthToken from '@/components/oauth/OAuthToken';

export default [
    {
        path: '/login/oauth2/code/kakao',
        name: 'OAuthToken',
        component: OAuthToken,
        props: true,
    },
];