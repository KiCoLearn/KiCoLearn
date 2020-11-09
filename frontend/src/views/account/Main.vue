<template>
    <div class="login">
        <v-layout>
            <v-flex>
                <v-row justify="center">
                    <Kakao />
                </v-row>

                <v-row justify="center"> 
                    <v-btn
                        text
                        class="kidslogin"
                        @click="kidslogin"
                    >
                        아이 로그인
                    </v-btn>
                </v-row>
            </v-flex>
        </v-layout> 
    </div>
</template>

<script>
import Kakao from '@/components/oauth/OAuthKakao';

export default {
    components: {
        Kakao,
    },
    methods: {
        kidslogin(){
            this.$router.push({name:'Kidslogin'});
        },
    },
};
</script>

<style scoped>

.kidslogin{
  font-size: 1rem !important;
}

.login{
    width: 100%;
    height: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
}

</style>