<template>
    <div
        v-if="kid.characterIdx!=undefined"
        class="detail"
    >
        <v-layout>
            <v-flex>
                <v-row
                    justify="center"
                    style="margin-left:33px"
                >
                    <img
                        :src="photo(kid.characterIdx)"
                        width="140px"
                    >
                    <v-dialog
                        v-model="dialog"
                        persistent
                        scrollable
                        width="300px"
                    >
                        <template v-slot:activator="{ on, attrs }">
                            <button
                                v-bind="attrs"
                                class="btn"
                                style="display:flex;align-items:flex-end"
                                v-on="on"
                            >
                                <img
                                    src="@/assets/gallery.png"
                                    width="45px"
                                    height="45px"
                                    class="profile"
                                >
                            </button>
                        </template>
                        <v-card>
                            <v-card-title><b>프로필 캐릭터</b></v-card-title>
                            <v-divider />
                            <v-card-text style="height: 300px; padding:15px">     
                                <img
                                    v-for="(item,idx) in items"
                                    :key="idx"
                                    :src="getPhotoPath(idx+1)"
                                    width="80px"
                                    :class="{'active': isActive(idx+1)}"
                                    style="margin:2px; padding:3px"
                                    @click="pick=idx+1"
                                >
                            </v-card-text>
                            <v-divider />
                            <v-card-actions>
                                <v-spacer />
                                <v-btn
                                    color="blue darken-1"
                                    text
                                    @click="changeProfile()"
                                >
                                    <b>결정</b>
                                </v-btn>
                            </v-card-actions>
                        </v-card>
                    </v-dialog> 
                </v-row>
                <v-row
                    justify="center"
                    style="font-size:1.2rem;margin-bottom:5px"
                >
                    <div>
                        <b>{{ kid.name }}</b> 어린이의 현재 잔액
                        <br>
                       
                        <b style="font-size:1.3rem"><animated-number
                            :value="kid.totalMoney"
                            :format-value="formatToPrice"
                            :duration="600"
                        />원</b>
                    </div>
                </v-row>

                <v-row
                    justify="center"
                    style="font-family:CookieRun-Regular;white-space:pre-line;background:lightgray;padding:10px"
                >
                    {{ quizComment }}
                </v-row>

                <div style="height:160px; padding:10px;margin-bottom:15px;border:2px dashed #fb8c00;border-radius: 4px; display:flex;justify-content: center;align-items: center;">
                    <v-col
                        v-for="q in quest.slice(0, 3)"
                        :key="q.questNo"  
                        class="quest"
                        justify="center"
                        @click="goQuest"
                    >
                        <v-col cols="12">
                            <img
                                src="@/assets/coin.png"
                                width="50px"
                            >
                        </v-col>
                        <v-col
                            cols="12"
                            class="questName"
                        >
                            {{ q.name }}
                        </v-col>
                        
                        <v-col cols="12">
                            {{ q.reward }}원
                        </v-col>
                    </v-col>

                    <div v-if="quest.length==0">
                        앗! 아직 퀘스트가 없어요!
                    </div>
                </div>
                
                <v-row
                    justify="center"
                    style="margin-top:25px;width:100%"
                >
                    <v-col
                        cols="4"
                        style="width:100px;height:100px; display:flex; align-items:center;margin-right:10px;justify-content:center"
                    >
                        <img
                            :src="imgSrc"
                            style="max-height:100px;max-width:100px;width:auto;height:auto"
                        >
                    </v-col>
                    <v-col
                        cols="8"
                        class="gift"
                    >
                        <div v-if="kid.likeItem">
                            <template v-if="leftMoneyToLikeItem>0"> 
                                <b>{{ likeItem.name }}</b> 사기 위해서<br> 
                                <b>{{ leftMoneyToLikeItem }}원</b>을 모아야해요!
                            </template>
                            <template v-else> 
                                <b>{{ likeItem.name }}</b><br>
                                지금 살 수있어요!<br>
                                사러 가볼까요?
                                <img
                                    src="@/assets/redo.png"
                                    width="20px"
                                    @click="goStore"
                                >
                            </template>
                        </div>
                        <div v-else>
                            <button
                                class="btn"
                                @click="goStore"
                            >
                                <img
                                    src="@/assets/add.png"
                                    width="50px"
                                >
                            </button>
                        </div>
                    </v-col>
                </v-row>
               
                <v-row
                    justify="end"
                    class="nav"
                    style="margin-bottom:0"
                >
                    <button
                        rounded
                        class="head btn"
                        @click="logout"
                    >
                        <b>로그아웃</b>
                    </button>
                </v-row>
            </v-flex>
        </v-layout>
    </div>
</template>

