<template>
  <Main>
    <div class="body bg-white  ">
            <div class="bg-dark py-5">
                <div class="container-xxl">
                    <span class="text-center fs-1 text-white">Liên hệ với chúng tôi</span>
                </div>
            </div>
            <div class="container-xxl my-5">
               <div class="row">
                <div class="col-lg-6 col-12 my-2">
                    <img src="https://www.qs.com/wp-content/uploads/2020/06/university-online-learning.jpg" class="w-100" alt="">
                </div>
                <div class="col-lg-6 col-12">

                    <div class="my-5">
                        <h3>CONTACT</h3>
                        <div class="row my-4">
                            <div class="col-6">
                                <label for="">First Name*</label>
                                <input class="px-2 input-focus input-hover py-3 w-100 px-2 border-0 border-bottom bg-white" type="text" >
                            </div>
                            <div class="col-6">
                                <label for="">Last Name*</label>
                                <input class="px-2 input-focus input-hover py-3 w-100 px-2 border-0 border-bottom bg-white" type="text" >
                            </div>
                        </div>
                        <div class="row my-4">
                            <div class="col-12">
                                <label for="">Email*</label>
                                <input class="px-2 input-focus input-hover py-3 w-100 px-2 me-2 border-bottom border-0 bg-white" type="email" >
                            </div>
                        </div>
                        <div class="row my-4">
                            <div class="col-12">
                                <label for="">Subject*</label>
                                <input class="px-2 input-focus input-hover py-3 w-100 px-2 me-2 border-bottom border-0 bg-white" type="email" >
                            </div>
                        </div>
                        <div class="row my-4">
                            <div class="col-12">
                                <label for="">Messenger</label>
                                <textarea class="px-2 input-focus input-hover py-3 w-100 px-2 me-2 border-bottom border-0 bg-white">
                                </textarea>
                            </div>
                        </div>
                        <div class="d-flex">
                            <a href="" class="btn-outline-dark btn mx-auto px-5 py-1">Submit</a>
                        </div>
                    </div>
                </div>
               </div>
            </div>
  </div>
  </Main>
</template>

<script>
import Main from '@layouts/main.vue';
export default {
  components:{
    Main
  },
  data(){
    return{

    }
  }
}
</script>

<style >
.input-focus:focus{
    border-radius: 0;
    border-top: none !important;
    border-left: none !important;
    border-right: none !important;
    border-bottom:solid black 1px !important;
    outline: yellow;
}
.input-hover:hover{
    border-bottom: solid 1px black !important;
}
.h-1000{
    height: 980px;
}
.bg-img{
    background-position: center center;
    background-repeat: no-repeat;
    background-size: cover;
}
.text-hover:hover {
    color: rgb(70, 98, 194) !important;
}
a{
    color: black;
    text-decoration: none;
}
.img-hover:hover .abs {
    opacity: 65% !important;
}
.btn-hover-white:hover{
    background-color: white !important;
    color: black !important;
}
.border-bottom{
  border-bottom: 1px rgba(128, 128, 128, 0.483) solid !important;
}
.py-10{
  padding-top: 50px;
  padding-bottom: 50px;
}
</style>
