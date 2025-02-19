<template>
    <v-container>
        <v-row>
            <v-col>
                <v-card>
                    <v-card-title class="text-center text-h5">
                        회원목록
                    </v-card-title>
                    <v-card-text>
                        <v-table>
                            <thead>
                                <tr>
                                    <th>ID</th><th>이름</th><th>email</th><th>주문수량</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr v-for="m in memberList" :key="m.id">
                                    <td>{{ m.id }}</td>
                                    <td>{{ m.name }}</td>
                                    <td>{{ m.email }}</td>
                                    <td>{{ m.orderCount }}</td>
                                </tr>
                            </tbody>
                        </v-table>
                    </v-card-text>
                </v-card>
            </v-col>
        </v-row>
    </v-container>
</template>
<script>
import axios from 'axios';

export default {
    data(){
        return{
            memberList:[]
        }
    },
    async created(){
        try{
            const response = await axios.get(`${process.env.VUE_APP_API_BASE_URL}/member-service/member/list`)
            this.memberList = response.data
        }catch(e){
            console.log(e)
        }
        
    }
}
</script>