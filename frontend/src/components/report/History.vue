<template>
    <v-container>
        <ul
            v-for="history in histories"
            :key="history.id"
        >
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
                        <v-card-title><h5>{{ history.title }}</h5></v-card-title>
                        <v-card-text>{{ `${history.price}원` }}</v-card-text>
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
            histories: [
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
           
        };
    },
        
};
</script>
