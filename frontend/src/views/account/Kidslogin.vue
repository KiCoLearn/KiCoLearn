<template>
    <div class="login">
        <v-layout>
            <v-flex>
                <v-row justify="center">
                    <input 
                        id="otp"
                        class="otpkids"
                        type="password" 
                        placeholder="번호를 입력해주세요" 
                        required
                    > 
                </v-row>

                <v-row justify="end">
                    <v-btn
                        text
                        class="items"
                        @click="kid"
                    >
                        로그인하기
                    </v-btn>
                </v-row>
            </v-flex>
        </v-layout>
    </div>
</template>
<script>
export default {
    methods: {
        kid(){
            this.$router.push({name: 'Kidsmain'});
        }
    },
};
</script>
<style scoped>
.mainimg {
  opacity: 0.5;
  background-size: contain;
  width: 100%;
  height: 100%;
}

.login{
    height: 100%;
    display: flex;
    align-items: center;
}

</style>