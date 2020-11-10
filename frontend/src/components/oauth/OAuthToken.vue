<template>
    <div />
</template>

<script>
export default {
    mounted() {
        const code = this.$route.query.code;

        this.$store.dispatch('auth/getToken', code)
            .then((token) => {
                this.$store.dispatch('auth/login', {
                    token,
                    provider: 'kakao',
                })
                    .finally(() => {
                        this.$router.push({
                            name: 'ParentsMain'
                        });
                    });
            }).catch(() => {

            }).finally(() => {
                this.$store.dispatch('auth/auth');
            });
    },
    methods: {
    }
};
</script>

<style>

</style>