<template>
    <v-card>
        <v-card-title>
            퀴즈 목록
        </v-card-title>
        <v-card-subtitle>
            <v-text-field
                v-model="search"
                append-icon="mdi-magnify"
                label="Search"
                single-line
                hide-details
            />
        </v-card-subtitle>

        <v-data-table
            :headers="headers"
            :items="problems"
            :items-per-page="10"
            show-expand
            item-key="quizNo"
            :search="search"
        >
            <template v-slot:expanded-item="{ headers, item }">
                <td :colspan="headers.length">
                    <QuizDetail
                        :item="item"
                    />
                </td>
            </template>
            <template v-slot:[`item.actions`]="{ item }">
                <QuizEdit
                    :item="item"
                />
                <QuizDelete
                    :item="item"
                />
            </template>
        </v-data-table>
    </v-card>
</template>

<script>
import { mapGetters } from 'vuex';
import QuizEdit from '@/components/quiz/admin/QuizEdit';
import QuizDelete from '@/components/quiz/admin/QuizDelete';
import QuizDetail from '@/components/quiz/admin/QuizDetail';

export default {
    name: 'QuizToday',
    components: {
        QuizEdit,
        QuizDelete,
        QuizDetail,
    },
    data: () => ({
        search: '',
        headers: [
            {
                text: '번호',
                align: 'start',
                value: 'quizNo',
                sortable: true,
                filterable: true,
                width: '10%',
            },
            {
                text: '문제',
                value: 'question',
                sortable: false,
                filterable: true,
                width: '55%',
            },
            {
                text: '정답',
                value: 'answer',
                align: 'center',
                sortable: false,
                filterable: true,
                width: '10%',
            },
            {
                text: '분류', 
                value: 'category',
                sortable: true,
                filterable: true,
                width: '10%',
            },
            {
                text: '',
                value: 'actions',
                sortable: false,
                filterable: false,
                width: '10%',
            },
            {
                text: '',
                value: 'data-table-expand',
                sortable: false,
                filterable: false,
                width: '5%',
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
};
</script>

<style lang="scss" scoped>
::v-deep tr.v-data-table__expanded__content {
    background-color: rgb(243, 243, 243);
    box-shadow:
        inset 0px -2px 4px -2px rgba(50, 50, 50, 0.75),
        inset 0px -2px 4px -2px rgba(50, 50, 50, 0.75)
        !important;
    box-shadow: unset !important;
}
</style>