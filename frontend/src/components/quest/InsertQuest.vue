<template>
    <v-row justify="center">
        <v-dialog
            v-model="dialog"
            width="360"
        >
            <template v-slot:activator="{ on, attrs }">
                <button
                    class="btn"
                    v-bind="attrs"
                    style="margin-top:10px"
                    v-on="on"
                >
                    <img 
                        src="@/assets/add.png"
                        width="50px"
                        alt="addquest"
                    >
                </button>
            </template>
            <v-card>
                <v-card-title class="headline">                    
                    퀘스트 등록
                </v-card-title>
                <template>                    
                    <v-form
                        id="insert-form"
                        ref="insert-form"
                        lazy-validation
                    >
                        <v-text-field
                            v-model="name"
                            type="text"
                            label="퀘스트 명"
                            required                            
                        />
                        <v-text-field
                            v-model="reward"
                            type="number"
                            label="보상"
                            required                            
                        />
                        <v-text-field
                            v-model="description"
                            type="text"
                            label="설명"
                            required                            
                        />                        
                    </v-form>
                </template>
                <v-card-actions>
                    <v-spacer />
                    <v-btn
                        color="orange darken-1"
                        @click="insert"
                    >
                        등록
                    </v-btn>
                    <v-btn
                        color="orange darken-1"
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
    name: 'InsertQuest',
    data() {
        return {
            dialog: false,
            name:'',
            reward:'',
            description:'',  
        };
    },
    computed: {
        ...mapGetters({
            parentId:'auth/id',
            kidId:'auth/select'
        })
    },
    methods: {
        insert() {        
            if (this.$refs['insert-form'].validate()) {
                axios.post('/api/quest/parent/regist', {
                    name: this.name,
                    parentId: this.parentId,
                    reward: this.reward,
                    description: this.description,    
                })
                    .then(() => {
                        this.$emit('insert-success');
                        this.closeDialog();
                    });
            }
        },
        closeDialog() {
            this.dialog = false;
        }
    },
    
};
</script>
