<template>
    <div>
        로그인중,,,
    </div>
</template>

<script>
export default {
    mounted() {
        const code = this.$route.query.code;
        this.$store.dispatch('auth/login', code)
            .then(() => {

            }).catch(() => {

            }).finally(() => {
                this.$store.dispatch('auth/auth')
                    .finally(() => {
                        this.$router.push('/');
                    });
            });
    },
};
</script>

<style>

</style>