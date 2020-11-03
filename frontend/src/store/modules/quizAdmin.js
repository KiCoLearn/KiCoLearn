import axios from '@/plugins/axios.js';

export default {
    namespaced: true,
    state: {
        problems: [],
    },
    getters: {
        problems(state) {
            return state.problems;
        },
    },
    mutations: {
        SET_PROBLEMS(state, { problems }) {
            state.problems = problems;
        }
    },
    actions: {
        delete({dispatch}, quizNo) {
            return new Promise((resolve, reject) => {
                axios({
                    url: `quiz/delete/${quizNo}`,
                    method: 'delete',
                })
                    .then((response) => {
                        dispatch('fetchProblems');
                        
                        resolve(response);
                    }).catch((error) => {
                        console.log(error.response);
                        reject(error);
                    });
            });
        },
        edit({dispatch}, quiz) {
            return new Promise((resolve, reject) => {
                axios({
                    url: 'quiz/update',
                    method: 'post',
                    data: quiz,
                })
                    .then((response) => {
                        dispatch('fetchProblems');
                        
                        resolve(response);
                    }).catch((error) => {
                        console.log(error.response);
                        reject(error);
                    });
            });
        },
        post({dispatch}, quiz) {
            return new Promise((resolve, reject) => {
                axios({
                    url: 'quiz/create',
                    method: 'post',
                    data: quiz,
                })
                    .then((response) => {
                        dispatch('fetchProblems');

                        resolve(response);
                    }).catch((error) => {
                        console.log(error.response);
                        reject(error);
                    });
            });
        },
        fetchProblems({ commit }) {
            return new Promise((resolve, reject) => {
                axios({
                    url: 'quiz',
                    method: 'get',
                })
                    .then((response) => {
                        const problems = response.data.data.map((item) => {
                            item.answer = item.answer ? 'O' : 'X';
                            return item;
                        });
                        
                        commit('SET_PROBLEMS', {
                            problems: problems,
                        });

                        resolve(response);
                        
                    }).catch((error) => {
                        reject(error);
                    });
            });
        }
    },
};
