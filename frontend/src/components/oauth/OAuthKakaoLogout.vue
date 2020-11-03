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