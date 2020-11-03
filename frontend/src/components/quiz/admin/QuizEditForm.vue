<template>
    <v-card>
        <v-card-title class="headline grey lighten-2">
            퀴즈 수정
        </v-card-title>

        <v-card-text>
            <v-form
                ref="form"
                v-model="valid"
                class="quiz-container"
                lazy-validation
            >
                <v-text-field
                    v-model="question"
                    :counter="20"
                    :rules="rules.question"
                    label="문제"
                    required
                />

                <v-text-field
                    v-model="description"
                    :counter="50"
                    :rules="rules.description"
                    label="설명"
                    required
                />
                
                <v-radio-group
                    v-model="answer"
                    label="정답"
                    required
                >
                    <v-radio
                        v-for="(answer, index) in answerEntries"
                        :key="answer + index"
                        :label="answer.text"
                        :value="answer.value"
                    />
                </v-radio-group>

                
                <v-text-field
                    v-model="category"
                    :counter="4"
                    :rules="rules.category"
                    label="분류"
                    required
                />
            </v-form>
        </v-card-text>

        <v-card-actions>
            <v-spacer />
            <v-btn
                :disabled="!valid"
                color="success"
                class="mr-4"
                @click="validate"
            >
                퀴즈 수정
            </v-btn>
        </v-card-actions>
    </v-card>
</template>

<script>
import rules from '@/components/quiz/admin/rules.js';

export default {
    name: 'QuizEditForm',
    props: {
        item: {
            type: Object,
            required: true,
        }
    },
    data: () => ({
        valid: true,
        rules,
        answerEntries: [
            {
                text: 'O',
                value: 'O',
            },
            {
                text: 'X',
                value: 'X',
            },
        ],
        question: '',
        description: '',
        answer: true,
        category: '',
    }),
    computed: {
    },
    created() {
        this.question = this.item.question;
        this.description = this.item.description;
        this.answer = this.item.answer;
        this.category = this.item.category;
    },
    methods: {
        validate() {
            const isValid = this.$refs.form.validate();
            if (!isValid) {
                return;
            }

            const quiz = {
                quizNo: this.item.quizNo,
                question: this.question,
                description: this.description,
                answer: this.answer === 'O' ? true : false,
                category: this.category,
            };
            
            this.$store.dispatch('quiz/admin/edit', quiz)
                .then(() => {
                    this.$emit('update:dialog', false);
                });
        },
    }
};
</script>
