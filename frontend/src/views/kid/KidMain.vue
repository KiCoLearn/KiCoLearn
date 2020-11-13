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
                    >
                        <img
                            src="@/assets/gallery.png"
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
                    justify="end"
                    class="nav"
                    style="margin-bottom:0"
                >
                    <button
                        rounded
                        class="head"
                    >
                        <b>로그아웃</b>
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
    name: 'KidMain',
    components: {
        AnimatedNumber,
    },
    data() {
        return {
            kid: Object,
            quest : new Array(),
        };
    },

    computed: {
        ...mapGetters({
            kidId : 'auth/id',
        })
    },
    created() {
        axios.get('/api/kidsaccount/detail/'+this.kidId)
            .then((res) => {
                this.kid= res.data.data;
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