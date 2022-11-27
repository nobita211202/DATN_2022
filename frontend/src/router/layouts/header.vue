<script>
export default {
  data() {
    return {
      currentUser: {
        name: 'admin',
        is_admin: true,
      },
    }
  },
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
                    <i class="fe fe-home"></i>
                  </a>
                </div>

                <div v-if="currentUser.name" class="dropdown d-flex profile-1">
                  <a
                    href="javascript:void(0)"
                    data-bs-toggle="dropdown"
                    class="nav-link leading-none d-flex icon"
                  >
                    <i class="fe fe-user"></i>
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
                    <a class="dropdown-item" href="/logout-all">
                      <i class="dropdown-icon fe fe-x"></i> Xoá toàn bộ session
                    </a>
                    <a class="dropdown-item" href="/logout">
                      <i class="dropdown-icon fe fe-log-out"></i> Đăng xuất
                    </a>
                  </div>
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
