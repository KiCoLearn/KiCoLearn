<template>
    <v-app
        id="app"
        style="background-color:cornsilk"
    >
        <ParentsHeader
            v-if="role === 'parents' && isAuthorized && !isMain"
            style="margin-top:20px;margin-bottom:20px"
        />
        <KidHeader
            v-if="role === 'kid' && isAuthorized"
            style="margin-top:20px;margin-bottom:20px"
        />
        
        <v-container
            fluid
            style="height: 100%;width:371px"
        >
            <router-view />
        </v-container>
    </v-app>
</template>

<script>
import ParentsHeader from '@/components/header/ParentsHeader';
import KidHeader from '@/components/header/KidHeader';
import { mapGetters } from 'vuex';
export default {
    components:{ ParentsHeader, KidHeader},
    computed: {
        ...mapGetters({
            role : 'auth/role',
            isAuthorized: 'auth/isAuthorized',
        }),
        isMain(){
            return this.$route.name ==='ParentsMain';
        }

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
@font-face {
    font-family: 'CookieRun-Regular';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/CookieRun-Regular.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
 
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  height: 100%;
  overflow: auto;
}

.btn{
    border: none;
    background: none;
}

.btn:focus{
    outline:none;
}

.btn:hover{
    cursor: pointer;
}

</style>
