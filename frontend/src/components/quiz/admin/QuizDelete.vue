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
                mdi-delete
            </v-icon>
        </template>

        <QuizDeleteConfirm
            :item="item"
            :dialog.sync="dialog"
        />
    </v-dialog>
</template>

<script>
import QuizDeleteConfirm from '@/components/quiz/admin/QuizDeleteConfirm';

export default {
    name: 'QuizDelete',
    components: {
        QuizDeleteConfirm,
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
        activateDelete() {
            this.$emit('update:state', 'delete');
        }
    }
};
</script>
