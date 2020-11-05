<template>
    <div>
        {{ sendData.itemNo }} {{ sendData.name }} {{ sendData.price }} {{ sendData.description }}
    </div>
</template>

<script>

export default {
    name:'ItemCard',
    props:{
        sendData: {
            type:Object,
            required:true,
        }
    },
};
</script>