<template>
  <div class="container">
    <div class="title text-center">
      <h1>Nạp tiền</h1>
    </div>
    <div class="header border-1 row">
      <form class="col"  @submit.prevent="pushCard">
        <select class="form-select" v-model="idTelecom" @change.prevent="getCardType(idTelecom)">
        <option selected disabled>Vui lòng chọn loại thẻ</option>
        <option class="text-dark" v-for="item in telecom" :value="item.id" :key="item.id">{{item.name}}</option>
      </select>
      <select class="form-select" v-model="cardPriceId">
        <option selected disabled>Vui lòng chọn mệnh giá</option>
        <option class="text-dark" v-for="item in cardType" :value="item.id" :key="item.id">{{item.price}}</option>
      </select>
      <div class="form-floating mb-3">
        <input type="text" class="form-control" id="floatingInput"  v-model="seri" placeholder="Serial">
        <label for="floatingInput">Seri(*)</label>
      </div>
      <div class="form-floating mb-3">
        <input type="text" class="form-control" id="floatingInput"  v-model="code" placeholder="Mã thẻ nạp">
        <label for="floatingInput">Mã thẻ(*)</label>
      </div>
      <button class="btn btn-sm btn-success">Nạp tiền</button>
      </form>
      <div class="table-responsive col">
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
                {{item.cardPrice.price}}
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
</template>

<script>
import axios from '@/node_modules/axios';
export default {
  name: 'ChargeCard',
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
      }
    }
  },mounted() {
    this.getTelecom();
    this.getHistory();
  },methods: {
    getCardType (id) {
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
      axios.post('/api/card-price/push',this.card).then((response) => {
        console.log(response.data)
      })
    }
  }
}
</script>
