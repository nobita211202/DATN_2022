
<template>
  <div class="w-100 h-100" >
    <div class="row">
      <span class="text-muted fs-3 fw-bold">Thống kê chung</span>
    </div>
    <div class="row">

      <div class="row">
        <div class="col-6">
          <select name="" v-model="selectTypeTk"  class="w-100 ms-2 form-select" id="">
            <option value="all">Tất cả</option>
            <option @click="getCountAll" value="day">Ngày</option>
          </select>
        </div>
        <div v-if="selectTypeTk === 'day'" class="w-50  d-flex">
          <div class="me-auto d-flex">
            <span class="my-auto fw-blod px-2 fs-6 text-muted">Từ</span>
            <input class="form-control" v-model="from1" type="date">
          </div>
          <div class="d-flex">
            <span class="my-auto fw-blod px-2 fs-6 text-muted">Đến</span>
            <input class="form-control" v-model="to1" type="date">
          </div>
          <b-overlay variant="" :show="loading">
            <button @click="getCountByDate" class="btn btn-primary">Lọc</button>
          </b-overlay>
        </div>
      </div>
     <div class="col-lg-4 col-xxl-3 col-4 "> <b-overlay :show="loading">

          <div class="m-2 p-5 h-100 border shadow d-flex flex-column">
            <span class="fs-5 text-max text-muted mb-2">Tổng số học viên</span>
            <div class="my-auto d-flex">
              <div class="d-flex">
                <div class="d-flex  bg-warning">
                  <span class="px-4 m-auto ">
                    <i class="bi fs-3 text-white  bi-people"></i>
                  </span>
                </div>
                <span class="fs-1 text-warning text-blod ms-1">{{ thongKeChung.hv }}</span>
              </div>
            </div>
          </div>
      </b-overlay>
    </div>

      <div class="col-lg-4 col-xxl-3 col-4 ">
      <b-overlay :show="loading">

          <div class="m-2 p-5 h-100 border shadow d-flex flex-column">
            <span class="fs-5 text-max text-muted mb-2">Tổng doanh thu</span>
            <div class="my-auto d-flex">
              <div class="d-flex">
                <div class="d-flex  bg-info">
                  <span class="px-4 m-auto ">
                    <i class="bi bi-cash-stack  fs-3 text-white"></i>
                    <!-- <i class="bi "></i> -->
                  </span>
                </div>
                <span class="fs-1 text-info text-blod ms-1">{{ thongKeChung.dt | formatNumber }}</span>
              </div>
            </div>
          </div>
      </b-overlay>
    </div>

      <div class="col-lg-4 col-xxl-3 col-4 ">
      <b-overlay :show="loading">

          <div class="m-2 p-5 h-100 border shadow d-flex flex-column">
            <span class="fs-5 text-max text-muted mb-2">Tổng số đánh giá</span>
            <div class="my-auto d-flex">
              <div class="d-flex">
                <div class="d-flex  bg-success">
                  <span class="px-4 m-auto">
                    <i class="bi bi-bookmark-star fs-3 text-white"></i>
                  </span>
                </div>
                <span class="fs-1 text-success text-blod ms-1">{{thongKeChung.dg}}</span>
              </div>
            </div>
          </div>
      </b-overlay>
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


      loading:false,
      today:{},
      data:[],
      labels:[],
      tkQuy:{},
      tkNam:{},
      from:null,
      to:null,
      thongKeChung:{
        hv:0,
        dg:0,
        dt:0,
      },
      selectTypeTk:"all",
      from1:null,
      to1:null
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
    this.getCountAll()
  },
  methods:{
    getCountByDate(){
      this.loading = true
      axios.get(`/api/statistical/getCountByDate/${this.from1}/${this.to1}`)
      .then(res=>{
        this.thongKeChung.hv=res.data[0]
        this.thongKeChung.dt=res.data[1]
        this.thongKeChung.dg=res.data[2]
      })
      .finally(()=>{this.loading = false})
    },
    getCountAll(){
      this.loading = true
      axios.get(`api/statistical/getCountAll`)
      .then(res=>{
        this.thongKeChung.hv= res.data[0]
        this.thongKeChung.dt= res.data[1]
        this.thongKeChung.dg= res.data[2]
      })
      .finally(()=>{this.loading = false})
    },
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
        this.data =[]
        dates.forEach(date =>{
          var dt = null;
          res.data.forEach(data=>{
            if (data.date === date){
              dt = data.doanhthu
            }
          })
          if(dt) this.data.push(dt)
          else this.data.push(0)
          console.log("data");
          console.log(dt);
          console.log(this.data);
        });
        this.labels = dates
        console.log("date");
        console.log(this.data);
      })
    },
    getDaysArray : function(start, end) {
        for(var arr=[],dt=new Date(start); dt<=new Date(end); dt.setDate(dt.getDate()+1)){
            arr.push(this.getDay(new Date(dt)));
        }
        return arr;
    }
  },
  filters:{
      formatNumber:function(value){
        return new Intl.NumberFormat().format(value)+"đ"
      }
  },
  watch:{
    selectTypeTk(){
      if (this.selectTypeTk === 'all')
      this.getCountAll()
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
