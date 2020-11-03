<template>
    <div class="box">
        <h2>아이 정보 등록</h2>
        <br>
        <div class="character">
            <img
                src="@/assets/character/001.png"
                width="150px"
            >
        </div>
        <br>
        <div>
            <v-layout row>
                <v-flex justify-center>
                    <v-row>
                        <v-col
                            cols="12"
                            style="display:flex; justify-content:center"
                        >
                            <v-label><b>이름</b></v-label>
                            <v-col
                                cols="9"
                                align="center"
                            >
                                <v-text-field
                                    v-model="name"
                                    style="margin-left:12px"
                                    outlined
                                    clearable
                                    dense
                                    placeholder="아이 이름"
                                />
                            </v-col>
                        </v-col>

                        <v-col
                            cols="12"
                            style="display:flex; justify-content:center"
                        >
                            <v-label><b>출생</b></v-label>
                            <v-col
                                cols="4"
                                align="center"
                                class="birth"
                            >
                                <v-select
                                    v-model="year"
                                    :items="years"
                                    placeholder="연도"
                                    outlined
                                    dense
                                />
                            </v-col>    
                            <v-col
                                cols="2"
                                align="center"
                                class="birth"
                            >
                                <v-text-field
                                    v-model="month"
                                    placeholder="월"
                                    outlined
                                    dense
                                />
                            </v-col>
                            <v-col
                                cols="2"
                                class="birth"
                                align="center"
                            >
                                <v-text-field
                                    v-model="day"
                                    placeholder="일"
                                    outlined
                                    dense
                                />
                            </v-col>
                        </v-col>
                       
                       
                        <v-col
                            cols="12"
                            style="display:flex; justify-content:center"
                        >
                            <v-label><b>성별</b></v-label>
                            <v-col
                                cols="9"
                                align="center" 
                                style="display:flex; justify-content:center"
                            >
                                <v-radio-group
                                    v-model="gender"
                                    row
                                >
                                    <v-radio
                                        label="남"
                                        value="1"
                                    />
                                    <v-radio
                                        label="여"
                                        value="2"
                                    />
                                </v-radio-group>
                            </v-col>
                        </v-col>

                        <v-col
                            cols="12"
                            style="display:flex; justify-content:center"
                        >
                            <v-btn
                                class="error"
                                @click="cancel"
                            >
                                <b>취소</b>
                            </v-btn>
                            <v-btn
                                class="primary"
                                style="margin-left: 10px;"
                                @click="regist"
                            >
                                <b>등록</b>
                            </v-btn>
                        </v-col>
                    </v-row>
                </v-flex>
            </v-layout>
        </div>
    </div>
</template>

<script>
export default {
    name:'KidsRegist',
    data: () => ({
        name:'',
        years : ['2010','2011','2012','2013','2014','2015','2016'],
        year:'',
        month:'',
        day:'',
        gender: 0,
    }),
    methods: {
        regist(){

        },
        cancel(){

        }
    },
};
</script>

<style scoped>
    .character{
        width: 150px;
        margin: auto;
        background: peachpuff;
        border-radius: 4px;
        margin-bottom: 15px;
    }

    .col{
        padding: 0px;
    }

    .box{
        margin-top: 50px;
    }

    .theme--light.v-label{
        top:13px;
    }

    .birth{
        margin-left: 12px;
    }
    
    .v-input--selection-controls{
        margin-top: 5px;
    }

</style>