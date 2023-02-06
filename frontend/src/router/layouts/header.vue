<script>
import axios from '@/node_modules/axios'
import store from '@/src/state/store'
export default {
  data() {
    return {
      user: Object.assign({},store.state.auth.currentUser),

      categories: [],
      cateModel:{},
      oldPassword:"",
      newPassword:"",
      confirmPassword:"",
      errMsg:"",
      isShowAdmin:false,
      isShowUser:false
    }
  },filters:{
    formatNumber:function(value){
      return new Intl.NumberFormat().format(value)+"đ"
    },

  },
  async created(){
    axios.get('/api/category/get/parent').then(resParent => {
      this.categories = resParent.data
      console.log(this.categories);
    })
    console.log("user");
    console.log(this.user?.name !== undefined);
    if(this.user?.id !== undefined){
      this.isShowAdmin =!this.user.usersRoles.map(userRole => userRole.role.id).includes(5) || !this.user.usersRoles.map(userRole => userRole.role.id).includes(3) || !this.user.usersRoles.map(userRole => userRole.role.id).includes(4);
      this.isShowUser = true
    }
  },
  methods:{
    changePassword(){
      if(this.newPassword === this.oldPassword) {
        this.errMsg = "Mật khẩu mới trùng mật khẩu cũ"
        return
      }
      if(this.newPassword === this.confirmPassword){
        console.log(this.user );
        if(this.user.password !== this.oldPassword){
          this.errMsg = "Mật khẩu cũ không đúng"
          return
        }
        this.errMsg = ""
        this.user.password=this.newPassword
        axios.post("/api/changePassword",this.user)
        .then(()=>{
          this.errMsg=""
          this.$toast.center('<div class="px-2 py-1"><i class="text-success fs-1 mb-1 fw-bold fa-solid fa-circle-check"></i> <p>Đổi thành công</p> </div>')
          this.$bvModal.hide("modal-dmk")
        })
      }
      else{
        this.errMsg = "Mật khẩu xác nhận chưa khớp"
      }
    },
  }
}
</script>

<style scoped>
  .input{
    outline: none;
    border-radius: 0;
    border: none !important;
  }
  .h-60px{
    height: 70px;
  }
  .hover:hover{
    background-color: rgba(128, 128, 128, 0.144);
  }
  .show-child:hover > ul{
    opacity: 1 !important;
  }
  .top-0{
    top: 0 !important;
  }
  .end-100{
    left: 100% !important;
  }
</style>

