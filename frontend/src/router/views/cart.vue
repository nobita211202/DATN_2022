<template>
  <div>
    <Main>
      <div class="bg-dark py-5">
        <div class="container-xxl ">
              <span class="d-flex mb-2 p-3  row text-white">
              <span class="pe-2">
                <svg xmlns="http://www.w3.org/2000/svg" width="30"  fill="currentColor" class="bi bi-cart3" viewBox="0 0 16 16">
                  <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .49.598l-1 5a.5.5 0 0 1-.465.401l-9.397.472L4.415 11H13a.5.5 0 0 1 0 1H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l.84 4.479 9.144-.459L13.89 4H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
                </svg>
              </span>
              <span class="">
                <span class="text-white fs-1">Giỏ hàng</span>
              </span>
            </span>
        </div>
      </div>
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
      <div class="d-flex h800px" v-if="lstCart.length === 0">
        <div class="text-center opacity-50 m-auto">
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

          <div class="bg-white ">
            <div class="container-xxl">
              <div class="row">
                <div class="col-lg-7 col-12">
                  <div v-for="cs,index in lstCart" :key="index" class="d-flex py-2 border-bottom">
                    <div class="d-flex w-100px pe-3">
                      <input type="checkbox" class="select" :value="cs.course.id" @click="select">
                      <img :src="getImg(cs.course.image)" class="w-100 my-auto" alt="">
                    </div>
                    <span class="ms-3">
                      <span class="d-flex fw-bold fs-5 ">{{ cs.course.name }}</span>
                      <span class="d-flex text-muted">{{ cs.user.username }}</span>
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
                  <div class=" bg-white mx-auto  shadow radius  p-5 col-lg-8 col-12 ">
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
                    <div>
                      <b-button v-b-modal.modal-1 class="ms-auto btn btn-success">Thanh toán</b-button>

                      <b-modal id="modal-1" title="Thông báo" hide-footer>
                        <p class="my-4">Bạn các chắc chắn muốn thanh toán</p>
                        <b-button class="mt-3" block @click="coursePayment">Thanh toán</b-button>
                        <b-button class="mt-3" block @click="$bvModal.hide('modal-1')">Quay lại</b-button>
                      </b-modal>
                    </div>
                    <!--                    <a class="ms-auto btn btn-success" @click="coursePayment">Thanh toán</a >-->
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="bg-light">
            <div class="container-xxl mt-5 py-5  ">
              <p class="fs-4 pt-3 fw-bold">Khóa học gợi ý</p>
              <div class="">
                <form class="d-flex w-100 py-1 bg-white border mx-lg-auto">
                  <span class="px-2">
                    <i class="fs-4 bi bi-search"></i>
                  </span>
                  <input class="w-75" v-model="txtSearch" type="search" placeholder="Search" aria-label="Search">
                </form>
              </div>
              <b-overlay :show="overlayGY" rounded="sm" >
                <div class="mt-2 border-top">
                  <div class="mt-5 row">
                    <LstcourseVue :classtext="'text-dark'" :listCourse="lstCourseGY" />
                  </div>
                </div>
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
import LstcourseVue from '@/src/components/lstcourse.vue';
import * as _ from 'lodash';
import store from '@/src/state/store';
const user = Object.assign({},store.state.auth.currentUser)
export default {
  components:{
    Main,
    LstcourseVue
  },
  data(){
    return{
      deleteCourse:{},
      overlayGY:false,
      debounceSearch:{},
      txtSearch:"",
      lstIdCourse:[],
      listCourse:[],
      lstCart:[],
      lstCourseGY:[],
      sumMoney:()=>{
        var sum=0;
        this.listCourse.forEach(c => {
          sum += c.course.price
        });
        return sum
      }
    }
  },
  created(){
    axios.get("/api/cart/get/"+user.id)

        .then(_ => {this.lstCart = _.data})



    axios.get(`/api/course/get`).then( res => {this.lstCourseGY = res.data})
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
    },
    select(){
      const checkBoxS = document.getElementsByClassName("select");
      console.log(this.lstCart);
      if(checkBoxS.length > 0) {
        this.listCourse=[]
        for (let i = 0; i < checkBoxS.length; i++) {
          if (checkBoxS[i].checked) {
            this.lstIdCourse.push(Number.parseInt(checkBoxS[i].value))
            this.listCourse.push(this.lstCart.find(c => c.course.id === Number.parseInt(checkBoxS[i].value)))
          }
          // else{
          //   this.listCourse = this.listCourse.splice(this.listCourse.findIndex(c => c.course.id === Number.parseInt(checkBoxS[i].value)) -1,1)
          // }
        }
      }},
    coursePayment(){
      this.$bvModal.hide('modal-1')
      var checkboxs = document.getElementsByClassName("select");
      console.log(this.lstCart);
      if(checkboxs.length > 0) {
        for (let i = 0; i < checkboxs.length; i++) {
          if (checkboxs[i].checked) {
            this.lstIdCourse.push(Number.parseInt(checkboxs[i].value))
          }
        }
        axios.post("/api/order-detail/course-payment/" + JSON.parse(localStorage.getItem('auth.currentUser')).id, this.lstIdCourse).then(res => {
          if (res.data.status === 200) {
            this.$toast.center('<div class="px-2 py-1"><i class="text-success fs-1 mb-1 fw-bold fa-solid fa-circle-check"></i> <p>Thanh toán thành công</p> </div>')
            _.forIn(this.listCourse, (value, key) => {
              console.log(value)
              axios.delete("/api/cart/delete/"+value.id)
            })
          } else {
            alert("Thanh toán thất bại");
          }
        });
      }
      else{
        alert("Bạn chưa chọn khóa học nào");
      }
    },
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
  width: 130px;
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
