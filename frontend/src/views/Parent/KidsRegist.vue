<template>
    <div class="box">
        <h2>아이 정보 등록</h2>
        <br>
        <div class="character">
            <img
                :src="photo"
                width="150px"
            >
        </div>
        <br>
        <div>
            <v-layout row>
                <v-flex justify-center>
                    <v-row style="display:flex; justify-content:center">
                        <v-form ref="form">
                            <v-col
                                cols="12"
                                style="display:flex; justify-content:center"
                            >
                                <v-label><b>이름</b></v-label>
                                <v-col
                                    cols="1"
                                />
                                <v-col
                                    cols="8"
                                    style="display:flex; justify-content:flex-start"
                                >
                                    <v-text-field
                                        ref="name"
                                        v-model="name"
                                        outlined
                                        clearable
                                        dense
                                        class="name"
                                        placeholder="아이 이름"
                                        :rules="[rules.nameRequired]"
                                    />
                                </v-col>
                            </v-col>

                            <v-col
                                cols="12"
                                style="display:flex; justify-content:center"
                            >
                                <v-label><b>출생</b></v-label>
                                <v-col
                                    cols="9"
                                    style="display:flex;justify-content:center;"
                                >
                                    <v-col
                                        cols="5"
                                        align="center"
                                        class="birth"
                                    >
                                        <v-select
                                            ref="year"
                                            v-model="year"
                                            :items="years"
                                            placeholder="연도"
                                            outlined
                                            dense
                                            :rules="[rules.yearRequired]"
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
                                            :rules="[rules.monthRequired]"
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
                                            :rules="[rules.dayRequired]"
                                        />
                                    </v-col>
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
                                    style="display:flex; justify-content:space-evenly;"
                                >
                                    <v-col
                                        cols="4"
                                        align="center"
                                        :class="{'gender2':!gender, 'gender':gender}"
                                        @click="changeGender(false)"
                                    > 
                                        <div>
                                            <img
                                                src="@/assets/male.png"
                                                width="40px"
                                            >
                                        </div>
                                    </v-col>
                          
                                    <v-col
                                        cols="4"
                                        align="center" 
                                        :class="{'gender2':gender, 'gender':!gender}"
                                        @click="changeGender(true)"
                                    > 
                                        <div>
                                            <img
                                                src="@/assets/female.png"
                                                width="40px"
                                            >
                                        </div>
                                    </v-col>
                                </v-col>
                            </v-col>
                        </v-form>

                        <v-col
                            cols="12"
                            style="display:flex; justify-content:center;margin-top:20px"
                        >
                            <button
                                class="btn"
                                @click="cancel"
                            >
                                <img
                                    src="@/assets/list.png"
                                    width="80px"
                                >
                            </button>
                            <button
                                class="btn"
                                style="margin-left: 10px;"
                                @click="regist"
                            >
                                <img
                                    src="@/assets/success.png"
                                    width="80px"
                                >
                            </button>
                        </v-col>
                    </v-row>
                </v-flex>
            </v-layout>
        </div>
    </div>
</template>

<script>
import axios from 'axios';
export default {
    name:'KidsRegist',
    data: () => ({
        name:'',
        years : ['2010','2011','2012','2013','2014','2015','2016'],
        year:'',
        month:'',
        day:'',
        gender: 0,
        photo:require('@/assets/character/047.png'),
        rules: {
            nameRequired: (value) => !!value,
            yearRequired: (value) => !!value,
            monthRequired: (value) => !!value,
            dayRequired: (value) => !!value,
            genderRequired: (value) => !!value,
        },
    }),
    methods: {
        regist(){
            if (this.$refs.form.validate()) {
                axios.post(process.env.VUE_APP_API_URL + '/api/kidsaccount/regist',
                    {
                        'birth': new Date(this.year, this.month-1, this.day),
                        'name': this.name,
                        'parentId': 1,
                        'gender' : this.gender
                    },
                    {
                        headers: {
                            'jwt-auth-token':''
                        },
                    })
                    .then(() => {
                        alert('등록 되었습니다.');
                        this.$router.push('/kidslist');
                    });
            }
        },
        cancel(){
            this.$router.push('/kidslist');
        },
        
        changeGender(flag){
            this.gender=flag;
            if(flag){
                this.photo=require('@/assets/character/001.png');
            }else{
                this.photo=require('@/assets/character/047.png');
            }
        }
    },
};
</script>

<style scoped>
    .v-text-field, .v-input--selection-controls .v-input__slot>.v-label, .v-input--selection-controls .v-radio>.v-label {
        font-size: 1.1rem !important;
    }
    

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
        padding: 20px;
        background-color: cornsilk;
    }

    .theme--light.v-label{
        top:13px;
        font-size: 1.1rem;
    }

    .birth{
        margin-left: 10px;
    }

    .name{
        max-width: 92% !important;
        margin-left: 10px;
    }
    
    .v-input--selection-controls{
        margin-top: 5px;
    }

    .btn{
        border: none;
        background: none;
    }

    .btn:focus{
        outline:none;
    }

    .btn:hover{
        cursor: pointer;
    }

    .gender2{
        display:flex;
        justify-content:center;
        padding-top:6px;
        border:1px solid rgba(0,0,0,.42);
        border-radius:4px;
        background-color: #ffdd93;
    }

    .gender{
        display:flex;
        justify-content:center;
        padding-top:6px;
        border:1px solid rgba(0,0,0,.42);
        border-radius:4px;
    }

</style>