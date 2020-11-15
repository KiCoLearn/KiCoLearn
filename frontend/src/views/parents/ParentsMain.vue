<template>
    <div>
        <div
            id="nav"
            class="nav"
        >
            <button
                v-if="isAdmin"
                class="head primary btn"
                @click="quizManage"
            >
                <b>퀴즈관리</b>
            </button>

            <button
                v-if="role === 'parents' && isAuthorized"
                class="head error btn"
                @click="dialog=true"
            >
                <b>회원탈퇴</b>
            </button>

            <v-dialog
                v-model="dialog"
                width="300"
            >
                <v-card>
                    <v-card-title style="display:flex;justify-content:center">
                        <b>정말 탈퇴하시겠습니까?</b>
                    </v-card-title>
                    <v-card-actions>
                        <v-spacer />

                        <v-btn
                            color="green darken-1"
                            text
                            @click="dialog = false"
                        >
                            <b>취소</b>
                        </v-btn>

                        <v-btn
                            color="green darken-1"
                            text
                            @click="unlink"
                        >
                            <b>탈퇴</b>
                        </v-btn>
                    </v-card-actions>
                </v-card>
            </v-dialog>

            <button
                v-if="role === 'parents' && isAuthorized"
                class="head btn"
                @click="logout"
            >
                <b>로그아웃</b>
            </button>
        </div>

        <div
            v-for="kid in kids"
            :key="kid.kidId"
            class="card"
            @click="goInfo(kid.kidId)"
        >
            <!-- 카드 헤더 --> 
            <div class="card-header">
                <div class="card-header-is_closed"> 
                    <div class="card-header-text">
                        퀘스트
                    </div> 
                    <div class="card-header-number">
                        2 / 5
                    </div> 
                </div>
                <div class="card-header-close" />
            </div>
            <!--  카드 바디 -->
            <div class="card-body">
                <img
                    :src="photo(kid.characterIdx)"
                    width="140px"
                >
                
                <!--  카드 바디 헤더 -->
                <div class="card-body-header">
                    <div style="font-size:1.3rem;margin-top:3px">
                        <b>{{ kid.name }}</b>
                    </div>
                    
                    <img
                        src="@/assets/wallet.png"
                        width="30px"
                        style="margin-left:10px;"
                    >
                    <b style="font-size: 1.2rem; color:black;margin-top:3px;margin-left:5px">{{ format(kid.totalMoney) }}원</b> 
                </div>
                
                <!--  카드 바디 푸터 -->
                <div class="card-body-footer">
                    <i class="icon icon-view_count" />완료 38회
                    <i class="icon icon-comments_count" />요청 4개
                    <i class="reg_date"> {{ kid.birth }} </i>
                </div>
            </div>
        </div>
    
        <button 
            class="btn" 
            @click="addKid"
        >
            <img 
                src="@/assets/add.png"
                width="80px"
            >
        </button>
    </div>
</template>

<script>
import axios from '@/plugins/axios';
//import KaKaoLogout from '@/components/oauth/OAuthKakaoLogout';
import { mapGetters, mapMutations } from 'vuex';

export default {
    name : 'KidsList',
    //components:{KaKaoLogout},
    data() {
        return {
            kids: new Array(),
            dialog:false,
        };
    },
    computed: {
        ...mapGetters({
            parentsId : 'auth/id',
            isAdmin : 'auth/isAdmin',
            role : 'auth/role',
            isAuthorized: 'auth/isAuthorized',
        }),
    },
    created() {
        axios.get('/api/kidsaccount/list/'+this.parentsId,
            {
            })
            .then((res) => {
                //console.log('response', res);
                this.kids = res.data.data;
                //console.log(this.kids);
            });
    },
    methods: {
        ...mapMutations({
            setSelect :'auth/SET_SELECT'
        }),

        addKid(){
            this.$router.push({name: 'KidRegist'});
        },
        goInfo(idx){
            this.setSelect({
                select : idx
            });
            this.$router.push({name: 'KidDetail'});
        },
        photo(idx){
            if(idx<10) return require('@/assets/character/00'+idx+'.png');
            return require('@/assets/character/0'+idx+'.png');
        },
        format(x){
            return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
        },
        quizManage(){
            this.$router.push({name:'QuizManage'});
        },
        logout() {
            this.$store.dispatch('auth/logout')
                .then(() => {
                    this.$router.push({name:'Main'});
                }).catch(() => {

                }).finally(() => {
                });
        },
        unlink() {
            this.$store.dispatch('auth/unlink')
                .then(() => {
                    this.dialog=false;
                    this.$router.push({name:'Main'});
                }).catch(() => {

                }).finally(() => {
                });
        }
    },
};
</script>

<style scoped>
*{
    padding:0;
    margin: 0;
}

.card {
    height: 300px;
    width: 300px !important;
    border-radius: 15px;
    display: block;
    margin: auto;
    margin-bottom: 20px;
    position: relative;
    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
    overflow: hidden;
    background: white;
}

.card:hover{
    cursor: pointer;
}

.card-header-close:hover{
    cursor: pointer;
}

.card-header {
	width: 100%;
	height: 61px;
	border-radius: 15px 15px 0 0;
    margin-top: 10px;
}

.card-header-close{
    float: right;
    margin-right: 10px;
}

.card-header-is_closed{
    background-color: #EF5A31 ;
    color: #FFF ;
    font-weight: bold ;
    text-align: center ;
    float: left;
    margin: 5px 0 0 15px;
    border-radius: 50%;
    font-weight: bold;
    padding: 8px 8px;
    line-height: 20px;
}

h1 {
    font-size: 1.5rem;
    font-weight: bold;
}

.card-body-header{
    display: flex;
    justify-content: center;
	align-items: center;
}

.card-body-footer {
    position: absolute;
    background-color: #EF5A31 ; 
    padding-top:11px;
    padding-bottom: 11px;
    bottom: 0;
    width: 300px; 
    font-size: 0.8rem;
    color: white;
}

.icon {
    display: inline-block;
    vertical-align: middle;
}

.icon-view_count {
    width: 10px;
    height: 17px;
}

.icon-comments_count {
	margin-left: 5px;
	width: 10px;
    height: 17px;
}

.reg_date {
    float: right;
    margin-right: 15px;
}

.btn{
    border: none;
    background: none;
}

.btn:focus{
    outline:none;
}

.btn:hover{
    cursor: pointer;
}

.row, .col{
    margin:0;
    padding: 0;
}

.nav{
    font-size: 1.2rem;
    font-family: 'Gaegu';
    color: white;
    display: flex;
    justify-content: flex-end;
    margin-bottom: 20px;
  }


  .head{
    background:#fb8c00;
    border-radius: 20px;
    padding:6px;
    margin-right: 10px;
  }
  
</style>