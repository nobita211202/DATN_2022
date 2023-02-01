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
                                <label for="">Họ và tên*</label>
                                <input v-model = "formAddSubject.fullname" class="px-2 input-focus input-hover py-3 w-100 px-2 border-0 border-bottom bg-white" type="text" >
                            </div>
                            <div class="col-6">
                                <label for="">Số điện thoại</label>
                                <input v-model = "formAddSubject.phone" class="px-2 input-focus input-hover py-3 w-100 px-2 me-2 border-bottom border-0 bg-white" type="email" >
                            </div>
                        </div>
                        <div class="row my-4">
                            <div class="col-12">
                                <label for="">Email*</label>
                                <input v-model = "formAddSubject.email" class="px-2 input-focus input-hover py-3 w-100 px-2 me-2 border-bottom border-0 bg-white" type="email" >
                            </div>
                        </div>

                        <div class="row my-4">
                            <div class="col-12">
                                <label for="">Vấn đề*</label> <br>

                                <select class="from-select p-2" v-model="formAddSubject.contactSubject" >
                                    <option :value="null" class="text-muted">Vấn đề bạn gặp phải</option>
                                    <b-form-select-option v-for="subject in subjects" :key="subject.name" :value="subject">{{subject.name}}</b-form-select-option>
                                </select>
                            </div>
                        </div>
                        <div class="row my-4">
                            <div class="col-12">
                                <label for="">Lời nhắn</label>
                                <textarea v-model = "formAddSubject.describe" class="px-2 input-focus input-hover py-3 w-100 px-2 me-2 border-bottom border-0 bg-white">
                                </textarea>
                            </div>
                        </div>
                        <div class="d-flex">
                            <b-overlay :show = "overlaySend">
                                <button @click = "sendContact" class="acction btn-outline-dark btn mx-auto px-5 py-1">Gửi</button>
                            </b-overlay>
                        </div>
                    </div>
                </div>
               </div>
            </div>
  </div>
  </Main>
</template>

<script>
import axios from '@/node_modules/axios';
import Main from '@layouts/main.vue';
export default {
  components:{
    Main
  },
  data(){
    return{
        subjects:[],
        overlaySend:false,
        formAddSubject:{
            contactSubject:null
        }
    }
  },
  created(){
    this.getSubject()
  },
  methods:{
    getSubject(){
        axios.get("/api/subject/get")
        .then(res =>{
            this.subjects = res.data
            console.log(res.data);
        })
    },
    sendContact(){
        this.overlaySend=true
        axios.post("/api/contact/send",this.formAddSubject)
        .finally(()=>{
            this.overlaySend =false
            document.getElementsByClassName("acction")[0].innerHTML= "Đã gửi"
        })
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
