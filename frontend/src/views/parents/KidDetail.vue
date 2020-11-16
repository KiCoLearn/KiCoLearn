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
                    style="font-size:1.2rem;margin-bottom:5px"
                >
                    <div>
                        <b>{{ kid.name }}</b> 어린이의 현재 잔액
                        <br>
                       
                        <b style="font-size:1.3rem"><animated-number
                            :value="kid.totalMoney"
                            :format-value="formatToPrice"
                            :duration="600"
                        />원</b>
                    </div>
                </v-row>
                <v-row
                    justify="center"
                    style="font-size:1.2rem;"
                >
                    <v-col
                        cols="6"
                        style="background:rgb(255, 221, 147)"
                    >
                        오늘 소비
                    </v-col>
                    
                    <v-col
                        cols="6"
                        style="background:rgb(255, 221, 147)"
                    >
                        이번주 소비
                    </v-col>

                    <v-col 
                        v-if="todaySpendData!=null"
                        cols="6"
                        style="border-right: 2px solid #ffdd93;"
                    >
                        <Chart :chart-data="todaySpendData" />
                    </v-col>
                    <v-col
                        v-else
                        cols="6"
                        style="border-right: 2px solid #ffdd93;margin:auto"
                    >
                        <img
                            src="@/assets/empty.png"
                            width="140px"
                        >   
                    </v-col>

                    <v-col
                        v-if="weekSpendData!=null"
                        cols="6"
                    >
                        <Chart :chart-data="weekSpendData" />
                    </v-col>

                    <v-col
                        v-else
                        cols="6"
                        style="margin:auto"
                    >
                        <img
                            src="@/assets/empty.png"
                            width="140px"
                        >   
                    </v-col>
  
                    <v-col
                        cols="6"
                        style="background:lightgray"
                    >
                        <animated-number
                            :value="todaySpend"
                            :format-value="formatToPrice"
                            :duration="600"
                        />원
                    </v-col>
                    
                    <v-col
                        cols="6"
                        style="background:lightgray"
                    >
                        <animated-number
                            :value="weekSpend"
                            :format-value="formatToPrice"
                            :duration="600"
                        />원
                    </v-col>
                </v-row>



                <v-row
                    justify="center"
                    style="font-size:1.2rem"
                >
                    <v-col
                        cols="6"
                        style="background:rgb(255, 221, 147)"
                    >
                        오늘 수입
                    </v-col>
                    
                    <v-col
                        cols="6"
                        style="background:rgb(255, 221, 147)"
                    >
                        이번주 수입
                    </v-col>

                    <v-col 
                        v-if="todayDepositData!=null"
                        cols="6"
                        style="border-right: 2px solid #ffdd93;"
                    >
                        <Chart :chart-data="todayDepositData" />
                    </v-col>
                    <v-col
                        v-else
                        cols="6"
                        style="margin:auto"
                    >
                        <img
                            src="@/assets/empty.png"
                            width="140px"
                        >   
                    </v-col>

                    <v-col
                        v-if="weekDepositData!=null"
                        cols="6"
                    >
                        <Chart :chart-data="weekDepositData" />
                    </v-col>

                    <v-col
                        v-else
                        cols="6"
                        style="border-right: 2px solid #ffdd93;margin:auto"
                    >
                        <img
                            src="@/assets/empty.png"
                            width="140px"
                        >   
                    </v-col>
  
                    <v-col
                        cols="6"
                        style="background:lightgray"
                    >
                        <animated-number
                            :value="todayDeposit"
                            :format-value="formatToPrice"
                            :duration="600"
                        />원
                    </v-col>
                    
                    <v-col
                        cols="6"
                        style="background:lightgray"
                    >
                        <animated-number
                            :value="weekDeposit"
                            :format-value="formatToPrice"
                            :duration="600"
                        />원
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
            weekSpend:0,
            todaySpend:0,
            weekDeposit:0,
            todayDeposit:0,
            todayDepositData:null,
            weekDepositData:null,
            todaySpendData:null,
            weekSpendData:null,
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
                axios.get('/api/money/spend/total/'+this.kidId)
                    .then((res) =>{
                        this.weekSpend = res.data.data.week;
                        this.todaySpend = res.data.data.today;
                    });
            });

        axios.get('/api/kidsaccount/detail/'+this.kidId)
            .then((res) => {
                this.kid= res.data.data;
                axios.get('/api/money/deposit/total/'+this.kidId)
                    .then((res) =>{
                        this.weekDeposit = res.data.data.week;
                        this.todayDeposit = res.data.data.today;
                    });
            });    
        
        axios.get('/api/quest/kid/list/'+this.kidId)
            .then((res) => {
                //console.log(res.data.data);
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
                        let color = new Array();
                        data.forEach((item,index) => {
                            arr[index] = item.amount;
                            label[index] = item.contents;
                            color[index] = `hsl(${Math.floor(Math.random()*360)}, ${Math.floor(Math.random()*30)+70}%, 80%)`;
                        
                        });
                        this.todayDepositData = {
                            labels : label,
                            hoverBackgroundColor: 'red',
                            hoverBorderWidth: 10,
                            datasets: [ 
                                {           
                                    backgroundColor: color,
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
                        let color = new Array();
                        data.forEach((item,index) => {
                            arr[index] = item.amount;
                            label[index] = item.contents;
                            color[index] = `hsl(${Math.floor(Math.random()*360)}, ${Math.floor(Math.random()*30)+70}%, 80%)`;
                        });
                    
                        this.weekDepositData = {
                            labels:label,
                            hoverBackgroundColor: 'red',
                            hoverBorderWidth: 10,
                            datasets: [ 
                                {           
                                    backgroundColor: color,
                                    data:arr
                                }
                            ],
                            
                        };
                    }

                })
                .catch(err => {
                    console.log(err);
                });  

            axios.get('/api/money/spend/today/'+this.kidId)
                .then((res) => {
                   
                    if(res.data.data.length>0){
                        const data = res.data.data;

                        let arr = new Array();
                        let label = new Array();
                        let color = new Array();
                        data.forEach((item,index) => {
                            arr[index] = item.amount;
                            label[index] = item.contents;
                            color[index] = `hsl(${Math.floor(Math.random()*360)}, ${Math.floor(Math.random()*30)+70}%, 80%)`;
                        
                        });
                        this.todaySpendData = {
                            labels:label,
                            hoverBackgroundColor: 'red',
                            hoverBorderWidth: 10,
                            datasets: [ 
                                {           
                                    backgroundColor: color,
                                    data:arr
                                }
                            ],
                        };
                    }

                })
                .catch(err => {
                    console.log(err);
                });  

            axios.get('/api/money/spend/week/'+this.kidId)
                .then((res) => {
                   
                    if(res.data.data.length>0){
                        const data = res.data.data;

                        let arr = new Array();
                        let label = new Array();
                        let color = new Array();
                        data.forEach((item,index) => {
                            arr[index] = item.amount;
                            label[index] = item.contents;
                            color[index] = `hsl(${Math.floor(Math.random()*360)}, ${Math.floor(Math.random()*30)+70}%, 80%)`;
                        
                        });
                        this.weekSpendData = {
                            labels:label,
                            hoverBackgroundColor: 'red',
                            hoverBorderWidth: 10,
                            datasets: [ 
                                {           
                                    backgroundColor: color,
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
    margin-right: 10px;
  }

  ::v-deep .chartjs-render-monitor{
    width: 150px!important;
    height: 150px !important;
    margin: auto;
    padding: 8px;
  }  
</style>