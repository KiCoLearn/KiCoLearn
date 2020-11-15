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
                        id="add-form"
                        ref="add-form"                        
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
                            v-model="name"
                            class="item-field"
                            label="이름"
                            required                            
                        />
                        <v-text-field
                            v-model="price"
                            class="item-field"
                            label="가격"
                            required                            
                        />
                        <v-text-field
                            v-model="description"
                            class="item-field"
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
</template>

<script>
import AWS from 'aws-sdk';
import axios from '@/plugins/axios';
import { mapGetters } from 'vuex';

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
            rules: [
                value => !value || value.size < 2000000 || '사진의 크기가 2MB보다 작아야 합니다.',
            ],
            photoKey:null             
        };
    },
    computed:{
        ...mapGetters({
            parentId:'auth/id'
        })
    },
    mounted() {
        document.querySelectorAll('.item-field input').forEach(e => {
            e.setAttribute('autocomplete', 'off');
        }); 
    },
    methods: {
        handleAddItem(){
            let base = +new Date() + Math.random() + this.parentId + this.name + this.description + this.price;
            base = btoa(unescape(encodeURIComponent(base)));
            //console.log(this.file, '파일이 업로드 되었습니다.');
            if(base.length>20){
                base = this.parentId + base.substr(0,20);
            } else {
                base = this.parentId + base;
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
                    axios.post(process.env.VUE_APP_API_URL+'/api/store/item/regist', {
                        description: this.description,    
                        name: this.name,
                        parentId: this.parentId,
                        price: this.price,
                        field: process.env.VUE_APP_S3_BASE_URL + '/items/' + this.photoKey
                    })
                        .then(()=>{
                            alert('등록되었습니다!');
                            this.handleDialog();
                            //window.location.reload();
                        });
                }
            });
            
             
        },
        handleDialog(){
            this.$emit('handle');
        },
    }
};
</script>

<style scoped>
#form{
    padding:0.4rem;    
}
#text{
    margin: 0.2rem;
    width: 300px;
}

::v-deep .v-text-field{
    width: 90%;
    margin: auto;
}
</style>