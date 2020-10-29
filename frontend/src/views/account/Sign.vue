<template>
    <div class="sign">
        <h3>회원가입 </h3>
    </div>
</template>
<script>
export default {
    methods: {
        kakaoLogin() {
            window.Kakao.Auth.login({
                scope: 'profile, account_email',
                success: this.GetMe,
            });
        },
    },
    
};
</script>
<style scoped>

</style>