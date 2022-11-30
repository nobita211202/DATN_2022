<script>
import Header from '@layouts/header.vue'
export default {
  components: { Header },
}
</script>

<template>
  <div class="position-relative">
    <div class="page">
      <div class="page-main">
        <Header />
        <div class="main-content mt-0">
          <div class="container-fluid">
            <div class="row mt-1">
              <slot />
            </div>
          </div>
        </div>
        <div class="footer border-top-0 position-absolute bottom-0 w-100 footer-1" style="background: #fff">
          <div class="container">
            <div class="row align-items-center flex-row-reverse">
              <div
                class="col-lg-12 col-sm-12 mt-3 mt-lg-0 text-center text-bold"
              >
                Đồ án tốt nghiệp 2022 - FPT Polytechnic
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
