<template>
    <v-row dense>
        <v-col
            cols="12"
        >
            <v-card>
                <div class="d-flex flex-no-wrap justify-space-between">
                    <div>
                        <v-card-title
                            class="headline"
                            v-text="sendData.name"
                        />

                        <v-card-subtitle>
                            소지금 : {{ sendData.totalMoney }}
                        </v-card-subtitle>

                        <v-card-actions>
                            <v-btn
                                class="ml-2 mt-5"
                                outlined
                                rounded
                                small
                                @click="handleDisplayItem"
                            >
                                진열하기
                            </v-btn>
                        </v-card-actions>
                    </div>

                    <v-avatar
                        class="ma-3"
                        size="125"
                        tile
                    >
                        <v-img :src="photo(sendData.characterIdx)" />
                    </v-avatar>
                </div>
            </v-card>
        </v-col>
    </v-row>    
</template>

<script>
import axios from 'axios';

export default {
    name:'ItemCard',
    props:{
        sendData: {
            type:Object,
            required:true,
        }
    },
    methods:{
        handleItemDelete () {
            let answer = confirm('아이템을 삭제하시겠습니까?');
            if(answer){
                axios.delete(process.env.VUE_APP_API_URL+'/api/store/item/delete/'+this.sendData.itemNo)
                    .then(()=>{
                        window.location.reload();
                    });
            } else {
                return;
            }
        },
        photo(idx){
            if(idx<10) return require('@/assets/character/00'+idx+'.png');
            return require('@/assets/character/0'+idx+'.png');
        },
        handleDisplayItem(){
            
        }
    }
};
</script>

<style>
#card {
    margin: 0.1rem;
}
</style>
