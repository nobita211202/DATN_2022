<template>
  <div class= "position-relative d-flex wh bg">
      <div class="position-absolute w-500px shadow-lg bg-light rounded-3 top-50 start-50 translate-middle">
          <div class="d-flex flex-column justify-content-start p-5">
              <div class="d-flex w-100 ps-5 border-start border-3 border-primary">
                  <div class="px-2 my-auto">
                      <span class="fs-3">Đăng kí</span>
                  </div>
                  <span class="ms-auto my-auto">
                      <a href="/login">
                          Đăng nhập
                          <span>
                              <svg xmlns="http://www.w3.org/2000/svg" width="25" fill="currentColor" class="bi bi-arrow-right" viewBox="0 0 16 16">
                                  <path fill-rule="evenodd" d="M1 8a.5.5 0 0 1 .5-.5h11.793l-3.147-3.146a.5.5 0 0 1 .708-.708l4 4a.5.5 0 0 1 0 .708l-4 4a.5.5 0 0 1-.708-.708L13.293 8.5H1.5A.5.5 0 0 1 1 8z"/>
                              </svg>
                          </span>
                      </a>
                  </span>
              </div>
             <div class="pt-3">
                  <div class="input-focus  my-5 d-flex" >
                    <span class="my-auto px-3">
                        <svg xmlns="http://www.w3.org/2000/svg" width="25"  fill="currentColor" class="bi bi-person" viewBox="0 0 16 16">
                            <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6Zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0Zm4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4Zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10Z"/>
                        </svg>
                    </span>
                    <input v-model="acc.username" class="bg-light w-100  input-outline-node-focus input-hover border-0 py-3  " type="text" placeholder="Usernmae" aria-label="Search">
                  </div>
                  <div class="row d-flex px-0 mx-0">
                    <div class="input-focus col-5 px-0 me-auto my-5 d-flex" >
                      <span class="my-auto px-3">
                        <svg xmlns="http://www.w3.org/2000/svg" width="25" fill="currentColor" class="bi bi-lock" viewBox="0 0 16 16">
                                <path d="M8 1a2 2 0 0 1 2 2v4H6V3a2 2 0 0 1 2-2zm3 6V3a3 3 0 0 0-6 0v4a2 2 0 0 0-2 2v5a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V9a2 2 0 0 0-2-2zM5 8h6a1 1 0 0 1 1 1v5a1 1 0 0 1-1 1H5a1 1 0 0 1-1-1V9a1 1 0 0 1 1-1z"/>
                            </svg>
                      </span>
                      <input v-model="acc.password" class="bg-light w-100  input-outline-node-focus input-hover border-0 py-3  " type="password" placeholder="Password" aria-label="Search">
                    </div>
                    <div class="input-focus col-6 px-0 my-5 d-flex" >
                      <span class="my-auto px-3">
                        <svg xmlns="http://www.w3.org/2000/svg" width="25" fill="currentColor" class="bi bi-lock" viewBox="0 0 16 16">
                                <path d="M8 1a2 2 0 0 1 2 2v4H6V3a2 2 0 0 1 2-2zm3 6V3a3 3 0 0 0-6 0v4a2 2 0 0 0-2 2v5a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V9a2 2 0 0 0-2-2zM5 8h6a1 1 0 0 1 1 1v5a1 1 0 0 1-1 1H5a1 1 0 0 1-1-1V9a1 1 0 0 1 1-1z"/>
                            </svg>
                      </span>
                      <input v-model="confirmPass" class="bg-light w-100  input-outline-node-focus input-hover border-0 py-3  " type="password" placeholder="Confirm assword" aria-label="Search">
                    </div>
                  </div>
                  <div class="row d-flex px-0 mx-0">
                    <div class="col-5  px-0 me-auto input-focus my-5 d-flex" >
                      <span class="my-auto px-3">
                        <svg xmlns="http://www.w3.org/2000/svg" width="25" fill="currentColor" class="bi bi-telephone" viewBox="0 0 16 16">
                          <path d="M3.654 1.328a.678.678 0 0 0-1.015-.063L1.605 2.3c-.483.484-.661 1.169-.45 1.77a17.568 17.568 0 0 0 4.168 6.608 17.569 17.569 0 0 0 6.608 4.168c.601.211 1.286.033 1.77-.45l1.034-1.034a.678.678 0 0 0-.063-1.015l-2.307-1.794a.678.678 0 0 0-.58-.122l-2.19.547a1.745 1.745 0 0 1-1.657-.459L5.482 8.062a1.745 1.745 0 0 1-.46-1.657l.548-2.19a.678.678 0 0 0-.122-.58L3.654 1.328zM1.884.511a1.745 1.745 0 0 1 2.612.163L6.29 2.98c.329.423.445.974.315 1.494l-.547 2.19a.678.678 0 0 0 .178.643l2.457 2.457a.678.678 0 0 0 .644.178l2.189-.547a1.745 1.745 0 0 1 1.494.315l2.306 1.794c.829.645.905 1.87.163 2.611l-1.034 1.034c-.74.74-1.846 1.065-2.877.702a18.634 18.634 0 0 1-7.01-4.42 18.634 18.634 0 0 1-4.42-7.009c-.362-1.03-.037-2.137.703-2.877L1.885.511z"/>
                        </svg>
                      </span>
                      <input v-model="acc.phone" class="bg-light w-100  input-outline-node-focus input-hover border-0 py-3  " type="text" placeholder="phone" aria-label="Search">
                    </div>
                    <div class="col-6 px-0 input-focus my-5 d-flex" >
                      <span class="my-auto px-3">
                        <svg xmlns="http://www.w3.org/2000/svg" width="25" fill="currentColor" class="bi bi-envelope" viewBox="0 0 16 16">
                          <path d="M0 4a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v8a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V4Zm2-1a1 1 0 0 0-1 1v.217l7 4.2 7-4.2V4a1 1 0 0 0-1-1H2Zm13 2.383-4.708 2.825L15 11.105V5.383Zm-.034 6.876-5.64-3.471L8 9.583l-1.326-.795-5.64 3.47A1 1 0 0 0 2 13h12a1 1 0 0 0 .966-.741ZM1 11.105l4.708-2.897L1 5.383v5.722Z"/>
                        </svg>
                      </span>
                      <input v-model="acc.email" class="bg-light w-100  input-outline-node-focus input-hover border-0 py-3  " type="email" placeholder="email" aria-label="Search" required>
                    </div>
                  </div>
                  <div class="input-focus my-5 d-flex" >
                    <span class="pt-2 px-3">
                      <svg xmlns="http://www.w3.org/2000/svg" width="25" fill="currentColor" class="bi bi-geo-alt" viewBox="0 0 16 16">
                        <path d="M12.166 8.94c-.524 1.062-1.234 2.12-1.96 3.07A31.493 31.493 0 0 1 8 14.58a31.481 31.481 0 0 1-2.206-2.57c-.726-.95-1.436-2.008-1.96-3.07C3.304 7.867 3 6.862 3 6a5 5 0 0 1 10 0c0 .862-.305 1.867-.834 2.94zM8 16s6-5.686 6-10A6 6 0 0 0 2 6c0 4.314 6 10 6 10z"/>
                        <path d="M8 8a2 2 0 1 1 0-4 2 2 0 0 1 0 4zm0 1a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
                      </svg>
                    </span>
                    <textarea v-model="acc.address" class="bg-light w-100  input-outline-node-focus input-hover border-0 py-3  " type="text" placeholder="Address" aria-label="Search"></textarea>
                  </div>


                  <button @click="signin" class="w-100 border boder-dark bg-light py-4">
                      Submit
                  </button>
             </div>

          </div>
      </div>
  </div>

