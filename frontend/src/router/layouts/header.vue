<script>
import axios from '@/node_modules/axios'

export default {
  data() {
    return {
      currentUser: {
        name: 'admin',
        is_admin: true,

      },
      oldPassword:"",
      newPassword:"",
      confirmPassword:"",
      errMsg:"",
    }
  },
  methods:{
    changePassword(){
      if(this.newPassword === this.confirmPassword){
        var user= JSON.parse(localStorage.getItem("user"))
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

<style></style>

<template>
  <div class="header sticky hor-header">
    <div class="main-container container">
      <div class="d-flex">
        <!-- sidebar-toggle-->
        <a class="logo-horizontal" href="/" title="Trang chủ 2FBUFF">
          <img
            src="/assets/images/brand/logo.png"
            class="header-brand-img desktop-logo"
            alt="logo"
          />
          <img
            src="/assets/images/brand/logo-3.png"
            class="header-brand-img light-logo1"
            alt="logo"
          />
        </a>
        <div class="d-flex order-lg-2 ms-auto header-right-icons">
          <!-- SEARCH -->
          <button
            class="navbar-toggler navresponsive-toggler d-lg-none ms-auto"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarSupportedContent-4"
            aria-controls="navbarSupportedContent-4"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span class="navbar-toggler-icon fe fe-align-justify"></span>
          </button>
          <div class="navbar navbar-collapse responsive-navbar p-0">
            <div id="navbarSupportedContent-4" class="collapse navbar-collapse">
              <div class="d-flex order-lg-2">
                <div
                  v-if="currentUser.name"
                  class="dropdown d-flex notifications"
                >
                  <a
                    href="/"
                    class="nav-link leading-none d-flex icon"
                    title="Trang chủ"
                  >
                    <i class="text-dark fe fe-home"></i>
                  </a>
                </div>

                <div v-if="currentUser.name" class="dropdown d-flex profile-1">
                  <a
                    href="javascript:void(0)"
                    data-bs-toggle="dropdown"
                    class="nav-link leading-none d-flex icon"
                  >
                    <i class="text-dark fe fe-user"></i>
                  </a>
                  <div
                    class="dropdown-menu dropdown-menu-end dropdown-menu-arrow"
                    style="width: 220px"
                  >
                    <div class="drop-heading">
                      <div class="text-center">
                        <h5 class="text-dark mb-0 fs-14 fw-semibold">
                          {{ currentUser.name }}</h5
                        >
                        <small class="text-muted"
                          >Số dư: {{ currentUser.name }}</small
                        >
                      </div>
                    </div>
                    <div class="dropdown-divider m-0"></div>
                    <a
                      v-if="currentUser.is_admin"
                      href="/admin"
                      class="dropdown-item"
                    >
                      <i class="dropdown-icon fe fe-settings"></i> Quản lý hệ
                      thống
                    </a>
                    <!--                    <a href="javascript:void(0)" class="dropdown-item" @click="$bvModal.show('bv-employee-management')">-->
                    <!--                      <i class="dropdown-icon fe fe-users"></i> Quản lý nhân viên-->
                    <!--                    </a>-->
                    <a href="/my-profile" class="dropdown-item">
                      <i class="dropdown-icon fe fe-user"></i> Quản lý tài khoản
                    </a>
                    <a class="dropdown-item btn " @click="$bvModal.show('modal-dmk')">
                      <i class="dropdown-icon fe fe-x"></i> Đổi mật khẩu
                    </a>
                    <a class="dropdown-item" href="/logout">
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
                </div>
                <div v-else class="dropdown d-flex profile-1">
                  <a
                    href="/login"
                    class="nav-link leading-none d-flex icon"
                    title="Đăng nhập"
                  >
                    <i class="fe fe-user"></i>
                  </a>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div> </div>
  </div>
</template>
