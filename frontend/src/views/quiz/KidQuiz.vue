<template>
    <v-layout class="quizbody">
        <v-flex style="margin-bottom:46px;">
            <v-row style="margin-left:10px; margin-bottom:10px">
                <h2>{{ date }} 퀴즈</h2>
            </v-row>
        
            <QuizViewToday
                v-if="state.progress === 'quiz'"
                class="quiz"
                :state.sync="state"
            />
            <QuizViewTodayResult
                v-if="state.progress === 'result'"
                class="quiz"
                :state.sync="state"
                :answer="state.answer"
            />
        </v-flex>
    </v-layout>
</template>

<script>
import QuizViewToday from '@/components/quiz/QuizViewToday';
import QuizViewTodayResult from '@/components/quiz/QuizViewTodayResult';

export default {
    name: 'QuizTest',
    components: {
        QuizViewToday,
        QuizViewTodayResult,
    },
    data: () => ({
        state: {
            progress: 'quiz',
            answer: '',
        },
        date : '',
    }),

    created() {
        this.date= this.getFormatDate(new Date());
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
@font-face {
    font-family: 'CookieRun-Regular';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/CookieRun-Regular.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

.quiz {
    ::v-deep &-container {
        display: flex;
        align-items: center;
        margin: auto;
        width: 330px;
        height: 330px;
        border-radius: 6px;
        background: #4A655A;
        border : 6px rgb(142, 105, 13) solid;
        color: white;
    }

    ::v-deep &-box {
        margin: auto;
        margin-bottom: 15px;
        width: 60%;
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
        height: 200px;
        max-width: 230px;
        align-items: center;
    }
    ::v-deep &-description{
        height: 200px;
        max-width: 230px;
        align-items: center;
        font-size: 1.5rem;
        margin: auto;
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

</style>