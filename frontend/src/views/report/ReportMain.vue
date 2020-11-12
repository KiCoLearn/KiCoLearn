<template>
    <div class="wrapper">
        <input
            id="tab1"
            type="radio"
            name="tab"
            checked
        >
        <label for="tab1">이용 내역</label>
        <input
            id="tab2"
            type="radio"
            name="tab"
        >
        <label for="tab2">소비 패턴</label>
		
        <div class="content">
            <div class="tab1">
                <ul
                    v-for="recent in recents"
                    :key="recent.kidId"
                >
                    <v-card class="recentlist">
                        <v-layout column>
                            <v-layout column>
                                <v-card-title><h5>{{ recent.contents }}</h5></v-card-title>
                                <v-card-text>{{ recent.amount }}</v-card-text>
                                <v-card-text>{{ recent.date }}</v-card-text>
                            </v-layout>
                        </v-layout>
                    </v-card>
                    <br>
                </ul>
            </div>
		
            <div class="tab2">
                <div class="wrapper2">
                    <div class="pie-charts">
                        <div class="pieID--operations pie-chart--wrapper">
                            <h3> 수입 </h3>
                            <div class="pie-chart">
                                <div class="pie-chart__pie" />
                                <ul class="pie-chart__legend">
                                    <li><em>Addition</em><span>486</span></li>
                                    <li><em>Subtraction</em><span>156</span></li>
                                    <li><em>Multiplication</em><span>215</span></li>
                                    <li><em>Division</em><span>143</span></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <h3> 지출 </h3>
            </div>
        </div>
        <br class="clear:both">
    </div><!-- //wrapper -->
</template>

<script>
import axios from '@/plugins/axios';

export default {
    name:'ReportMain',
    data() {
        return {
            recents: new Array(),
            kidId:1,
           
        };
    },
    created() {
        axios.get(process.env.VUE_APP_API_URL + '/api/money/activity/'+this.kidId)
            .then((res) => {
                console.log(res);
                console.log(res.data.data);
                this.recents = res.data.data;
            })
            .catch(err => {
                console.log(err);
            });

    }
        
};
</script>

<style scoped>
* {
  box-sizing: border-box;
}

.wrapper {
  width: 100%;
  margin: 10px auto;
  max-width: 800px;
  height: 100%;
}
.wrapper label {
  cursor: pointer;
  float: left;
  margin-right: 5px;
  display: block;
  padding: 10px 30px;
  background-color: #ecf0f1;
  border-radius: 20px 20px 0 0;
}
.wrapper input {
  display: none;
}
.wrapper input:checked + label {
  background-color: #e74c3c;
  color: #fefefe;
}
.wrapper input[id="tab1"]:checked ~ .content .tab1,
.wrapper input[id="tab2"]:checked ~ .content .tab2 {
  display: block;
}
.wrapper .content {
  clear: both;
  position: relative;
}
.tab1{
  width: 100%;
  padding: 50px;
  position: absolute;
  top: 0;
  left: 0;
  background-color: #fff;
  display: none;
  border-top: 5px solid #e74c3c;
  border-radius: 0 10px 10px 10px;
}
.tab2{
  width: 100%;
  padding: 70px 50px;
  position: absolute;
  top: 0;
  left: 0;
  background-color: #fff;
  display: none;
  border-top: 5px solid #e74c3c;
  border-radius: 0 10px 10px 10px;
}


@-webkit-keyframes bake-pie {
  from {
    -webkit-transform: rotate(0deg) translate3d(0, 0, 0);
            transform: rotate(0deg) translate3d(0, 0, 0);
  }
}

@keyframes bake-pie {
  from {
    -webkit-transform: rotate(0deg) translate3d(0, 0, 0);
            transform: rotate(0deg) translate3d(0, 0, 0);
  }
}
.pie-chart {
  font-family: "Open Sans", Arial;
}
.pie-chart--wrapper {
  width: 400px;
  margin: 30px auto;
  text-align: center;
}
.pie-chart__pie, .pie-chart__legend {
  display: inline-block;
  vertical-align: top;
}
.pie-chart__pie {
  position: relative;
  height: 200px;
  width: 200px;
  margin: 10px auto 35px;
}
.pie-chart__pie::before {
  content: "";
  display: block;
  position: absolute;
  z-index: 1;
  width: 100px;
  height: 100px;
  background: #EEE;
  border-radius: 50%;
  top: 50px;
  left: 50px;
}
.pie-chart__pie::after {
  content: "";
  display: block;
  width: 120px;
  height: 2px;
  background: rgba(0, 0, 0, 0.1);
  border-radius: 50%;
  box-shadow: 0 0 3px 4px rgba(0, 0, 0, 0.1);
  margin: 220px auto;
}

.slice {
  position: absolute;
  width: 200px;
  height: 200px;
  clip: rect(0px, 200px, 200px, 100px);
  -webkit-animation: bake-pie 1s;
          animation: bake-pie 1s;
}
.slice span {
  display: block;
  position: absolute;
  top: 0;
  left: 0;
  background-color: black;
  width: 200px;
  height: 200px;
  border-radius: 50%;
  clip: rect(0px, 200px, 200px, 100px);
}

.pie-chart__legend {
  display: block;
  list-style-type: none;
  padding: 0;
  margin: 0 auto;
  background: #FFF;
  padding: 0.75em 0.75em 0.05em;
  font-size: 13px;
  box-shadow: 1px 1px 0 #DDD, 2px 2px 0 #BBB;
  text-align: left;
  width: 65%;
}
.pie-chart__legend li {
  height: 1.25em;
  margin-bottom: 0.7em;
  padding-left: 0.5em;
  border-left: 1.25em solid black;
}
.pie-chart__legend em {
  font-style: normal;
}
.pie-chart__legend span {
  float: right;
}

.pie-charts {
  display: -webkit-box;
  display: flex;
  -webkit-box-orient: horizontal;
  -webkit-box-direction: normal;
          flex-direction: row;
}
@media (max-width: 500px) {
  .pie-charts {
    -webkit-box-orient: vertical;
    -webkit-box-direction: normal;
            flex-direction: column;
  }
}

</style>