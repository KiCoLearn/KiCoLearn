
import { Pie, mixins } from 'vue-chartjs';
import Chart from 'chart.js';

Chart.plugins.register({
    afterDatasetsDraw: function (chart) {
        // To only draw at the end of animation, check for easing === 1
        var ctx = chart.ctx;
        chart.data.datasets.forEach(function (dataset, i) {
            var meta = chart.getDatasetMeta(i);
            var sum = 0;
            if (!meta.hidden) {
                meta.data.forEach(function (element, index) {
                    sum+= dataset.data[index];
                });
                meta.__sum=sum;
            }
        });
        

        const label = chart.data.labels;
        chart.data.datasets.forEach(function (dataset, i) {
            var meta = chart.getDatasetMeta(i);
            if (!meta.hidden) {
                meta.data.forEach(function (element, index) {
                    if(meta.__sum/6 > dataset.data[index]) return;

                    // Draw the text in black, with the specified font
                    ctx.fillStyle = 'rgb(0, 0, 0)';

                    var fontSize = 16;
                    var fontStyle = 'normal';
                    var fontFamily = 'Helvetica Neue';
                    ctx.font = Chart.helpers.fontString(fontSize, fontStyle, fontFamily);

                    // Just naively convert to string for now
                    var dataString = dataset.data[index].toString();
                    var dataLabel =label[index].toString();


                    // Make sure alignment settings are correct
                    ctx.textAlign = 'center';
                    ctx.textBaseline = 'middle';

                    var padding = 5;
                    var position = element.tooltipPosition();
                    ctx.fillText(dataString, position.x, position.y - (fontSize / 2) - padding);
                    ctx.fillText(dataLabel, position.x, position.y + (fontSize / 2) + padding);
                });
            }
        });
    }
});

const { reactiveProp } = mixins;
export default {
    extends: Pie,
    mixins:[reactiveProp],
    props: ['options'],
    mounted() {
        this.renderChart(this.chartData, this.options);
    }
};
