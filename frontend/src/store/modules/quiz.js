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
        fetchProblemResult() {
            return new Promise((resolve, reject) => {
                axios({
                    url: 'quiz/solve',
                    method: 'post',
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
                    url: 'quiz/today',
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
        }
    },
};
