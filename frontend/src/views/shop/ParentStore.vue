<template>
    <div class="wrapper">
        <input
            id="tab1"
            type="radio"
            name="tab"
            checked
        >
        <label for="tab1">내 아이템</label>
        <input
            id="tab2"
            type="radio"
            name="tab"
        >
        <label for="tab2">스토어 관리</label>
        <div class="content">
            <div class="tab1">
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
                            <v-spacer />
                            <v-btn
                                class="editbtn"
                                rounded
                                color="#0A97A1"
                                @click="handleAddItem"   
                            >
                                아이템 추가
                                <v-icon
                                    right
                                    color="black"
                                >
                                    mdi-gift-outline
                                </v-icon>                                    
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
                    color="#20B7E5"
                />
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
            <div class="tab2">
                <v-container fluid>
                    <v-row>
                        <v-col cols="12">
                            <v-combobox
                                v-model="select"
                                :items="listName"
                                label="아이 선택"                                    
                                outlined
                                rounded
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
                            <v-spacer />
                            <v-btn
                                rounded
                                color="#0A97A1"
                                @click="handleStoreManager"           
                            >
                                스토어 관리
                                <v-icon>mdi-cog-outline</v-icon>
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
                <store-manager 
                    :dialog="manager"
                    :send-data="myItems"
                    :kids="select"
                    @handleStoreManager="handleStoreManager"
                    @handleStoreItem="handleStoreItem"
                    @getKidsInfo="getKidsInfo"
                />
            </div>
        </div>
    </div>
</template>

<script>
import axios from '@/plugins/axios';
import AddItem from '@/components/shop/AddItem';
import ModifyItem from '@/components/shop/ModifyItem';
import StoreManager from '@/components/shop/StoreManager';
import { mapGetters } from 'vuex';

export default {    
    name:'ParentStore',
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
                { text: '아이템명', value: 'name', sortable: false},
                { text: '가격', value: 'price', sortable: false},
                { text: '수정/삭제', value: 'actions', sortable: false },
            ],
            kheaders: [
                { value: 'field', sortable:false},
                { text: '아이템명', value: 'name', sortable: false },
                { text: '가격', value: 'price', sortable: false },
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
            this.target={
                ...item,
            };
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

<style lang="scss" scoped>
* {
  box-sizing: border-box;
}

.wrapper {
  width: 100%;
  margin: 10px auto 0px auto;
  max-width: 800px;
  height: 100%;
}
.wrapper label {
  cursor: pointer;
  float: left;
  margin-right: 5px;
  display: block;
  padding: 10px 30px;
  background-color: #ecf0f1;
  border-radius: 20px 20px 0 0;
}
.wrapper input {
  display: none;
}
.wrapper input[id="tab1"]:checked + label {
  background-color: #20B7E5;
  color: #fefefe;
}

.wrapper input[id="tab2"]:checked + label {
  background-color: #20B7E5;
  color: #fefefe;
}

.wrapper input[id="tab1"]:checked ~ .content .tab1,
.wrapper input[id="tab2"]:checked ~ .content .tab2 {
  display: block;
}
.wrapper .content {
  clear: both;
  position: relative;
}

.combobox{
    padding:10;
}
.v-data-table-header.v-data-table-header-mobile{
    height: 0;
}
.editbtn{
    margin: 30;
}

.tab1{
  width: 100%;
  padding: 10px 10px;
  position: absolute;
  top: 0;
  left: 0;
  background-color: #fff;
  display: none;
  border-top: 5px solid #20B7E5;
  border-radius: 0 10px 10px 10px;
}
.tab2{
  width: 100%;
  padding: 10px 10px;
  position: absolute;
  top: 0;
  left: 0;
  background-color: #fff;
  display: none;
  border-top: 5px solid #20B7E5;
  border-radius: 0 10px 10px 10px;
}

ul {
  margin: 0;
  padding: 0, 10;
  max-height: 390px;
  overflow-y: auto;
  border: 1px solid rgba(0, 0, 0, 0.1);
  padding: 5px 5px 0 5px;
  border-left: none;
  border-right: none;
padding-inline-start: 0px;

}

li {
  list-style: none;
  background-color: rgba(0, 0, 0, 0.05);
  background-image: 
    linear-gradient(
      90deg,
      #FFD32E 10px,
      #EEE 10px,
      #EEE 11px,
      transparent 11px);
  padding: 10px 15px 10px 25px;
  border: 1px solid #CCC;
  box-shadow: inset 1px 1px 0 rgba(255, 255, 255, 0.5);
  margin-bottom: 5px;
  width: 100%;
  box-sizing: border-box;
  cursor: pointer;
  border-radius: 3px;
}
.detail{
  width: 70%;
  margin-top: 5px;
}
.right {
  float: right;
  position: relative;
  top: 3px;
}
.start,.end{
      margin-left: 10px;
  }
  
::v-deep .v-input--selection-controls__input{
    display: none;
}
.col{
        padding: 0;
    }

</style>>