<template>
    <OrderListComponent
        :isMyPage="false"
    />
</template>

<script>
import OrderListComponent from '@/components/practice/OrderListComponent.vue';

    export default{
        components:{
            OrderListComponent
        }
    }
</script>