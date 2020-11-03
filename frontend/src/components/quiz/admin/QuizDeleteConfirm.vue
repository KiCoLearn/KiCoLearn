<template>
    <v-card>
        <v-card-title class="headline grey lighten-2">
            퀴즈 삭제
        </v-card-title>

        <v-card-text>
            번호: {{ item.quizNo }}
            <br>
            문제: {{ item.question }}
        </v-card-text>

        <v-card-text>
            해당 퀴즈를 정말로 삭제하시겠습니까?
        </v-card-text>

        <v-card-actions>
            <v-spacer />
            <v-btn
                outlined
                color="error"
                class="mr-4"
                @click="cancel"
            >
                취소
            </v-btn>
            <v-btn
                color="success"
                class="mr-4"
                @click="remove"
            >
                삭제
            </v-btn>
        </v-card-actions>
    </v-card>
</template>

<script>
export default {
    name: 'QuizDelete',
    components: {
    },
    props: {
        item: {
            type: Object,
            required: true,
        }
    },
    data: () => ({
    }),
    computed: {
    },
    created() {
    },
    methods: {
        remove() {
            this.$store.dispatch('quiz/admin/delete', this.item.quizNo)
                .then(() => {
                    this.$emit('update:dialog', false);
                });
        },
        cancel() {
            this.$emit('update:dialog', false);
        }
    }
};
</script>