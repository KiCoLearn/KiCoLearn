<template>
    <div>
        <img
            alt="kakao logo"
            src="@/assets/kakao_login_medium_narrow.png"
            @click="logout"
        >
    </div>
</template>

<script>
export default {
    methods: {
        logout() {
            this.$store.dispatch('auth/logout')
                .then(() => {

                }).catch(() => {

                }).finally(() => {
                    this.$store.dispatch('auth/auth')
                        .finally(() => {
                        });
                });
        },
    }
};
</script>

<style>

</style>