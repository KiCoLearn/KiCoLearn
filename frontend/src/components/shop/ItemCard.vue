<template>
    <v-card
        id="card"
        class="mx-auto mb-3 mt-1"
    >
        <v-img
            class="item-img"
            :src="sendData.field"
        />
        <div>
            <v-card-title
                class="headline"
                v-text="sendData.name"
            />                        
            <v-card-subtitle
                class="text-left" 
                v-text="sendData.description"                            
            />
            <v-spacer />
            <div class="parent">
                <div class="child1">
                    <v-img
                        class="child1 mr-3"
                        :src="require('@/assets/coins.png')"
                        width="25px"
                        height="25px"
                    />
                </div>
                <div>
                    <p class="child2 font-weight-bold pr-5">
                        {{ sendData.price }}원
                    </p>
                </div>                
            </div>

            <v-card-actions class="text-right">
                <v-btn
                    v-if="likeNo===sendData.itemNo"                              
                    class="ml-1 mt-3"                    
                    height="40px"
                    right
                    rounded
                    text
                    width="40px"
                    @click="handleRelease"
                >
                    <v-icon color="red">
                        mdi-heart-multiple
                    </v-icon>
                    찜
                </v-btn>
                <v-btn
                    v-else
                    class="ml-1 mt-3"
                    height="40px"
                    rounded
                    text
                    right
                    width="40px"
                    @click="handleLikeItem(sendData.itemNo)"
                >
                    <v-icon>mdi-heart-multiple-outline</v-icon>
                    찜
                </v-btn>
                <v-spacer />
                <v-btn
                    v-if="sendData.request===true"
                    class="mr-1 mt-3"
                    rounded
                    outlined
                    disabled
                >                    
                    <v-icon>mdi-shopping-outline</v-icon>
                    요청완료
                </v-btn>
                <v-btn
                    v-else-if="sendData.request===false"
                    class="mr-1 mt-3"
                    rounded
                    outlined
                    @click="handlePurchaseRequest(sendData)"
                >                    
                    <v-icon>mdi-shopping-outline</v-icon>
                    구매요청
                </v-btn>
            </v-card-actions>
        </div>  
    </v-card>
</template>

<script>
import axios from '@/plugins/axios';
import { mapGetters } from 'vuex';

export default {
    name:'ItemCard',
    props:{
        sendData: {
            type:Object,
            required:true,
        },
        likeNo:{
            type:Number,
            required:true
        },
        holding:{
            type:Number,
            required:true
        }
    },

    computed:{
        ...mapGetters({
            kidId:'auth/id'
        })
    },

    methods:{
        handleLikeItem(itemNo) {
            this.$emit('handleLike', itemNo);
        },
        handleRelease() {
            this.$emit('releaseLike');
        },
        handlePurchaseRequest(item){
            console.log(item);
            console.log(this.holding);
            if(this.holding < item.price){
                alert('소지금이 부족합니다!');
                return;
            }

            let ans = confirm('구매요청 하시겠습니까?').valueOf();

            if(ans===true){
                axios.put(process.env.VUE_APP_API_URL + '/api/store/request/purchase', {
                    itemNo:item.itemNo,
                    kidId:this.kidId
                })
                    .then(()=>{
                        alert('요청 완료!');
                        this.sendData.request = true;
                    }); 
            }

        }
    }
};
</script>

<style>
#card {
    margin: 0.01rem;
    max-width: 320px;
    min-width: 300px;
    border-radius: 20px;    
}
.request-btn{
    position: absolute;
    right: -100px;
    top: 5px;
}
.item-img{
    height: 150px;
    object-fit: contain;
}
div.v-card__text.left{
    float: left;
    width: 20%;
    padding: 0;
}
div.v-card__text.right{
    float: left;
    width: 80%;
    padding: 0;
}
.parent {
    display: flex;
}
.child1 {
    flex: 1;
    float: right;
}
.child2 {
    flex: 2;
}
</style>