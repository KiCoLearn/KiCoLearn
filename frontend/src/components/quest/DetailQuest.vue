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
                        v-model="valid"
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

export default {
    name:'DetailQuest',
    props:{
        dialog:{
            type:Boolean
        },
    },
    data(){
        return {
            valid:true,
            parentId:0,
            questNo:this.questNo,
            name:'',
            reward:'',
            description:'',
        };
    },
    created() {
        this.name = this.item.name;
        this.description = this.item.description;
        this.reward = this.item.reward;
        this.parentId = this.item.parentId;
    },
    
    methods: {
        update(){        
            if (this.$refs.form.validate()){
                axios.put(process.env.VUE_APP_API_URL+'/api/quest/update', {
                    'description' : this.item.description,    
                    'name':this.item.name,
                    'parentId': this.item.parentId,
                    'reward':this.item.reward,
                })
                    .then(()=>{
                        alert('수정되었습니다!');
                        this.back();
                        window.location.reload();
                    });
                console.log('success!!');
            }
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