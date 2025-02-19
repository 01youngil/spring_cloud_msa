<template>
    <h1>!!store component A!!</h1>
    <p>일반 변수 값: {{ count }}</p>
    <v-btn @click="incrementCount()"> count값 증가</v-btn>
    <p>Store 변수 값: {{ getStoreCount }}</p>
    <v-btn @click="incrementStoreCount()"> Store count값 증가</v-btn><br><br>

    <v-text-field label="search" v-model="message"/>
    <input type="text" v-model="message">
    <v-table><p>{{ getStoreMessage }}</p></v-table>
    <v-btn @click="updateMessage()">바꾸기</v-btn>
</template>
<script>
export default{
    data(){
        return{
            count: 0,
            message: ""
        }
    },
    computed:{
        getStoreCount(){
            return this.$store.getters.getCount;
        },
        getStoreMessage(){
            return this.$store.getters.getMessage;
        }
    },
    methods:{
        incrementCount(){
            this.count++;
        },
        incrementStoreCount(){
            //      vuex에서 actions를 호출할 때에넌 dispatch를 사용용
            this.$store.dispatch('increment')
        },
        updateMessage(){
            this.$store.dispatch('updateMessage', this.message)
        }
    }
    
}
</script>