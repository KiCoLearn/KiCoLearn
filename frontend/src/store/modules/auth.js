import axios from 'axios';
import baseAxios from '@/axios';

export default {
    namespaced: true,
    state: {
        accessToken: '',
        provider: '',
    },
    getters: {
        token(state) {
            return state.accessToken;
        },
        provider(state) {
            return state.provider;
        }
    },
    mutations: {
        GET_ACCESS_TOKEN(state, { accessToken, provider }) {
            state.accessToken = accessToken;
            state.provider = provider;
        }
    },
    actions: {
        auth() {
            return new Promise((resolve, reject) => {
                baseAxios({
                    url: '/test',
                    method: 'GET',
                }).then((response) => {
                    console.log(response);
                    resolve(response);
                }).catch((error) => {
                    console.log(error.response);
                    reject(error);
                });
            });
        },
        logout({state, commit}) {
            return new Promise((resolve) => {
                baseAxios({
                    url: '/logout/kakao',
                    method: 'post',
                    params: {
                        token: state.accessToken,
                    },
                })
                    .then((response) => {
                        console.log('logout');
                        console.log(response);
                        commit('GET_ACCESS_TOKEN', {
                            accessToken: '',
                            provider: '',
                        });
                        resolve(response.data.id);
                    }).catch((error) => {
                        console.warn(error.response);
                    });
            });
        },
        login({commit}, code) {
            return new Promise((resolve, reject) => {
                axios({
                    url: 'https://kauth.kakao.com/oauth/token',
                    method: 'POST',
                    params: {
                        'grant_type': 'authorization_code',
                        'client_id': process.env.VUE_APP_OAUTH_KAKAO_CLIENT_ID,
                        'redirect_uri': process.env.VUE_APP_OAUTH_KAKAO_REDIRECTION_URI,
                        'code': code,
                        'client_secret': process.env.VUE_APP_OAUTH_KAKAO_CLIENT_SECRET,
                    },
                    headers: {
                        'Content-type': 'application/x-www-form-urlencoded'
                    },
                })
                    .then((response) => {
                        const accessToken = response.data.access_token;
                        commit('GET_ACCESS_TOKEN', {
                            accessToken,
                            provider: 'kakao',
                        });
                        resolve(accessToken);
                        
                    }).catch((error) => {
                        reject(error);
                    });
            });
        }
    },
};
