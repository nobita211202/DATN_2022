<script>
import Layout from '@layouts/main.vue'
import axios from '@/node_modules/axios';
// import CropImg from "@components/CropImg.vue"
export default {
  components: {Layout
    // , CropImg
  },
  props:{
    user:Object
  },
  data(){
    return{
      imageSelected:null,
      imageUP:null,
      fileRender:null
    }
  },
  created(){
    this.fileRender = new FileReader()
    this.fileRender.onload = (Even) => {
        this.imageSelected = Even.target.result
    }
  },
  methods:{
    getImg(name){
      console.log(name);
      return `${axios.defaults.baseURL}/api/image/get/${name}`
    },
    chooseImg(even) {
      this.imageSelected = even.target.files[0]
      this.imageUP= this.imageSelected
      this.fileRender.readAsDataURL(this.imageSelected)
    },
    updateAcc(){
      const dataForm= new FormData()
      delete  this.user.usersRoles
      // delete  this.user.money
      delete  this.user.password
      delete  this.user.username
      dataForm.append("json", JSON.stringify(this.user))
      console.log(this.user);
      if(this.imageUP !== null) dataForm.append("file",this.imageUP)
      axios.put(`api/user/update`,dataForm,{
        headers:{
            'Content-Type':'multipart/form-data',
            'Accept':'application/json'}
      }).then(_=>{
        this.$toast.center('<div class="px-2 py-1"><i class="text-success fs-1 mb-1 fw-bold fa-solid fa-circle-check"></i> <p>Đã cập nhật</p> </div>')
      })
    }
  },
  filters:{
    formatNumber:function(value){
      return new Intl.NumberFormat().format(value)+"đ"
    },

  },
}
</script>

