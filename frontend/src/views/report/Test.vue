<template>
    <div id="app2">
        <pie-chart
            ref="PieChart"
            :chart-data="chartData"            
        />
    </div>
</template>

<script>
import PieChart from './pie.js';
import axios from '@/plugins/axios';

export default {
    name:'Report',
    components: {
        PieChart
    },
    data() {
        return {
            kidId:1,
            chartOptions: {
                hoverBorderWidth: 20
            },
            data:null,
            chartData:null
            // chartData: {
            //     hoverBackgroundColor: 'red',
            //     hoverBorderWidth: 10,
            //     datasets: [ 
            //         {           
            //             backgroundColor: ['#41B883', '#E46651', '#00D8FF'],
            //             data:[]
            //         }
            //     ],
            //     //deposit:new Array(),
            // }
        };
    },
    mounted(){
        this.fillChartData();
    },
    created() {
        axios.get('/api/money/reportdeposit/'+this.kidId)
            .then((res) => {
                console.log(res.data.data);
                //this.data = res.data.data;
                // var a = res.data.data;
                // console.log(this.chartData.datasets[0]);
                // this.chartData.datasets.data = new Array(a.length);
                // a.forEach((item,index) => {
                //     this.chartData.datasets.data[index] = item.amount;
                //     console.log(this.chartData.datasets.data[index], index);
                // });
                this.fillChartData(res.data.data);
              
            })
            .catch(err => {
                console.log(err);
            });
    },
    methods:{        
        fillChartData(data){
            let arr = new Array(data.length);
            data.forEach((item,index) => {
                arr[index] = item.amount;
                console.log(arr[index], index);
            });
            this.chartData = {
                hoverBackgroundColor: 'red',
                hoverBorderWidth: 10,
                datasets: [ 
                    {           
                        backgroundColor: ['#41B883', '#E46651', '#00D8FF'],
                        data:arr
                    }
                ],
            };
            
        }
    }
};
</script>

