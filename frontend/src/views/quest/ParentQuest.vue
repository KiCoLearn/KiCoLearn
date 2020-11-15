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
                        v-for="kidQuest in kidQuests" 
                        id="notebook_ul" 
                        :key="kidQuest.questNo"
                    >
                        <li>
                            <v-row>
                                <kid-quest-detail
                                    :target="kidQuest"
                                    @update-success="fetchKidQuests"
                                />
                                <div class="right top">
                                    <button
                                        class="btn"
                                        style="margin-right:5px"
                                        @click="deleteKidQuest(kidQuest.questNo)"      
                                    >
                                        <img 
                                            src="@/assets/delete.png"
                                            width="40px"
                                        >
                                    </button>
                                </div>
                                <button
                                    v-if="kidQuest.finish"
                                    class="btn"
                                >
                                    <img
                                        width="45px"
                                        src="@/assets/good.png"
                                        alt="success"
                                    >
                                </button>
                                <button
                                    v-else
                                    class="btn"
                                    @click="successQuest(kidQuest)"      
                                >
                                    <img
                                            
                                        :src="!kidQuest.request ? norequest :newrequest"
                                        width="45px"
                                        alt="success"
                                    >
                                </button>
                            </v-row>
                        </li>
                    </ul>
                </div>
                <div />
            </div>

            <!--퀘스트 관리 탭 -->
            <div class="tab2">
                <div class="page">
                    <ul
                        v-for="quest in quests" 
                        id="notebook_ul" 
                        :key="quest.questNo"
                        :quests-per-page="questsPerPage"
                        :pageCount="pageCount"
                    >
                        <li>
                            <v-row>
                                <detail-quest
                                    :target="quest"
                                    @update-success="fetchParentsQuests"
                                />
                                <div class="right top">
                                    <button
                                        class="btn"
                                        style="margin-right:5px"
                                        @click="deleteQuest(quest.questNo)"
                                    >
                                        <img 
                                            src="@/assets/delete.png"
                                            width="40px"
                                            alt="deletequest"
                                        >
                                    </button>
                                    
                                    <connect-kid
                                        :target="quest"
                                        @connect-success="fetchKidQuests"
                                    />
                                </div>
                            </v-row>
                        </li>
                    </ul>
                </div>
                <div>
                    <insert-quest
                        @insert-success="fetchParentsQuests"
                    /> 
                </div>
                <br>
                <v-pagination
                    v-model="page"
                    :length="pageCount"
                    prev-icon="mdi-menu-left"
                    next-icon="mdi-menu-right"
                    color="#fb8c00"
                />
            </div>
        </div>
    </div>
</template>

<script>
import axios from '@/plugins/axios';
import InsertQuest from '@/components/quest/InsertQuest.vue';
import DetailQuest from '@/components/quest/DetailQuest.vue';
import ConnectKid from '@/components/quest/ConnectKid.vue';
import KidQuestDetail from '@/components/quest/KidQuestDetail.vue';
import { mapGetters } from 'vuex';

export default {
    components: { 
        InsertQuest, DetailQuest, ConnectKid, KidQuestDetail
    },
    data() {
        return {
            valid:true,
            quests: new Array(),
            kidQuests: new Array(),
            kidsList: new Array(),
            listName: new Array(),
            page:1,
            pageCount:1,
            questsPerPage: 7,
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
            norequest: `${require('@/assets/norequest.png')}`,
            newrequest :  `${require('@/assets/newrequest.png')}`
        };
    },
    computed:{
        ...mapGetters({
            parentId:'auth/id',
            kidId:'auth/select'
        }),
    },
    created() {
        this.fetchParentsQuests();
        this.fetchKidQuests();
    },
    methods: {
        fetchParentsQuests(){
            axios.get('/api/quest/list/'+this.parentId)
                .then((res) => {
                    this.quests = res.data.data;
                })
                .catch(err => {
                    console.log(err);
                });
        },

        fetchKidQuests(){
            axios.get('/api/quest/kid/list/'+this.kidId)
                .then((res) => {
                    this.kidQuests = res.data.data;
                    //console.log(this.kidQuests);
                })
                .catch(err => {
                    console.log(err);
                });
        },
        deleteQuest(no){
            let answer = confirm('퀘스트를 삭제하시겠습니까?');
            if(answer){
                axios.delete(`/api/quest/parent/delete/${no}`)
                    .then(()=>{
                        this.fetchParentsQuests();
                        this.fetchKidQuests();
                    });
            } else {
                return;
            }
        },
        deleteKidQuest(no){
            let answer = confirm('퀘스트를 삭제하시겠습니까?');
            if(answer){
                axios.delete(`/api/quest/kid/delete/${no}/${this.kidId}`)
                    .then(()=>{
                        this.fetchKidQuests();
                    });
            } else {
                return;
            }
        },
        successQuest(kidquest){
            let answer = confirm('퀘스트를 완료하시겠습니까?');
            if(answer){
                axios.put('/api/quest/kid/finish', {
                    questNo: kidquest.questNo,
                    kidId: kidquest.kidId,
                })
                    .then(()=>{
                        //console.log('퀘스트 완료!');
                    });
            }
        },

        
    }
};
</script>

<style lang="scss" scoped>
* {
  box-sizing: border-box;
}

.row{
    margin: 0;
    align-items: center;
}

.complete-image {
  position: absolute;
  top: -22px;
  right: -60px;
  width: auto;
  height: auto;
  max-width: 4.5rem;
  max-height: 4.5rem;
}
.wrapper {
  width: 100%;
  margin: 10px auto;
  max-width: 330px;
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
  padding: 50px 0px;
  position: absolute;
  top: 0;
  left: 0;
  background-color: #fff;
  display: none;
  border-top: 5px solid #e74c3c;
  border-radius: 0 10px 10px 10px;
}
.tab2{
  width: 100%;
  padding: 50px 0 0 0;
  position: absolute;
  top: 0;
  left: 0;
  background-color: #fff;
  display: none;
  border-top: 5px solid #e74c3c;
  border-radius: 0 10px 10px 10px;

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
::v-deep .detail{
  width: 60%;
  padding-bottom: 0 !important;
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



</style>
