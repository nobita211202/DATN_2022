
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
          <div v-for="cr,index in courses" :key="index"  class="  col-lg-3 col-md-4 col-sm-6 col-12 my-3">
            <div class=" p-0 d-flex flex-column position-relative block-hover h-100">
              <div class="w-100" >
                <img class="w-100 img" :src="getImg(cr.image)" alt="">
              </div>
              <div class="m-1 d-flex flex-column ">
                <span class="fw-bold " ><a  :href="`/course/${cr.id}`" :class="'text-dark'">{{ cr.name }}</a></span>
                <span class="opacity-75">{{ cr.user.username }}</span>
                <span class="opacity-75">12.5h<span class="px-2">/</span>16 video</span>
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

              </div>
              <b-overlay
                :show="false"
                rounded="sm"
              >
                <a :href="`/`"><b-button class=" w-100"  variant="outline-dark">Vào học</b-button></a>
              </b-overlay>
            </div>
          </div>
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
      lstCart:[],
      lstCourseGY:[],
      sumMoney:()=>{
        var sum=0;
        this.lstCart.forEach(c => {
          sum += c.course.price
        });
        return sum
      }
    }
  },
  created(){
    this.getMyCourse()
  },
  methods:{
    getMyCourse(){
      axios.get(`/api/course/get/myCourse/${user.id}`)
      .then(res=>{
        this.courses = res.data
        console.log(res.data);
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
    height: 600px;
  }
</style>
