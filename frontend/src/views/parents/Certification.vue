<template>
    <div
        v-if="!flag"
        class="login2"
    >
        <v-layout>
            <v-flex>
                <!-- <v-row>
                    <button class="btn" style="display:flex;margin-bottom:10px" @click="back">
                        <img src="@/assets/back.png" width="50px">
                    </button>
                </v-row> -->

                <v-row justify="center">
                    <h2>아이 인증 코드</h2>
                </v-row>

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
                            v-model="code[0]"
                            outlined
                            readonly
                        />
                    </v-col>
                    <v-col cols="1">
                        <v-text-field
                            ref="input-code-2"
                            v-model="code[1]"
                            outlined
                            readonly
                        />
                    </v-col>
                    <v-col cols="1">
                        <v-text-field
                            ref="input-code-3"
                            v-model="code[2]"
                            outlined
                            readonly
                        />
                    </v-col>
                    <v-col cols="1">
                        <v-text-field
                            ref="input-code-4"
                            v-model="code[3]"
                            outlined
                            readonly
                        />
                    </v-col>
                    <v-col cols="1">
                        <v-text-field
                            ref="input-code-5"
                            v-model="code[4]"
                            outlined
                            readonly
                        />
                    </v-col>
                    <v-col cols="1">
                        <v-text-field
                            ref="input-code-6"
                            v-model="code[5]"
                            outlined
                            readonly
                        />
                    </v-col>
                </v-row>
            </v-flex>
        </v-layout>
    </div>
</template>
<script>
import axios from '@/plugins/axios';
import { mapGetters } from 'vuex';
export default {
    data() {
        return {
            code : new Array(6),
            flag : true,
        };
    },
    computed: {
        ...mapGetters({
            kidId : 'auth/select',
            parentsId : 'auth/id',
        })
    },
    created() {
        axios.post('/api/certification',
            {
                kidId: this.kidId,
                parentId : this.parentsId
            })
            .then((response) => {
                console.log(typeof(response.data.data));
                const str = String(response.data.data);
                for (let index = 0; index < 6; index++) {
                    this.code[index] = str.substr(index,1);
                }
                console.log(this.code);
                this.flag=false;
            });           
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


</style>