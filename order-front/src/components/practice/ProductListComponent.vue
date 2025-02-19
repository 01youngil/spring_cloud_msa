<template>
    <v-container>
        <v-row class="d-flex justify-content-between mt-5">
            <!-- 검색용 v-col -->
            <v-col>
                <v-form>
                    <v-row>
                        <v-col cols= "auto">
                            <v-select
                            v-model="searchType"
                            :items="searchOptions"
                            item-title="text"
                            item-value="value"
                            />
                        </v-col>
                        <v-col>
                            <v-text-field
                            v-model="searchValue" label="search"
                            />
                        </v-col>
                        <v-col cols= "auto">
                            <v-btn @click="searchProducts()">검색</v-btn>
                        </v-col>
                    </v-row>
                </v-form>
            </v-col>
            <v-col cols="auto" v-if="!isAdmin">
                <v-btn @click="addCart()" color="secondary" class="mr-2">장바구니</v-btn>
                <v-btn @click="createOrder()" color="success">주문하기</v-btn>
                
            </v-col>
            <v-col cols="auto" v-if="isAdmin">
                <v-btn :to="{path:'/product/create'}">상품등록</v-btn>
            </v-col>
        </v-row>
        <v-row>
            <v-col>
                <v-card>
                    <v-card-title class="text-center text-h5">
                        {{ pageTitle }}
                    </v-card-title>
                    <v-card-text>
                        <v-table>
                            <thead>
                                <tr>
                                    <th>제품사진</th>
                                    <th>제품명</th>
                                    <th>가격</th>
                                    <th>재고수량</th>
                                    <th v-if="!isAdmin">주문수량</th>
                                    <th v-if="!isAdmin">주문선택</th>
                                    <th v-if="isAdmin">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr v-for="product in productList" :key="product.id">
                                    <td>
                                        <v-img :src="product.imagePath" style="height: 100px; width: auto;"></v-img>
                                    </td>
                                    <td>{{ product.name }}</td>
                                    <td>{{ product.price }}</td>
                                    <td>{{ product.stockQuantity }}</td>
                                    <td v-if="!isAdmin">
                                        <v-text-field
                                        v-model.number="product.productCount"
                                        type="number" 
                                        style="width:70px"
                                        min="1"
                                        />
                                    </td>
                                    <td class="text-center" v-if="!isAdmin">
                                        <input type="checkbox" v-model="selected[product.id]">
                                    </td>
                                    <td v-if="isAdmin">
                                        <v-btn color="secondary">
                                            삭제
                                        </v-btn>
                                    </td>
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


export default{
    props:['isAdmin', 'pageTitle'],
    data(){
        // 예시: {1:true, 2:false, 3:true}
        return {
            selected: {},
            productList:[],
            pageSize: 7,
            currentPage: 0,
            isLoading: false,
            isLastPage: false,

            searchType:"optional",
            searchValue:"",
            searchOptions:[{text:"--선택--", value:"optional"},{text:"상품명", value:"name"},{text:"카테고리", value:"category"}]
        }
    },
    async created(){
        this.loadData();
        window.addEventListener('scroll', this.scrollPagenation)
    },
    methods: {

        searchProducts(){
            this.productList = [];
            this.currentPage = 0;
            this.isLastPage = false;
            this.isLoading = false;
            this.loadData();
        },
        async loadData(){
            try{
                if(this.isLoading || this.isLastPage) return;
                this.isLoading = true;
                //      params라는 키워드를 사용하면 아래 데이터가 ?size=xxx@page=yyy 형태의 파라미터 형식으로 전송
                //      
                let params ={
                    size: this.pageSize,
                    page: this.currentPage
                    
                }
                if(this.searchType==='name'){
                    params.productName = this.searchValue
                }else if(this.searchType==='category'){
                    params.category = this.searchValue
                }
                const response = await axios.get(`${process.env.VUE_APP_API_BASE_URL}/product/list`,{params})
                const additionalData= response.data.content.map(p => ({...p, productCount:0}));
                if(additionalData.length == 0){
                    this.isLastPage = true;
                    return;
                }
                this.productList = [...this.productList, ...additionalData]
                this.currentPage++;
                this.isLoading = false;
            }catch(e){
                console.log(e);
        }

        },
        scrollPagenation(){
            // (현재 화면 높이 + 스크롤로 이동한 거리) > (전체화면 - n(내가 원하는 길이)) 가 성립되면 추가 데이터 로드
            const isBottom = window.innerHeight + window.scrollY >= document.body.offsetHeight - 200;
            if(isBottom && !this.isLoading && !this.isLastPage){
                this.loadData();
            }
        },
        addCart(){
            const orderProductList = Object.keys(this.selected).filter(key=>this.selected[key]===true)
                                        .map(key =>{
                                            const product = this.productList.filter(p=>p.productCount>0).find(p=>p.id==key)
                                            if(product){
                                                return {productId:product.id, name:product.name, productCount: product.productCount}
                                            }else{
                                                return null;
                                            }

                                        }).filter(product => product != null);
            orderProductList.forEach(product => this.$store.dispatch('addCart',product))
        },
        async createOrder(){
            // selected된 product만 filtering
            // [{productId:1, productCount:3},{productId:2, productCount:2}, ... ]
            const orderProductList = Object.keys(this.selected).filter(key=>this.selected[key]===true)
                                        .map(key =>{
                                            const product = this.productList.filter(p=>p.productCount>0).find(p=>p.id==key)
                                            if(product){
                                                return {productId:product.id, productCount: product.productCount}
                                            }else{
                                                return null;
                                            }

                                        }).filter(product => product != null)
                                        ;
            // console.log(orderProductList)
            if(orderProductList.length<1){
                alert("주문하신 물건이 없습니다람쥐")
                return;
            }
            const totalCount = orderProductList.reduce((sum, p) => sum+p.productCount, 0)
            const yesOrNo = confirm(`${totalCount} 개의 상품을 주문하시겠습니까마귀`)
            if(!yesOrNo){
                console.log("주문이 취소됐습니다.")
                return;
            }
            try{
                await axios.post(`${process.env.VUE_APP_API_BASE_URL}/ordering/create`, orderProductList);
                alert("주문이 정상적으로 진행됐습니다")
                window.location.reload();
            }catch(e){
                console.log(e);
                alert("주문이 실패했습니다.")
            }
        }
        
    }
}
</script>