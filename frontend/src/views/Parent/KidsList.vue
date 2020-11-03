<template>
    <div>
        <div 
            v-for="kid in kids"
            :key="kid.kidId"
            class="card"
        >
            <!-- 카드 헤더 -->
            <div class="card-header">
                <img
                    src="@/assets/character/001.png"
                    width="150px"
                > 
                <div class="card-header-is_closed"> 
                    <div class="card-header-text">
                        퀘스트
                    </div> 
                    <div class="card-header-number">
                        2 / 5
                    </div> 
                </div>
            </div>
            <!--  카드 바디 -->
            <div class="card-body">
                <!--  카드 바디 헤더 -->
                <div class="card-body-header">
                    <h1>{{ kid.name }}</h1>
                    <p class="card-body-hashtag">
                        <b>{{ kid.totalMoney }}원</b>
                    </p>
                </div>
                
                <!--  카드 바디 푸터 -->
                <div class="card-body-footer">
                    <i class="icon icon-view_count" />완료 38회
                    <i class="icon icon-comments_count" />요청 4개
                    <i class="reg_date"> 2020/11/03 </i>
                </div>
            </div>
        </div>
        <br>
        <button 
            class="btn" 
            @click="addKid"
        >
            <img 
                src="@/assets/add.png"
                width="100px"
            >
        </button>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    name : 'KidsList',
    data() {
        return {
            parentId:1,
            kids: new Array(),
        };
    },
    created() {
        axios.get(process.env.VUE_APP_API_URL + '/api/kidsaccount/list/'+this.parentId,
            {
                headers: {
                    'jwt-auth-token':''
                },
            })
            .then((res) => {
                this.kids = res.data.data;
            });
    },
    methods: {
        addKid(){
            this.$router.push('/kidsregist');
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
    width: 300px;
    border-radius: 15px;
    display: inline-block;
    margin-top: 30px;
    margin-bottom: 30px;
    position: relative;
    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
    overflow: hidden;
}

.card:hover{
    cursor: pointer;
}

.card-header {
	-webkit-transition: 0.5s; /*사파리 & 크롬*/
    -moz-transition: 0.5s;  /*파이어폭스*/
    -ms-transition: 0.5s;	/*인터넷 익스플로러*/
    -o-transition: 0.5s;  /*오페라*/
    transition: 0.5s;
	width: 100%;
	height: 160px;
	border-radius: 15px 15px 0 0;
    margin-top: 15px;
		
}

.card-header-is_closed{
    background-color: #EF5A31 ;
    color: #FFF ;
    font-weight: bold ;
    text-align: center ;
    float: right;
    margin: 15px 15px 0 0;
    border-radius: 50%;
    font-weight: bold;
    padding: 10px 10px;
    line-height: 20px;
}

h1 {
    font-size: 1.5rem;
    font-weight: bold;
}

.card-body-header{
	line-height: 25px;
	margin: 10px 20px 0px 20px;
}

.card-body-hashtag {
	color: #2478FF;
    margin-top: 10px;
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

</style>