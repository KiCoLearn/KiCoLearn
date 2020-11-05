<template>
    <div class="quiz-container">
        오늘의 퀴즈 : 정답 VIEW
        <div class="quiz-box quiz-question">
            정답: {{ todayProblem.answer }}
            <br>
            설명: {{ todayProblem.description }}
        </div>
        <div
            :class="[
                {'correct': 'O' === answer && 'O' === todayProblem.answer},
                {'incorrect': 'O' === answer && 'O' !== todayProblem.answer},
                'quiz-answer']"
        >
            O
        </div>
        <div
            :class="[
                {'correct': 'X' === answer && 'X' === todayProblem.answer},
                {'incorrect': 'X' === answer && 'X' !== todayProblem.answer},
                'quiz-answer']"
        >
            X
        </div>
        <div
            :class="[
                'quiz-box',
                'quiz-response'
            ]"
            @click="resolve"
        >
            다시풀기
        </div>
    </div>
</template>

<script>
import { mapGetters } from 'vuex';

export default {
    props: {
        answer: {
            type: String,
            default: '',
        }
    },
    data: () => ({

    }),
    computed: {
        ...mapGetters({
            todayProblem: 'quiz/todayProblem',
        }),
    },
    created() {
        this.$store.dispatch('quiz/fetchProblemResult')
            .then((response) => {
                console.debug(response);
            }).catch((error) => {
                console.debug(error);
            });
    },
    methods: {
        resolve() {
            this.$emit('update:state', {
                progress: 'quiz',
                answer: '',
            });
        }
    }
};
</script>