</template>

<script>
  import axios from "axios"
  export default{
      name:"loginForm",
      data(){
          return{
              acc:{
                  username:"",
                  password:"",
                  address:"",
                  phone:"",
                  email:"",
                  image:"",
                  userRole:{
                    role:{id:5},
                  },
                status:0,
              },
              errUsername:"",
              errPassword:"",
              confirmPass:""
          }
      },
      methods:{
        async signin() {
          if(this.acc.email==="" || this.acc.username==="" || this.acc.password==="" || this.acc.address==="" || this.acc.phone===""){
            alert("Vui lòng điền đủ thông tin")
            return
          }
          if(this.acc.password !== this.confirmPass){
            alert("Mật khẩu không khớp")
            return
          }
          if(this.acc.email.indexOf("@")===-1){
            alert("Email không hợp lệ")
            return
          }
          var submit=false;
          await axios.get(`api/forgotPassword/getExists/${this.acc.username}`).then(_=> {submit =true}).catch(_ => console.log(_))
          if(!submit) await axios.get(`api/forgotPassword/getExists/${this.acc.email}`).then(_=> {submit =true}).catch(_ => console.log(_.status))
          if(!submit){
            axios.post(`/api/signin`, this.acc).then(_=>{
              this.$router.push("/login")
            })
          }
        },
      }
  }
</script>

<style scoped>
  .w-500px{
      width: 600px;
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
      aspect-ratio: 5/3;
  }
 a{
  text-decoration: none;
  color: black;
 }
 button:hover{
  background-color: black !important;
  color: white;
  transition: 1s;
 }
</style>
