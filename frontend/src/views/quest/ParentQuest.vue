<template>
    <div class="wrapper">
        <input
            id="tab1"
            type="radio"
            name="tab"
            checked
        >
        <label for="tab1">아이 퀘스트</label>
        <input
            id="tab2"
            type="radio"
            name="tab"
        >
        <label for="tab2">퀘스트 관리</label>
        <div class="content">
            <!-- 아이퀘스트 관리  -->
            <div class="tab1">
                <div>
                    <ul
                        v-for="(kidquest,idx) in kidquests" 
                        id="notebook_ul" 
                        :key="idx"
                    >
                        <li>
                            <v-row>
                                <div
                                    class="detail"
                                    @click="detailquest"
                                >
                                    {{ kidquest.name }}
                                </div>
                                <div class="right top">
                                    <button
                                        class="btn"
                                        @click="deletequest(kidquest.questNo)"
                                    >
                                        <img 
                                            src="@/assets/delete.png"
                                            width="30px"
                                            alt="deletequest"
                                        >
                                    </button>
                                </div>
                            </v-row>
                        </li>
                    </ul>
                </div>
                <div>
                    <button
                        class="btn"
                        @click="addquest"
                    >
                        <img 
                            src="@/assets/add.png"
                            width="40px"
                            alt="addquest"
                        >
                    </button>
                    <insert-quest
                        :dialog="insertQuest"
                        @handle="addquest"
                    /> 
                    <detail-quest
                        :dialog="detailQuest"
                        @handle="detailquest"
                    />
                </div>
            </div>

            <!--퀘스트 관리 탭 -->
            <div class="tab2">
                <div>
                    <ul
                        v-for="(quest,idx) in quests" 
                        id="notebook_ul" 
                        :key="idx"
                    >
                        <li>
                            <v-row>
                                <div
                                    class="detail"
                                    @click="detailquest"
                                >
                                    {{ quest.name }}
                                </div>
                                <div class="right top">
                                    <button
                                        class="btn"
                                        @click="deletequest(quest.questNo)"
                                    >
                                        <img 
                                            src="@/assets/delete.png"
                                            width="30px"
                                            alt="deletequest"
                                        >
                                    </button>
                 &nbsp;
                 &nbsp;
                   
                                    <v-dialog
                                        v-model="dialog"
                                        width="400"
                                    >
                                        <template v-slot:activator="{ on, attrs }">
                                            <button
                                                v-bind="attrs"
                                                class="btn"
                                                v-on="on"
                                            >
                                                <img 
                                                    src="@/assets/children.png"
                                                    width="30px"
                                                    alt="kidselect"
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
                                </div>
                            </v-row>
                        </li>
                    </ul>
                </div>
                <div>
                    <button
                        class="btn"
                        @click="addquest"
                    >
                        <img 
                            src="@/assets/add.png"
                            width="40px"
                            alt="addquest"
                        >
                    </button>
                    <insert-quest
                        :dialog="insertQuest"
                        @handle="addquest"
                    /> 
                    <detail-quest
                        :dialog="detailQuest"
                        :send-data="quests"
                        @handle="detailquest"
                    />
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from '@/plugins/axios';
import InsertQuest from '@/components/quest/InsertQuest.vue';
import DetailQuest from '@/components/quest/DetailQuest.vue';
import { mapGetters } from 'vuex';
export default {
    components: { InsertQuest,DetailQuest, },
    computed:{
        ...mapGetters({
            parentId:'auth/id',
            kidId:'auth/id'
        })
    },
    data() {
        return {
            valid:true,
            parentId:4,
            quests: new Array(),
            kidquests: new Array(),
            kidsList: new Array(),
            listName: new Array(),
            insertQuest:false,
            detailQuest:false,
            dialog:false,
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
            questNo:this.questNo,
            rules:{
                required: (value) => !!value,
                month : (value) => (value>=1 && value<=12),
                day : (value) => (value>=1 && value<=31),
            },

        };
    },
    created() {
        axios.get(process.env.VUE_APP_API_URL + '/api/quest/list/'+this.parentId)
            .then((res) => {
                console.log(res);
                console.log(res.data.data);
                this.quests = res.data.data;
            })
            .catch(err => {
                console.log(err);
            });
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
        //아이 퀘스트 리스트
        axios.get(process.env.VUE_APP_API_URL + '/api/quest/kid/list/'+this.kidId)
            .then((res) => {
                console.log('아이 퀘스트 리스트');
                console.log(res);
                console.log('아이 번호'+this.kidId);
                console.log('체크1');
                console.log(res.data);
                console.log('체크2');
                
                console.log(res.data.data);
                this.kidquests = res.data;
            })
            .catch(err => {
                console.log(err);
            });

    },
    methods: {
        detailquest(){
            this.detailQuest = this.detailQuest ? false : true;
        },
        addquest(){
            this.insertQuest = this.insertQuest ? false :true;
        },
        deletequest(no){
            let answer = confirm('아이템을 삭제하시겠습니까?');
            if(answer){
                axios.delete(process.env.VUE_APP_API_URL+'/api/quest/parent/delete/'+no)
                    .then(()=>{
                        window.location.reload();
                    });
            } else {
                return;
            }
        },
        addkidquest(){
      
            axios.post(process.env.VUE_APP_API_URL+'/api/quest/kid/regist', {
                         
                kidId: this.kidId,    
                questNo : this.questNo,
                'startTime': new Date(this.year, this.month-1, this.day),
                'endTime' : new Date(this.endyear, this.endmonth-1, this.endday),
  
            })
                .then(()=>{
                    console.log('퀘스트 번호');
                    console.log(this.qusestNo);
                    alert('등록되었습니다!');
                    this.handleDialog();
                    window.location.reload();
                });
            console.log('success!!');

        },
        handleDialog(){
            this.$emit('handle');
        },
    }
};
</script>

