<template>
    <v-dialog
        v-model="dialog"
        width="300"
    >
        <template v-slot:activator="{ on, attrs }">    
            <button
                class="btn"
                v-bind="attrs"
                v-on="on"
            >
                <img 
                    src="@/assets/children.png"
                    width="30px"
                    alt="addquest"
                >
            </button>                          
        </template>
        <v-card>
            <v-card-title style="display:flex;justify-content:center">
                아이 퀘스트 등록
            </v-card-title>
         
            <template>
                <div>                   
                    <v-form
                        id="connect-form"
                        ref="connect-form"
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
                    @click="closeDialog"
                >
                    <b>취소</b>
                </v-btn>
                <v-btn
                    color="green darken-1"
                    text
                    @click="addKidQuest"
                >
                    <b>등록</b>
                </v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script>
import axios from '@/plugins/axios';
import { mapGetters } from 'vuex';
export default {
    name: 'ConnectKid',
    props: {
        target: {
            type: Object,
            required: true,
        },
    },
    data() {
        return {
            dialog: false,
            valid: true,
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
        }),
    },

    watch: {
        target(newValue){
            this.name = newValue.name;
            this.reward = newValue.reward;
            this.description = newValue.description;
        }
      
    },
    created() {
        // 아이 리스트 가지고 오기 
        axios.get('/api/kidsaccount/list/'+this.parentId)
            .then((res) => {
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
    methods: {
        addKidQuest() {
            console.log(this.target);
            axios.post('/api/quest/kid/regist', {          
                kidId: this.selectkid,    
                questNo: this.target.questNo,
                startTime: new Date(this.year, this.month-1, this.day),
                endTime: new Date(this.endyear, this.endmonth-1, this.endday),
            })
                .then((res) => {
                    console.log(res);
                    this.$emit('connect-success');
                    this.closeDialog();
                });
        },
        closeDialog() {
            this.dialog=false;
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