<template>
  <div class="h-60px">
    <nav class="navbar  navbar-expand-lg navbar-white">
      <div class="container-fluid border-bottom py-2 bg-white fixed-top">
        <a class="navbar-brand" href="#">
          <img class="h-100" src="/assets/images/brand/logo-2.png" alt="">
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="text-dark navbar-toggler-icon"><i class="fs-3 fw-bold bi bi-list"></i></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">

          <ul class="navbar-nav d-flex flex-lg-row flex-column ms-auto mb-2 mb-lg-0">
            <li class="nav-item w-100 ">
                <a class="nav-link" href="/" tabindex="-1" aria-disabled="true">Home</a>
            </li>
            <li class="nav-item pr w-100 dropdown pe-5 hover">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Category
              </a>
              <ul  class=" dropdown-menu py-0 bg-white " >
                <span class="show-child position-relative" v-for="ct,index in categories" :key="index">
                  <li  class="pr  dropdown-item" ><a >{{ ct.name }} </a></li>
                  <ul v-if=" ct.children.length > 0 " class="position-absolute top-0 opacity-0 w-100 h-100 dropdown-menu bg-white py-0 show-child  end-100 ">
                      <li v-for="tc,index in ct.children" :key="index + 'h'" class="d-block"  >
                        <a >{{tc.name}}</a>
                      </li>
                  </ul>
                </span>
              </ul>
            </li>
            <li class="nav-item w-100">
                <a class="nav-link" href="/contact" tabindex="-1" aria-disabled="true">Contact</a>
            </li>



          </ul>
          <form class="d-flex w-100 py-1 border mx-lg-auto">
            <span class="px-2">
              <i class="fs-4 bi bi-search"></i>
            </span>
            <input class="w-75 input" type="search" placeholder="Search" aria-label="Search">
          </form>
          <div class="d-flex ps-lg-3 w-100">
              <!-- SEARCH -->
              <li class="nav-item ms-auto">
                <a class="nav-link" href="/cart" tabindex="-1" aria-disabled="true">
                  <i class="text-dark fs-3 bi bi-cart2"></i>
                </a>
              </li>
                  <div class=" d-flex order-lg-2">
                    <div v-if="isShowUser" class="position-relative d-flex profile-1">
                      <a
                        href="javascript:void(0)"
                        data-bs-toggle="dropdown"
                        class="nav-link leading-none d-flex icon"
                      >
                        <i class="fs-3 text-dark fe fe-user"></i>
                      </a>
                      <div
                        class="position-absolute dropdown-menu dropdown-menu-end dropdown-menu-arrow"
                        style="width: 220px"
                      >
                        <div class="drop-heading">
                          <div class="text-center d-flex flex-column">
                            <h5 class="text-dark mb-0 fs-14 fw-semibold">
                              {{ user.name }}</h5
                            >
                            <small class="text-muted"
                              >Số dư: {{ user.money | formatNumber }}</small
                            >
                            <a href="/user/charge-card" class="btn btn-primary p-0">Nạp tiền</a>
                          </div>
                        </div>
                        <div class="dropdown-divider m-0"></div>
                        <a
                          v-if="isShowAdmin"
                          href="/admin"
                          class="dropdown-item"
                        >
                          <i class="dropdown-icon fe fe-settings"></i> Quản lý hệ
                          thống
                        </a>
                        <!--                    <a href="javascript:void(0)" class="dropdown-item" @click="$bvModal.show('bv-employee-management')">-->
                        <!--                      <i class="dropdown-icon fe fe-users"></i> Quản lý nhân viên-->
                        <!--                    </a>-->
                        <a href="/profile" class="dropdown-item">
                          <i class="dropdown-icon fe fe-user"></i> Quản lý tài khoản
                        </a>
                        <a href="/myCourse" class="dropdown-item">
                          <i class="dropdown-icon fe fe-user"></i> Khóa học của tôi
                        </a>
                        <a  class="dropdown-item btn " @click="$bvModal.show('modal-dmk')">
                          <i class="dropdown-icon fe fe-x"></i> Đổi mật khẩu
                        </a>
                        <a   @click="$bvModal.show('modal-logout')"  class=" btn dropdown-item" >
                          <i class="dropdown-icon fe fe-log-out"></i> Đăng xuất
                        </a>
                      </div>
                      <b-modal
                        id="modal-dmk"
                        size="lg"
                        centered
                        scrollable
                        hide-backdrop
                        hide-header-close
                      >
                        <template v-slot:modal-title> Đổi mật khẩu </template>
                        <b-form >
                            <b-form-group label="Mật khẩu cũ" label-for="newPass">
                              <b-form-input
                                id="newPass"
                                v-model="oldPassword"
                                type="password"
                              ></b-form-input>
                            </b-form-group>
                            <b-form-group label="Mật khẩu mới" label-for="pass">
                              <b-form-input
                                id="pass"
                                v-model="newPassword"
                                type="password"
                              ></b-form-input>
                            </b-form-group>
                            <b-form-group label="Xác nhận mật khẩu mới" label-for="cpass">
                              <b-form-input
                                id="cpass"
                                type="password"
                                v-model="confirmPassword"
                              ></b-form-input>
                            </b-form-group>
                            <b-row>
                              <p class="text-danger">{{ errMsg }}</p>
                            </b-row>
                        </b-form>
                        <template v-slot:modal-footer>
                          <b-button type="button" @click="changePassword" variant="primary" >
                            Xác nhận</b-button
                          >
                        </template>
                      </b-modal>
                      <b-modal
                        id="modal-logout"
                        size="sm"
                        centered
                        scrollable
                        hide-backdrop
                        hide-header-close
                      >
                        <template v-slot:modal-title>Đăng xuất </template>
                        <b-form >
                            <div class="mb-5">
                              <p>Xác nhận đăng xuất</p>
                            </div>
                        </b-form>
                        <template v-slot:modal-footer class="d-flex mb-5">
                          <b-button class="mx-auto" type="button" @click="$bvModal.hide('modal-logout')" variant="primary" >
                          Đóng</b-button
                          >
                          <a href="/logout" class="mx-auto btn btn-danger" variant="danger" >
                            Xác nhận</a
                          >
                        </template>
                      </b-modal>
                    </div>
                    <div v-else class="dropdown d-flex profile-1">
                      <a
                        href="/login"
                        class="nav-link leading-none d-flex icon"
                        title="Đăng nhập"
                      >
                        <b-button variant="">Đăng nhập</b-button>
                      </a>
                    </div>
                  </div>
            </div>

        </div>
      </div>
    </nav>
  </div>
</template>
