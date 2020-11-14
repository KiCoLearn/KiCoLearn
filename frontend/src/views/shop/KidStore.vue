<template>
    <div class="kidstore">
        <div>
            <v-img
                class="mx-auto"
                :src="require('@/assets/shop.png')"
                width="128"
                height="128"
            />
        </div>
        <div class="store-back mx-auto">
            <item-card 
                v-for="item in kidItems"
                :key="item.itemNo"                    
                :send-data="item"
                :like-no="likeNo"
                @handleLike="likeItem"
                @releaseLike="releaseLike"
            />
        </div>
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
            kidItems:[],
            likeNo:0,
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
        axios.get(process.env.VUE_APP_API_URL + '/api/kidsaccount/detail/'+this.kidId)
            .then((res)=>{
                this.likeNo = res.data.data.likeItem;
            });
    },
    methods:{
        likeItem(itemNo){
            //console.log(itemNo);
            //this.likeNo = itemNo;
            axios.post(process.env.VUE_APP_API_URL + '/api/kidsaccount/update/like', {
                kidId:this.kidId,
                likeItem:itemNo
            })
                .then(()=>{
                    this.likeNo = itemNo;
                });
        },
        releaseLike(){
            //console.log(itemNo);
            //this.likeNo = 0;
            axios.post(process.env.VUE_APP_API_URL + '/api/kidsaccount/update/like', {
                kidId:this.kidId,
                likeItem:0
            })
                .then(()=>{
                    this.likeNo = 0;
                });
        }
    }   
    
};
</script>

<style scoped>
.kidstore{
    display: inline;
    padding: 0;
    justify-content: center;
}
.store-back{
    background-color: #ffffff;
    width: 100%;
    border-radius: 10px;
    padding: 3px;
    border-top: 15px solid #b3754c;
    border-bottom: 15px solid #b3754c;
}
</style>