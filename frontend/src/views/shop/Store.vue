<template>
    <div class="store">
        <v-tabs 
            v-model="tab"            
        >
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
                    <v-data-table
                        :headers="headers"
                        :items="myItems"
                        :page.sync="page"
                        :items-per-page="itemsPerPage"
                        sort-by="name"
                        class="elevation-1"
                        hide-default-footer
                        @page-count="pageCount = $event"
                    >
                        <template v-slot:top>
                            <v-toolbar
                                flat
                            >
                                <!-- <v-toolbar-title>My CRUD</v-toolbar-title>
                                <v-divider
                                    class="mx-4"
                                    inset
                                    vertical
                                /> -->
                                <v-spacer />
                                <v-btn
                                    class="warning"
                                    @click="handleAddItem"           
                                >
                                    아이템 추가
                                </v-btn>                                
                            </v-toolbar>
                        </template>
                        <template v-slot:[`item.field`]="{ item }">
                            <div class="p-2">
                                <v-img
                                    class="img"
                                    :src="item.field"
                                    :alt="item.field" 
                                    height="150px" 
                                    width="150px" 
                                />
                            </div>
                        </template>
                        <template v-slot:[`item.actions`]="{ item }">        
                            <v-icon
                                small
                                class="mr-2"
                                @click="editParentItem(item)"
                            >
                                mdi-pencil
                            </v-icon>
                            <v-icon
                                small
                                @click="deleteParentItem(item)"
                            >
                                mdi-delete
                            </v-icon>
                        </template>
                        <template v-slot:no-data>
                            생성된 아이템이 존재하지 않습니다.
                        </template>      
                    </v-data-table>
                    <v-pagination
                        v-model="page"
                        :length="pageCount"
                        color="#fb8c00"
                    />
                    <!-- <item-card 
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
                    </button> -->
                    <add-item 
                        :dialog="addItem"
                        @handle="handleAddItem"
                    />
                    <modify-item
                        :target="target"
                        :dialog="edit"
                        @handle="editParentItem"
                    />                       
                </div>
                <div v-if="title === '스토어 관리'">
                    <v-container fluid>
                        <v-row>
                            <v-col cols="12">
                                <v-combobox
                                    v-model="select"
                                    :items="listName"
                                    label="아이 선택"                                    
                                    outlined
                                    dense
                                    @change="handleSelect(select)"
                                />
                            </v-col>
                        </v-row>
                    </v-container>
                    <v-data-table
                        :headers="kheaders"
                        :items="kidsItems"                        
                        mobile-breakpoint="0"
                        sort-by="name"
                        class="elevation-1"
                        hide-default-footer
                    >
                        <template v-slot:top>
                            <v-toolbar
                                flat
                            > 
                                <v-btn
                                    class="warning"
                                    @click="handleStoreManager"           
                                >
                                    스토어 관리
                                </v-btn>                                
                            </v-toolbar>
                        </template>
                        <template v-slot:[`item.field`]="{ item }">
                            <div class="p-2">
                                <v-img
                                    class="img"
                                    :src="item.field"
                                    :alt="item.field" 
                                    height="50px" 
                                    width="50px" 
                                />
                            </div>
                        </template>
                        <template v-slot:[`item.actions`]="{ item }">        
                            <v-icon
                                small
                                class="mr-2"
                                @click="deleteKidsItem(item)"
                            >
                                mdi-close-thick
                            </v-icon>                            
                        </template>
                        <template v-slot:no-data>
                            현재 스토어에 아이템이 존재하지 않습니다.
                        </template>    
                    </v-data-table>
                    <!-- <kids-card 
                        v-for="kids in kidsList"
                        :key="kids.kidId"                    
                        :send-data="kids"
                    />                    
                    <add-item 
                        :dialog="addItem"
                        @handle="handleAddItem"
                    />-->
                    <store-manager 
                        :dialog="manager"
                        :send-data="myItems"
                        :kids="select"
                        @handleStoreManager="handleStoreManager"
                        @handleStoreItem="handleStoreItem"
                        @getKidsInfo="getKidsInfo"
                    />
                </div>
            </v-tab-item>
        </v-tabs-items>
    </div>
