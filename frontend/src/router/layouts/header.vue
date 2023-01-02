<script>
import axios from '@/node_modules/axios'
import store from '@/src/state/store'
console.log(store.state);
export default {
  data() {
    return {
      user: store.state.auth.currentUser,
      currentUser: {
        name: 'admin',
        is_admin: true,

      },
      oldPassword:"",
      newPassword:"",
      confirmPassword:"",
      errMsg:"",
    }
  },filters:{
    formatNumber:function(value){
      return new Intl.NumberFormat().format(value)+"đ"
    },

  },
  methods:{
    changePassword(){
      if(this.newPassword === this.oldPassword) {
        this.errMsg = "Mật khẩu mới trùng mật khẩu cũ"
        return
      }
      if(this.newPassword === this.confirmPassword){
        var user = JSON.parse(localStorage.getItem("user"))
        console.log(user );
        if(user.password !== this.oldPassword){
          this.errMsg = "Mật khẩu cũ không đúng"
          return
        }
        this.errMsg = ""
        user.password=this.newPassword
        axios.post("/api/changePassword",user)
        .then(()=>{
          this.errMsg=""
          localStorage.setItem("user",JSON.stringify(user))
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
    height: 40px;
  }
</style>

<template>
  <div class="h-60px">
    <nav class="navbar  navbar-expand-lg navbar-white">
      <div class="container-fluid py-2 bg-white fixed-top">
        <a class="navbar-brand" href="#">
          <img class="h-100" src="/assets/images/brand/logo-2.png" alt="">
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="text-dark navbar-toggler-icon"><i class="fs-3 fw-bold bi bi-list"></i></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <form class="d-flex w-100 py-1 border mx-lg-auto">
            <span class="px-2">
              <i class="fs-4 bi bi-search"></i>
            </span>
            <input class="w-75 input" type="search" placeholder="Search" aria-label="Search">
          </form>
          <ul class="navbar-nav d-flex flex-lg-row flex-column ms-auto mb-2 mb-lg-0">
            <li class="nav-item w-100 ">
                <a class="nav-link" href="/" tabindex="-1" aria-disabled="true">Home</a>
            </li>
            <li class="nav-item w-100">
                <a class="nav-link" href="/contact" tabindex="-1" aria-disabled="true">Contact</a>
            </li>

            <div class="d-flex ps-lg-3 w-100">
              <!-- SEARCH -->
              <li class="nav-item ms-auto">
                <a class="nav-link" href="/cart" tabindex="-1" aria-disabled="true">
                  <i class="text-dark fs-3 bi bi-cart2"></i>
                </a>
              </li>
                  <div class=" d-flex order-lg-2">
                    <div v-if="user" class="dropdown position-relative d-flex profile-1">
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
                          v-if="!user.usersRoles.map(userRole => userRole.role.id).includes(5)"
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

          </ul>

        </div>
      </div>
    </nav>
  </div>
</template>
