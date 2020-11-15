<template>
    <v-dialog
        v-model="dialog"
        width="360"
    >
        <template v-slot:activator="{ on, attrs }">  
            <div
                class="detail"
                v-bind="attrs"
                v-on="on"
            >
                {{ target.name }}
            </div>
        </template>
        <v-card>
            <v-card-title class="headline">                    
                퀘스트 수정
            </v-card-title>
            <v-form
                id="detail-form"
                ref="detail-form"
                lazy-validation
            >
                <v-col
                    cols="12"
                    style="display:flex; justify-content:center"
                >
                    <label class="label1"><b>퀘스트 이름</b></label>
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
                    <label class="label1"><b>보상 포인트</b></label>
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
                    <label class="label1"><b>퀘스트 내용</b></label>
                    
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
            <v-card-actions>
                <v-spacer />
                <v-btn
                    color="orange darken-1"
                    text
                    @click="closeDialog"
                >
                    취소
                </v-btn>
                <v-btn
                    color="orange darken-1"
                    text
                    @click="update"
                >
                    수정
                </v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script>
import axios from '@/plugins/axios';
import { mapGetters } from 'vuex';
export default {
    name:'DetailQuest',
    props:{
        target:{
            type:Object,
            required:true
        }
    },
    data(){
        return {
            dialog: false,
            name: '',
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
    created() {
        this.name = this.target.name;
        this.reward = this.target.reward;
        this.description = this.target.description;
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
            this.dialog = false;
        }
    },
    
};
</script>

<style scoped>
 .label1{
    margin-top:8px;
    margin-right: 10px;
 }

.col{
    padding:0;
}

</style>