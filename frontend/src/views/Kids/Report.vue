<template>
    <div>
        <div
            id="tabs"
            class="container"
        >
            <div class="tabs">
                <a
                    :class="[ activetab === 1 ? 'active' : '' ]"
                    @click="activetab=1"
                >최근이용내역</a>
                <a
                    :class="[ activetab === 2 ? 'active' : '' ]"
                    @click="activetab=2"
                >소비 레포트</a>
            </div>

            <div class="content">
                <div
                    v-if="activetab === 1"
                    class="tabcontent"
                >
                    <History />
                </div>
                <div
                    v-if="activetab === 2"
                    class="tabcontent"
                >
                    <UseReport />
                </div>
            </div>
        </div>
    </div>
</template>

<script >
import History from '@/components/report/History.vue';
import UseReport from '@/components/report/UseReport.vue';
import axios from 'axios';

export default {
    name:'Report',
    components:{
        History,
        UseReport,
   
    },
    data() {
        return {
            activetab:1,
        };
    },  
    created() {
        axios.post(process.env.VUE_APP_API_URL + '/api/money/activity')
            .then((res) => {
                //this.kids = res.data.data;
                console.log(res.data.data);
            });
    },
        
};
</script>
<style scoped>

* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}
.container {  
    max-width: 100%; 
    min-width: 20rem;
    margin: 2.5rem auto;
    font-family: Arial, Helvetica, sans-serif;
    color: #888;
}
.tabs {
    overflow: hidden;
  margin-left: 1rem;
    margin-bottom: -0.1rem; 
}
.tabs ul {
    list-style-type: none;
    margin-left: 1rem;
}
.tabs a{
    float: initial;
    cursor: pointer;
    padding: 0.5rem 1rem;
    transition: background-color 0.2s;
    border: 1px solid #ccc;
    border-right: none;
    background-color: #f1f1f1;
    border-radius: 1rem 1rem 0 0;
    font-weight: bold;
}
.tabs a:last-child { 
    border-right: 0.1rem solid #ccc;
}

/* Change background color of tabs on hover */
.tabs a:hover {
    background-color: #aaa;
    color: #fff;
}

/* Styling for active tab */
.tabs a.active {
    background-color: #fff;
    color: #484848;
    border-bottom: 0.1rem solid #fff;
    cursor: default;
}

/* Style the tab content */
.tabcontent {
    padding: 1rem;
    border: 1px solid #ccc;
    border-radius: 2rem;
  box-shadow: 3px 3px 6px #e1e1e1
}
</style>