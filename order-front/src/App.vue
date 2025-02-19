<template>
  <!-- router-view를 통해 router파일에서 설정한 url경로로 라우팅한 화면을 여기서 보여주겠다는 뜻. -->
   <!-- vuetify에서는 모든 태그의 최상단단에 v-app으로 감싸져 있어야 정상적으로 동작 -->
  <v-app>
    <HeaderComponent/>

    <v-main>
      <router-view/>
    </v-main>
    
    <FooterComponent/>
  </v-app>
</template>

<script>
  import HeaderComponent from './components/HeaderComponent.vue';
  import FooterComponent from './components/FooterComponent.vue';
  export default{
    // 화면을 import한 이후에 template에서 사용하려고 할때는 components에 나열.
    components: {
      HeaderComponent, FooterComponent
    }
  }
</script>

<style>

</style>
