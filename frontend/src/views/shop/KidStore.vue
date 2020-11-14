<template>
    <div class="kidstore">
        <div class="store-left">
            <v-img
                class="mx-auto"
                :src="require('@/assets/shop.png')"
                width="128"
                height="128"
            />
        </div>
        <div class="store-right">
            <div class="money-info">
                <div class="pt-3">
                    <p class="font-weight-black mb-1">
                        소지금
                    </p>
                </div>
                <div class="money-parent pl-3 pt-2">
                    <div class="money-icon">
                        <v-img
                            :src="require('@/assets/coins.png')"
                            width="30px"
                            height="30px"
                        />
                    </div>
                    <div class="money-amount pt-1">
                        <p class="font-weight-black">
                            {{ holding }} 원
                        </p>
                    </div>
                </div>
            </div>
        </div>
        <div class="store-back mx-auto">
            <item-card 
                v-for="item in kidItems"
                :key="item.itemNo"                    
                :send-data="item"
                :like-no="likeNo"
                :holding="holding"
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
            holding:0
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
        axios.get(process.env.VUE_APP_API_URL + '/api/money/'+this.kidId)
            .then((res)=>{
                //console.log(res.data.data);
                this.holding = res.data.data;
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
div {
    width: 100%;
}
div .store-left{
    float: left;
    width: 50%;
}
div .store-right{
    float: right;
    width: 50%;
}
.store-back{
    background-color: #ffffff;
    width: 100%;
    border-radius: 10px;
    top:128px;
    padding: 3px;
    border-top: 15px solid #b3754c;
    border-bottom: 15px solid #b3754c;
    margin-top: 128px;
}
.money-info{
    background-color: #b68f54;
    width: 150px;
    height: 100px;
    border-radius: 10px;
    box-shadow: 1px 1px 3px 3px #c7c5c5;
    margin: 4px;
}
.money-parent{
    display: flex;
}
.money-icon{
    flex:1;
}
.money-amount{
    flex:2;
}
</style>