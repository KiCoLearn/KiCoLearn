<template>
    <v-dialog
        v-model="dialog"
        width="300"
    >
        <template v-slot:activator="{ on, attrs }">                             
            <div
                class="detail"
                v-bind="attrs"
                v-on="on"
            >
                퀘스트 : {{ target.name }}
            </div>
        </template>
        <v-card>
            <v-card-title
                class="headline"
                label="이름"
                v-text="target.name"
            />                        
            <v-card-subtitle
                class="text-left" 
                label="보상 포인트"   
                v-text="target.reward"                         
            />

            <v-card-text>
                <v-text-field 
                    class="text-left"                            
                    label="설명" 
                    v-text="target.description"   
                />
            </v-card-text>
           
      
            <v-card-text 
                class="text-left"                            
                aria-label="시작 시간" 
                v-text="formatedStartTime"   
            />

            <v-card-text 
                class="text-left"                            
                aria-label="완료 시간" 
                v-text="formatedEndTime"   
            />

            <v-card-actions>
                <v-spacer />
                   
                <v-btn
                    color="orange darken-1"
                    text
                    @click="closeDialog"
                >
                    확인
                </v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script>

export default {
    name:'KidDetailquest',
    props:{
        target: {
            type:Object,
            required:true,
        },
    },
    data: () => ({
        dialog: false,
    }),
    computed: {
        formatedStartTime(){
            if(!this.target || !this.target.startTime) return null;
            return new Date(this.target.startTime).format('yyyy년 MM월 dd일 hh시 mm분');
        },
        formatedEndTime(){
            if(!this.target || !this.target.endTime) return null;
            return new Date(this.target.endTime).format('yyyy년 MM월 dd일 hh시 mm분');
        },
    },
    methods:{
        closeDialog() {
            this.dialog = false;
            //    this.$emit('update:dialog', false);
            
        }
    }
};
</script>

<style lang="scss" scoped>
    ::v-deep .v-card__subtitle, div{
        padding-bottom: 8px !important;
        margin-top: 0 !important;
    }
</style>