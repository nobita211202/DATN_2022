<script>
import axios from "@/node_modules/axios"

export default {
  components:{
  },
  data() {
    return {
      overlayTB:null,
      debounceSearch:null,
      showSendMail:false,
      contacts: {
        isBusy: false,
        data: [],
        fields: [
          { key: 'subject', label: 'Vấn đề', sortable: true },
          { key: 'fullname', label: 'Họ và tên ', sortable: true },
          { key: 'phone', label: 'SDT', sortable: true },
          { key: 'created', label: 'Ngày nhận' },
          { key: 'action', label: 'Hành động' },
        ],
        meta: {
          pagination: {
            total: 1,
            count: 1,
          },
        },
      },
      mail:{
        title:"Phản hồi của bạn về web khóa học",
        email:"",
        conten:""
      },
      busy: false,
      formAddcontact: { },
      formEditcontact: {},
      contactDelete: {},
      currentPage: 1,
      categoriesPerPage: 5,
      txtSearch: '',
    }
  },

  watch:{
    txtSearch(){
      this.overlayTB = true
      clearTimeout(this.debounceSearch)
      this.debounceSearch = setTimeout(
        ()=>{
          this.contacts.data = []
          axios.get(`/api/contact/search/${this.txtSearch}`)
          .then((res)=>{
            console.log(res.data);
            this.contacts.data = res.data
            this.overlayTB= false
          })
          .catch( er => {
            console.log(er);
            this.overlayTB= false})

        },1000
      )
    }
  },

  created() {
    this.getContact()
  },

  methods: {
    getContact(){
      this.overlayTB= true
      axios.get('/api/contact/get')
      .then(res =>{
        this.contacts.data = res.data
      })
      .finally(()=> {this.overlayTB = false})
    },
    getContacted(){
      this.overlayTB= true
      axios.get('/api/contact/contacted')
      .then(res =>{
        this.contacts.data = res.data
      })
      .finally(()=> {this.overlayTB = false})
    },
    tryDeleteContact(id){
      axios.post(`/api/contact/delete/${id}`)
    },
    setStatus(contact){
      axios.post(`/api/contact/afterSendMail`,contact)
      this.showSendMail = false
    },
    trySendMail(){
      var formData = new FormData()
      formData.append("email",this.mail.email)
      formData.append("mail",this.mail.conten)
      formData.append("title",this.mail.title)
      axios.post(`/api/contact/sendMail`,formData)
      .then(res =>{
        this.setStatus(res.data)
      })
    },
    showMail(email){
      this.showSendMail = true
      this.mail.email = email
    },
    hideMail(){
      this.showSendMail = false
    }
  },
  filters:{
    formatNumber:function(value){
      return new Intl.NumberFormat().format(value)+"đ"
    },

  },
}
</script>

<template>
  <div>

    <div>
      <ul class="nav nav-tabs" id="myTab" role="tablist">
          <li class="nav-item" role="presentation">
            <button class="nav-link active" id="home-tab" data-bs-toggle="tab" @click="getContact" data-bs-target="#home-tab-pane" type="button" role="tab" aria-controls="home-tab-pane" aria-selected="true">Chưa sử lý</button>
          </li>
          <li class="nav-item" role="presentation">
            <button class="nav-link" id="profile-tab" data-bs-toggle="tab" @click="getContacted" data-bs-target="#profile-tab-pane" type="button" role="tab" aria-controls="profile-tab-pane" aria-selected="false">Đã sử lý</button>
          </li>
        </ul>
    </div>
    <b-overlay
      :show="overlayTB"
      rounded="sm"
    >
      <b-table
        striped
        hover
        responsive
        :items="contacts.data"
        :fields="contacts.fields"
        :busy="contacts.isBusy"
      >
        <template v-slot:cell(subject) = "contact">
          <div class="d-flex flex-column">
            <span class="fw-bold text-max">Vấn đề gặp phải: {{ contact.item.contactSubject.name }}</span>
            <span class="">email: {{ contact.item.email }}</span>
            <span class=" text-max">Tin nhắn: {{ contact.item.describe }}</span>
          </div>
        </template>
        <template v-slot:cell(created) = "contact">
          {{ contact.item.created | formatDate }}
        </template>
        <template v-slot:cell(action)="contact">
          <b-button
            class="mr-5"
            variant="outline-danger"
            ><b-icon icon="trash-fill" aria-hidden="true"></b-icon> Xoá</b-button>
            <b-button
            class="mr-5"
            variant="outline-primary"
            @click="showMail(contact.item.email)"
            ><b-icon icon="arrow90deg-right" aria-hidden="true"></b-icon> Phản hồi mail</b-button>

        </template>
      </b-table>
    </b-overlay>

    <b-overlay class="w-100" :show="showSendMail" no-wrap>
      <template class="" v-slot:overlay>
        <div
          ref="dialog"
          tabindex="-1"
          role="dialog"
          aria-modal="false"
          class="text-center p3"
        >
          <div class="fs-2 mb-5">Phản hồi mail</div>
          <div class="mb-3">
            <input class="form-control my-1" v-model = "mail.title" placeholder="Tiêu đè" type="text">
            <ckeditor v-model="mail.conten" ></ckeditor>
          </div>
          <div style="justify-content: center">
            <b-button variant="outline-default" class="mr-3" @click="hideMail"
              >Đóng</b-button
            >
            <b-button
              variant="outline-danger"
              class="mr-3"
              @click="trySendMail"
              >Gửi mail</b-button
            >
          </div>
        </div>
      </template>
    </b-overlay>

    <b-overlay :show="busy" no-wrap>
      <template v-slot:overlay>
        <div
          ref="dialog"
          tabindex="-1"
          role="dialog"
          aria-modal="false"
          class="text-center p3"
        >
          <p>
            <strong id="form-confirm-label">
              Bạn có chắc muốn <span class="text-danger">xoá</span
              ><span class="text-bold"> {{ categoryDelete.name_ }}</span>
            </strong>
          </p>
          <div style="justify-content: center">
            <b-button variant="outline-default" class="mr-3" @click="onCancel"
              >Huỷ</b-button
            >
            <b-button
              variant="outline-danger"
              class="mr-3"
              @click="tryRemoveCategory"
              >Xoá</b-button
            >

          </div>
        </div>
      </template>
    </b-overlay>
  </div>
</template>

<style>
  .text-max{
    display: block;
  	display: -webkit-box;
  	-webkit-line-clamp: 1;
  	-webkit-box-orient: vertical;
  	overflow: hidden;
    line-height: 16px;
  	text-overflow: ellipsis;
    cursor: pointer;
}
</style>
