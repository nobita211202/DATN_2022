<template>
  <div>
    <div class="row">
      <div v-for="cr,index in listCourse" :key="cr.id"  class="  col-lg-3 col-md-4 col-sm-6 col-12 my-3">
        <div class=" p-0 d-flex flex-column position-relative block-hover h-100">
          <div class="w-100" >
            <img class="w-100 img" :src="getImg(cr[0].image)" alt="">
          </div>
          <div class="m-1 d-flex flex-column ">
            <span class="fw-bold"><a :class="classtext" :href="`/course/${cr[0].id}`">{{ cr[0].name }}</a></span>
            <span class="opacity-75">{{ cr[0].user.username }}</span>
            <span class="opacity-75">12.5h<span class="px-2">/</span>16 video</span>
            <span class="text-bold">
              {{cr[0].price | formatNumber}}
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
            <span class="contro" @click="debounceLikeClick(cr[0].id,index)">
              <i  class="bi"  :class=" cr[1] ? 'text-danger bi-heart-fill' : 'bi-heart'"></i>
            </span>
          </div>
          <b-button class="btn-danger" @click.prevent="timerCartCourse(cr[0].id)"> Thêm vào giỏ</b-button>
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
  export default {
    data() {
      return {
        like:{},
        debounceAddCart: null,
        debounceLike:null,
      }
    },
    props:{
      listCourse:Array,
      classtext:String,
    },
    created(){

    },
    watch:{

    },
    methods: {
      debounceLikeClick(idCourse, index){

        console.log(this.listCourse);
        clearTimeout(this.debounceLike)
        this.debounceLike = setTimeout(()=>{this.likeClick(idCourse,index);},1000)
      },
      likeClick(idCourse,index){
        this.like = {
          course:{id : idCourse},
          user_id: 15
        }
        const changer= this.listCourse[index]

        if(!changer[1]){
          axios.post("/api/like/add",this.like)
          .then(()=>{
            changer.pop();
            changer.push(true)
            console.log(1);
          })
        }else{
          axios.delete(`/api/like/delete/${this.like.user_id}`,this.like)
          .then(()=>{
            changer.pop();
            changer.push(false)
            console.log(2);
          })
        }
      },
      getImg(name){
        return `${axios.defaults.baseURL}/api/image/get/${name}`
      },
      timerCartCourse(id){
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
        .then( _ =>
        this.$toast.center('<div class="px-2 py-1"><i class="text-success fs-1 mb-1 fw-bold fa-solid fa-circle-check"></i> <p>Đã thêm</p> </div>')
        ).catch(_ =>
          this.$toast.center('<div class="px-2 py-1"><i class="text-success fs-1 mb-1 fw-bold fa-solid fa-circle-check"></i> <p>Đã có khóa học này trong giỏ hàng rồi. </p> </div>')
        )
      },
    },
    filters:{
      formatNumber:function(value){
        return new Intl.NumberFormat().format(value)+"đ"
      }
    },
}
</script>
