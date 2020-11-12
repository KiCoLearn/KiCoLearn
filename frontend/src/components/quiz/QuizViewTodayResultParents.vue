<template>
    <v-layout class="quiz-container">
        <v-flex>
            <v-row
                justify="center"
                class="quiz-description"
            >
                <div class="quiz-main">
                    문제 : {{ todayProblem.question }}
                </div>
            </v-row>

            <v-row
                justify="center"
                class="quiz-description"
            >
                <div class="quiz-main">
                    해설 : {{ todayProblem.description }}
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
        </v-flex>
    </v-layout>
</template>

<script>
import { mapGetters } from 'vuex';

export default {
    props: {
        correct: {
            type: String,
            default:'',
        }, 
    },
    data: () => ({
        answer : ''
    }),
    computed: {
        ...mapGetters({
            todayProblem: 'quiz/todayProblem',
        }),
    },
    created() {
        if(this.correct === 'none'){    
            this.answer='';
        }
        else if(this.correct=='correct'){
            this.answer = this.todayProblem.answer;
        }
        else {
            this.answer = this.todayProblem.answer=='O'?'X':'O';    
        }
    },
};
</script>
