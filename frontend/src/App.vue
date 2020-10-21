<template>
    <div id="app">
        <div id="nav">
            <img 
                class="mainimg"
                src="@/assets/background.jpg"
                alt="배경 이미지"
            >
            <router-link to="/sign">
                <img 
                    class="kakao-sign"
                    src="@/assets/kakao.png"
                    alt="카카오 로그인"
                >
            </router-link>
        </div>
        <router-view />
    </div>
</template>

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
.mainimg {
  opacity: 0.5;
  background-size: 100%;
  width: 100%;
  height: 100%;
}
.kakao-sign{
    position: absolute;
    top: 400px;
    left: 120px;
}
</style>
