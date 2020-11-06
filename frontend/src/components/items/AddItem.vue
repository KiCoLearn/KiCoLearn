<template>
    <v-row justify="center">
        <v-dialog
            v-model="dialog"
            persistent
            max-width="360"
        >            
            <v-card>
                <v-card-title class="headline">                    
                    아이템 등록
                </v-card-title>
                <template>
                    <v-form
                        id="form"
                        ref="form"
                        v-model="valid"
                        lazy-validation
                    >
                        <v-text-field
                            id="text"
                            v-model="name"
                            label="이름"
                            required                            
                        />
                        <v-text-field
                            id="text"
                            v-model="price"
                            label="가격"
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
                        color="green darken-1"
                        text
                        @click="handleAddItem"
                    >
                        등록
                    </v-btn>
                    <v-btn
                        color="green darken-1"
                        text
                        @click="handleDialog"
                    >
                        취소
                    </v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </v-row>
    <!-- <v-container>
        <h1>파일 업로더</h1>
        <input 
            id="file-selector" 
            ref="file" 
            type="file"
            @change="handleFileUpload()"
        >
        <v-btn
            color="primary" 
            text
            @click="upload"
        >
            업로드
        </v-btn>
    </v-container> -->
</template>

<script>
import AWS from 'aws-sdk';
import axios from 'axios';


export default {
    name:'AddItem',
    props:{
        dialog:{
            type:Boolean
        },
    },
    
    data(){
        return {
            file:null,
            name:null,
            price:null,
            description:null,
            parentId:0,             
        };
    },
    methods: {
        handleAddItem(){
            axios.post(process.env.VUE_APP_API_URL+'/api/store/item/regist', {
                description: this.description,    
                name: this.name,
                parentId: this.parentId,
                price: this.price
            })
                .then(()=>{
                    alert('등록되었습니다!');
                    this.handleDialog();
                    window.location.reload();
                });
        },
        handleDialog(){
            this.$emit('handle');
        },
        handleFileUpload(){            
            this.file = this.$refs.file.files[0];
            console.log(this.file, '파일이 업로드 되었습니다.');
        },
        upload(){
            AWS.config.update({
                region:process.env.VUE_APP_BUCKET_REGION,
                credentials: new AWS.CognitoIdentityCredentials({
                    IdentityPoolId:process.env.VUE_APP_IDENTITY_POOL_ID,
                })
            });

            let s3 = new AWS.S3({
                apiVersion:'2006-03-01',
                params : {
                    Bucket: process.env.VUE_APP_BUCKET_NAME+'/items',
                }
            });
            let photoKey = this.file.name;
            s3.upload({
                Key:photoKey,
                Body:this.file,
                ACL:'public-read'
            }, (err,data) =>{
                if(err){
                    console.log('error!!:',err);
                } else {
                    console.log('success!!',data);
                }
            });
        }
    }
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