<template>
  <Layout>
    <div class= "container mt-5">
      <div class=" shadow-lg bg-light rounded-3 ">
          <div class="d-flex flex-column justify-content-start p-5">
              <div class="d-flex w-100 ps-5 border-start border-3 border-primary">
                  <div class="px-2 my-auto">
                      <span class="fs-3">Cập nhật tài khoản</span>
                  </div>
              </div>
             <div class="pt-3">
                  <div class="row">
                    <div class="col-4 col-lg-3 d-flex flex-column">
                      <img v-if="!imageSelected" class="shadow w-100 rounded m-auto" :src="getImg(user.image)" alt="">
                      <img v-else class="w-100 shadow rounded m-auto" :src="imageSelected" alt="">
                    </div>
                    <div class="col-8">
                      <div class="input-focus  my-5 d-flex" >

                        <input  v-model="user.name" class="bg-light w-100 ps-3 input-outline-node-focus input-hover border-0 py-3  " type="text" aria-label="Search">
                        <span class="text-muted my-auto">Fullname</span>
                      </div>
                      <div class="input-focus  my-5 d-flex" >

                        <input disabled :value="user.username" class=" ps-3 bg-light w-100 w-100  input-outline-node-focus input-hover border-0 py-3  " type="text"  aria-label="Search">
                        <span class="text-muted my-auto">Username</span>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-4">
                      <span>Thay ảnh</span>
                      <input @change="chooseImg" class="w-100 form-control" type="file">
                    </div>
                  </div>
                  <div class="d-flex">
                    <div class=" px-0 me-auto my-5 d-flex" >
                      <span class="my-auto px-3">
                        <svg xmlns="http://www.w3.org/2000/svg" width="25" fill="currentColor" class="bi bi-wallet2" viewBox="0 0 16 16">
                          <path d="M12.136.326A1.5 1.5 0 0 1 14 1.78V3h.5A1.5 1.5 0 0 1 16 4.5v9a1.5 1.5 0 0 1-1.5 1.5h-13A1.5 1.5 0 0 1 0 13.5v-9a1.5 1.5 0 0 1 1.432-1.499L12.136.326zM5.562 3H13V1.78a.5.5 0 0 0-.621-.484L5.562 3zM1.5 4a.5.5 0 0 0-.5.5v9a.5.5 0 0 0 .5.5h13a.5.5 0 0 0 .5-.5v-9a.5.5 0 0 0-.5-.5h-13z"/>
                        </svg>
                      </span>
                      <span class="fs-2 fw-bold">{{ user.money | formatNumber }}</span>
                    </div>
                    <a href="/user/charge-card" class="btn btn-danger my-auto text-white ">
                      Nạp tiền
                    </a>
                  </div>

                  <div class="row d-flex px-0 mx-0">
                    <div class="col-5  px-0 me-auto input-focus my-5 d-flex" >
                      <span class="my-auto px-3">
                        <svg xmlns="http://www.w3.org/2000/svg" width="25" fill="currentColor" class="bi bi-telephone" viewBox="0 0 16 16">
                          <path d="M3.654 1.328a.678.678 0 0 0-1.015-.063L1.605 2.3c-.483.484-.661 1.169-.45 1.77a17.568 17.568 0 0 0 4.168 6.608 17.569 17.569 0 0 0 6.608 4.168c.601.211 1.286.033 1.77-.45l1.034-1.034a.678.678 0 0 0-.063-1.015l-2.307-1.794a.678.678 0 0 0-.58-.122l-2.19.547a1.745 1.745 0 0 1-1.657-.459L5.482 8.062a1.745 1.745 0 0 1-.46-1.657l.548-2.19a.678.678 0 0 0-.122-.58L3.654 1.328zM1.884.511a1.745 1.745 0 0 1 2.612.163L6.29 2.98c.329.423.445.974.315 1.494l-.547 2.19a.678.678 0 0 0 .178.643l2.457 2.457a.678.678 0 0 0 .644.178l2.189-.547a1.745 1.745 0 0 1 1.494.315l2.306 1.794c.829.645.905 1.87.163 2.611l-1.034 1.034c-.74.74-1.846 1.065-2.877.702a18.634 18.634 0 0 1-7.01-4.42 18.634 18.634 0 0 1-4.42-7.009c-.362-1.03-.037-2.137.703-2.877L1.885.511z"/>
                        </svg>
                      </span>
                      <input v-model="user.phone" class="bg-light w-100  input-outline-node-focus input-hover border-0 py-3  " type="text" placeholder="phone" aria-label="Search">
                    </div>
                    <div class="col-6 px-0 input-focus my-5 d-flex" >
                      <span class="my-auto px-3">
                        <svg xmlns="http://www.w3.org/2000/svg" width="25" fill="currentColor" class="bi bi-envelope" viewBox="0 0 16 16">
                          <path d="M0 4a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v8a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V4Zm2-1a1 1 0 0 0-1 1v.217l7 4.2 7-4.2V4a1 1 0 0 0-1-1H2Zm13 2.383-4.708 2.825L15 11.105V5.383Zm-.034 6.876-5.64-3.471L8 9.583l-1.326-.795-5.64 3.47A1 1 0 0 0 2 13h12a1 1 0 0 0 .966-.741ZM1 11.105l4.708-2.897L1 5.383v5.722Z"/>
                        </svg>
                      </span>
                      <input v-model="user.email" class="bg-light w-100  input-outline-node-focus input-hover border-0 py-3  " type="text" placeholder="email" aria-label="Search">
                    </div>
                  </div>
                  <div class="input-focus my-5 d-flex" >
                    <span class="pt-2 px-3">
                      <svg xmlns="http://www.w3.org/2000/svg" width="25" fill="currentColor" class="bi bi-geo-alt" viewBox="0 0 16 16">
                        <path d="M12.166 8.94c-.524 1.062-1.234 2.12-1.96 3.07A31.493 31.493 0 0 1 8 14.58a31.481 31.481 0 0 1-2.206-2.57c-.726-.95-1.436-2.008-1.96-3.07C3.304 7.867 3 6.862 3 6a5 5 0 0 1 10 0c0 .862-.305 1.867-.834 2.94zM8 16s6-5.686 6-10A6 6 0 0 0 2 6c0 4.314 6 10 6 10z"/>
                        <path d="M8 8a2 2 0 1 1 0-4 2 2 0 0 1 0 4zm0 1a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
                      </svg>
                    </span>
                    <textarea v-model="user.address" class="bg-light w-100  input-outline-node-focus input-hover border-0 py-3  " type="text" placeholder="Address" aria-label="Search"></textarea>
                  </div>


                  <a @click="updateAcc" class="w-100 border btn btn-success boder-dark text-white bg-dark  py-4">
                    Cập nhật
                  </a>
             </div>

          </div>
      </div>
  </div>
  </Layout>
</template>

<style >
  .w-500px{
      width: 400px;
  }
  .input-focus:focus-within {

    border-bottom:solid black 1px !important;
    transition: 1s;
  }
  .input-focus{
    border-bottom:solid rgba(0, 0, 0, 0.308) 1px !important;
  }
  input:focus,textarea:focus{
    outline: none;
    border-radius: 0;
    border-top: none !important;
    border-left: none !important;
    border-right: none !important;
  }
  .wh{
      aspect-ratio: 5/2;
  }
 a{
  text-decoration: none;
  color: black;
 }
 a:hover{
  transition: 1s;
  cursor: pointer;
 }
 .shadow{
 }
</style>
