<template>
    <v-container>
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
    </v-container>
</template>

<script>
import AWS from 'aws-sdk';

export default {
    data(){
        return {
            file:null,            
        };
    },
    methods: {
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
                    Bucket: process.env.VUE_APP_BUCKET_NAME,
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