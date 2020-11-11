<template>
    <v-app
        id="app"
        style="background-color:cornsilk"
    >
        <ParentsHeader
            v-if="role === 'parents' && isAuthorized"
            style="margin:20px 0 20px 10px;"
        />
        <KidHeader
            v-if="role === 'kid' && isAuthorized"
            style="margin:20px 0 20px 10px;"
        />
    
        <v-container
            fluid
            style="height: 100%;"
        >
            <router-view />
        </v-container>
        <div
            id="nav"
            style="text-align:right"
        >
            <div v-if="role === 'parents' && isAuthorized">
                <KaKaoLogout />
            </div>
        </div> 
    </v-app>
</template>

<script>
import KaKaoLogout from '@/components/oauth/OAuthKakaoLogout';
import ParentsHeader from '@/components/header/ParentsHeader';
import KidHeader from '@/components/header/KidHeader';
import { mapGetters } from 'vuex';
export default {
    components:{ KaKaoLogout, ParentsHeader, KidHeader},
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

<style>
 
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  height: 100%;
}

</style>
