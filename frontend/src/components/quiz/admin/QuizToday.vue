<template>
    <v-card
        elevation="2"
    >
        <v-card-title class="text-left">
            오늘의 퀴즈
        </v-card-title>

        <v-card-subtitle class="text-left">
            {{ now }}
        </v-card-subtitle>

        <v-data-table
            :headers="headers"
            :items="[problem]"
            hide-default-footer
        />
    </v-card>
</template>

<script>
import { mapGetters } from 'vuex';

export default {
    name: 'QuizToday',
    data: () => ({
        headers: [
            {
                text: '번호',
                align: 'start',
                value: 'quizNo',
                sortable: false,
                width: '10%',
            },
            {
                text: '문제',
                value: 'question',
                sortable: false,
                width: '30%',
            },
            {
                text: '설명',
                value: 'description',
                sortable: false,
                width: '40%',
            },
            {
                text: '정답',
                value: 'answer',
                sortable: false,
                width: '10%',
            },
            {
                text: '분류', 
                value: 'category',
                sortable: false,
                width: '10%',
            },
        ],
    }),
    computed: {
        ...mapGetters({
            problem: 'quiz/todayProblem',
        }),
        now() {
            return new Date().format('yyyy년 MM월 dd일');
        }
    },
    created() {
        this.$store.dispatch('quiz/fetchTodayProblem')
            .then(() => {
            }).catch(() => {
            });
    },
};
</script>

<style>

</style>