<style lang="scss" scoped>
* {
  box-sizing: border-box;
}

.wrapper {
  width: 100%;
  margin: 10px auto;
  max-width: 800px;
  height: 100%;
}
.wrapper label {
  cursor: pointer;
  float: left;
  margin-right: 5px;
  display: block;
  padding: 10px 30px;
  background-color: #ecf0f1;
  border-radius: 20px 20px 0 0;
}
.wrapper input {
  display: none;
}
.wrapper input:checked + label {
  background-color: #e74c3c;
  color: #fefefe;
}
.wrapper input[id="tab1"]:checked ~ .content .tab1,
.wrapper input[id="tab2"]:checked ~ .content .tab2 {
  display: block;
}
.wrapper .content {
  clear: both;
  position: relative;
}

.tab1{
  width: 100%;
  padding: 70px 10px;
  position: absolute;
  top: 0;
  left: 0;
  background-color: #fff;
  display: none;
  border-top: 5px solid #e74c3c;
  border-radius: 0 10px 10px 10px;
  overflow-y: scroll;
}
.tab2{
  width: 100%;
  padding: 70px 10px;
  position: absolute;
  top: 0;
  left: 0;
  background-color: #fff;
  display: none;
  border-top: 5px solid #e74c3c;
  border-radius: 0 10px 10px 10px;
  overflow-y: scroll;

}

ul {
  margin: 0;
  padding: 0, 10;
  max-height: 390px;
  overflow-y: auto;
  border: 1px solid rgba(0, 0, 0, 0.1);
  padding: 5px 5px 0 5px;
  border-left: none;
  border-right: none;
padding-inline-start: 0px;

}

li {
  list-style: none;
  background-color: rgba(0, 0, 0, 0.05);
  background-image: 
    linear-gradient(
      90deg,
      #FFD32E 10px,
      #EEE 10px,
      #EEE 11px,
      transparent 11px);
  padding: 10px 15px 10px 25px;
  border: 1px solid #CCC;
  box-shadow: inset 1px 1px 0 rgba(255, 255, 255, 0.5);
  margin-bottom: 5px;
  width: 100%;
  box-sizing: border-box;
  cursor: pointer;
  border-radius: 3px;
}
.detail{
  width: 70%;
  margin-top: 5px;
}
.right {
  float: right;
  position: relative;
  top: 3px;
}
.start,.end{
      margin-left: 10px;
  }
  
::v-deep .v-input--selection-controls__input{
    display: none;
}
.col{
        padding: 0;
    }

</style>>
