import firebase from 'firebase/app';
import '@firebase/messaging';
import axios from '@/plugins/axios.js';

const firebaseConfig = {
    apiKey: process.env.VUE_APP_API_KEY,
    authDomain: process.env.VUE_APP_AUTH_DOMAIN,
    databaseURL: process.env.VUE_APP_DATABASE_URL,
    projectId: process.env.VUE_APP_PROJECT_ID,
    storageBucket: process.env.VUE_APP_STORAGE_BUCKET,
    messagingSenderId: process.env.VUE_APP_MESSAGE_SENDER_ID,
    appId: process.env.VUE_APP_APP_ID,
    measurementId: process.env.VUE_APP_MEASUREMENT_ID
};

firebase.initializeApp(firebaseConfig);

const messaging = firebase.messaging();

messaging.onMessage(
    (payload) => {
        console.log('payload', payload);
    },
    (error) => {
        console.log('error', error);
    },
    (complete) => {
        console.log('complete', complete);
    },
);

export default {
    namespaced: true,
    state: {
        token: null,
        hasNotificationPermission: false,
    },
    getters: {
        hasNotificationPermission(state) {
            return state.hasNotificationPermission;
        }
    },
    mutations: {
        GRANT_PERMISSION(state) {
            state.hasNotificationPermission = true;
        },
        DENY_PERMISSION(state) {
            state.hasNotificationPermission = false;
        },
        SET_TOKEN(state, token) {
            state.token = token;
        },
        RESET_TOKEN(state) {
            state.token = null;
        }
    },
    actions: {
        requestPermission({commit, dispatch}, _$isRetry) {
            return new Promise((resolve, reject) => {
                if (firebase.messaging.isSupported() && 'Notification' in window) {
                    Notification.requestPermission()
                        .then((permission) => {
                            switch(permission) {
                            case 'granted':
                                commit('GRANT_PERMISSION');
                                resolve(true);
                                break;
                            case 'denied':
                                commit('DENY_PERMISSION');
                                reject(false);
                                break;
                            case 'default':
                                if (!_$isRetry) {
                                    return dispatch('requestPermission', true);
                                } else {
                                    reject(false);
                                }
                                break;
                            default:
                                commit('DENY_PERMISSION');
                                reject(false);
                                break;
                            }
                        })
                        .catch(() => {
                            commit('DENY_PERMISSION');
                            reject(false);
                        });

                } else {
                    commit('DENY_PERMISSION');
                    reject(false);
                }
            });
        },
        getToken({ commit }) {
            return new Promise((resolve, reject) => {
                messaging.getToken(process.env.VUE_APP_PUBLIC_VAPID_KEY)
                    .then((token) => {
                        commit('SET_TOKEN', token);
                        resolve(token);
                    }).catch(() => {
                        commit('RESET_TOKEN');
                        reject(false);
                    });
            });
        },
        registToken(ignore, {token, id}) {
            return new Promise((resolve, reject) => {
                axios({
                    url: `/api/parents/accounts/${id}/messaging-token`,
                    method: 'post',
                    data: {
                        'messaging-token': token,
                    },
                })
                    .then((response) => {
                        resolve(response);
                    })
                    .catch((error) => {
                        console.error(error);

                        reject(error);
                    });
            });
        }
    },
    modules: {
    }
};
