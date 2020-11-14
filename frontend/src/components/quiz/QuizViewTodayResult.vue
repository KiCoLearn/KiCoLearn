<template>
    <v-layout class="quiz-container">
        <v-flex>
            <v-row
                justify="center"
                class="quiz-description"
            >
                <div class="quiz-main">
                    {{ todayProblem.description }}
                </div>
            </v-row>

            <v-row>
                <v-col
                    cols="4"
                    :class="[
                        {'correct': 'O' === answer && 'O' === todayProblem.answer},
                        {'incorrect': 'O' === answer && 'O' !== todayProblem.answer},
                        'quiz-answer']"
                >
                    <b style="color:blue">O</b>
                </v-col>
                <v-col
                    cols="4"
                    :class="[
                        {'correct': 'X' === answer && 'X' === todayProblem.answer},
                        {'incorrect': 'X' === answer && 'X' !== todayProblem.answer},
                        'quiz-answer']"
                >
                    <b style="color:red">X</b>
                </v-col>
            </v-row>
        <!-- <v-row justify="center"
            class="quiz-response"
            @click="resolve"
        >
            돌아가기
        </v-row> -->
        </v-flex>
    </v-layout>
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
            kidId : 'auth/id'
        }),
    },
    created() {
        this.$store.dispatch('quiz/fetchProblemResult',
            {'kidId' : this.kidId, 'correct': this.answer === this.todayProblem.answer})
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
        },

    }
};
</script>
