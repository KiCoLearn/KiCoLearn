<template>
    <v-container>
        <QuizToday />

        <br>

        <QuizCreate />

        <br>
        <br>

        <QuizProblems />
    </v-container>
</template>

<script>
import { mapGetters } from 'vuex';

import QuizCreate from '@/components/quiz/admin/QuizCreate';

import QuizToday from '@/components/quiz/admin/QuizToday';
import QuizProblems from '@/components/quiz/admin/QuizProblems';

export default {
    name: 'QuizManage',
    components: {
        QuizCreate,
        QuizToday,
        QuizProblems,
    },
    data: () => ({
        headers: [
            {
                text: '번호',
                align: 'start',
                value: 'quizNo',
                sortable: true,
            },
            {
                text: '문제',
                value: 'question',
                sortable: false,
            },
            {
                text: '설명',
                value: 'description',
                sortable: false,
            },
            {
                text: '정답',
                value: 'answer',
                sortable: false,
            },
            {
                text: '분류', 
                value: 'category',
                groupable: true,
                filterable: true,
                sortable: true,
            },
            {
                text: 'Actions',
                value: 'actions',
                sortable: false,
            },
        ],
    }),
    computed: {
        ...mapGetters({
            problems: 'quiz/admin/problems',
        }),
    },
    created() {
        this.$store.dispatch('quiz/admin/fetchProblems')
            .then(() => {
            }).catch(() => {
            });
    },
    methods: {
    }
};
</script>


<style lang="scss" scoped>
</style>