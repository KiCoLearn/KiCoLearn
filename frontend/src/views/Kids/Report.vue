<template>
    <div class="report">
        <div class="header">
            <KidsHeader />
        </div>
        <div class="mainreport">
            <button
                id="btn"
                class="points"
            >
                이거는 포인트 확인
            </button>
            <h3>여기는레포트</h3>
            <h3>여기는레포트</h3>
            <h3>여기는레포트</h3>
            <h3>여기는레포트</h3>
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
        
    },
    
};
</script>

<style scoped>
.mainreport{
    height: 80 rem;
    margin-top: 20rem;
}
</style>