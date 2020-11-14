<template>
    <v-row justify="center">
        <v-dialog
            :value="dialog"
            width="360"
        >            
            <v-card>
                <v-card-title class="headline">                    
                    퀘스트 수정
                </v-card-title>
                <template>                    
                    <v-form
                        id="detail-form"
                        ref="detail-form"
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
                                    v-model="name"
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
                                    v-model="reward"
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
                                    v-model="description"
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
                        @click="closeDialog"
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
            type:Boolean,
            required:true,
        }, 
        
        target:{
            type:Object,
            required:true
        }
      
    },
    data(){
        return {
            name:'',
            reward:'',
            description:'',
        };
    },
    computed:{
        ...mapGetters({
            parentId:'auth/id',
            kidId:'auth/select'
        })
    },
    watch: {
        target(newValue){
            this.name = newValue.name;
            this.reward = newValue.reward;
            this.description = newValue.description;
        }
      
    },
    
    methods: {
        update(){        
            axios.put('/api/quest/parent/update', {
                questNo:this.target.questNo,
                name:this.name,
                reward:this.reward,
                description : this.description,  
            }).then(() => {
                this.$emit('update-success');
                this.closeDialog();
            });
        },
        closeDialog() {
            this.$emit('update:dialog', false);
        }
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