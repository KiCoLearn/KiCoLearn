<template>
    <v-row justify="center">
        <v-dialog
            v-model="dialog"
            persistent
            max-width="360"
        >            
            <v-card>
                <v-card-title class="headline">                    
                    아이템 수정
                </v-card-title>
                <template>                    
                    <v-form
                        id="form"
                        ref="form"                        
                        lazy-validation
                    >
                        <v-file-input                            
                            v-model="file"
                            :rules="rules"
                            accept="image/png, image/jpeg"
                            placeholder="사진 등록" 
                            prepend-icon="mdi-camera"                                                     
                        />
                        <v-text-field
                            id="name"
                            v-model="target.name"
                            label="이름"
                            required                                                       
                        />
                        <v-text-field
                            id="price"
                            v-model="target.price"
                            label="가격"
                            required                        
                        />
                        <v-text-field
                            id="desc"
                            v-model="target.description"
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
                        @click="handleModifyItem"
                    >
                        수정
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
</template>

<script>
import AWS from 'aws-sdk';
import axios from '@/plugins/axios';


export default {
    name:'AddItem',
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
            file:null,
            //name:this.target.name,
            //price:this.target.price,
            //description:this.target.description,
            rules: [
                value => !value || value.size < 2000000 || '사진의 크기가 2MB보다 작아야 합니다.',
            ],
            photoKey:null             
        };
    },
    methods: {
        handleModifyItem(){
            if(this.file===null){
                axios.put(process.env.VUE_APP_API_URL+'/api/store/item/update', {
                    description: this.target.description,    
                    name: this.target.name,
                    itemNo:this.target.itemNo,
                    parentId: this.target.parentId,
                    price: this.target.price,
                    field: this.target.field
                })
                    .then(()=>{
                        alert('수정되었습니다!');
                        this.handleDialog();
                        window.location.reload();
                    });
            } else{
                let base = +new Date() + Math.random() + this.target.itemNo + this.name + this.description + this.price;
                base = btoa(unescape(encodeURIComponent(base)));
                //console.log(this.file, '파일이 업로드 되었습니다.');
                if(base.length>20){
                    base = this.target.itemNo + base.substr(0,20);
                } else {
                    base = this.target.itemNo + base;
                }
                if(this.file.type==='image/jpeg'){
                    this.photoKey = base + '.jpg';
                } 
                if(this.file.type==='image/png'){
                    this.photoKey = base + '.png';
                }
                
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
                
                s3.upload({
                    Key:this.photoKey,
                    Body:this.file,
                    ACL:'public-read'
                }, (err) =>{
                    if(err){
                        console.log('error!!:',err);
                    } else {
                        axios.put(process.env.VUE_APP_API_URL+'/api/store/item/update', {
                            description: this.target.description,    
                            name: this.target.name,
                            parentId: this.target.parentId,
                            price: this.target.price,
                            field: process.env.VUE_APP_S3_BASE_URL + '/items/' + this.photoKey
                        })
                            .then(()=>{
                                alert('수정되었습니다!');
                                this.handleDialog();
                                window.location.reload();
                            });
                    }
                });
            }
        },
        handleDialog(){
            this.$emit('handle');
        },
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