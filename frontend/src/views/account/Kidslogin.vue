<template>
    <div class="kidslogin">
        <div class="input">
            <input 
                id="otp"
                class="otpkids"
                type="password" 
                placeholder="번호를 입력해주세요" 
                required
            >
            <p>
                <button
                    class="items"
                    @click="kid"
                >
                    로그인하기
                </button>
            </p>
        </div>
    </div>
</template>
<script>
export default {
    methods: {
        kid(){
            this.$router.push({name: 'Kidsmain'});
        }
    },
};
</script>
<style scoped>
.mainimg {
  opacity: 0.5;
  background-size: contain;
  width: 100%;
  height: 100%;
}
.kidslogin{
    background-image: url("../../assets/background.jpg");
    background-size: contain;
    margin: 0rem,0rem,0rem,0rem;
    height: 45rem;
    opacity: 0.5;
    background-repeat:no-repeat;
    
}
.input{
    position: absolute;
   
}

</style>