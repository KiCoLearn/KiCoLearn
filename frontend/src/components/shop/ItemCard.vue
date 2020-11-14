<template>
    <v-card
        id="card"
        class="mx-auto"
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

            <v-card-text 
                class="text-left"                            
                v-text="sendData.price" 
            />

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
                    class="mr-1 mt-3"
                    rounded
                    outlined
                >                    
                    <v-icon>mdi-shopping-outline</v-icon>
                    구매요청
                </v-btn>
            </v-card-actions>
        </div>  
    </v-card>
</template>

<script>
//import axios from '@/plugins/axios';

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
        }
    },
    methods:{
        handleLikeItem(itemNo) {
            this.$emit('handleLike', itemNo);
        },
        handleRelease() {
            this.$emit('releaseLike');
        }
    }
};
</script>

<style>
#card {
    margin: 0.01rem;
    max-width: 340px;
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
</style>