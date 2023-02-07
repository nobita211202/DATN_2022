
<template>
  <div>
    <Main>
      <div class="bg-dark py-5">
        <div class="container-xxl">
          <span class="fs-1 text-white ">Khóa học của tôi</span>
        </div>
      </div>
      <div class="container-xxl">

        <div class="row">
          <b-overlay variant="light" :show="loading">
            <div :class="loading ? 'h800px' : ''">
              <div v-for="cr,index in courses" :key="index"  class="  col-lg-3 col-md-4 col-sm-6 col-12 my-3">
              <div class=" p-0 d-flex flex-column position-relative block-hover h-100">
                <div class="w-100" >
                  <img class="w-100 img" :src="getImg(cr.image)" alt="">
                </div>
                <div class="m-1 d-flex flex-column ">
                  <span class="fw-bold"><a :class="classtext" :href="`/course/${cr.id}`">{{ cr.name }}</a></span>
                  <span class="opacity-75">{{ cr.user.username }}</span>
                  <span class="opacity-75">{{ cr.videos.length }} video</span>
                  <span class="text-bold">
                    {{cr.price | formatNumber}}
                  </span>
                </div>
                <!-- <div class="mx-1 mb-3 mt-auto d-flex">
                  <span class="me-auto">
                    <span class=" me-1 fw-bold">4.8</span>
                    <i class="fa fa-star text-warning"></i>
                    <i class="fa fa-star text-warning"></i>
                    <i class="fa fa-star text-warning"></i>
                    <i class="fa fa-star text-warning"></i>
                    <i class="fa fa-star text-warning"></i>
                    <span>(12.000)</span>
                  </span>

                </div> -->
                <b-overlay
                  :show="false"
                  rounded="sm"
                >
                  <a :href="`/video/${cr.id}`"><b-button class=" w-100"  variant="outline-dark">Vào học</b-button></a>
                </b-overlay>
              </div>
            </div>
            </div>
            <div class="d-flex h800px" v-if="courses.length === 0">
              <div class="text-center opacity-50 m-auto">
                <span class="fs-1 text-muted ">
                  <svg xmlns="http://www.w3.org/2000/svg" height="130" fill="currentColor" class="bi bi-cart3" viewBox="0 0 16 16">
                    <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .49.598l-1 5a.5.5 0 0 1-.465.401l-9.397.472L4.415 11H13a.5.5 0 0 1 0 1H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l.84 4.479 9.144-.459L13.89 4H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
                  </svg>
                </span>
                <p>Bạn chưa mua khóa học nào </p>
                <p>Đi tới <a href="/"> danh sách khóa học</a></p>
              </div>
            </div>
          </b-overlay>

        </div>
      </div>
    </Main>

  </div>
</template>

<script>
import axios from '@/node_modules/axios';
import Main from '@layouts/main.vue'
import store from '@/src/state/store';
const user = Object.assign({},store.state.auth.currentUser)
export default {
  components:{
    Main,
  },
  data(){
    return{
      debounceSearch:{},
      txtSearch:"",
      courses:[],
      loading:false

    }
  },
  created(){
    this.getMyCourse()
  },
  methods:{
    getMyCourse(){
      this.loading = true
      axios.get(`/api/course/get/myCourse/${user.id}`)
      .then(res=>{
        this.courses = res.data
        console.log(res.data);
      })
      .finally(()=>{
        this.loading = false
      })
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
  watch:{
    txtSearch(){
      this.overlayGY = true
      clearTimeout(this.debounceSearch)
      this.debounceSearch = setTimeout(
        ()=>{
          this.lstCourseGY = []
          axios.get(`/api/course/search/${this.txtSearch}`)
          .then((res)=>{
            this.lstCourseGY = res.data
            console.log(this.lstCourseGY);
            this.overlayGY= false
          })
          .catch( er => {
            console.log(er);
            this.overlayGY= false})

        },1000
      )
    }
  }
}
</script>

<style >

  .w-100px{
    width: 100px;
  }
  .w-card{
    width: 200px;
  }
  .shadow{
    box-shadow: -50px 0px 40px rgba(156, 156, 156, 0.323) !important;
  }
  .w-pay{
    width: 300px;
  }
  input{
    outline: none;
    border-radius: 0;
    border: none !important;
  }
  .h800px{
    height: 800px;
  }
</style>
