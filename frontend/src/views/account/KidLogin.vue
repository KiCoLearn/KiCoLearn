<template>
    <div class="login2">
        <v-layout>
            <v-flex>
                <v-row justify="center"> 
                    <img
                        src="@/assets/children.png"
                        width="150px"
                    >
                </v-row>

                <v-row
                    justify="center"
                    class="codebox"
                >
                    <v-col cols="1">
                        <v-text-field
                            ref="input-code-1"
                            v-model="code1"
                            type="number"
                            min="0"
                            max="10"
                            outlined
                            @keyup="nextField(1)"
                        />
                    </v-col>
                    <v-col cols="1">
                        <v-text-field
                            ref="input-code-2"
                            v-model="code2"
                            type="number"
                            outlined
                            @keyup="nextField(2)"
                        />
                    </v-col>
                    <v-col cols="1">
                        <v-text-field
                            ref="input-code-3"
                            v-model="code3"
                            type="number"
                            outlined
                            @keyup="nextField(3)"
                        />
                    </v-col>
                    <v-col cols="1">
                        <v-text-field
                            ref="input-code-4"
                            v-model="code4"
                            type="number"
                            outlined
                            @keyup="nextField(4)"
                        />
                    </v-col>
                    <v-col cols="1">
                        <v-text-field
                            ref="input-code-5"
                            v-model="code5"
                            type="number"
                            outlined
                            @keyup="nextField(5)"
                        />
                    </v-col>
                    <v-col cols="1">
                        <v-text-field
                            ref="input-code-6"
                            v-model="code6"
                            type="number"
                            outlined
                            @keyup="nextField(6)"
                        />
                    </v-col>
                </v-row>

                <v-row justify="center">
                    <v-btn
                        text
                        class="items"
                        style="font-size:1rem"
                        @click="kidlogin"
                    >
                        로그인하기
                    </v-btn>
                </v-row>
            </v-flex>
        </v-layout>
    </div>
</template>
<script>
import axios from '@/plugins/axios';
import { mapMutations } from 'vuex';
export default {
    data() {
        return {
            code1:'',
            code2:'',
            code3:'',
            code4:'',
            code5:'',
            code6:'',
        };
    },
    mounted() {
        this.$el.querySelectorAll('.v-input__control input').forEach(e => {
            e.setAttribute('autocomplete', 'off');
        });
    },
    methods: {
        ...mapMutations(
            {
                setToken: 'auth/SET_TOKEN',
                login: 'auth/LOGIN'
            }
        ),
        kidlogin(){
            const code = ''+this.code1+this.code2+this.code3+this.code4+this.code5+this.code6;
            axios.post('/api/certification/login',
                {
                    certificationNo: code
                })
                .then((response) => {
                    //console.log(response);
                    this.setToken({
                        accessToken: response.headers['authorization'],
                        refreshToken: null,
                        provider: response.headers['provider'],
                    });
                    this.login({
                        id: response.data.data.kidId,
                        role: 'kid',
                    });
                
                    this.$router.push({name: 'KidMain'});
                })
                .catch((err) => {
                    console.log(err.response.data.msg);
                    alert('인증번호가 잘못 되었거나 인증시간이 만료되었습니다.');
                });            
        },
        nextField(id){
            
            if(event.target.value<0 || event.target.value>=10 || event.target.value ===''){
                this.$refs[`input-code-${id}`].reset();
            }else{
                if(id<=5){
                    this.$refs[`input-code-${id+1}`].focus();
                }
            }

        }
    },
    
};
</script>
<style scoped>

.login2{
    height: 100%;
    display: flex;
    align-items: center;
}
::v-deep .v-text-field.v-text-field--enclosed .v-text-field__details{
    display: none;
}

.row{
    margin-bottom: 10px;
}

.col{
    padding: 0;
    margin: 3px;
    max-width: 60px !important;
}

::v-deep .v-text-field{
    width: 40px;
    
}

::v-deep .v-input__control input{
    text-align: center;
    font-size: 1.2rem;
}

::v-deep .v-input__slot{
    background: white !important;
}

.codebox{
    border-top: 2px solid white;
    border-bottom: 2px solid white;
    padding-top: 8px;
    background:rgb(196, 232, 255) ;
}

::v-deep input::-webkit-outer-spin-button,
::v-deep input::-webkit-inner-spin-button {
    -webkit-appearance: none;
    margin: 0;
}
</style>