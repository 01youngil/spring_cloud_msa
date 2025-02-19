<template>
    <!-- 문자열로 넘길경우에는 '변수명 =' 형태, 그외에는 ':변수명' 형태로 전달 -->
    <ProductListComponent
        :isAdmin = "true"
        pageTitle = "상품관리"
    />
</template>
<script>
import ProductListComponent from '@/components/practice/ProductListComponent.vue';

export default{
    components:{
        ProductListComponent
    }
}

</script>