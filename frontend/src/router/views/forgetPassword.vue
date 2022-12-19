<template>
  <div class= "position-relative d-flex wh bg">
      <div class="position-absolute w-500px shadow-lg bg-light rounded-3 top-50 start-50 translate-middle">
          <div class="d-flex flex-column justify-content-start p-5">
              <div class="d-flex w-100 ps-5 border-start border-3 border-primary">
                  <div class="px-2 my-auto">
                      <span class="fs-3">Forgot password</span>
                  </div>
                  <span class="ms-auto my-auto">
                      <a href="#">
                          Sign Up
                          <span>
                              <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-right" viewBox="0 0 16 16">
                                  <path fill-rule="evenodd" d="M1 8a.5.5 0 0 1 .5-.5h11.793l-3.147-3.146a.5.5 0 0 1 .708-.708l4 4a.5.5 0 0 1 0 .708l-4 4a.5.5 0 0 1-.708-.708L13.293 8.5H1.5A.5.5 0 0 1 1 8z"/>
                              </svg>
                          </span>
                      </a>
                  </span>
              </div>
              <div v-show="doShow">
                <a @click="doShow = false" class="btn px-0 mx-0">Không phải bạn</a>
              </div>
             <div class="pt-3">
                  <div v-show="!doShow" class="my-5">
                      <span class="position-relative w-100" href="#">
                          <input v-model="acc.username" class=" ps-5 bg-light w-100 input-focus input-outline-node-focus input-hover py-3 border-bottom boder-light px-2 me-2 border-0" type="text" placeholder="Email or usernmae" aria-label="Search">
                          <span class="position-absolute  top-50 start-0 translate-middle ps-5">
                              <svg xmlns="http://www.w3.org/2000/svg" width="25"  fill="currentColor" class="bi bi-person" viewBox="0 0 16 16">
                                  <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6Zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0Zm4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4Zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10Z"/>
                              </svg>
                          </span>
                      </span>
                      <p class="d-flex justify-content-start text-danger" >{{errUsername}}</p>
                  </div>
                  <div class="my-4" v-show="doShow">
                    Gửi password mới đến:
                    <div>
                      <input name="x" checked id="s" type="radio">
                      <label for="s"><p>Email: {{ replateString }}</p></label>
                      <!-- <input name="x" type="radio">
                      <label for=""><p>Email: {{ replateString }}</p></label> -->
                    </div>
                  </div>

                  <b-overlay

                    :show="busy"
                    rounded
                    opacity="0.6"
                    spinner-small
                    spinner-variant="dark"
                    class="d-inline-block w-100"

                  >
                    <button v-show="!doShow" @click="forgotPassword" class="w-100 border boder-dark bg-light py-4">
                        Submit
                    </button>
                    <button  v-show="doShow" @click="sendEmail" class="w-100 border boder-dark bg-light py-4">
                        {{ statusSendMail }}
                    </button>
                  </b-overlay>
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
            busy:false,
              acc:{
                  username:"",
                  email:""
              },
              errUsername:"",
              doShow:false,
              replateString:"",
              deboundSendmail:{},
              statusSendMail:"Ok",
              // enableButton:true
          }
      },
      methods:{
          forgotPassword(){
            this.busy=true
              if(this.validate(this.acc)) {
                this.busy=false
                return
              }
              axios.get(`api/forgotPassword/getExists/${this.acc.username}`)
              .then((res)=>{

                setTimeout(()=>{
                  if(res.data === this.acc.username) this.doShow=true
                  this.acc.email = Object.assign(res.data)
                  this.replateString = Object.assign(res.data)
                  if(this.acc.email !== this.acc.username){
                    var sub;
                    sub = this.acc.email.substring(3,this.acc.email.indexOf("@"))
                    this.replateString = this.acc.email.replace(sub,"***")
                  }
                  this.busy=false
                  this.doShow=true
                  this.errUsername=""
                },1000)
              })
              .catch(()=>{
                this.errUsername="Username hoặc email này không tồn tại!"
                this.busy=false
              })
          },
          validate(acc){
              if(!acc.username){
                  this.errUsername="Không bỏ trống Username";
                  return true
              }else{this.errUsername="";}
              return false
          },
          sendEmail(){
            this.busy=true
            clearTimeout(this.deboundSendmail)
            this.statusSendMail="Đang gửi email"
            setTimeout(()=>{
              axios.get(`api/forgotPassword/sendEmail/${this.acc.email}`)
            .then(()=>{
              this.statusSendMail="Đã gửi"
              this.busy=false
            })
            },3000)

          }

      }
  }
</script>

<style scoped>
  .w-500px{
      width: 400px;
  }
  .input-focus:focus{
  border-radius: 0;
  border-top: none !important;
  border-left: none !important;
  border-right: none !important;
  border-bottom:solid black 1px !important;
  outline: none;
  transition: 1s;
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
