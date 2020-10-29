<template>
    <div>
        <a
            :href="kakaoOAuthLink"
            alt="kakao login"
        >
            <img
                alt="kakao logo"
                src="@/assets/kakao_login_medium_narrow.png" 
            >
        </a>
    </div>
</template>

<script>
export default {
    computed: {
        kakaoOAuthLink() {
            return `https://kauth.kakao.com/oauth/authorize?client_id=${process.env.VUE_APP_OAUTH_KAKAO_CLIENT_ID}&redirect_uri=${process.env.VUE_APP_OAUTH_KAKAO_REDIRECTION_URI}&response_type=code`;
        }
    },
};
</script>

<style>

</style>