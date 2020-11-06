<template>
    <div class="startmain">
        <div class="login">
            <Kakao />
            <Kakao2 />
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
            <p>
                <button
                    class="sign"
                    @click="$router.push({ name: 'QuizTest' })"
                >
                    퀴즈풀기*
                </button>
                <button
                    class="sign"
                    @click="$router.push({ name: 'QuizManage' })"
                >
                    퀴즈관리*
                </button>
            </p>
        </div>
    </div>
</template>

<script>
import Kakao from '@/components/oauth/OAuthKakao';
import Kakao2 from '@/components/oauth/OAuthKakaoLogout';

export default {
    components: {
        Kakao,
        Kakao2,
    },
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