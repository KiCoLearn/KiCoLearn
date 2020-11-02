<template>
    <div class="startmain">
        <div class="login">
            <p>
                <img 
                    class="kakao-sign"
                    src="@/assets/kakao.png"
                    ty
                    alt="카카오 로그인"
                    @click="clickkakaologin"
                >
            </p>
            <p>
                <button
                    class="kidslogin"
                    @click="kidslogin"
                >
                    아이 로그인
                </button>
            </p>
            <button
                class="sign"
                @click="sign"
            >
                회원가입
            </button>
        </div>
    </div>
</template>

<script>
export default {
    methods: {
        kidslogin(){
            this.$router.push({name:'Kidslogin'});
        },
        sign(){//필요한가...?
            this.$router.push({name:'Sign'});
        },
    },
};
</script>

<style scoped>

.kidslogin,.sign{
    background-color: #8cca79;
    color: #F8F8F8;
    width: 6rem;
    border-radius: 2rem;
}

</style>