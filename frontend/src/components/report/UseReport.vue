<template>
    <v-container>
        <ul
            v-for="report in reports"
            :key="report.id"
        >
            <v-card class="reportlist">
                <v-layout>
                    <v-flex xs3>
                        <v-img
                            :src="report.imgsrc"
                            contain
                            height="4rem"
                        />
                    </v-flex>
                    <v-layout column>
                        <v-card-title><h5>{{ report.title }}</h5></v-card-title>
                        <v-card-text>{{ `${report.percent}%` }}</v-card-text>
                    </v-layout>
                </v-layout>
            </v-card>
            <br>
        </ul>
    </v-container>
</template>

<script lang="js">
export default {
    data() {
        return {
            reports:[
                {
                    id: 1,
                    title: '소비가 줄었어요!',
                    percent: 23,
                    imgsrc: require('@/assets/great.png')
                },
                {
                    id: 2,
                    title: '소비가 줄었어요!',
                    percent: 50,
                    imgsrc: require('@/assets/great.png')
                },
                {
                    id: 3,
                    title: '소비가 늘었어요!',
                    percent: 40,
                    imgsrc: require('@/assets/great.png')
                },

            ],
           
        };
    },
        
};
</script>
