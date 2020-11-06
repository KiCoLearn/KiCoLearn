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
            </router-link>|
            <router-link to="/kidslist">
                KidsList
            </router-link>              
        </div>
        <v-main>
            <v-container fluid>
                <router-view />
            </v-container>
        </v-main>
    </v-app>
</template>

<script>

export default {
    
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
