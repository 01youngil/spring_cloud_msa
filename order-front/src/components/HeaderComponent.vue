<template>
    <v-app-bar app dark>
        <v-container>
            <v-row>
                <v-col class="d-flex justify-start">
                    <div v-if="userRole==='ADMIN'">
                        <v-btn :to="{path:'/member/list'}">회원관리</v-btn>
                        <v-btn :to="{path:'/product/manage'}">상품관리</v-btn>
                        <v-btn href="/order/list">실시간주문건수 {{ liveQuantity }}</v-btn>
                    </div>
                </v-col>
                <v-col class="text-center">
                    <v-btn :to="{path:'/'}">java shop</v-btn>
                </v-col>
                <v-col class="d-flex justify-end">
                    <v-btn v-if="isLogin" :to="{path:'/order/cart'}">장바구니 {{ getTotalQuantity }}</v-btn>
                    <v-btn :to="{path:'/product/list'}">상품목록</v-btn>
                    <v-btn v-if="isLogin" :to="{path:'/member/mypage'}">MyPage</v-btn>
                    <v-btn v-if="!isLogin" :to="{path:'/member/create'}">회원가입</v-btn>
                    <v-btn v-if="!isLogin" :to="{path:'/member/login'}">로그인</v-btn>
                    <v-btn v-if="isLogin" @click="doLogout()">로그아웃</v-btn>
                </v-col>
            </v-row>
        </v-container>
    </v-app-bar>
</template>

<script>
import axios from 'axios';
import { EventSourcePolyfill } from 'event-source-polyfill';
import { jwtDecode } from 'jwt-decode';

    export default{
        data(){
            return{
                userRole:null,
                isLogin:false,
                liveQuantity: 0,
            }
        },
        computed:{
            getTotalQuantity(){
                return this.$store.getters.getTotalQuantity
            }
        },
        created(){
            const token = localStorage.getItem('token')
            if(token){
                const payload = jwtDecode(token)
                this.userRole = payload.role
                this.isLogin = true
            }
            const options = {
            heartbeatTimeout: 600000,  // 600초 동안 이벤트가 없으면 연결을 재시도
            headers: {Authorization: `Bearer ${token}`}  // 쿠키와 인증 정보도 포함
            };
            if(this.userRole === 'ADMIN'){
                // sse 연결요청을 위해 event-source-polyfill 라이브러리 사용
                let sse = new EventSourcePolyfill(`${process.env.VUE_APP_API_BASE_URL}/subscribe`, options)
                sse.addEventListener('connect', (event)=>{
                    console.log(event)
                })
                sse.addEventListener('ordered', (event)=>{
                    console.log(event)
                    this.liveQuantity++
                })
            }
            
        },
        methods:{
            async doLogout(){
                await axios.get(`${process.env.VUE_APP_API_BASE_URL}/unsubscribe`)
                localStorage.clear()
                window.location.href="/"
            }
        }

    }
</script>