<template>
  <div class= "position-relative d-flex wh bg">
      <div class="position-absolute w-500px shadow-lg bg-light rounded-3 top-50 start-50 translate-middle">
          <div class="d-flex flex-column justify-content-start p-5">
              <div class="d-flex w-100 ps-5 border-start border-3 border-primary">
                  <div class="px-2 my-auto">
                      <span class="fs-3">Login</span>
                  </div>
                  <span class="ms-auto my-auto">
                      <a href="/signin">
                          Sign In
                          <span>
                              <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-right" viewBox="0 0 16 16">
                                  <path fill-rule="evenodd" d="M1 8a.5.5 0 0 1 .5-.5h11.793l-3.147-3.146a.5.5 0 0 1 .708-.708l4 4a.5.5 0 0 1 0 .708l-4 4a.5.5 0 0 1-.708-.708L13.293 8.5H1.5A.5.5 0 0 1 1 8z"/>
                              </svg>
                          </span>
                      </a>
                  </span>
              </div>

             <div class="pt-3">
                <p v-show="loginFalseMsg" class="text-danger">Tài khoản hoặc mật khẩu không chính xác!</p>
                <div class="my-5">
                    <div class="input-focus  d-flex" >
                        <span class="my-auto px-3">
                            <svg xmlns="http://www.w3.org/2000/svg" width="25"  fill="currentColor" class="bi bi-person" viewBox="0 0 16 16">
                                <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6Zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0Zm4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4Zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10Z"/>
                            </svg>
                        </span>
                        <input v-model="acc.username" class="bg-light w-100  input-outline-node-focus input-hover border-0 py-3  " type="text" placeholder="Usernmae" aria-label="Search">
                    </div>
                    <p class="d-flex justify-content-start text-danger" >{{errUsername}}</p>
                </div>
                <div class="my-5">
                    <div class="input-focus  d-flex" >
                        <span class="my-auto px-3">
                            <svg xmlns="http://www.w3.org/2000/svg" width="25"  fill="currentColor" class="bi bi-key" viewBox="0 0 16 16">
                                <path d="M0 8a4 4 0 0 1 7.465-2H14a.5.5 0 0 1 .354.146l1.5 1.5a.5.5 0 0 1 0 .708l-1.5 1.5a.5.5 0 0 1-.708 0L13 9.207l-.646.647a.5.5 0 0 1-.708 0L11 9.207l-.646.647a.5.5 0 0 1-.708 0L9 9.207l-.646.647A.5.5 0 0 1 8 10h-.535A4 4 0 0 1 0 8zm4-3a3 3 0 1 0 2.712 4.285A.5.5 0 0 1 7.163 9h.63l.853-.854a.5.5 0 0 1 .708 0l.646.647.646-.647a.5.5 0 0 1 .708 0l.646.647.646-.647a.5.5 0 0 1 .708 0l.646.647.793-.793-1-1h-6.63a.5.5 0 0 1-.451-.285A3 3 0 0 0 4 5z"/>
                                <path d="M4 8a1 1 0 1 1-2 0 1 1 0 0 1 2 0z"/>
                            </svg>
                        </span>
                        <input v-model="acc.password" class="bg-light w-100  input-outline-node-focus input-hover border-0 py-3  " type="password" placeholder="Password" aria-label="Search">
                        <p class="d-flex justify-content-start text-danger" >{{errUsername}}</p>
                    </div>
                </div>
                <a href="/forgetpassword">Quên Mật khẩu?</a>
                  <button @click="login" class="w-100 border boder-dark bg-light py-4">
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
                  password:""
              },
              errUsername:"",
              errPassword:"",
              loginFalseMsg:false

          }
      },
      methods:{
          login(){
              if(this.validate(this.acc)) return
              this.acc.email= this.acc.username
              axios.post("/api/login",this.acc)
              .then((res)=>{
                console.log(res.data.value);
                // this.$cookie.set("acc",res.data)
                this.loginFalseMsg=false
                localStorage.setItem("user",JSON.stringify(res.data.value))
                this.$router.push("/admin")
              }).catch(()=>{
                this.loginFalseMsg=true
              })
          },
          validate(acc){
              if(!acc.username){
                  this.errUsername="Không bỏ trống Username";
                  return true
              }else{this.errUsername="";}
              if(!acc.password)  {
                  this.errPassword="Không Bỏ trống Password"
                  return true
              }else{this.errPassword=""}
              return false
          }
      }
  }
</script>

<style scoped>
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
 button:hover{
  background-color: black !important;
  color: white;
  transition: 1s;
 }
</style>
