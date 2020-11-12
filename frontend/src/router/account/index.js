import AccountKidRoutes from '@/router/account/kid.js';
import AccountOauthRoutes from '@/router/account/oauth.js';
import AccountParentsRoutes from '@/router/account/parents.js';


export default [
    ...AccountKidRoutes,
    ...AccountOauthRoutes,
    ...AccountParentsRoutes,
];