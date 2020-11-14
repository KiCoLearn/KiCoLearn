<template>
    <v-layout class="quizbody">
        <v-flex style="margin-bottom:46px;">
            <v-row style="margin-left:10px; margin-bottom:10px">
                <h2>{{ date }} 퀴즈</h2>
            </v-row>
        
            <QuizViewTodayResult
                v-if="correct!=null"
                class="quiz"
                :correct="correct"
            />

            <v-row
                justify="center"
                class="advice"
            >
                {{ advice }}
            </v-row>
        </v-flex>
    </v-layout>
</template>

<script>
import QuizViewTodayResult from '@/components/quiz/QuizViewTodayResultParents';
import axios from '@/plugins/axios';
import { mapGetters } from 'vuex';

export default {
    name: 'QuizTest',
    components: {
        QuizViewTodayResult,
    },
    data: () => ({
        correct : null,
        date : '',
        advice:'아직 아이가 퀴즈를 풀지 않았네요!'
    }),
    computed: {
        ...mapGetters({
            kidId : 'auth/select',
        })
    },
    created() {
        this.$store.dispatch('quiz/fetchTodayProblem');

        this.date= this.getFormatDate(new Date());
        axios.get('/api/quiz/today/'+this.kidId)
            .then((res) => {
                //console.log(res.data.data);
                if(res.data.data!=null) {
                    this.correct = res.data.data.correct ? 'correct':'not';
                    this.advice = res.data.data.correct ? '아이가 정답을 맞췄습니다!' : '아이가 정답을 아쉽게 틀렸습니다!';
                } else{
                    this.correct='none';
                }
            });

    },
    methods: {
        getFormatDate(date){
            var year = date.getFullYear();              //yyyy
            var month = (1 + date.getMonth());          //M
            month = month >= 10 ? month : '0' + month;  //month 두자리로 저장
            var day = date.getDate();                   //d
            day = day >= 10 ? day : '0' + day;          //day 두자리로 저장
            return  year +'년 ' + month + '월 ' + day+'일';      //'-' 추가하여 yyyy-mm-dd 형태 생성 가능
        }
    },
};
</script>


<style lang="scss" scoped>

.quiz {
    ::v-deep &-container {
        display: flex;
        align-items: center;
        margin: auto;
        width: 330px;
        border-radius: 6px;
        background: #4A655A;
        border : 6px rgb(142, 105, 13) solid;
        color: white;
        padding: 10px;
    }

    ::v-deep &-box {
        margin: auto;
        margin-bottom: 15px;
        width: 62%;
        font-size: 1.7rem;
        border-radius: 4px;
    }

    ::v-deep &-answer {
        margin: auto;
        margin-bottom: 5px;
        display: inline-block;
        width: 30%;;
        border: 4px solid rgb(142, 105, 13);
        border-radius: 4px;;
        font-size: 1.3rem;
        
    }

    ::v-deep &-response:hover {
        cursor: pointer;
    }
    
    ::v-deep .correct {
        background-color: aquamarine;
    }
    ::v-deep .incorrect {
        background-color: pink;
    }

    ::v-deep &-question{
       
        max-width: 130px;
        align-items: center;
    }
    ::v-deep &-description{
        max-width: 230px;
        align-items: center;
        font-size: 1.4rem;
        margin: auto;
        margin-bottom: 7.5px;
        margin-top: 7.5px;
    }
    ::v-deep &-main{
        overflow-wrap: anywhere;
    }

}
.quizbody{
    width: 340px;
    margin: auto;
    display: flex;
    height: 100%;
    justify-content: center;
    align-items: center;
    font-family: 'CookieRun-Regular';
}

.advice{
    margin-top: 15px;
    font-size: 1.1rem;
}

</style>