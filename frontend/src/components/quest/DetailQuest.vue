<template>
    <v-row justify="center">
        <v-dialog
            v-model="dialog"
            persistent
            max-width="360"
        >            
            <v-card>
                <v-card-title class="headline">                    
                    퀘스트 수정
                </v-card-title>
                <template>                    
                    <v-form
                        id="form"
                        ref="form"
                        lazy-validation
                    >
                        <v-col
                            cols="12"
                            style="display:flex; justify-content:center"
                        >
                            <label><b>퀘스트 이름</b></label>
                            <v-col cols="1" />
                            <v-col
                                cols="8"
                                style="display:flex; justify-content:flex-start"
                            >
                                <v-text-field
                                    ref="name"
                                    v-model="target.name"
                                    outlined
                                    clearable
                                    dense
                                    class="name"
                                    placeholder="퀘스트 이름"
                                />
                            </v-col>
                        </v-col>
                        <v-col
                            cols="12"
                            style="display:flex; justify-content:center"
                        >
                            <label><b>보상 포인트</b></label>
                            <v-col cols="1" />
                            <v-col
                                cols="8"
                                style="display:flex; justify-content:flex-start"
                            >
                                <v-text-field
                                    ref="reward"
                                    v-model="target.reward"
                                    outlined
                                    clearable
                                    dense
                                    class="reward"
                                    placeholder="보상 포인트"
                                />
                            </v-col>
                        </v-col>
                        <v-col
                            cols="12"
                            style="display:flex; justify-content:center"
                        >
                            <label><b>설명</b></label>
                            <v-col cols="1" />
                            <v-col
                                cols="8"
                                style="display:flex; justify-content:flex-start"
                            >
                                <v-text-field
                                    ref="description"
                                    v-model="target.description"
                                    outlined
                                    clearable
                                    dense
                                    class="description"
                                    placeholder="설명"
                                />
                            </v-col>
                        </v-col>              
                    </v-form>
                </template>
                <v-card-actions>
                    <v-spacer />
                    <v-btn
                        color="orange darken-1"
                        text
                        @click="update"
                    >
                        수정
                    </v-btn>
                    <v-btn
                        color="orange darken-1"
                        text
                        @click="handleDialog"
                    >
                        취소
                    </v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </v-row>    
</template>

<script>
import axios from '@/plugins/axios';
import { mapGetters } from 'vuex';
export default {
    name:'DetailQuest',
    props:{
        dialog:{
            type:Boolean
        }, 
        
        target:{
            type:Object,
            required:true
        }
      
    },
    data(){
        return {
            //valid:true,
            //parentId:0,
            //questNo:this.questNo,
            //name:'',
            //reward:'',
            //description:'',
            //kid: Object,
            //quests : new Array(),
        };
    },
    computed:{
        ...mapGetters({
            parentId:'auth/id',
            kidId:'auth/id'
        })
    },
    
    methods: {
        update(){        
            axios.put(process.env.VUE_APP_API_URL+'/api/quest/parent/update', {
                //questNo:this.target.questNo,
                name:this.target.name,
                reward:this.target.reward,
                description : this.target.description,  
                //parentId: this.target.parentId,
                //parentId: this.target.parentId,
            }).then(()=>{
                console.log('퀘스트 수정');
                alert('수정되었습니다!');
                this.handleDialog();
                window.location.reload();
            });
            console.log('success!!');
        
        },
        handleDialog(){
            this.$emit('handle');
        },
    },
    
};
</script>

<style>
#form{
    padding:0.4rem;    
}
#text{
    margin: 0.2rem;
    width: 300px;
}
</style>