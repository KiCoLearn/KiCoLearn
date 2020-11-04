import axios from 'axios';
import axiosAPI from '@/plugins/axios.js';

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
        },
        LOGOUT(state) {
            state.accessToken = '';
            state.provider = '';
        },
    },
    actions: {
        auth() {
            return new Promise((resolve, reject) => {
                axiosAPI({
                    url: '/test',
                    method: 'get',
                }).then((response) => {
                    console.debug(response);
                    resolve(response);
                }).catch((error) => {
                    console.debug(error.response);
                    reject(error);
                });
            });
        },
        getToken({commit}, code) {
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
        },
        login(ignore, {token, provider}) {
            return new Promise((resolve, reject) => {
                axiosAPI({
                    url: `login/${provider}`,
                    method: 'POST',
                    params: {
                        'token': token,
                    },
                })
                    .then((response) => {
                        console.log(response);
                        resolve(response);
                        
                    }).catch((error) => {
                        reject(error);
                    });
            });
        },
        logout({state, commit}) {
            return new Promise((resolve) => {
                axiosAPI({
                    url: '/logout/kakao',
                    method: 'post',
                    params: {
                        token: state.accessToken,
                    },
                })
                    .then((response) => {
                        commit('LOGOUT');

                        resolve(response.data.id);
                    }).catch((error) => {
                        console.warn(error.response);
                    });
            });
        },
        unlink({state, commit}) {
            return new Promise((resolve) => {
                axiosAPI({
                    url: '/unlink/kakao',
                    method: 'post',
                    params: {
                        token: state.accessToken,
                    },
                })
                    .then((response) => {
                        commit('LOGOUT');

                        resolve(response.data.id);
                    }).catch((error) => {
                        console.warn(error.response);
                    });
            });
        },
    },
};
