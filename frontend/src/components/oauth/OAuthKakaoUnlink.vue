<template>
    <div>
        <button
            text="LOGOUT"
            @click="logout"
        >
            회원탈퇴
        </button>
    </div>
</template>

<script>
export default {
    methods: {
        logout() {
            this.$store.dispatch('auth/unlink')
                .then(() => {

                }).catch(() => {

                }).finally(() => {
                });
        },
    }
};
</script>

<style>

</style>