<template>
    <div class="quiz-container">
        오늘의 퀴즈 : 퀴즈 VIEW
        <div class="quiz-box quiz-question">
            문제: {{ todayProblem.question }}
        </div>
        <div
            class="quiz-answer quiz-response"
            @click="checkResult"
        >
            O
        </div>
        <div
            class="quiz-answer quiz-response"
            @click="checkResult"
        >
            X
        </div>
    </div>
</template>

<script>
import { mapGetters } from 'vuex';

export default {
    data: () => ({

    }),
    computed: {
        ...mapGetters({
            todayProblem: 'quiz/todayProblem',
        }),
    },
    created() {
        this.$store.dispatch('quiz/fetchTodayProblem')
            .then(() => {
            }).catch(() => {
            });
    },
    methods: {
        checkResult(event) {
            this.$emit('update:state', {
                progress: 'result',
                answer: event.target.textContent.trim(),
            });
        }
    }
};
</script>
