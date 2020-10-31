<template>
    <div class="kidsmain">
        <div class="header">
            <KidsHeader />
        </div>
        <div class="point">
            <h3> 오늘 쓴거 내역</h3> 
            <h3> 이번주 쓴거 내역</h3>
            <h5> 참고: 1.용돈기입장 2.퀘스트 3.스토어 4.퀴즈 - 이건 지울거</h5>
        </div>
        <div class="menu">
            <p>
                <button
                    id="reportbtn"
                    class="report"
                    type="button"
                    @click="clickreport"
                >
                    <img
                        class="btn-report"
                        src="@/assets/report.png"
                        alt="용돈 기입장"
                    > 
                </button>
            &nbsp;
            &nbsp;
                <button
                    id="questbtn"
                    class="quest"
                    type="button"
                    @click="clickquest"
                >
                    <img
                        class="btn-quest"
                        src="@/assets/report.png"
                        alt="용돈 기입장"
                    >
                </button>
            </p>
            <p>
                <button
                    id="storebtn"
                    class="store"
                    type="button"
                    @click="clickstore"
                >
                    <img
                        class="btn-store"
                        src="@/assets/report.png"
                        alt="용돈 기입장"
                    >
                </button>
              &nbsp;
               &nbsp;
                <button
                    id="quizbtn"
                    class="quiz"
                    type="button"
                    @click="clickquiz"
                >
                    <img
                        class="btn-quiz"
                        src="@/assets/report.png"
                        alt="용돈 기입장"
                    >
                </button>
            </p>
        </div>
    </div>
</template>
<script>
import KidsHeader from '@/components/Kids/KidsHeader.vue';
export default {
    components:{
        KidsHeader
    },
    methods: {
        clickreport(){
            this.$router.push({name:'Report'});
        },
        clickquest(){
            this.$router.push({name:'Quest'});
        },
        clickstore(){
            this.$router.push({name:'Store'});
        },
        clickquiz(){
            this.$router.push({name:'Quiz'});
        },
    },
    
};
</script>

<style scoped>
.point{
    height: 50 rem;
    margin-top: 16rem;
}
.menu{
    height: 80 rem;
    margin-top: 2rem;
}
.report,.quest,.store,.quiz{
    width: 10rem;
    height: 10rem;
    border-radius: 20%;
    background-color: #d5e4a1;
}
.btn-report,.btn-quest,.btn-store,.btn-quiz{
    height: 8rem;
    width: 8rem;
}
</style>>
