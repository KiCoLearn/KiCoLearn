<template>
    <div class="nav">
        <router-link :to="{name : 'KidMain' }">
            <span
                :class="{head:true, active: to==='KidMain'}"
                @click="home=true;quest=false;store=false;quiz=false"
            ><b>홈</b></span>
        </router-link>
        <router-link :to="{name : 'Quest' }">
            <span
                :class="{head:true, active: to==='Quest'}"
                @click="quest=true;home=false;store=false;quiz=false"
            ><b>퀘스트</b></span>
        </router-link>
        <router-link :to="{name : 'KidQuiz' }">
            <span
                :class="{head:true, active: to==='KidQuiz'}"
                @clik="quiz=true;home=false;store=false;quest=false"
            ><b>퀴즈</b></span>
        </router-link>
        <router-link :to="{name : 'Store' }">
            <span
                :class="{head:true, active: to==='Store'}"
                @click="store=true;home=false;quiz=false;quest=false"
            ><b>스토어</b></span>
        </router-link>
    </div>
</template>

<script>

export default {
    name:'KidHeader',
    data() {
        return {
            to : '',
        };
    },
    watch: {
        '$route' (to) {
            this.to = to.name;
        }
    },
    created() {
        this.to = this.$route.name;
    },

};
</script>

<style scoped>
   .nav{
    font-size: 1.4rem;
    font-family: 'Gaegu';
  }
  a:-webkit-any-link {
    text-decoration: none !important;
    color: black;
    margin-left: 10px;
  }

  .head{
    border: 3px solid #9ad3bc;
    border-radius: 15px;
    padding:10px;
  }

  .active{
    background: #9ad3bc;
  }
</style>