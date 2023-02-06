
<template>
  <div class="w-100 h-100" >
    <div class="row">
      <span class="text-muted fs-3 fw-bold">Thống kê chung</span>
    </div>
    <div class="row">
      <div class="col-6">
        <select name=""  class="w-100 ms-2 form-select" id="">
          <option value="">Tất cả</option>
          <option value="">Ngày</option>
        </select>
      </div>
      <div class="w-50 col-6 d-flex">
        <div class="me-auto d-flex">
          <span class="my-auto fw-blod px-2 fs-6 text-muted">Từ</span>
          <input class="form-control" type="date">
        </div>
        <div class="d-flex">
          <span class="my-auto fw-blod px-2 fs-6 text-muted">Đến</span>
          <input class="form-control" type="date">
        </div>
      </div>
      <div class="col-lg-4 col-xxl-3 col-4 ">
        <div class="m-2 p-5 h-100 border shadow d-flex flex-column">
          <span class="fs-5 text-max text-muted mb-2">Tổng số học viên</span>
          <div class="my-auto d-flex">
            <div class="d-flex">
              <div class="d-flex  bg-warning">
                <span class="px-4 m-auto ">
                  <i class="bi fs-3 text-white  bi-people"></i>
                </span>
              </div>
              <span class="fs-1 text-warning text-blod ms-1">12</span>
            </div>
          </div>

        </div>

      </div>
      <div class="col-lg-4 col-xxl-3 col-4 ">
        <div class="m-2 p-5 h-100 border shadow d-flex flex-column">
          <span class="fs-5 text-max text-muted mb-2">Tổng số bình luận</span>
          <div class="my-auto d-flex">
            <div class="d-flex">
              <div class="d-flex  bg-info">
                <span class="px-4 m-auto ">
                  <i class="bi bi-chat-left-text  fs-3 text-white"></i>
                </span>
              </div>
              <span class="fs-1 text-info text-blod ms-1">1200</span>
            </div>
          </div>
        </div>
      </div>
      <div class="col-lg-4 col-xxl-3 col-4 ">
        <div class="m-2 p-5 h-100 border shadow d-flex flex-column">
          <span class="fs-5 text-max text-muted mb-2">Tổng số đánh giá</span>
          <div class="my-auto d-flex">
            <div class="d-flex">
              <div class="d-flex  bg-success">
                <span class="px-4 m-auto">
                  <i class="bi bi-bookmark-star fs-3 text-white"></i>
                </span>
              </div>
              <span class="fs-1 text-success text-blod ms-1">12</span>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="row mt-5">
        <div class="fs-3 text-muted fw-bold">Biểu đồ biến động doanh thu</div>
        <div class="col-12 col-lg-9  bg-light px-auto">
          <Chart :data="data" :labels="labels" class=""/>
        </div>
        <div class="col-lg-3 pt-2 f-flex flex-column flex-lg-row bg-light col-12">
          <span>Thống kê theo</span>
          <select name="" class="form-select" id="">
            <option value="">Ngày</option>
            <option value="">Tháng</option>
            <option value="">Năm</option>
            <option value="">Quý</option>
          </select>
          <span>Từ</span>
          <input class="form-control" v-model="from" min="" :max="today"  type="date">
          <span>Đến</span>
          <input class="form-control"  min="" v-model="to" :max="today"  type="date">
          <button class="btn btn-primary mt-2" @click="getThongKeNgay">Thống kê</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "@/node_modules/axios";
import chart from "@components/chart.vue";
export default {
  components:{
    Chart:chart
  },
  data(){
    return{
      today:{},
      data:[],
      labels:[],
      tkQuy:{},
      tkNam:{},
      from:null,
      to:null
    }
  },
  created(){
    const date = new Date()
    this.getDay(date)
    this.to = this.getDay(date)
    console.log(this.to);
    date.setDate(date.getDate()-10)
    this.from = this.getDay(date)
    console.log(this.from);
    this.getThongKeNgay()
  },
  methods:{
    getDay(date){
      const month = date.getMonth() < 9 ? `0${date.getMonth()+1}`: date.getMonth() + 1
      const year = date.getFullYear()
      const day = date.getDate() < 10 ? `0${date.getDate()}` : date.getDate()
      return `${year}-${month}-${day}`
    },
    getThongKeNgay(){
      console.log(`/api/statistical/getByDate/${this.from}/${this.to}`);
      axios.get(`/api/statistical/getByDate/${this.from}/${this.to}`)
      .then(res =>{
        // this.data = res.data.map(data => data.doanhthu);
        // this.labels = res.data.map(data => data.date)
        const dates = this.getDaysArray(this.from,this.to);
        res.data.forEach(data =>{
          dates.forEach(date=>{
            if (data.date === date){
              this.data.push(data.doanhthu)
            }
          })
          this.data.push(0)
        });
        this.labels = dates

      })
    },
    getDaysArray : function(start, end) {
        for(var arr=[],dt=new Date(start); dt<=new Date(end); dt.setDate(dt.getDate()+1)){
            arr.push(this.getDay(new Date(dt)));
        }
        return arr;
    }
  }
}
</script>

<style>

.border-start{
  border-left: 1px dotted black !important;
}
.shadow{
  box-shadow: 2px 0px 10px rgba(128, 128, 128, 0.235) !important;
}
.text-max{
  display: block;
  	display: -webkit-box;
  	-webkit-line-clamp: 1;
  	-webkit-box-orient: vertical;
  	overflow: hidden;
    line-height: 20px !important;
  	text-overflow: ellipsis;
    cursor: pointer;
}
</style>
