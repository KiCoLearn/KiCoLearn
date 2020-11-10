<template>
    <v-app
        id="app"
        style="background-color:cornsilk"
    >
        <div id="nav">
            <router-link to="/">
                Home
            </router-link>|
            <router-link to="/store">
                Store
            </router-link>
            <div v-if="role === 'parents' && isAuthorized">
                <KaKaoLogout />
            </div>
        </div>
     
        <v-container
            fluid
            style="height: 100%;"
        >
            <router-view />
        </v-container>
    </v-app>
</template>

<script>
import KaKaoLogout from '@/components/oauth/OAuthKakaoLogout';
import { mapGetters } from 'vuex';
export default {
    components:{KaKaoLogout},
    computed: {
        ...mapGetters({
            role : 'auth/role',
            isAuthorized: 'auth/isAuthorized',
        })
    },
    mounted() {
        this.$store.dispatch('fcm/requestPermission')
            .then(() => {
                this.$store.dispatch('fcm/getToken');
            }).catch(() => {
            });
    },


};
</script>

<style scoped>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  height: 100%;
}

#nav {
  padding: 0px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}



</style>
