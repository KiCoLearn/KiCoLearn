<template>
    <div class="store">
        <v-tabs v-model="tab">
            <v-tab 
                v-for="title in titles" 
                :key="title"
            >
                {{ title }}
            </v-tab>
        </v-tabs>
        <v-tabs-items v-model="tab">
            <v-tab-item
                v-for="title in titles"
                :key="title"
            >
                <div v-if="title === '내 아이템'">
                    <item-card 
                        v-for="myItem in myItems"
                        :key="myItem.itemNo"                    
                        :send-data="myItem"
                    />
                    <button 
                        class="btn" 
                        @click="addItem"
                    >
                        <img 
                            src="@/assets/add.png"
                            width="100px"
                        >
                    </button>                          
                </div>
            </v-tab-item>
        </v-tabs-items>
        <!-- <item-card></item-card>     -->
    </div>
</template>

<script>
import axios from 'axios';
import ItemCard from '@/components/items/ItemCard';
export default {    
    name:'Store',
    components: {
        ItemCard,
    },
    
    data() {
        return {
            tab:null,
            parentId:0,
            titles:['내 아이템', '아이 목록'],
            myItems: [{itemNo:1, name: '연필'}, {itemNo:2, name: '색연필'}],
            kidsList: new Array(),
            addItem:false,
        };
    },
    
    created() {
        axios.get(process.env.VUE_APP_API_URL + '/api/store/plist/'+this.parentId)
            .then((res) => {
                //this.kids = res.data.data;
                //console.log(res.data.data);
                this.myItems = res.data.data;
            });
    },
    
    methods: {
        handleAddItem(){
            console.log('create item!');
        }
    },
};
</script>