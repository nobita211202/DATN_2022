<template>
  <Main>
    <div class="text-center text-white bg-dark py-5">
        <h1>Nạp tiền</h1>
      </div>
    <div class="container-xxl">
      <div class="loading" v-show="isShow">
              <div class="spinner-border text-primary spinner-center" role="status">
              </div>
          </div>

      <div class="header border-1 my-4 row">
        <form class="col-12 col-lg-6 mt-2"  @submit.prevent="pushCard">
         <div class="mt-3">
          <label for="">Loại thẻ</label>
            <select class="form-select " v-model="idTelecom" @change.prevent="getCardType(idTelecom)">
            <option selected disabled>Vui lòng chọn loại thẻ</option>
            <option class="text-dark" v-for="item in telecom" :value="item.id" :key="item.id">{{item.name}}</option>
            </select>
         </div>
        <div class="my-3">
          <label for="">Mệnh giá thẻ</label>
          <select class="form-select " v-model="card.cardPriceId">
            <option selected disabled>Vui lòng chọn mệnh giá</option>
            <option class="text-dark" v-for="item in cardType" :value="item.id" :key="item.id">{{item.price | formatNumber}}</option>
          </select>
        </div>
        <div class="form-floating mb-3">
          <input type="text" class="form-control" id="floatingInput"  v-model="card.seri" placeholder="Serial">
          <label for="floatingInput">Seri(*)</label>
        </div>
        <div class="form-floating mb-3">
          <input type="text" class="form-control" id="floatingInput"  v-model="card.code" placeholder="Mã thẻ nạp">
          <label for="floatingInput">Mã thẻ(*)</label>
        </div>
        <button class="btn btn-sm btn-success">Nạp tiền</button>
        </form>
        <div class="table-responsive col-12 col-lg-6 mt-2">
          <table class="table table-borderd table-hover table-striped">
            <thead>
              <tr>
                <th class="text-center text-dark" colspan="6">
                  Lịch sử nạp thẻ
                </th>
              </tr>
              <tr>
                <th class="text-center text-dark">
                  Mã giao dịch
                </th>
                <th class="text-center text-dark">
                  Loại thẻ
                </th>
                <th class="text-center text-dark">
                  Mệnh giá
                </th>
                <th class="text-center text-dark">
                  Thời gian
                </th>
                <th class="text-center text-dark">
                  Trạng thái
                </th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="item in history" :key="item.id">
                <td class="text-center text-danger">
                  {{item.requestCode}}
                </td>
                <td class="text-center text-dark">
                  {{item.cardPrice.telecom.name}}
                </td>
                <td class="text-center text-dark">
                  {{item.cardPrice.price | formatNumber}}
                </td>
                <td class="text-center text-dark">
                  {{item.created |formatDate}}
                </td>
                <td class="text-center text-dark p-1 mt-5 m-lg-3" :class="getClass(item)">
                  {{ item.status === 0 ? 'Đã duyệt' : item.status === 1 ? 'Đang xử lý' : 'Thẻ sai'}}
                </td>
              </tr>

            </tbody>
          </table>
        </div>
      </div>
    </div>
  </Main>
</template>

<script>
import axios from '@/node_modules/axios';
import Main from "@layouts/main.vue"
import io from '@/node_modules/socket.io-client';
const socket = io('http://fpolycourse.xyz:3636');
export default {
  name: 'ChargeCard',
  components:{
    Main
  },
  data () {
    return {
      cardType: [
      ],
      telecom : [],
      idTelecom: '',
      history: [],
      getClass(item) {
        if (item.status === 0) {
          return 'badge bg-success text-dark'
        } else if (item.status === 1) {
          return 'badge bg-warning text-dark'
        } else {
          return 'badge bg-danger text-dark'
        }
      },card : {
        userId : 15,
        cardPriceId : '',
        seri : '',
        code : ''
      },isShow : false
    }
  },created() {
    this.getTelecom();
    this.getHistory();
    this.reloadWhenChangeData();
  },methods: {
    getCardType(id) {
      axios.get('/api/card-price/find-by-telecom/'+id).then((response) => {
        this.cardType = response.data
      })
    },
    getTelecom () {
      axios.get('/api/card-price/get-all-telecom').then((response) => {
        this.telecom = response.data
      })
    },getHistory () {
      axios.get('/api/card-price/get-history/15').then((response) => {
        this.history = response.data
      })
    },pushCard(){
      this.show=true;
      axios.post('/api/auto-card/push?userId='+this.card.userId+"&cardPriceId="+this.card.cardPriceId+"&seri="+this.card.seri+"&code="+this.card.code).then((response) => {
        console.log(response.data)
        this.getHistory();
        if(response.data.trans_id === null || response.data.trans_id === undefined){
          axios.put("/api/auto-card/update-status/" + response.data.request_id).then(res => {
            this.getHistory();
            console.log(res.data)
          })
        }
      })
      setTimeout(() => {
          this.isShow = false;
        }, 3000);
    },reloadWhenChangeData(){
      socket.on('reload', (data) => {
        if(data !== null || data !== undefined){
          this.getHistory();
        }
      })
    }
  },
  filters:{
    formatNumber:function(value){
      return new Intl.NumberFormat().format(value)+"đ"
    }
  }
}
</script>

<style scoped>
.loading {
    background: rgba(0, 0, 0, .5);
    width: 100%;
    height: 100%;
    position: fixed;
    top: 0;
    left: 0;
    z-index: 999;
}

.spinner-center {
    top: 50%;
    left: 50%;
    position: absolute;
}
</style>
