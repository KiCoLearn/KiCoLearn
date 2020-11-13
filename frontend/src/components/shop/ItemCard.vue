<template>
    <v-row dense>
        <v-col
            cols="12"
        >
            <v-card class="justify-center">
                <div class="d-flex flex-no-wrap">
                    <!-- <v-card-actions class="text-right">                        
                        <v-btn
                            v-if="likeNo===sendData.itemNo"
                            @click="handleRelease(sendData.itemNo)"
                        >
                            찜
                        </v-btn>
                        <v-btn
                            v-else
                            @click="handleLikeItem(sendData.itemNo)"
                        >
                            선택
                        </v-btn>
                    </v-card-actions> -->
                    <!-- <v-col> 
                        <div>
                            <v-btn>
                                선택
                            </v-btn>
                        </div>
                    </v-col> -->
                    <v-avatar
                        class="ma-4"
                        size="120"
                        tile
                    >
                        <v-img :src="sendData.field" />
                    </v-avatar>
                    <div>
                        <v-card-title
                            class="headline"
                            v-text="sendData.name"
                        />                        
                        <v-card-subtitle
                            class="text-left" 
                            v-text="sendData.price"                            
                        />

                        <v-card-text 
                            class="text-left"                            
                            v-text="sendData.description" 
                        />

                        <v-card-actions class="text-right">
                            <v-btn
                                v-if="likeNo===sendData.itemNo"                              
                                class="ml-1 mt-3"
                                fab
                                icon
                                height="40px"
                                right
                                width="40px"
                                @click="handleRelease"
                            >
                                <v-icon color="red">
                                    mdi-heart-multiple
                                </v-icon>
                            </v-btn>
                            <v-btn
                                v-else
                                class="ml-1 mt-3"
                                fab
                                icon
                                height="40px"
                                right
                                width="40px"
                                @click="handleLikeItem(sendData.itemNo)"
                            >
                                <v-icon>mdi-heart-multiple-outline</v-icon>
                            </v-btn>
                        </v-card-actions>
                    </div>
                </div>  
            </v-card>
        </v-col>
    </v-row>
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
    margin: 0.1rem;
}
</style>