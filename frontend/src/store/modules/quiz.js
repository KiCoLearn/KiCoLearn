import axios from '@/plugins/axios.js';
import quizAdmin from '@/store/modules/quizAdmin';

export default {
    namespaced: true,
    modules: {
        admin: quizAdmin,
    },
    state: {
        todayProblem: {},
    },
    getters: {
        todayProblem(state) {
            return state.todayProblem;
        },
    },
    mutations: {
        SET_TODAY_PROBLEM(state, { todayProblem }) {
            todayProblem.answer = todayProblem.answer ? 'O' : 'X';
            
            state.todayProblem = todayProblem;
        }
    },

    actions: {
        fetchProblemResult(ignore, {kidId, correct}) {
            return new Promise((resolve, reject) => {
                axios({
                    url: '/api/quiz/solve',
                    method: 'post',
                    data : {
                        'kidId' : kidId,
                        'correct': correct
                    }
                })
                    .then((response) => {
                        resolve(response);
                    }).catch((error) => {
                        reject(error);
                    });
            });
        },
        fetchTodayProblem({ commit }) {
            return new Promise((resolve, reject) => {
                axios({
                    url: '/api/quiz/today',
                    method: 'get',
                })
                    .then((response) => {
                        commit('SET_TODAY_PROBLEM', {
                            todayProblem: response.data.data,
                        });
                        resolve(response);
                        
                    }).catch((error) => {
                        reject(error);
                    });
            });
        },
        fetchBonusMoney(ignore, {kidId}) {
            return new Promise((resolve, reject) => {
                axios({
                    url: '/api/quiz/today/'+kidId,
                    method: 'get'
                })
                    .then((res) => {
                        if(res.data.data==null){
                            console.log(res.data.data);
                            axios({
                                url: '/api/money/activity',
                                method: 'post',
                                data : {
                                    'amount': 300,
                                    'contents': '퀴즈',
                                    'isDeposit': true,
                                    'kidId': kidId,
                                }
                            })
                                .then((response) => {
                                    resolve(response);
                                }).catch((error) => {
                                    reject(error);
                                });
                        }

                       
                    });                
            });
        },
    },
};
