<script>
export default {

}
</script>

<template>
  <div class="w-100 h-100" >
    <div class="row">
      <div class="col-xl-3 col-6 ">
        <div class="m-2 p-5 border shadow d-flex flex-column">
          <span class="fs-5  text-muted mb-2">Tổng số đơn hàng mới</span>
          <div class="my-auto d-flex">
            <div class="d-flex">
              <div class="bg-danger">
                <span class="p-5 ">
                  <i class="bi fs-1 text-white bi-cart2"></i>
                </span>
              </div>
              <span class="fs-1 text-danger text-blod ms-1">12</span>
            </div>
            <div class=" d-flex border-start ms-auto flex-column">
              <span class="ms-auto ps-1 ">
                <span class="fw-bold pe-1">1</span>Đã hoàn trả<br>
                <span class="fw-bold pe-1">3</span>Đã hoàn thành<br>
              </span>
            </div>
          </div>
        </div>
      </div>
      <div class="col-xl-3 col-6 ">
        <div class="m-2 p-5 border shadow d-flex flex-column">
          <span class="fs-5  text-muted mb-2">Tổng số học viên</span>
          <div class="my-auto d-flex">
            <div class="d-flex">
              <div class="bg-warning">
                <span class="p-5 ">
                  <i class="bi fs-1 text-white  bi-people"></i>
                </span>
              </div>
              <span class="fs-1 text-warning text-blod ms-1">12</span>
            </div>
            <div class=" d-flex border-start ms-auto flex-column">
              <span class="ms-auto ps-1 ">
                <span class="fw-bold pe-1">1</span>Đã hoàn trả<br>
                <span class="fw-bold pe-1">3</span>Đã hoàn thành<br>
              </span>
            </div>
          </div>
        </div>
      </div>
      <div class="col-xl-3 col-6 ">
        <div class="m-2 p-5 border shadow d-flex flex-column">
          <span class="fs-5  text-muted mb-2">Tổng số bình luận</span>
          <div class="my-auto d-flex">
            <div class="d-flex">
              <div class="bg-info">
                <span class="p-5 ">
                  <i class="bi bi-chat-left-text  fs-1 text-white"></i>
                </span>
              </div>
              <span class="fs-1 text-info text-blod ms-1">1200</span>
            </div>
            <div class=" d-flex border-start ms-auto flex-column">
              <span class="ms-auto ps-1 ">
                <span class="fw-bold pe-1">1</span>Đã hoàn trả<br>
                <span class="fw-bold pe-1">3</span>Đã hoàn thành<br>
              </span>
            </div>
          </div>
        </div>
      </div>
      <div class="col-xl-3 col-6 ">
        <div class="m-2 p-5 border shadow d-flex flex-column">
          <span class="fs-5  text-muted mb-2">Tổng số đánh giá</span>
          <div class="my-auto d-flex">
            <div class="d-flex">
              <div class="bg-success">
                <span class="p-5">
                  <i class="bi bi-bookmark-star fs-1 text-white"></i>
                </span>
              </div>
              <span class="fs-1 text-success text-blod ms-1">12</span>
            </div>
            <div class=" d-flex border-start ms-auto flex-column">
              <span class="ms-auto ps-1 ">
                <span class="fw-bold pe-1">1</span>Đã hoàn trả<br>
                <span class="fw-bold pe-1">3</span>Đã hoàn thành<br>
              </span>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-12 col-lg-6">
        <div>
          <Chart/>
        </div>
      </div>
      <div class="col-12 col-lg-6">
        <div>
          <Chart/>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-12 col-lg-6">
        <div class="">

        </div>
      </div>
      <div class="col-12 col-lg-6">
        <div>

        </div>
      </div>
    </div>

  </div>
</template>

<script>
import chart from "@components/chart.vue";
export default {
  components:{
    Chart:chart
  }
}
</script>

<style >

.border-start{
  border-left: 1px dotted black !important;
}
.shadow{
  box-shadow: 2px 0px 10px rgba(128, 128, 128, 0.235) !important;
}
</style>
