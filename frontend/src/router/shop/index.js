import Store from '@/views/shop/Store';
import AddItem from '@/components/shop/AddItem';

export default[
    {
        path: '/store',
        name: 'Store',
        component:Store
    },
    {
        path: '/additem',
        name: 'AddItem',
        component:AddItem
    },

];