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
                        v-if="todayData!=null"
                        cols="6"
                        style="border-right: 2px solid #ffdd93;"
                    >
                        <Chart :chart-data="todayData" />
                    </v-col>
                    <v-col
                        v-if="weekData!=null"
                        cols="6"
                    >
                        <Chart :chart-data="weekData" />
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
                    v-for="q in quest"
                    :key="q.questNo"  
                    :v-if="quest.length>0"
                    class="quest"
                    justify="center"
                >
                    <v-col cols="3">
                        {{ q.name }}
                    </v-col>
                    <v-col cols="6">
                        {{ q.description }}
                    </v-col>
                    <v-col cols="3">
                        {{ q.reward }}원
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
import Chart from '@/views/report/Chart';
import { mapGetters } from 'vuex';

export default {
    name: 'KidDetail',
    components: {
        AnimatedNumber,
        Chart
    },
    data() {
        return {
            kid: Object,
            value: 1000,
            week:0,
            today:0,
            todayData:null,
            weekData:null,
            quest : new Array(),
        };
    },

    computed: {
        ...mapGetters({
            kidId : 'auth/select',
        })
    },
    created() {
        this.fillChartData();
        axios.get('/api/kidsaccount/detail/'+this.kidId)
            .then((res) => {
                this.kid= res.data.data;
                axios.get('/api/money/week/'+this.kidId)
                    .then((res) =>{
                        this.week = res.data.data.week;
                        this.today = res.data.data.today;
                    });
            });
        
        axios.get('/api/quest/kid/list/'+this.kidId)
            .then((res) => {
                console.log(res.data.data);
                this.quest = res.data.data;
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
        },
        fillChartData(){
            axios.get('/api/money/deposit/today/'+this.kidId)
                .then((res) => {
                    const data = res.data.data;

                    if(data.length>0){
                        let arr = new Array();
                        let label = new Array();
                        data.forEach((item,index) => {
                            arr[index] = item.amount;
                            label[index] = item.contents;
                        });
                        this.todayData = {
                            labels : label,
                            hoverBackgroundColor: 'red',
                            hoverBorderWidth: 10,
                            datasets: [ 
                                {           
                                    backgroundColor: ['#41B883', '#E46651', '#00D8FF'],
                                    data:arr,
                                }
                            ],
                        };
                    }
                })
                .catch(err => {
                    console.log(err);
                });  
                
            axios.get('/api/money/deposit/week/'+this.kidId)
                .then((res) => {
                   
                    if(res.data.data.length>0){
                        const data = res.data.data;

                        let arr = new Array();
                        let label = new Array();
                        data.forEach((item,index) => {
                            arr[index] = item.amount;
                            label[index] = item.contents;
                        });
                        this.weekData = {
                            labels:label,
                            hoverBackgroundColor: 'red',
                            hoverBorderWidth: 10,
                            datasets: [ 
                                {           
                                    backgroundColor: ['gray', '#E46651', '#00D8FF'],
                                    data:arr
                                }
                            ],
                        };
                    }

                })
                .catch(err => {
                    console.log(err);
                });  
            
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

  ::v-deep .chartjs-render-monitor{
    width: 150px!important;
    height: 150px !important;
    margin: auto;
    margin-bottom: 10px;
  }  
</style>