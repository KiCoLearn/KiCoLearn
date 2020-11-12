<template>
    <v-layout class="quiz-container">
        <v-flex>
            <v-row
                class="quiz-box quiz-question"
                justify="center"
            >
                <div class="quiz-main">
                    문제 : {{ todayProblem.question }}
                </div>
            </v-row>

            <v-row>
                <v-col
                    cols="4"
                    class="quiz-answer quiz-response"
                    @click="checkResult"
                >
                    <b style="color:blue">O</b>
                </v-col>
                <v-col
                    cols="4"
                    class="quiz-answer quiz-response"
                    @click="checkResult"
                >
                    <b style="color:red">X</b>
                </v-col>
            </v-row>
        </v-flex>
    </v-layout>
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