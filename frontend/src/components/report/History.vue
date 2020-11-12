<template>
    <v-container>
        <v-card class="historylist">
            <v-layout>
                <v-flex xs3>
                    <v-img
                        :src="history.imgsrc"
                        contain
                        height="4rem"
                    />
                </v-flex>
                <v-layout column>
                    <v-card-title><h5>{{ history.contents }}</h5></v-card-title>
                    <v-card-text>{{ `${history.amount}원` }}</v-card-text>
                </v-layout>
            </v-layout>
        </v-card>
        <br>
    </v-container>
</template>

<script lang="js">
import axios from 'axios';

export default {
    name:'History',
    props:{
        history: {
            type:Object,
            required:true,
        }
    },
    data: () => ({

    }),
    created() {
        axios.get(process.env.VUE_APP_API_URL + '/api/money/activity/'+this.kidId)
            .then((res) => {
                this.history = res.data.data;
                console.log(this.history);
            });
    },
        
};
</script>
