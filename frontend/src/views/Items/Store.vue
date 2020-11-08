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
                        @click="handleAddItem"
                    >
                        <img 
                            src="@/assets/add.png"
                            width="100px"
                        >
                    </button>
                    <add-item 
                        :dialog="addItem"
                        @handle="handleAddItem"
                    />                          
                </div>
            </v-tab-item>
        </v-tabs-items>
        <!-- <item-card></item-card>     -->
    </div>
</template>

<script>
import axios from 'axios';
import ItemCard from '@/components/items/ItemCard';
import AddItem from '@/components/items/AddItem';
export default {    
    name:'Store',
    components: {
        ItemCard,
        AddItem,
    },
    
    data() {
        return {
            tab:null,
            parentId:0,
            titles:['내 아이템', '아이 목록'],
            myItems: new Array(),
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
            //console.log('create item!');
            this.addItem = this.addItem ? false : true;
        }
    },
};
</script>