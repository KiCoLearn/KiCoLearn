<template>
    <v-card
        max-width="400"
        class="mx-auto"
    >
        <v-container>
            <v-btn>최근 입출금기록</v-btn>
            <v-btn>레포트</v-btn>
            <li
                v-for="uselog in uselogs"
                :key="uselog.id"
            >
                <v-card class="useloglist">
                    <v-layout>
                        <v-flex xs3>
                            <v-img
                                :src="uselog.imgsrc"
                                contain
                                height="4rem"
                            />
                        </v-flex>
                        <v-layout column>
                            <v-card-title><h5>{{ uselog.title }}</h5></v-card-title>
                            <v-card-text>{{ `${uselog.price}원` }}</v-card-text>
                        </v-layout>
                    </v-layout>
                </v-card>
            </li>
            <v-card
                v-for="report in reports"
                :key="report.id"
            >
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
            <v-spacer />
        </v-container>
    </v-card>
</template>

<script lang="js">
export default {
    data() {
        return {
            uselogs: [
                {
                    id: 1,
                    title: '미션 성공',
                    price: 500,
                    imgsrc: require('@/assets/questsuc.png')
                },
                {
                    id: 2,
                    title: '깁밥',
                    price: 749,
                    imgsrc: require('@/assets/questsuc.png')
                },
                {
                    id: 3,
                    title: '용돈',
                    price: 949,
                    imgsrc: require('@/assets/questsuc.png')
                },
            ],
            reports:[
                {
                    id: 1,
                    title: '소비가 줄었어요!',
                    percent: 23,
                    imgsrc: require('@/assets/questsuc.png')
                },
                {
                    id: 2,
                    title: '소비가 줄었어요!',
                    percent: 50,
                    imgsrc: require('@/assets/questsuc.png')
                },
                {
                    id: 3,
                    title: '소비가 늘었어요!',
                    percent: 40,
                    imgsrc: require('@/assets/questsuc.png')
                },

            ],
        };
    },
        
};
</script>
