<template>
    <div>
        로그인중,,,
    </div>
</template>

<script>
export default {
    mounted() {
        const code = this.$route.query.code;
        console.log(this.$router);
        this.$store.dispatch('auth/login', code)
            .then(() => {

            }).catch(() => {

            }).finally(() => {
                this.$store.dispatch('auth/auth')
                    .finally(() => {
                        console.log(this.$router);
                        this.$router.go(-1);
                    });
            });
    },
};
</script>

<style>

</style>