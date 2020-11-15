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
                    width="45px"
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
                            sm="6"
                            md="4"
                        >
                            <v-dialog
                                ref="dialog"
                                v-model="dateModal"
                                :return-value.sync="date"
                                persistent
                              
                                
                                width="290px"
                            >
                                <template v-slot:activator="{ on }">
                                    <v-text-field
                                        v-model="sdate"
                                        label="시작 날짜"
                                        prepend-icon="mdi-calendar"
                                        readonly
                                        v-on="on"
                                    />
                                </template>
                                <v-date-picker
                                    v-model="date"
                                    scrollable
                                >
                                    <v-spacer />
                                    <v-btn
                                        text
                                        color="primary"
                                        @click="dateModal = false"
                                    >
                                        Cancel
                                    </v-btn>
                                    <v-btn
                                        text
                                        color="primary"
                                        @click="timeModal = true"
                                    >
                                        OK
                                    </v-btn>
                                </v-date-picker>
                            </v-dialog>

                            <v-dialog
                                ref="dialog2"
                                v-model="timeModal"
                                :return-value.sync="time"
                                persistent
                    
                               
                                width="290px"
                            >
                                <v-time-picker
                                    v-if="timeModal"
                                    v-model="time"
                                >
                                    <v-spacer />
                                    <v-btn
                                        text
                                        color="primary"
                                        @click="timeModal = false"
                                    >
                                        Cancel
                                    </v-btn>
                                    <v-btn
                                        text
                                        color="primary"
                                        @click="set()"
                                    >
                                        OK
                                    </v-btn>
                                </v-time-picker>
                            </v-dialog>
                        </v-col>
                        <v-col
                            cols="12"
                            sm="6"
                            md="4"
                        >
                            <v-dialog
                                ref="dialog3"
                                v-model="enddateModal"
                                :return-value.sync="enddate"
                                persistent
                              
                                width="290px"
                            >
                                <template v-slot:activator="{ on }">
                                    <v-text-field
                                        v-model="edate"
                                        label="종료 날짜"
                                        prepend-icon="mdi-calendar"
                                        readonly
                                        v-on="on"
                                    />
                                </template>
                                <v-date-picker
                                    v-model="enddate"
                                    scrollable
                                >
                                    <v-spacer />
                                    <v-btn
                                        text
                                        color="primary"
                                        @click="enddateModal = false"
                                    >
                                        Cancel
                                    </v-btn>
                                    <v-btn
                                        text
                                        color="primary"
                                        @click="endtimeModal = true"
                                    >
                                        OK
                                    </v-btn>
                                </v-date-picker>
                            </v-dialog>

                            <v-dialog
                                ref="dialog4"
                                v-model="endtimeModal"
                                :return-value.sync="endtime"
                                persistent
                               
                                width="290px"
                            >
                                <v-time-picker
                                    v-if="endtimeModal"
                                    v-model="endtime"
                                >
                                    <v-spacer />
                                    <v-btn
                                        text
                                        color="primary"
                                        @click="endtimeModal = false"
                                    >
                                        Cancel
                                    </v-btn>
                                    <v-btn
                                        text
                                        color="primary"
                                        @click="set2()"
                                    >
                                        OK
                                    </v-btn>
                                </v-time-picker>
                            </v-dialog>
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
            dialog:false,
            //startdate: false,
            //starttime:false,
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
            date: '',
            sdate: '',
            dateModal: false,
            time: '',
            timeModal: false,
            enddate: '',
            edate: '',
            enddateModal: false,
            endtime: '',
            endtimeModal: false,

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
        set() {
            this.sdate = this.date +' '+ this.time;
            this.$refs.dialog.save(this.date);
            this.$refs.dialog2.save(this.time);
        },
        set2() {
            this.edate = this.enddate +' '+ this.endtime;
            this.$refs.dialog3.save(this.enddate);
            this.$refs.dialog4.save(this.endtime);
            console.log(this.enddate);
        },

        addKidQuest() {
            console.log(this.target);
            axios.post('/api/quest/kid/regist', {          
                kidId: this.selectkid,    
                questNo: this.target.questNo,
                startTime: this.date +'T'+ this.time+':00.000',
                endTime:  this.enddate +'T'+ this.endtime+ ':00.000',
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
