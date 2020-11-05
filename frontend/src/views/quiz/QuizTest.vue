<template>
    <div>
        TODO: THIS IS QUIZ TEST
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
    </div>
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
    }),
};
</script>


<style lang="scss" scoped>
.quiz {
    ::v-deep &-container {
        margin: auto;
        width: 60%;
        border: 1px black solid;
    }

    ::v-deep &-box {
        margin: auto;
        margin-bottom: 5px;
        width: 60%;
        border: 1px black solid;
    }

    ::v-deep &-answer {
        margin: auto;
        margin-bottom: 5px;
        display: inline-block;
        width: 30%;
        color: red;
        border: 1px black solid;
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
}
</style>