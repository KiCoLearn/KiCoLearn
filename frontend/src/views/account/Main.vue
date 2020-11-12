<template>
    <div class="login">
        <v-layout>
            <v-flex>
                <v-row justify="center"> 
                    <img
                        src="@/assets/adults.png"
                        width="150px"
                    >
                </v-row>

                <v-row justify="center">
                    <Kakao />
                </v-row>

                <v-row justify="center"> 
                    <v-btn
                        text
                        class="kidlogin"
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
            this.$router.push({name:'KidLogin'});
        },
    },
};
</script>

<style scoped>
.row{
    margin-bottom: 10px;
}

.kidlogin{
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