import ConditionalAxiosComponent from "@/components/practice/ConditionalAxiosComponent.vue";
import HookComponent from "@/components/practice/HookComponent.vue";
import ModelingComponent from "@/components/practice/ModelingComponent.vue";
import StoreTestComponent from "@/components/practice/StoreTestComponent.vue";
import VuetifyComponent from "@/components/practice/VuetifyComponent.vue";
export const practiceRouter = [
    {
        path: '/practice/vuetify',
        name: 'VuetifyComponent',
        component: VuetifyComponent
    },
    {
        path: '/practice/modeling',
        name: 'ModelingComponent',
        component: ModelingComponent
    },
    {
        path: '/practice/hooks',
        name: 'HookComponent',
        component: HookComponent
    },
    {
        path: '/practice/conditional',
        name: 'ContionalAxiosComponent',
        component: ConditionalAxiosComponent
    },
    {
        path: '/practice/storetest',
        name: 'StoreTestComponent',
        component: StoreTestComponent
    }
]