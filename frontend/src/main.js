import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import '@/plugins/format.js';
import vuetify from '@/plugins/vuetify.js';
import '@/registerServiceWorker';
import Carousel3d from 'vue-carousel-3d';


Vue.config.productionTip = false;
Vue.use(Carousel3d);
new Vue({
    router,
    store,
    vuetify,
    render: (h) => h(App),
}).$mount('#app');
