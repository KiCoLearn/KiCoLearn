import axios from 'axios';
import axiosAPI from '@/plugins/axios.js';

export default {
    namespaced: true,
    state: {
        accessToken: null,
        refreshToken: null,
        provider: null,
        id: null,
    },
    getters: {
        isAdmin() {
            return true;
        },
        isAuthorized(state) {
            return state.accessToken !== null && state.provider !== null;
        },
        id(state) {
            return state.id;
        },
        token(state) {
            return state.accessToken;
        },
        provider(state) {
            return state.provider;
        }
    },
    mutations: {
        LOGIN(state, { id }) {
            state.id = id;
        },
        GET_TOKEN(state, { accessToken, refreshToken, provider }) {
            state.accessToken = accessToken;
            state.refreshToken = refreshToken;
            state.provider = provider;
        },
        REFRESH_ACCESS_TOKEN(state, { accessToken, refreshToken }) {
            if (refreshToken) {
                state.refreshToken = refreshToken;
            }
            state.accessToken = accessToken;
        },
        LOGOUT(state) {
            state.accessToken = null;
            state.refreshToken = null;
            state.provider = null;
            state.id = null;
        },
    },
    actions: {
        auth() {
            return new Promise((resolve, reject) => {
                axiosAPI({
                    url: '/test',
                    method: 'get',
                }).then((response) => {
                    resolve(response);
                }).catch((error) => {
                    reject(error);
                });
            });
        },
        refreshToken({state, commit}) {
            return new Promise((resolve, reject) => {
                axios({
                    url: 'https://kauth.kakao.com/oauth/token',
                    method: 'POST',
                    params: {
                        'grant_type': 'refresh_token',
                        'client_id': process.env.VUE_APP_OAUTH_KAKAO_CLIENT_ID,
                        'code': state.refreshToken,
                        'client_secret': process.env.VUE_APP_OAUTH_KAKAO_CLIENT_SECRET,
                    },
                    headers: {
                        'Content-type': 'application/x-www-form-urlencoded'
                    },
                })
                    .then((response) => {
                        const accessToken = response.data.access_token;
                        const refreshToken = response.data.refresh_token;
                        commit('REFRESH_ACCESS_TOKEN', {
                            accessToken,
                            refreshToken,
                        });
                        resolve(accessToken);
                        
                    }).catch((error) => {
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
                        const refreshToken = response.data.refresh_token;
                        commit('GET_TOKEN', {
                            accessToken,
                            refreshToken,
                            provider: 'kakao',
                        });
                        resolve(accessToken);
                        
                    }).catch((error) => {
                        reject(error);
                    });
            });
        },
        login({commit}, {token, provider}) {
            return new Promise((resolve, reject) => {
                axiosAPI({
                    url: `login/${provider}`,
                    method: 'POST',
                    params: {
                        'token': token,
                    },
                })
                    .then((response) => {
                        const id = response.data.id;
                        commit('LOGIN', { id });

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
