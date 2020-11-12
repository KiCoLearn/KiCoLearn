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
    min-width: 220px;
    margin: 2.5rem auto;
    font-family: Arial, Helvetica, sans-serif;
    color: rgb(250, 114, 104);
}
.tabs {
    overflow: hidden;
    margin-bottom: 3px; 
}
.tabs ul {
    list-style-type: none;
}
.tabs a{
    float: initial;
    cursor: pointer;
    padding: 3px 3px;
    margin: 2px;
    transition: background-color 0.2s;
    border: 1px solid #ccc;
    border-right: none;
    background-color: #f5edd4;
    border-radius: 1rem 1rem 0 0;
    font-weight: bold;
}
.tabs a:last-child { 
    border-right: 0.1rem solid rgb(126, 103, 103);
}

/* Change background color of tabs on hover */
.tabs a:hover {
    background-color: #aaa;
    color: #fff;
}

/* Styling for active tab */
.tabs a.active {
    background-color: rgb(252, 167, 70);
    color: #fdfdfd;
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