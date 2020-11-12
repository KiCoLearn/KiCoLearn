<template>
    <v-row justify="center">
        <v-dialog
            v-model="dialog"
            persistent
            max-width="360"
        >            
            <v-card>
                <v-card-title class="headline">                    
                    퀘스트 등록
                </v-card-title>
                <template>                    
                    <v-form
                        id="form"
                        ref="form"
                        lazy-validation
                    >
                        <v-text-field
                            id="text"
                            v-model="name"
                            label="퀘스트 명"
                            required                            
                        />
                        <v-text-field
                            id="text"
                            v-model="reward"
                            label="보상"
                            required                            
                        />
                        <v-text-field
                            id="text"
                            v-model="description"
                            label="설명"
                            required                            
                        />                        
                    </v-form>
                </template>
                <v-card-actions>
                    <v-spacer />
                    <v-btn
                        color="orange darken-1"
                        text
                        @click="handleAddItem"
                    >
                        등록
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
    name:'InsertQuest',
    data(){
        return {
            name:'',
            reward:'',
            description:'',
            parentId:3,
            dialog: true,
        };
    },
    methods: {
        handleAddItem(){        
            if (this.$refs.form.validate()){
                axios.post(process.env.VUE_APP_API_URL+'/api/quest/parent/regist', {
                    description: this.description,    
                    name: this.name,
                    parentId: this.parentId,
                    reward: this.reward,
                })
                    .then(()=>{
                        alert('등록되었습니다!');
                        this.handleDialog();
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