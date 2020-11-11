<template>
    <div id="app2">
        <pie-chart
            :data="chartData"
            :options="chartOptions"
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
            chartData: {
                hoverBackgroundColor: 'red',
                hoverBorderWidth: 10,
                datasets: [ 
                    {           
                        backgroundColor: ['#41B883', '#E46651', '#00D8FF'],
                        data:this.amount,
                    }
                ],
                deposit:new Array(),
            }
        };
    },
    created() {
        axios.get('/api/money/reportdeposit/'+this.kidId)
            .then((res) => {

                console.log(res.data.data);
                var a = res.data.data;
                console.log(this.datasets);
                a.forEach((item,index) => {
                    this.deposit[index] = item.amount;
                });
              
            })
            .catch(err => {
                console.log(err);
            });

    }
};
</script>

