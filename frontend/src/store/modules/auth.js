import axios from 'axios';
import axiosAPI from '@/plugins/axios.js';

export default {
    namespaced: true,
    state: {
        accessToken: null,
        refreshToken: null,
        provider: null,
        id: null,
        role : null,
    },
    getters: {
        isAdmin() {
            return true;
        },
        isAuthorized(state) {
            return state.accessToken !== null 
            && state.provider !== null 
            && state.id !== null
            && state.role !== null;
        },
        isParents(state) {
            return state.accessToken !== null 
            && state.provider !== null 
            && state.id !== null
            && state.role === 'parents';
        },
        isKid(state) {
            return state.accessToken !== null 
            && state.provider !== null 
            && state.id !== null
            && state.role === 'kid';
        },
        id(state) {
            return state.id;
        },
        token(state) {
            return state.accessToken;
        },
        provider(state) {
            return state.provider;
        },
        role(state){
            return state.role;
        },
        GET_ROLE(state, {provider}) {
            switch(provider) {
            case 'kakao':
                return 'parents';
            case 'kicolearn':
                return 'kid';
            default : 
                return null;
            }
        }
    },
    mutations: {
        LOGIN(state, { id, role }) {
            state.id = id;
            state.role = role;
        },
        SET_TOKEN(state, { accessToken, refreshToken, provider }) {
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
            state.role = null;
        },
    },
    actions: {
        auth() {
            return new Promise((resolve, reject) => {
                axiosAPI({
                    url: '/api/parents/test',
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
                        commit('SET_TOKEN', {
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
                    url: `/api/parents/login/${provider}`,
                    method: 'POST',
                    params: {
                        'token': token,
                    },
                })
                    .then((response) => {
                        const id = response.data.id;
                        const role = 'parents';
                        commit('LOGIN', { id, role });

                        resolve(response);
                        
                    }).catch((error) => {
                        reject(error);
                    });
            });
        },
        logout({state, commit}) {
            return new Promise((resolve) => {
                axiosAPI({
                    url: '/api/parents/logout/kakao',
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
                    url: '/api/parents/unlink/kakao',
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
