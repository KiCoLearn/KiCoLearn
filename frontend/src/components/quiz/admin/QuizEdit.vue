<template>
    <v-dialog
        v-model="dialog"
        max-width="300"
    >
        <template v-slot:activator="{ on, attrs }">
            <v-icon
                small
                class="mr-2"
                v-bind="attrs"
                v-on="on"
            >
                mdi-pencil
            </v-icon>
        </template>

        <QuizEditForm
            :item="item"
            :dialog.sync="dialog"
        />
    </v-dialog>
</template>

<script>
import QuizEditForm from '@/components/quiz/admin/QuizEditForm';

export default {
    name: 'QuizEdit',
    components: {
        QuizEditForm,
    },
    props: {
        item: {
            type: Object,
            required: true,
        }
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
