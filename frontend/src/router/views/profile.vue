<script>
import Layout from '@layouts/main.vue'
import CropImg from "@components/CropImg.vue"

export default {
  components: {Layout, CropImg},

}
</script>

<template>
  <Layout>
    <div class="h-100">
      <CropImg :src="'https://www.shutterstock.com/image-vector/water-drop-vector-illustration-isolated-260nw-409873933.jpg'" />
    </div>
  </Layout>
</template>
