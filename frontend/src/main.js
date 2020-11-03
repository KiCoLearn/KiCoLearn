import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import '@/plugins/format.js';
import vuetify from '@/plugins/vuetify.js';

Vue.use(Vuetify);
Vue.config.productionTip = false;

new Vue({
    vuetify: new Vuetify(),
    router,
    store,
    vuetify,
    render: (h) => h(App),
}).$mount('#app');
