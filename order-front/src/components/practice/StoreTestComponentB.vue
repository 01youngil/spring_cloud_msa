<template>
    <h1>!!store component B!!</h1>
    <p>일반 변수 값: {{ count }}</p>
    <p>store 변수 값: {{ getStoreCount }}</p><br><br>
    <v-table>store 변수값 message: <p>{{ getStoreMessage }}</p></v-table>

</template>
<script>
export default{
    data(){
        return{
            count: 0,
            message:""
        }
    },
    computed:{
        getStoreCount(){
            return this.$store.getters.getCount;
        },
        getStoreMessage(){
            return this.$store.getters.getMessage;
        }
    }

    
}
</script>