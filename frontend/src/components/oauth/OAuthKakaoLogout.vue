<template>
    <div class="nav">
        <button
            class="head"
            text="LOGOUT"
            @click="logout"
        >
            <b>로그아웃</b>
        </button>
    </div>
</template>

<script>
export default {
    methods: {
        logout() {
            this.$store.dispatch('auth/logout')
                .then(() => {
                    this.$router.go();
                }).catch(() => {

                }).finally(() => {
                });
        },
    }
};
</script>

<style scoped>
 .nav{
    font-size: 1.2rem;
    font-family: 'Gaegu';
    color: white;
  }


  .head{
    background:#fb8c00;
    border-radius: 40px;
    padding:6px;
    margin-right: 15px;
    margin-bottom: 10px;
  }

  .active{
    background: #9ad3bc;
  }

</style>