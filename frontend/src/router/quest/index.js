import KidQuest from '@/views/quest/KidQuest';
import AddQuest from '@/views/quest/AddQuest';
import InsertQuest from '@/components/quest/InsertQuest';
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
];