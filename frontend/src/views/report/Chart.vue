<template>
    <div>
        <pie-chart
            ref="PieChart"
            :chart-data="chartData"            
        />
    </div>
</template>

<script>
import PieChart from './pie.js';

export default {
    name:'Report',
    components: {
        PieChart
    },
    props:{
        chartData: {
            type: undefined,
            required:true,
        }
    },
   
    data() {
        return {
            chartOptions: {
                hoverBorderWidth: 20,
            },
        };
    },
};
</script>

