<template>
    <div class="nav">
        <router-link :to="{name : 'ParentsMain' }">
            <span
                :class="{head:true, active: home}"
                @click="home=true;quest=false;store=false;quiz=false"
            ><b>홈</b></span>
        </router-link>
        <router-link :to="{name : 'Quest' }">
            <span
                :class="{head:true, active: quest}"
                @click="quest=true;home=false;store=false;quiz=false"
            ><b>퀘스트</b></span>
        </router-link>
        <router-link :to="{name : 'Quiz' }">
            <span
                :class="{head:true, active: quiz}"
                @clik="quiz=true;home=false;store=false;quest=false"
            ><b>퀴즈</b></span>
        </router-link>
        <router-link :to="{name : 'Store' }">
            <span
                :class="{head:true, active: store}"
                @click="store=true;home=false;quiz=false;quest=false"
            ><b>스토어</b></span>
        </router-link>
    </div>
</template>

<script>

export default {
    name:'ParentsHeader',
    data() {
        return {
            home: true,
            store:false,
            quest:false,
            quiz:false,
        };
    },

};
</script>

<style scoped>
   .nav{
    text-align:left;
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