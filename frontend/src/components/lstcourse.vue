<template>
  <div>
    <div class="row">
      <div v-for="cr in listCourse" :key="cr.id"  class="col-lg-3 col-md-4 col-sm-6 col-12 my-3">
        <div class=" p-0 d-flex flex-column">
          <div class="w-100" >
            <img class="w-100 img" :src="getImg(cr.image)" alt="">
          </div>
          <div class="m-1 d-flex flex-column ">
            <span class="fw-bold">{{ cr.name }}</span>
            <span class="text-muted-a">{{ cr.user.username }}</span>
            <span class="text-muted-a">12.5h<span class="px-2">/</span>16 video</span>
            <span class="text-bold">
              {{cr.price | formatNumber}}
            </span>
          </div>
          <div class="mx-1 mb-3 mt-auto d-flex">
            <span class="me-auto">
              <span class=" me-1 fw-bold">4.8</span>
              <i class="fa fa-star text-warning"></i>
              <i class="fa fa-star text-warning"></i>
              <i class="fa fa-star text-warning"></i>
              <i class="fa fa-star text-warning"></i>
              <i class="fa fa-star text-warning"></i>
              <span>(12.000)</span>
            </span>
            <span class="">
              <i class="bi bi-heart"></i>
            </span>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>

<style >

.bg-img{
    background-position: center center;
    background-repeat: no-repeat;
    background-size: cover;
}
</style>

<script>
import axios from '@/node_modules/axios'
  export default {
    data() {
      return {
        slide: 0,
        sliding: null
      }
    },
    props:{
      listCourse:Array
    },
    created(){
      console.log(this.listCourse);
    },
    methods: {
      onSlideStart(slide) {
        this.sliding = true
      },
      onSlideEnd(slide) {
        this.sliding = false
      },
      getImg(name){
        return `${axios.defaults.baseURL}/api/image/get/${name}`
      }
    },
    filters:{
      formatNumber:function(value){
        return new Intl.NumberFormat().format(value)+"đ"
      }
    },
  }
</script>
