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
    }
);

export default instance;
