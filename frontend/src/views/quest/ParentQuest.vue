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
                        v-for="kidquest in kidquests" 
                        id="notebook_ul" 
                        :key="kidquest.questNo"
                    >
                        <li>
                            <v-row>
                                <div
                                    class="detail"
                                    @click="detailquest(kidquest)" 
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
                                            alt="kidquestdelete"
                                        >
                                    </button>
                          
                                    &nbsp;
                                    &nbsp;
                                    <button
                                        class="btn"
                                        @click="success"      
                                    >
                                        <img 
                                            src="@/assets/success.png"
                                            width="30px"
                                            alt="success"
                                        >
                                    </button>
                                    <div />
                                </div>
                            </v-row>
                        </li>
                    </ul>
                </div>
                <!-- <div>
                    <insert-quest
                        :dialog="insertQuest"
                        @handle="addquest"
                    /> 
                    <detail-quest
                        :target="target"
                        :dialog="detailQuest"
                        @handle="detailquest"
                        @update-success="fetchParentsQuests"
                    />
                </div> -->
            </div>

            <!--퀘스트 관리 탭 -->
            <div class="tab2">
                <div>
                    <ul
                        v-for="quest in quests" 
                        id="notebook_ul" 
                        :key="quest.questNo"
                    >
                        <li>
                            <v-row>
                                <div
                                    class="detail"
                                    @click="detailquest(quest)"
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
                                    
                                    <button
                                        class="btn"
                                        @click="connectkid(quest)"
                                    >
                                        <img 
                                            src="@/assets/children.png"
                                            width="30px"
                                            alt="addquest"
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
                        @click="openInsertForm"
                    >
                        <img 
                            src="@/assets/add.png"
                            width="40px"
                            alt="addquest"
                        >
                    </button>
                    <insert-quest
                        :dialog.sync="insertQuest"
                        @insert-success="fetchParentsQuests"
                    /> 
                    <connect-kid
                        :target="target"
                        :dialog="connectKid"
                        @handle="connectKid"
                        @handleConnectquest="handleConnectquest"
                    />
                    <detail-quest
                        :target="target"
                        :dialog.sync="detailQuest"
                        @update-success="fetchParentsQuests"
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
import ConnectKid from '@/components/quest/ConnectKid.vue';
import { mapGetters } from 'vuex';

export default {
    components: { InsertQuest,DetailQuest, ConnectKid,},

    data() {
        return {
            valid:true,
            quests: new Array(),
            kidquests: new Array(),
            kidsList: new Array(),
            listName: new Array(),
            insertQuest:false,
            detailQuest:false,
            connectKid:false,
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
            rules:{
                required: (value) => !!value,
                month : (value) => (value>=1 && value<=12),
                day : (value) => (value>=1 && value<=31),
            },
            target:{
                description:null,
                questNo:null,
                parentId:null,
                reward:null,
                name:null,
            },

        };
    },
    computed:{
        ...mapGetters({
            parentId:'auth/id',
            kidId:'auth/select'
        })
    },
    created() {
        this.fetchParentsQuests();
        this.fetchKidQuests();
    },
    methods: {
        fetchParentsQuests(){
            //부모 퀘스트 리스트
            axios.get('/api/quest/list/'+this.parentId)
                .then((res) => {
                    console.log(res);
                    console.log('부모 번호' +this.parentId);
                    console.log(res.data.data);
                    this.quests = res.data.data;
                })
                .catch(err => {
                    console.log(err);
                });
        },

        fetchKidQuests(){
            //아이 퀘스트 리스트
            axios.get('/api/quest/kid/list/'+this.kidId)
                .then((res) => {
                    console.log(res);
                    console.log(res.data.data);
                    this.kidquests = res.data.data;
                })
                .catch(err => {
                    console.log(err);
                });
        },
        openInsertForm() {
            this.insertQuest=true;
        },
        detailquest(quest) {
            this.target={
                ...quest,
            };
            this.detailQuest = this.detailQuest ? false : true;
        },
        connectkid(quest) {
            this.target={
                ...quest,
            };
            this.connectKid = this.connectKid ? false : true;
        },
        // 퀘스트 관리탭에서 지우는 것
        deletequest(no){
            let answer = confirm('퀘스트를 삭제하시겠습니까?');
            if(answer){
                axios.delete(process.env.VUE_APP_API_URL+'/api/quest/parent/delete/'+no)
                    .then(()=>{
                        window.location.reload();
                    });
            } else {
                return;
            }
        },
        // 아이퀘스트 탭에서 지우는것 
        kiddeletequest(no){
            let answer = confirm('퀘스트를 삭제하시겠습니까?');
            if(answer){
                axios.delete(process.env.VUE_APP_API_URL+'/api/quest/kid/delete/'+no+'/'+this.kidId)
                    .then(()=>{
                        window.location.reload();
                    });
            } else {
                return;
            }
        },
        success(){
            axios.put('/api/quest/kid/finish', {
                //questNo:this.target.questNo,
                
                //parentId: this.target.parentId,
                //parentId: this.target.parentId,
            }).then(()=>{
                console.log('퀘스트 완료!');
                alert('완료 승인 되었습니다!');
                this.handleDialog();
                window.location.reload();
            });
            console.log('success!!');
            
        },
        handleConnectquest(quest){
            this.kidquests.push(quest);
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
  padding: 0, 10px;
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
  padding: 10px 15px 10px 5px;
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
