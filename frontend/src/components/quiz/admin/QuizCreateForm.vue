<template>
    <v-card>
        <v-card-title class="headline grey lighten-2">
            퀴즈 추가
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
                    :counter="50"
                    :rules="rules.question"
                    label="문제"
                    required
                />

                <v-text-field
                    v-model="description"
                    :counter="100"
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
                퀴즈 추가
            </v-btn>
        </v-card-actions>
    </v-card>
</template>

<script>
import rules from '@/components/quiz/admin/rules.js';

export default {
    name: 'QuizCreateForm',
    data: () => ({
        valid: true,
        rules,
        answerEntries: [
            {
                text: 'O',
                value: true,
            },
            {
                text: 'X',
                value: false,
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
    },
    methods: {
        validate() {
            const isValid = this.$refs.form.validate();
            if (!isValid) {
                return;
            }

            const quiz = {
                question: this.question,
                description: this.description,
                answer: this.answer,
                category: this.category,
            };
            
            for (let index = 0; index < 1; index++) {
                this.$store.dispatch('quiz/admin/post', quiz)
                    .then(() => {
                        this.$emit('update:dialog', false);
                    });
            
            }
        },
    }
};
</script>
