
<template>
  <div>
    <Main>
      <b-modal
      id="modal-rm-course"
      size="sm"
      centered
      scrollable
      hide-backdrop
      hide-header-close
    >
      <template v-slot:modal-title> Bỏ khóa hoc </template>
        <p>
          Xác nhận bỏ khóa học <span class="fw-bold"> {{deleteCourse.name}}</span> ?
        </p>
      <template v-slot:modal-footer>
        <b-button type="button" variant="success" @click="rmCourse(deleteCourse.id)">
          Oke</b-button
        >
      </template>
    </b-modal>
      <div class="position-static" v-if="lstCart.length === 0">
        <div class="text-center opacity-50 position-absolute top-50 start-50 translate-middle">
          <span class="fs-1 text-muted ">
            <svg xmlns="http://www.w3.org/2000/svg" height="130" fill="currentColor" class="bi bi-cart3" viewBox="0 0 16 16">
              <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .49.598l-1 5a.5.5 0 0 1-.465.401l-9.397.472L4.415 11H13a.5.5 0 0 1 0 1H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l.84 4.479 9.144-.459L13.89 4H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
            </svg>
          </span>
          <p>Chưa có khóa học nào trong giỏ hàng</p>
          <p>Đi tới <a href="/"> danh sách khóa học</a></p>
        </div>
      </div>
      <div class=" px-0 mt-2 ">
        <div v-if="lstCart.length !== 0" class="my-5 ">
          <div class="bg-success">
            <div class="container ">
              <span class="d-flex mb-2 p-3  row text-white">
              <span class="border-end pe-2">
                <svg xmlns="http://www.w3.org/2000/svg" width="30"  fill="currentColor" class="bi bi-cart3" viewBox="0 0 16 16">
                  <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .49.598l-1 5a.5.5 0 0 1-.465.401l-9.397.472L4.415 11H13a.5.5 0 0 1 0 1H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l.84 4.479 9.144-.459L13.89 4H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
                </svg>
              </span>
              <span class="d-flex ps-2">
                <span class=" my-auto fs-4 fw-bold">Giỏ hàng</span>
              </span>
            </span>
            </div>
          </div>
          <div class="bg-white ">
            <div class=" container px-0">
              <div class="row">
                <div class="col-lg-7 col-12">
                  <div v-for="cs,index in lstCart" :key="index" class="d-flex py-2 border-bottom">
                    <div class="d-flex w-100px">
                      <img :src="getImg(cs.course.image)" class="w-100 my-auto" alt="">
                    </div>
                    <span class="mx-2">
                      <span class="d-flex fw-bold fs-5 ">{{ cs.course.name }}</span>
                      <span class="d-flex text-muted">giảng viên</span>
                      <span class="d-flex text-danger">{{cs.course.price | formatNumber}}</span>
                    </span>
                    <span class="ms-auto d-flex">
                      <b @click="mdrmCourse(cs.id,cs.course.name)" class="btn my-auto">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x-lg" viewBox="0 0 16 16">
                          <path d="M2.146 2.854a.5.5 0 1 1 .708-.708L8 7.293l5.146-5.147a.5.5 0 0 1 .708.708L8.707 8l5.147 5.146a.5.5 0 0 1-.708.708L8 8.707l-5.146 5.147a.5.5 0 0 1-.708-.708L7.293 8 2.146 2.854Z"/>
                        </svg>
                      </b>
                    </span>
                  </div>
                </div>
                <div class="col-12 my-2 col-lg-5 d-flex">
                  <div class=" bg-white m-auto  shadow radius  p-5 col-lg-8 col-12 ">
                    <span class=" d-flex ">
                      Giá ban đầu: <span class="fw-bold ms-auto">{{sumMoney() | formatNumber}}</span>
                    </span>
                    <hr>
                    <span class=" d-flex ">
                      Giảm giá: <span class="fw-bold ms-auto">{{0 | formatNumber}}</span>
                    </span>
                    <hr>
                    <span class="fs-4 d-flex ">
                      Tổng: <span class="fw-bold ms-auto">{{sumMoney() | formatNumber}}</span>
                    </span><hr>
                    <b-button class="ms-auto">Thanh toán</b-button>
                  </div>
                </div>
              </div>
          </div>
          </div>


          <div class="container-fluid mt-5 row bg-white py-5 bg-white">
            <p class="fs-4 pt-3 fw-bold">Khóa học gợi ý</p>
            <div class="">
              <b-form-input class="col-6" v-model="txtSearch" placeholder="Tìm kiếm"></b-form-input>
            </div>
              <b-overlay :show="overlayGY" rounded="sm" >
                <div class="mt-2 border-top">
                  <div class="mt-5 row">

                    <a
                      v-for="c,index in lstCourseGY"
                      :key="index"
                      class="col-sm-3 col-4 col-xl-2 mb-3 text-dark"
                      :href="`/course/${c.id}`"
                    >
                      <div class="border d-flex flex-column p-0 h-100">
                          <img :src="getImg(c.image)" class="w-100" alt="">
                          <div class="mt-2 mx-1 d-flex mb-4 flex-column">
                            <span class="fw-bold">{{c.name}}</span>
                            <span class="text-muted">Giảng viên</span>
                          </div>
                          <span class="d-flex mx-1 mb-2 mt-auto">
                              <span>
                                <i class="fa fa-star-o co-or text-warning"></i>
                                <i class="fa fa-star-o co-or text-warning"></i>
                                <i class="fa fa-star-o co-or text-warning"></i>
                                <i class="fa fa-star-o co-or text-warning"></i>
                                <i class="fa fa-star-o co-or text-warning"></i>
                              </span>
                              <span class="text-bold ms-auto">{{c.price | formatNumber}}</span>
                            </span>
                      </div>
                    </a>
                  </div>
                </div>
              </b-overlay>
          </div>
        </div>

      </div>
    </Main>

  </div>
</template>

<script>
import axios from '@/node_modules/axios';
import Main from '@layouts/main.vue'
export default {
  components:{
    Main
  },
  data(){
    return{
      deleteCourse:{},
      overlayGY:false,
      debounceSearch:{},
      txtSearch:"",
      lstIdCourse:[],
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
    axios.get("/api/cart/get")
    .then(_ => {this.lstCart = _.data})



    axios.get("api/course/get").then( res => {this.lstCourseGY = res.data})
  },
  methods:{
    rmCourse(id){
      this.lstCart = this.lstCart.filter(c => c.id !== id)
      axios.delete("/api/cart/delete/"+id)
      this.$bvModal.hide("modal-rm-course")
    },
    mdrmCourse(id,name){
      this.deleteCourse.name = name
      this.deleteCourse.id = id
      this.$bvModal.show("modal-rm-course")
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
          axios.post(`/api/course/search`,this.txtSearch,{
            headers:{
              "Content-Type":"application/json"
            }
          })
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
  .container-1200px{
    width: 1200px;
    margin: 0 auto;
  }
  .shadow{
    box-shadow: -50px 0px 40px rgba(156, 156, 156, 0.323) !important;
  }
  .w-pay{
    width: 300px;
  }

</style>
