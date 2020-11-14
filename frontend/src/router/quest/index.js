import KidQuest from '@/views/quest/KidQuest';
import AddQuest from '@/views/quest/AddQuest';
import ParentQuest from '@/views/quest/ParentQuest';
import InsertQuest from '@/components/quest/InsertQuest';
import DetailQuest from '@/components/quest/DetailQuest';
import ConnectKid from '@/components/quest/ConnectKid';
import KidDetailquest from '@/components/quest/KidDetailquest';
export default[
    {
        path: '/kidquest',
        name: 'KidQuest',
        component:KidQuest
    },

    {
        path: '/addquest',
        name: 'AddQuest',
        component:AddQuest
    },
    {
        path: '/insertquest',
        name: 'InsertQuest',
        component:InsertQuest
    },
    {
        path: '/parentquest',
        name: 'ParentQuest',
        component:ParentQuest
    },
    {
        path: '/detailquest',
        name: 'DetailQuest',
        component:DetailQuest
    },
    {
        path: '/connectkid',
        name: 'ConnectKid',
        component:ConnectKid
    },
    {
        path: '/kiddetailquest',
        name: 'KidDetailquest',
        component:KidDetailquest
    },
];