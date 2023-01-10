<template>
  <div>
    <div class="row">
      <div v-for="cr,index in listCourse" :key="cr.id"  class="  col-lg-3 col-md-4 col-sm-6 col-12 my-3">
        <div class=" p-0 d-flex flex-column position-relative block-hover h-100">
          <div class="w-100" >
            <img class="w-100 img" :src="getImg(cr.image)" alt="">
          </div>
          <div class="m-1 d-flex flex-column ">
            <span class="fw-bold"><a :class="classtext" :href="`/course/${cr.id}`">{{ cr.name }}</a></span>
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
            <b-button class="btn-danger w-100" @click.prevent="timerCartCourse(cr.id,index)"> Thêm vào giỏ</b-button>
          </b-overlay>
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
.contro{
  cursor:pointer;
}
</style>

<script>
import axios from '@/node_modules/axios'
import store from '@state/store'
  export default {
    data() {
      return {
        like:{},
        likeCount:[],
        debounceAddCart: null,
        debounceLike:null,
        user: Object.assign({},store.state.auth.currentUser)
      }
    },
    props:{
      listCourse:Array,
      classtext:String,
    },
    mounted (){
      console.log(this.user);
    },
    watch:{
      async listCourse(){


      },
    },
    methods: {

      getImg(name){
        return `${axios.defaults.baseURL}/api/image/get/${name}`
      },
      timerCartCourse(id,index){
        if(!this.user) this.$router.push("/login")
        clearTimeout(this.debounceAddCart)
        this.debounceAddCart = setTimeout(()=>{this.cartCourse(id);},1000)
      },
      cartCourse(id){
        var cart = {
          "user":{id:"15"},
          "course":{id:id}
        }
        console.log(cart);
        axios.post("/api/cart/add",cart)
        .then( _ =>{
          this.$toast.center('<div class="px-2 py-1"><i class="text-success fs-1 mb-1 fw-bold fa-solid fa-circle-check"></i> <p>Đã thêm</p> </div>')
        }).catch(_ =>{
          this.$toast.center('<div class="px-2 py-1"><i class="text-success fs-1 mb-1 fw-bold fa-solid fa-circle-check"></i> <p>Đã có khóa học này trong giỏ hàng rồi. </p> </div>')
        })
      },
    },
    filters:{
      formatNumber:function(value){
        return new Intl.NumberFormat().format(value)+"đ"
      }
    },
}
</script>
