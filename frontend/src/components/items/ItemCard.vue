<template>
    <v-row dense>
        <v-col
            cols="12"
        >
            <v-card class="justify-center">
                <div class="d-flex flex-no-wrap">
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
                                class="ml-1 mt-3"
                                fab
                                icon
                                height="40px"
                                right
                                width="40px"
                            >
                                <v-icon>mdi-pencil</v-icon>
                            </v-btn>
                            <v-btn
                                class="ml-1 mt-3"
                                fab
                                icon
                                height="40px"
                                right
                                width="40px"
                                @click="handleItemDelete"
                            >
                                <v-icon>mdi-delete</v-icon>
                            </v-btn>                                        
                        </v-card-actions>
                    </div>
                </div>  
            </v-card>
        </v-col>
    </v-row>

    <!-- <v-card
        id="card"
    >
        <v-img
            src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg"
            height="200px"            
        />
        <v-card-title class="headline">
            {{ sendData.name }}
        </v-card-title>

        <v-card-subtitle>
            1,000 miles of wonder
        </v-card-subtitle>
    </v-card> -->
    <!-- <div>
        {{ sendData.itemNo }} {{ sendData.name }} {{ sendData.price }} {{ sendData.description }}
    </div> -->
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
        }
    }
};
</script>

<style>
#card {
    margin: 0.1rem;
}
</style>