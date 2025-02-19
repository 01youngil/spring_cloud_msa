import ProductCreate from "@/views/ProductCreate.vue";
import ProductList from "@/views/ProductList.vue";
import ProductListManage from "@/views/ProductListManage.vue";

export const productRouter = [
    {
        path: '/product/list',
        name: 'ProductList',
        component: ProductList
    },
    {
        path: '/product/manage',
        name: 'ProductListManage',
        component: ProductListManage
    },
    {
        path: '/product/create',
        name: 'ProductCreate',
        component: ProductCreate
    },
    
]