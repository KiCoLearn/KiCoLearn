<template>
    <div id="app">
        <Kakao />
        <Kakao2 />
        <div id="nav">
            <router-link to="/">
                Home
            </router-link> |
            <router-link to="/about">
                About
            </router-link>
        </div>
        <router-view />
    </div>
</template>

<script>
import Kakao from '@/components/oauth/OAuthKakao';
import Kakao2 from '@/components/oauth/OAuthKakaoLogout';

export default {
    components: {
        Kakao,     
        Kakao2,
    },
    mounted() {
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
