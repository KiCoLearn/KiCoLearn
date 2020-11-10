import AccountKidRoutes from '@/router/account/kid.js';
import AccountOauthRoutes from '@/router/account/oauth.js';

export default [
    ...AccountKidRoutes,
    ...AccountOauthRoutes,
];