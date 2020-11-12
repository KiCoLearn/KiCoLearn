import axios from 'axios';
import store from '@/store';

const instance = axios.create({
    baseURL: process.env.VUE_APP_API_URL,
});


instance.interceptors.request.use(
    (config) => {
        if (store.getters['auth/token']) {
            config.headers['Authorization'] = store.getters['auth/token'];
            config.headers['Provider'] = store.getters['auth/provider'];
        }
        
        return config;
    },
    (response) => {
        return response;
    },
    (error) => {
        if(error.status === 401 && error.config && !error.config.__isRetryRequest) {
            return store.dispatch('auth/refreshToken')
                .then(() => {
                    error.config.__isRetryRequest = true;
                    return instance(error.config);
                })
                .catch((error) => {
                    throw error;
                });
        }
        throw error;
    }
);

export default instance;
