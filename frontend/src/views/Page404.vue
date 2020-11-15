<template>
    <div class="error2">
        <img
            src="@/assets/logo.png"
            width="200px"
        >
        <br>
        <h2>잘못된 페이지 접근입니다.</h2>
        <br>
        <div class="nav">
            <button
                class="head"
                @click="goHome"
            >
                <b>메인으로 돌아가기</b>
            </button>
        </div>
    </div>
</template>

<script>
export default {
    name: 'Error',
    methods :{
        goHome(){
            this.$router.push({name:'Main'});
        }
    }

};
</script>

<style scoped>
    .error2{
        height: 88%;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
    }
    .nav{
    font-size: 1.2rem;
    font-family: 'Gaegu';
    color: white;
    display: flex;
    justify-content: flex-end;
    margin-bottom: 20px;
  }


  .head{
    background:#fb8c00;
    border-radius: 20px;
    padding:6px;
    margin-right: 10px;
  }

</style>