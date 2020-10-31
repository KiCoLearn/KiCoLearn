<template>
    <div class="kidsheader">
        <button
            id="home"
            class="home"
            @click="home"
        >
            메인으로 가기
        </button>
        <p class="kidsname">
            아이 이름
        </p>
        <p class="kidspoint">
            아이 포인트
        </p>
    </div>
</template>

<script>
export default {
    methods: {
        home(){
            this.$router.push({name:'Kidsmain'});
        },
    },
    
};
</script>

<style scoped>

.kidsheader{
    background-image: url("https://i.pinimg.com/736x/18/b7/a4/18b7a445ed60714c929132b92b585615.jpg");
    position: fixed;
    top: 0;
    width: 100%;
    height: 15rem;
    background-repeat: no-repeat;
}
</style>