<template>
    <v-dialog
        v-model="dialog"
        max-width="300"
    >
        <template v-slot:activator="{ on, attrs }">
            <v-btn
                color="primary"
                elevation="2"
                v-bind="attrs"
                v-on="on"
            >
                퀴즈 추가
            </v-btn>
        </template>

        <QuizCreateForm
            :dialog.sync="dialog"
        />
    </v-dialog>
</template>

<script>
import QuizCreateForm from '@/components/quiz/admin/QuizCreateForm';

export default {
    name: 'QuizCreate',
    components: {
        QuizCreateForm,
    },
    data: () => ({
        dialog: false,
    }),
    computed: {
    },
    created() {
    },
    methods: {
    }
};
</script>
