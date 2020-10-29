<template>
    <div class="kidslogin">
        <img 
            class="mainimg"
            src="@/assets/background.jpg"
            alt="배경 이미지"
        >
        <div class="input">
            <input 
                id="otp"
                class="otpkids"
                type="password" 
                placeholder="번호를 입력해주세요" 
                required
            >
            <p class="my-3">
                <span
                    class="items"
                    @click="kid"
                >로그인하기</span>
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


</style>