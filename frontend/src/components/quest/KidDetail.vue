<template>
    <v-row justify="center">
        <v-dialog
            v-model="dialog"
            persistent
            max-width="200"
        >        
            <v-card class="justify-center">
                <div class="d-flex flex-no-wrap">
                    <div>
                        <v-card-title
                            class="headline"
                            label="이름"
                            v-text="target.name"
                        />                        
                        <v-card-subtitle
                            class="text-left" 
                            label="보상 포인트"   
                            v-text="target.reward"                         
                        />

                        <v-card-text 
                            class="text-left"                            
                            aria-label="설명" 
                            v-text="target.description"   
                        />

                        <v-card-actions>
                            <v-spacer />
                   
                            <v-btn
                                color="orange darken-1"
                                text
                                @click="handleDialog"
                            >
                                확인
                            </v-btn>
                        </v-card-actions>    
                    </div>
                </div>  
            </v-card>
        </v-dialog>
    </v-row>
</template>

<script>

export default {
    name:'KidDetail',
    props:{
        dialog:{
            type:Boolean
        }, 
        target: {
            type:Object,
            required:true,
        },
    },
    methods:{
       
        handleDialog() {
            this.$emit('handle');
        }
    }
};
</script>

<style>
#card {
    margin: 1rem;
    width: 70%;
    
}
</style>