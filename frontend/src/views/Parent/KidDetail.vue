<template>
    <div v-if="kid.characterIdx!=undefined">
        <v-layout>
            <v-flex>
                <v-row>
                    <button
                        class="btn"
                        style="margin-left:15px"
                        @click="goList"
                    >
                        <img
                            src="@/assets/list.png"
                            width="60px"
                        >
                    </button>
                </v-row>
                <v-row justify="center">
                    <img
                        :src="photo(kid.characterIdx)"
                        width="140px"
                    >
                </v-row>
                <v-row
                    justify="center"
                    style="font-size:1.2rem"
                >
                    <div>
                        <b>{{ kid.name }}</b> 어린이의 현재 잔액
                        <br>
                       
                        <animated-number
                            :value="kid.totalMoney"
                            :format-value="formatToPrice"
                            :duration="600"
                        />원
                    </div>
                </v-row>
                <v-row
                    justify="center"
                    style="font-size:1.2rem"
                >
                    <v-col cols="6">
                        오늘 소비
                    </v-col>
                    
                    <v-col cols="6">
                        이번주 소비
                    </v-col>
  
                    <v-col cols="6">
                        <animated-number
                            :value="kid.totalMoney"
                            :format-value="formatToPrice"
                            :duration="600"
                        />원
                    </v-col>
                    
                    <v-col cols="6">
                        <animated-number
                            :value="kid.totalMoney"
                            :format-value="formatToPrice"
                            :duration="600"
                        />원
                    </v-col>
                </v-row>

                <v-row
                    v-for="idx in 5"
                    :key="idx"
                    justify="center"
                >
                    <div>퀘스트{{ idx }}</div>
                </v-row>

                <v-row justify="center">
                    <v-col cols="6">
                        <button>
                            <button>
                                <img
                                    src="@/assets/list2.png"
                                    width="80px"
                                >
                            </button>
                        </button>
                    </v-col>
                    <v-col cols="6">
                        <button>
                            <img
                                src="@/assets/store.png"
                                width="80px"
                            >
                        </button>
                    </v-col>

                    <v-col cols="6">
                        용돈 기입장
                    </v-col>
                    <v-col cols="6">
                        스토어관리
                    </v-col>
                </v-row>

                <v-row justify="end">
                    <button style="margin-right:20px;">
                        아이 로그인 시키기
                    </button>
                </v-row>
            </v-flex>
        </v-layout>
    </div>
</template>

<script>
import axios from 'axios';  
import AnimatedNumber from 'animated-number-vue';

export default {
    name: 'KidDetail',
    components: {
        AnimatedNumber
    },
    data() {
        return {
            kid: Object,
            value: 1000
        };
    },

    computed: {
        kidId(){
            return this.$route.query.id;
        }
    },
    created() {
        axios.get(process.env.VUE_APP_API_URL + '/api/kidsaccount/detail/'+this.kidId,
            {
                headers: {
                    'jwt-auth-token':''
                },
            })
            .then((res) => {
                this.kid= res.data.data;
            });
    },
    methods: {
        photo(idx){
            if(idx<10) return require('@/assets/character/00'+idx+'.png');
            return require('@/assets/character/0'+idx+'.png');
        },
        formatToPrice(value) {
            return `${value.toFixed(0)}`;
        },
        goList(){
            this.$router.push('/kidslist');
        } 
    },

};
</script>

<style scoped>
  .row{
    margin-bottom:15px;
  }

  .col{
    padding:0;
    margin: 0;
  }
</style>