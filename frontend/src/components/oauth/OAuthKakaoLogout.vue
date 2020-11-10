<template>
    <div>
        <button
            text="LOGOUT"
            @click="logout"
        >
            로그아웃
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

<style>

</style>