<template>
    <div class="box">
        <h2>아이 정보 등록</h2>
        <v-layout justify-center>
            <div class="character">
                <img
                    :src="photo"
                    width="150px"
                    style="margin-top:7px"
                >
            </div>

            <v-dialog
                v-model="dialog"
                scrollable
                width="300px"
            >
                <template v-slot:activator="{ on, attrs }">
                    <button
                        v-bind="attrs"
                        class="btn"
                        v-on="on"
                    >
                        <img
                            src="@/assets/gallery.png"
                            width="40px"
                            height="40px"
                            class="profile"
                        >
                    </button>
                </template>
                <v-card>
                    <v-card-title><b>프로필 캐릭터</b></v-card-title>
                    <v-divider />
                    <v-card-text style="height: 300px; padding:15px">     
                        <img
                            v-for="(item,idx) in items"
                            :key="idx"
                            :src="getPhotoPath(idx+1)"
                            width="80px"
                            :class="{'active': isActive(idx+1)}"
                            style="margin:2px; padding:3px"
                            @click="pick=idx+1"
                        >
                    </v-card-text>
                    <v-divider />
                    <v-card-actions>
                        <v-spacer />
                        <v-btn
                            color="blue darken-1"
                            text
                            @click="changeProfile()"
                        >
                            <b>결정</b>
                        </v-btn>
                    </v-card-actions>
                </v-card>
            </v-dialog> 
        </v-layout>
        <div>
            <v-layout
                row
            >
                <v-flex justify-center>
                    <v-row style="display:flex; justify-content:center">
                        <v-form 
                            ref="form"
                            v-model="valid"
                            lazy-validation
                        >
                            <v-col
                                cols="12"
                                style="display:flex; justify-content:center"
                            >
                                <label><b>이름</b></label>
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
                                        :rules="[rules.required]"
                                    />
                                </v-col>
                            </v-col>

                            <v-col
                                cols="12"
                                style="display:flex; justify-content:center"
                            >
                                <label><b>출생</b></label>
                                <v-col
                                    cols="9"
                                    style="display:flex;justify-content:center;"
                                >
                                    <v-col
                                        cols="5"
                                       
                                        class="birth"
                                    >
                                        <v-select
                                            ref="year"
                                            v-model="year"
                                            :items="years"
                                            placeholder="연도"
                                            outlined
                                            dense
                                            :rules="[rules.required]"
                                        />
                                    </v-col>    
                                    <v-col
                                        cols="2"
                                     
                                        class="birth"
                                    >
                                        <v-text-field
                                            v-model="month"
                                            placeholder="월"
                                            outlined
                                            dense
                                            :rules="[rules.required, rules.month]"
                                        />
                                    </v-col>
                                    <v-col
                                        cols="2"
                                        class="birth"
                                    >
                                        <v-text-field
                                            v-model="day"
                                            placeholder="일"
                                            outlined
                                            dense
                                            :rules="[rules.required, rules.day]"
                                        />
                                    </v-col>
                                </v-col>
                            </v-col>
                       
                            <v-col
                                cols="12"
                                style="display:flex; justify-content:center"
                            >
                                <label class="label1"><b>성별</b></label>
                                <v-col
                                    cols="9"
                                >
                                    <v-radio-group
                                        v-model="gender"
                                        :rules="[rules.genderRequired]"
                                        row
                                        style="display:flex;justify-content:space-between;margin-left:67px"
                                    >
                                        <v-radio
                                            :value="0"
                                        >
                                            <template slot="label">
                                                <img
                                                    src="@/assets/male.png"
                                                    width="55px"
                                                    :class="{'gender':true, 'gender_active': gender===0}"
                                                > 
                                            </template>
                                        </v-radio>
                                      
                                          
                                        <v-radio                                            
                                            :value="1"
                                        >
                                            <template slot="label">
                                                <img
                                                    src="@/assets/female.png"
                                                    width="55px"
                                                    :class="{'gender':true, 'gender_active': gender===1}"
                                                > 
                                            </template>
                                        </v-radio>
                                    </v-radio-group>
                                </v-col>
                            </v-col>
                        </v-form>

                        <v-col
                            cols="12"
                            style="display:flex; justify-content:center;margin-left:25px"
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
        valid:true,
        dialog:false,
        name:'',
        years:['2010','2011','2012','2013','2014','2015','2016'],
        year:'',
        month:'',
        day:'',
        gender:-1,
        photo:require('@/assets/character/001.png'),
        pick:1,
        items:48,
        noGender:false,
        rules:{
            required: (value) => !!value,
            month : (value) => (value>=1 && value<=12),
            day : (value) => (value>=1 && value<=31),
            genderRequired: (value) => (value===0 || value===1),
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
                        'gender' : this.gender,
                        'characterIdx': this.pick
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

        changeProfile(){
            this.dialog = false;
            this.photo = this.getPhotoPath(this.pick);
        },

        getPhotoPath(idx){
            if(idx<10) return require('@/assets/character/00'+idx+'.png');
            return require('@/assets/character/0'+idx+'.png');
        },
        
        isActive(idx){
            if(this.pick==idx) return true;
            return false;
        }
    },
};
</script>

<style scoped>
    ::v-deep .v-text-field.v-text-field--enclosed .v-text-field__details {
        margin: 0;
    }

    .v-text-field {
        font-size: 1.1rem !important;
    }

    .character{
        width: 150px;
        background: peachpuff;
        border-radius: 4px;
        margin-top:20px;
        margin-bottom:25px;
        margin-left: 45px;
    }

    .profile{
        margin-top: 143px;
        margin-left: 5px;
    }

    .active{
        border-radius: 4px;
        background: peachpuff;
    }

    .col{
        padding: 0;
    }

    .box{
        margin: auto;
        width: 342px;
        margin-top: 50px;
        padding: 20px 20px 5px 20px;
        background-color: white;
        border-radius: 4px;
       
    }

    label{
        position: relative;
        top:7px;
        font-size: 1.1rem;
    }

    .birth{
        margin-left: 10px;
    }

    .name{
        max-width: 93% !important;
        margin-left: 10px;
    }
    
    .v-input--selection-controls{
        margin-top: 0;
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

    .gender_active{
        background-color: #ffdd93;
    }

    .gender{
        display:flex;
        justify-content:center;
        padding:6px;
        border:1px solid rgba(0,0,0,.42);
        border-radius:4px;
    }

    ::v-deep .v-input--selection-controls__input{
        display: none;
    }

    .label1{
        top:20px;
    }

    ::v-deep .error--text > .gender{
        border:2px solid #ff5252;
    }
</style>