<script>
import axios from '@/plugins/axios';  
import AnimatedNumber from 'animated-number-vue';
import { mapGetters } from 'vuex';

export default {
    name: 'KidMain',
    components: {
        AnimatedNumber,
    },
    data() {
        return {
            kid: Object,
            quest : new Array(),
            dialog: false,
            items: 48,
            pick: null,
            quizComment:null,
            likeItem : Object,
            imgSrc : require('@/assets/heart.png'),
        };
    },

    computed: {
        ...mapGetters({
            kidId : 'auth/id',
        }),
        leftMoneyToLikeItem(){
            if(this.likeItem.price-this.kid.totalMoney<0) return 0;
            return this.likeItem.price-this.kid.totalMoney;
        } 
    },
    created() {
        axios.get('/api/quiz/today/'+this.kidId)
            .then((res) => {
                //console.log(res.data.data);
                if(res.data.data!=null) {
                    this.quizComment = res.data.data.correct ? `오늘의 퀴즈로 300원을 얻었어요!` : `아쉽게 오늘의 퀴즈를 틀렸지만 \n내일 맞추면 300원을 얻을 수 있어요^^`;
                } else{
                    this.quizComment='아직 오늘의 퀴즈를 풀지 않았어요.\n맞추면 300원! 얼른 풀러가볼까요?';
                }
            })
            .catch(() => {
            });

        axios.get('/api/kidsaccount/detail/'+this.kidId)
            .then((res) => {
                this.kid= res.data.data;
                this.pick =res.data.data.characterIdx;
                if(this.kid.likeItem){
                    axios.get('/api/store/detail/'+this.kid.likeItem)
                        .then((res) => {
                            this.likeItem = res.data.data;
                            console.log(this.likeItem);
                            this.imgSrc = this.likeItem.field;
                        });
                }

            })
            .catch(() => {
            });

        axios.get('/api/quest/kid/list/'+this.kidId)
            .then((res) => {
            //console.log(res.data.data);
                this.quest = res.data.data;
            })
            .catch(() => {
            }); 
    },
    methods: {
        photo(idx){
            if(idx<10) return require('@/assets/character/00'+idx+'.png');
            return require('@/assets/character/0'+idx+'.png');
        },
        formatToPrice(value) {
            return `${value.toFixed(0)}`;
        },
        getPhotoPath(idx){
            if(idx<10) return require('@/assets/character/00'+idx+'.png');
            return require('@/assets/character/0'+idx+'.png');
        },
        isActive(idx){
            if(this.pick==idx) return true;
            return false;
        },
        changeProfile(){
            this.dialog = false;
            this.kid.characterIdx=this.pick;
            axios.put('/api/kidsaccount/updateProfile',
                {
                    'kidId': this.kid.kidId,
                    'characterIdx': this.pick
                })
                .then(() => {
                    
                });
        },
        goStore(){
            this.$router.push({name:'KidStore'});
        },
        goQuest(){
            this.$router.push({name:'KidQuest'});
        },
        logout(){
            this.$store.commit('auth/LOGOUT');
            this.$router.push({name:'Main'});
        }
    },

};
</script>

<style lang="scss" scoped>
  .row{
    margin-bottom:25px;
    margin-left: 0;
    margin-right: 0;
  }

  .col{
    padding:0;
    margin: 0;
    flex-grow: unset;
    flex-basis: unset;
  }

.btn2{
    padding:10px 15px 5px 15px;
    border:2px solid #ffdd93;
    border-radius: 6px;
    background-color: white;
    outline:none;
}

.quest{
    display: flex;
    flex-direction: column;
    justify-content: center;
    padding:5px;
    height: 130px;
    max-width: 84px;
    text-overflow: ellipsis;
    border-radius: 15px;
    background-color: #ffdd93;
    margin-left: 10px;
    margin-right: 10px;
    box-shadow: 0 2px 4px 0 rgba(0, 0, 0, 0.15), 0 4px 8px 0 rgba(0, 0, 0, 0.15);
}

.detail{
    width: 336px;
    margin: auto;  
}

.nav{
    font-size: 1.2rem;
    font-family: 'Gaegu';
    color: white;
}

.head{
    background:#fb8c00;
    border-radius: 40px;
    padding:6px;
    margin-right: 15px;
}

::v-deep .chartjs-render-monitor{
    width: 150px!important;
    height: 150px !important;
    margin: auto;
    margin-bottom: 10px;
}

.active{
    border-radius: 4px;
    background: peachpuff;
}

.questName{
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
} 

.gift{
    display: flex;
    border-radius: 4px;
    background: rgb(196, 232, 255);
    align-items: center;
    justify-content: center;
}
</style>