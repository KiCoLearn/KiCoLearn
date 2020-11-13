<template>
    <div class="kidstore">
        <item-card 
            v-for="item in kidItems"
            :key="item.itemNo"                    
            :send-data="item"
        />
    </div>
</template>

<script>
import axios from '@/plugins/axios';
import ItemCard from '@/components/shop/ItemCard';
import { mapGetters } from 'vuex';

export default {
    name:'KidStore',
    components: {
        ItemCard
    },

    data(){
        return {
            kidItems:[]
        };
    },

    computed:{
        ...mapGetters({
            kidId:'auth/id'
        })
    },

    created(){
        axios.get(process.env.VUE_APP_API_URL + '/api/store/klist/'+this.kidId)
            .then((res)=>{
                this.kidItems = res.data.data;
            });
    },    
    
};
</script>