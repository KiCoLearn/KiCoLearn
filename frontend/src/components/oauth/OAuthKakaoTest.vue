<template>
    <div>
        <button
            text="LOGOUT"
            @click="logout"
        >
            로그인 상태 확인 : {{ ok }}
        </button>
    </div>
</template>

<script>
export default {
    data: () => ({
        ok: false,
    }),
    methods: {
        logout() {
            this.$store.dispatch('auth/auth')
                .then(() => {
                    this.ok = true;
                }).catch(() => {
                    this.ok = false;

                }).finally(() => {
                });
        },
    }
};
</script>

<style>

</style>