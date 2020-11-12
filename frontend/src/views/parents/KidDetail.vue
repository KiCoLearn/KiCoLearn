<template>
    <div
        v-if="kid.characterIdx!=undefined"
        class="detail"
    >
        <v-layout>
            <v-flex>
                <v-row
                    justify="center"
                    style="margin-left:33px"
                >
                    <img
                        :src="photo(kid.characterIdx)"
                        width="140px"
                    >
                    <button
                        class="btn"
                        style="display:flex;align-items:flex-end"
                        @click="goUpdate"
                    >
                        <img
                            src="@/assets/settings.png"
                            width="45px"
                        >
                    </button>
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
                    <v-col
                        cols="6"
                        style="border-right: 2px solid #ffdd93;"
                    >
                        오늘 소비
                    </v-col>
                    
                    <v-col cols="6">
                        이번주 소비
                    </v-col>
  
                    <v-col
                        cols="6"
                        style="border-right: 2px solid #ffdd93;"
                    >
                        <animated-number
                            :value="today"
                            :format-value="formatToPrice"
                            :duration="600"
                        />원
                    </v-col>
                    
                    <v-col cols="6">
                        <animated-number
                            :value="week"
                            :format-value="formatToPrice"
                            :duration="600"
                        />원
                    </v-col>
                </v-row>
    

                <v-row
                    v-for="idx in 3"  
                    :key="idx"
                    class="quest"
                    justify="center"
                >
                    <v-col cols="1">
                        {{ idx }}
                    </v-col>
                    <v-col cols="6">
                        퀘스트 내용
                    </v-col>
                    <v-col cols="3">
                        2020/11/06
                    </v-col>
                </v-row>
            
                <v-row
                    justify="end"
                    class="nav"
                    style="margin-bottom:0"
                >
                    <button
                        rounded
                        class="head"
                        @click="goCertification"
                    >
                        <b>아이 로그인 시키기</b>
                    </button>
                </v-row>
            </v-flex>
        </v-layout>
    </div>
</template>

<script>
import axios from '@/plugins/axios';  
import AnimatedNumber from 'animated-number-vue';
import { mapGetters } from 'vuex';

export default {
    name: 'KidDetail',
    components: {
        AnimatedNumber
    },
    data() {
        return {
            kid: Object,
            value: 1000,
            week:0,
            today:0,
        };
    },

    computed: {
        ...mapGetters({
            kidId : 'auth/select',
        })
    },
    created() {
        axios.get('/api/kidsaccount/detail/'+this.kidId)
            .then((res) => {
                this.kid= res.data.data;
                axios.get('/api/money/week/'+this.kidId)
                    .then((res) =>{
                        this.week = res.data.data.week;
                        this.today = res.data.data.today;
                    });
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
        goUpdate(){
            this.$router.push({name: 'KidUpdate'});
        },
        goCertification(){
            this.$router.push({name:'Certification'});
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

  .btn2{
    padding:10px 15px 5px 15px;
    border:2px solid #ffdd93;
    border-radius: 6px;
    background-color: white;
    outline:none;
  }

  .quest{
    padding:5px;
    border-radius: 15px;
    background-color: #ffdd93;
    margin-left: 10px;
    margin-right: 10px;
    box-shadow: 0 2px 4px 0 rgba(0, 0, 0, 0.15), 0 4px 8px 0 rgba(0, 0, 0, 0.15);
  }

.detail{
    width: 336px;
    margin: auto;
    
}

.nav{
    font-size: 1.2rem;
    font-family: 'Gaegu';
    color: white;
  }


  .head{
    background:#fb8c00;
    border-radius: 40px;
    padding:6px;
    margin-right: 15px;
  }

</style>