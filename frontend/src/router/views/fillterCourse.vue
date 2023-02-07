<template>
  <layouts>
    <div class="container-xxl">
    <div class="row mt-5">

      <div class="col-lg-3 col-12">
        <div>
          <button class="btn btn-outline-primary fw-bold fs-4 w-100">
            <i class="bi bi-funnel"></i>
            Lọc</button>
        </div>
        <div class="fs-5 border-bottom mt-4 mb-2" >Số sao</div>
        <div class="mx-1 my-2" v-for="x in [3,4,5]" :key ="x + 'x'">
          <input type="radio" name="redio" >
          <span v-for="y in [1,2,3,4,5]" :key="y + 'y'" ><i class=" bi bi-star-fill" :class="y <= x ? 'text-warning' : 'text-light'"></i></span>
          <span class="px-2 text-muted">{{ x }} sao trở lên.</span>
        </div>
        <div class="border-bottom fs-5 mb-2">
          Giá
        </div>
        <div class="d-flex">
          <input class="form-control" placeholder="Từ" type="number">
          <span class="fw-bold fs-4 px-1">-</span>
          <input class="form-control" placeholder="Đến" type="number">
        </div>
        <span class="text-danger opacity-75 my-1" >error</span>
      </div>
      <div class="col-lg-9 col-12">
        <div class="">
          <div v-for="course,index in courses" :key="course.id" class="d-flex row border-bottom py-4" :class="index % 2 === 0 ? 'bg-white' : 'bg-light'">
            <div class="col-3">
              <img :src="getImg(course.image)" class="w-100" alt="">
            </div>
            <div class="d-flex flex-column px-2 py-1 col-9">
              <span class="text-max fs-4 fw-bold">{{course.name}}</span>
              <span class="text-max fs-6" v-html="course.describe"></span>
              <span class="text-muted fw-bold fs-6">Giảng viên: {{ course.user.name }}</span>
              <span class="text-muted">{{ course.videos.length }} Bài giảng</span>
              <span ><button class="btn btn-outline-danger" >Thêm vào giỏ</button></span>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  </layouts>
</template>


<script>
import axios from '@/node_modules/axios';
import layouts from '@layouts/main.vue';
export default{
  components:{
    layouts
  },
  data(){
    return{
      courses:[]
    }
  },
  created(){
    this.getCourse()

  },
  methods:{
    getCourse(){
      axios.get(`/api/course/get`)
      .then(res=>{
        this.courses = res.data
      })
    },
    getImg(name){
        return `${axios.defaults.baseURL}/api/image/get/${name}`
    },
  }
}

</script>

<style>

.text-max{
    display: block;
  	display: -webkit-box;
  	-webkit-line-clamp: 2;
  	-webkit-box-orient: vertical;
  	overflow: hidden;
    line-height: 25px;
  	text-overflow: ellipsis;
    cursor: pointer;
}
</style>
