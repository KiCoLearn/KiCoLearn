<template>
    <v-container>
        {{ item.description }}
    </v-container>
</template>

<script>
export default {
    props: {
        item: {
            type: Object,
            required: true,
        }
    },
};
</script>

<style>

</style>