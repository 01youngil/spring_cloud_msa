<template>
    <v-container>
        <v-row justify="center">
            <v-col cols="12" sm="4" md="6">
                <v-card>
                    <v-card-title class="text-h5 text-center">
                        상품등록
                    </v-card-title>
                    <v-card-text>
                        <v-form @keydown.enter="register()">
                            <v-text-field
                            label="상품명"
                            v-model="name"
                            required                        
                            />
                            <v-text-field
                            label="카테고리"
                            v-model="category"
                            required                        
                            />
                            <v-text-field
                            label="가격"
                            v-model="price"
                            required 
                            />
                            <v-text-field
                            label="재고수량"
                            v-model="stockQuantity"
                            required 
                            />
                            <v-file-input
                            label="상품이미지"
                            accept="image/**"
                            @change="fileUpload"
                            multiple
                            />
                            <v-row>
                                <v-col cols="12">
                                    <v-btn color="red" block @click="register()">등록</v-btn>
                                </v-col>
                            </v-row>               
                        </v-form>
                    </v-card-text>
                </v-card>
            </v-col>
        </v-row>
    </v-container>
</template>
<script>
import axios from 'axios';

export default{
data(){
    return{
        name:"",
        category:"",
        price:null,
        stockQuantity:null,
        productImage:null
    }
},methods:{
    async register(){
        try{
            let registerData = new FormData();
        registerData.append("name", this.name);
        registerData.append("category", this.category);
        registerData.append("price", this.price);
        registerData.append("stockQuantity", this.stockQuantity);
        registerData.append("productImage", this.productImage);
        
        await axios.post(`${process.env.VUE_APP_API_BASE_URL}/product/create`, registerData)
        this.$router.push("/product/manage")
        }catch(e){
            alert("상품등록에 실패했습니다.")
        }
        
        
    },
    fileUpload(event){
        this.productImage = event.target.files[0];

    }
}


}
</script>