</template>

<script>
import axios from '@/plugins/axios';
import AddItem from '@/components/shop/AddItem';
import ModifyItem from '@/components/shop/ModifyItem';
import StoreManager from '@/components/shop/StoreManager';
import { mapGetters } from 'vuex';

export default {    
    name:'Store',
    components: {
        AddItem,
        StoreManager,
        ModifyItem
    },

    data() {
        return {
            tab:null,
            titles:['내 아이템', '스토어 관리'],
            myItems: new Array(),
            kidsList: new Array(),
            listName: new Array(),
            addItem:false,
            manager:false,
            edit:false,          
            parentItems:[],
            kidsItems:[],
            page: 1,
            pageCount: 0,
            itemsPerPage: 3,
            select:null,
            target:{
                description:null,
                field:null,
                itemNo:null,
                parentId:null,
                price:null
            },
            headers: [
                { value: 'field', sortable:false},
                { text: '아이템명', value: 'name' },
                { text: '가격', value: 'price' },
                { text: '수정/삭제', value: 'actions', sortable: false },
            ],
            kheaders: [
                { value: 'field', sortable:false},
                { text: '아이템명', value: 'name' },
                { text: '가격', value: 'price' },
                { text: '삭제', value: 'actions', sortable: false },
            ],
        };
    },

    computed:{
        ...mapGetters({
            parentId:'auth/id'
        })
    },   
    
    created() {
        axios.get(process.env.VUE_APP_API_URL + '/api/store/plist/'+this.parentId)
            .then((res) => {
                this.myItems = res.data.data;
            });
        
        axios.get(process.env.VUE_APP_API_URL + '/api/kidsaccount/list/'+this.parentId)
            .then((res) => {
                console.log(res.data);
                this.kidsList = res.data.data;
                this.listName = new Array(this.kidsList.length);
                for (let index = 0; index < this.kidsList.length; index++) {
                    const element = this.kidsList[index];
                    this.listName[index] = {
                        text:element.name,
                        value:element.kidId,
                    };                    
                }
            });

    },
    
    methods: {
        handleAddItem(){
            this.addItem = this.addItem ? false : true;
        },
        editParentItem(item){
            //console.log(item);
            this.target={
                ...item,
            };
            console.log(this.target);
            this.edit = this.edit ? false : true;
        },
        deleteParentItem(item){
            if(item.parentId===0){
                alert('기본 아이템은 삭제할 수 없습니다.');
                return;
            }
            axios.delete(process.env.VUE_APP_API_URL + '/api/store/item/delete/'+ item.itemNo)
                .then(() => {
                    this.myItems.forEach((data,index)=>{
                        if(data.itemNo===item.itemNo){
                            this.myItems.splice(index, 1);
                        }
                    });
                });
        },
        handleSelect(kids){
            console.log(kids);
            axios.get(process.env.VUE_APP_API_URL + '/api/store/klist/'+ kids.value)
                .then((res) => {
                    //console.log(res.data);
                    this.kidsItems = res.data.data;
                });
        },
        handleStoreManager(){
            if(this.select===null){
                alert('아이를 선택해주세요!');
                return;
            }
            this.manager = this.manager ? false : true;
        },
        handleStoreItem(item){
            this.kidsItems.push(item);
        },
        getKidsInfo(callback){
            callback(this.select);
        },
        deleteKidsItem(item){
            axios.post(process.env.VUE_APP_API_URL + '/api/store/kidsitem/delete',{
                itemNo:item.itemNo,
                kidId:this.select.value
            })
                .then(()=>{
                    this.kidsItems.forEach((data,index)=>{
                        if(data.itemNo===item.itemNo){
                            this.kidsItems.splice(index, 1);
                        }
                    });
                })
                .catch(()=>{
                    alert('이미 등록된 아이템입니다.');
                });
        }
    },
};
</script>

<style scoped>
::v-deep .v-data-table__mobile-table-row > td:first-child{
    display: flex;
    justify-content: center;
}

::v-deep .v-image__image--cover {
    background-size: contain;
}
</style>