<template>
    <div class="nav">
        <router-link :to="{name : 'KidMain' }">
            <span
                :class="{head:true, active: to==='KidMain'}"
            ><b>홈</b></span>
        </router-link>
        <router-link :to="{name : 'Quest' }">
            <span
                :class="{head:true, active: to==='Quest'}"
            ><b>퀘스트</b></span>
        </router-link>
        <router-link :to="{name : 'KidQuiz' }">
            <span
                :class="{head:true, active: to==='KidQuiz'}"
            ><b>퀴즈</b></span>
        </router-link>
        <router-link :to="{name : 'Store' }">
            <span
                :class="{head:true, active: to==='Store'}"
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