<template>
    <v-row justify="center">
        <v-dialog
            v-model="dialog"
            persistent
            max-width="350"
        >
            <v-card>
                <v-card-title class="headline">
                    스토어 관리
                </v-card-title>
                <v-data-table
                    :headers="headers"
                    :items="sendData"                        
                    mobile-breakpoint="0"
                    sort-by="name"
                    class="elevation-1"
                    hide-default-footer
                >
                    <!-- <template v-slot:top>
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
                    </template> -->
                    <template v-slot:[`item.field`]="{ item }">
                        <div class="p-2">
                            <v-img
                                class="img"
                                :src="item.field"
                                :alt="item.field"
                            />
                        </div>
                    </template>
                    <template v-slot:[`item.actions`]="{ item }">        
                        <v-icon
                            small
                            class="mr-2"
                            @click="insertItem(item)"
                        >
                            mdi-plus-box
                        </v-icon>                            
                    </template>
                    <template v-slot:no-data>
                        등록된 아이템이 없습니다.
                    </template>    
                </v-data-table>
                <v-card-actions>
                    <v-spacer />                    
                    <v-btn
                        color="green darken-1"
                        text
                        @click="handleDialog"
                    >
                        종료
                    </v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </v-row>
</template>

<script>
import axios from '@/plugins/axios';

export default {
    name:'StoreManager',
    props:{
        dialog:{
            type:Boolean
        },
        sendData:{
            type:Array,
            required:true
        },
        // kids:{
        //     type:Object,
        //     required:true
        // },        
    },
    data(){
        return {
            headers:[
                { value: 'field', sortable:false},
                { text: '아이템명', value: 'name' },
                { text: '가격', value: 'price' },
                { text: '추가', value: 'actions', sortable: false },
            ]
        };
    },
    methods:{
        handleDialog(){
            this.$emit('handleStoreManager');
        },
        insertItem(item){            
            let kids;
            this.$emit('getKidsInfo', (res)=>kids=res);
            console.log(item);
            console.log(kids);
            axios.post(process.env.VUE_APP_API_URL + '/api/store/kidsitem/add',{
                itemNo:item.itemNo,
                kidId:kids.value
            })
                .then(()=>{
                    this.$emit('handleStoreItem', item);
                })
                .catch(()=>{
                    alert('이미 등록된 아이템입니다.');
                });
        },
    }
};
</script>

<style lang="scss" scoped>
    .img{
        max-height:50px;
        max-width:50px;
        width:auto;
        height:auto;
        margin-top: 4px;
        margin-bottom: 4px;
        
    }

    ::v-deep .v-image__image--cover{
        background-size: contain;
    }
</style>