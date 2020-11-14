<template>
    <v-row justify="center">
        <v-dialog
            v-model="dialog"
            width="400"
        >
            <v-card>
                <v-card-title style="display:flex;justify-content:center">
                    아이 퀘스트 등록
                </v-card-title>
         
                <template>
                    <div>                   
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
                                <label><b>아이선택</b></label>
                                <v-col cols="1" />
                                <v-col
                                    cols="8"
                                    style="display:flex; justify-content:flex-start"
                                >
                                    <v-select
                                        ref="kidId"
                                        v-model="selectkid"
                                        :items="listName"
                                        outlined
                                        clearable
                                        dense
                                        class="kidId"
                                        placeholder="아이선택"
                                    />
                                </v-col>
                            </v-col>
                            <v-col
                                cols="12"
                                style="display:flex; justify-content:center"
                            >
                                <label><b>시작일</b></label>
                                <v-col
                                    cols="9"
                                    style="display:flex;justify-content:center;"
                                >
                                    <v-col
                                        cols="5"
                                        class="startTime"
                                    >
                                        <v-select
                                            ref="year"
                                            v-model="year"
                                            :items="years"
                                            placeholder="연도"
                                            outlined
                                            dense
                                            :rules="[rules.required]"
                                        />
                                    </v-col>    
                                    <v-col
                                        cols="2"
                                        class="startTime"
                                    >
                                        <v-text-field
                                            v-model="month"
                                            placeholder="월"
                                            outlined
                                            dense
                                            :rules="[rules.required, rules.month]"
                                        />
                                    </v-col>
                                    <v-col
                                        cols="2"
                                        class="startTime"
                                    >
                                        <v-text-field
                                            v-model="day"
                                            placeholder="일"
                                            outlined
                                            dense
                                            :rules="[rules.required, rules.day]"
                                        />
                                    </v-col>
                                </v-col>
                            </v-col>       
                            <v-col
                                cols="12"
                                style="display:flex; justify-content:center"
                            >
                                <label><b>종료일</b></label>
                                <v-col
                                    cols="9"
                                    style="display:flex;justify-content:center;"
                                >
                                    <v-col
                                        cols="5"
                                        class="endTime"
                                    >
                                        <v-select
                                            ref="year"
                                            v-model="endyear"
                                            :items="endyears"
                                            placeholder="연도"
                                            outlined
                                            dense
                                            :rules="[rules.required]"
                                        />
                                    </v-col>    
                                    <v-col
                                        cols="2"
                                        class="endTime"
                                    >
                                        <v-text-field
                                            v-model="endmonth"
                                            placeholder="월"
                                            outlined
                                            dense
                                            :rules="[rules.required, rules.month]"
                                        />
                                    </v-col>
                                    <v-col
                                        cols="2"
                                        class="endTime"
                                    >
                                        <v-text-field
                                            v-model="endday"
                                            placeholder="일"
                                            outlined
                                            dense
                                            :rules="[rules.required, rules.day]"
                                        />
                                    </v-col>
                                </v-col>
                            </v-col>  
                        </v-form>
                    </div>
                </template>
                <v-card-actions>
                    <v-spacer />
                    <v-btn
                        color="green darken-1"
                        text
                        @click="dialog= false"
                    >
                        <b>취소</b>
                    </v-btn>
                    <v-btn
                        color="green darken-1"
                        text
                        @click="addkidquest"
                    >
                        <b>등록</b>
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
    name:'ConnectKid',
    props:{
        dialog:{
            type:Boolean
        }, 
        target: {
            type:Object,
            required:true,
        },
    },
    data() {
        return {
            valid:true,
            
            //kidquests: new Array(),
            kidsList: new Array(),
            listName: new Array(),
            
            years:['2020','2021'],
            year:'',
            month:'',
            day:'',
            endyears:['2020','2021'],
            endyear:'',
            endmonth:'',
            endday:'',
            items:48,
            selectkid:null,
            rules:{
                required: (value) => !!value,
                month : (value) => (value>=1 && value<=12),
                day : (value) => (value>=1 && value<=31),
            },

        };
    },
    computed:{
        ...mapGetters({
            parentId:'auth/id',
            kidId:'auth/id'
        })
    },
    created() {
       
        // 아이 리스트 가지고 오기 
        axios.get(process.env.VUE_APP_API_URL + '/api/kidsaccount/list/'+this.parentId)
            .then((res) => {
                console.log(res.data);
                this.kidsList = res.data.data;
                this.listName = new Array(this.kidsList.length);
                for (let index = 0; index < this.kidsList.length; index++) {
                    const element = this.kidsList[index];
                    this.listName[index] = {
                        text:element.name,
                        value:element.kidId,
                    };
                    
                }
            });
       
    },
    methods:{
        addkidquest(){
            axios.post(process.env.VUE_APP_API_URL+'/api/quest/kid/regist', {
                         
                kidId: this.kidId,    
                questNo : this.target.questNo,
                'startTime': new Date(this.year, this.month-1, this.day),
                'endTime' : new Date(this.endyear, this.endmonth-1, this.endday),
  
            })
                .then(()=>{
                    console.log('퀘스트 번호'+ this.target.questNo);
                    console.log(this.qusestNo);
                    alert('등록되었습니다!');
                    this.handleDialog();
                    window.location.reload();
                });
            console.log('success!!');

        },
       
        handleDialog() {
            this.$emit('handle');
        }
    }
};
</script>

<style scoped>
.start,.end{
      margin-left: 10px;
  }
  ::v-deep .v-input--selection-controls__input{
    display: none;
}
.col{
        padding: 0;
    }
  
</style>
