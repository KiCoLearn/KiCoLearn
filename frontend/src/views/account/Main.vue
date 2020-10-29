<template>
    <div class="startmain">
        <img 
            class="mainimg"
            src="@/assets/background.jpg"
            alt="배경 이미지"
        >
        <div class="login">
            <img 
                class="kakao-sign"
                src="@/assets/kakao.png"
                alt="카카오 로그인"
                @click="clickkakaologin"
            >
            <button
                class="kidslogin"
                @click="kidslogin"
            >
                아이 로그인
            </button><br>
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
            this.$router.push({name:'kidslogin'});
        },
        sign(){//필요한가...?
            this.$router.push({name:'sign'});
        },
    },
};
</script>

<style scoped>
.mainimg {
  opacity: 0.5;
  background-size: 100%;
  width: 100%;
  height: 100%;
}
.kakao-sign{
    position: absolute;
    top: 400px;
    left: 120px;
}
.kidslogin,.sign{
    background-color: #8cca79;
    color: #F8F8F8;
    width: 6rem;
    border-radius: 2rem;
    
}

</style>