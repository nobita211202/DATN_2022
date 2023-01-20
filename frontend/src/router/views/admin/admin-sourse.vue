<script>
import axios from '@/node_modules/axios'
const url="/api/"
export default {
  components:{
  },
  created(){
    this.getCourseToAccept()
  },
  data() {
    var mail = require("@assets/mailMau.json")
    return {
      videos:{
        data:[],
        fields:[
          { key: 'title', label: 'Bài giảng'},
        ]
      },
      mail:mail,
      debounceSearch:null,
      overlayTB:null,
    courseAtt:[],
    btHide:true,
    table: {
        isBusy: false,
        data: [],
        fields: [
          { key: 'image', label: 'img', sortable: true },
          { key: 'name', label: 'Tên khóa học' },
          { key: 'studyTime', label: 'Học trong', sortable: true },
          { key: 'price', label: 'Giá bán', sortable: true },
          { key: 'created', label: 'Ngày tạo' },
          { key: 'creator', label: 'Người tạo' },
          { key: 'action', label: 'Hành động' },
        ],
        meta: {
          pagination: {
            total: 1,
            count: 1,
            per_page: 0,
          },
        },
      },
      busy: false,
      courseDelete: {},
      showCourse: {},
      currentPage: 1,
      rolesPerPage: 5,
      txtSearch: '',
      video:{
        title:"",
        link:"",
      },
      formAddVideo:{},
      btEditVideo:true,
      thongBao:{
        isAddShow:false,
        isUpdateShow:false,
        isDeleteShow:false,
      }
    }
  },


  methods: {
    chooseImg(even) {
      this.imageSelected = even.target.files[0]
      this.imageUP=this.imageSelected
      this.fileRender.readAsDataURL(this.imageSelected)
    },
    sendMai(){
      this.mail.content.replace("*name",this.courseDelete.user.name)
      this.mail.content.replace("*nameCourse",this.courseDelete.name)
    },
    mdShowCourse(item) {
      this.showCourse = Object.assign({}, item)
      this.$bvModal.show('modal-show-course')
    },
    accept() {

      axios.get(`${url}course/accept/${this.showCourse.id}`)
      .then(()=>{
        this.table.data = this.table.data.filter(
          (e) => e.id !== this.courseDelete.id
        )
      })
      this.busy = false
      this.showCourse = {}
      this.$bvModal.hide('modal-show-course')
    },


    onRemoveCourse(item) {

      this.courseDelete = item
      this.busy = true
      this.sendMai()
      // this.table.data = this.roles.data.filter((e) => e.id !== id)
    },
    onCancel() {
      this.busy = false
      this.courseDelete = {}
    },

    notAccept() {

      axios.get(`${url}course/not_accept/${this.courseDelete.id}`)
      .then(()=>{
        var formData = new FormData();
        formData.append("mail",this.mail.content)
        formData.append("email",this.courseDelete.user.email)
        axios.post(`${url}mail/sendMail`,formData)
        .then(_=> {
          this.table.data = this.table.data.filter(
            (e) => e.id !== this.courseDelete.id
          )
        })
      })

      this.busy = false
      this.courseDelete = {}
    },
    getImg(name){
      return `${axios.defaults.baseURL}${url}image/get/${name}`
    },
    async getCourseAccept(){
      axios.get(`${url}course/get/accept`)
      .then((res)=>{
        this.btHide= false
        this.table.data = res.data
        console.log(res.data);
      })
    },
    async getCourseNotAccept(){
      axios.get(`${url}course/get/not_accept`)
      .then((res)=>{
        this.table.data = res.data
        console.log(res.data);
      })
    },
    async getCourseToAccept(){
      axios.get(`${url}course/get/toAccept`)
      .then((res)=>{
        this.btHide = true
        this.table.data = res.data
        console.log(res.data);
      })
    },
    mdAddVideo(course){
      this.showCourse = Object.assign({},course)
      axios.get(`${url}video/getByCourseId/${course.id}`)
      .then((res)=>{
        this.videos.data = res.data
        console.log(res.data);
        this.video.courseId = course.id
      })
      this.$bvModal.show("modal-add-video")
    },
    addVideo(){
      var video = Object.assign(this.video)
      console.log(video);
      axios.post(`${url}video/add`,video)
      .then((res)=>{
        this.videos.data.push(res.data)
        console.log(res.data);
        this.thongBao.isAddShow = false
        this.resetForm()
        this.$bvModal.hide("modal-add-video")
      })
    },
    resetForm(){
      this.video = {
          title:"",
          link:""
        }
    },
    deleteVideo(idVideo){
      axios.delete(`${url}video/delete/${idVideo}`)
        this.videos.data = this.videos.data.filter(video => video.id !== idVideo)
        this.thongBao.isDeleteShow = false
    },
    putVideo(){
      var video = Object.assign(this.video)
      axios.put(`${url}video/update`,video)
      .then((res)=>{
        for (const obj of this.videos.data){
          if (obj === video.id ) {
            obj.title = video.title
            obj.link = video.link
          }
        }
        this.thongBao.isAddShow = false
        this.resetForm()
        this.thongBao.isUpdateShow = false
        this.btEditVideo = true
      })
    },
    showEditVideo(video){
      this.video = Object.assign({},video)
      this.btEditVideo = false
    }
  },
  filters:{
    formatNumber:function(value){
      return new Intl.NumberFormat().format(value)+"đ"
    },
  },
  watch:{
    txtSearch(){
      this.overlayTB = true
      clearTimeout(this.debounceSearch)
      this.debounceSearch = setTimeout(
        ()=>{
          this.table.data = []
          axios.get(`/api/course/search/${this.txtSearch}`)
          .then((res)=>{
            console.log(res.data);
            this.table.data = res.data
            this.overlayTB= false
          })
          .catch( er => {
            console.log(er);
            this.overlayTB= false})

        },1000
      )
    }
  }
}
</script>

<template>
  <div>
    <b-row class="mb-10">

      <b-col>
        <b-form-input class="mx-0 my-2" v-model="txtSearch" placeholder="Tìm kiếm"></b-form-input>
      </b-col>
      <b-col>
      </b-col>
      <b-col sm="12">
        <ul class="nav nav-tabs" id="myTab" role="tablist">

          <li class="nav-item" role="presentation">
            <button class="nav-link active" id="home-tab" data-bs-toggle="tab" @click="getCourseToAccept" data-bs-target="#home-tab-pane" type="button" role="tab" aria-controls="home-tab-pane" aria-selected="true">Chưa duyệt</button>
          </li>
          <li class="nav-item" role="presentation">
            <button class="nav-link" id="profile-tab" data-bs-toggle="tab" @click="getCourseAccept" data-bs-target="#profile-tab-pane" type="button" role="tab" aria-controls="profile-tab-pane" aria-selected="false">Dã duyệt</button>
          </li>
        </ul>
      </b-col>
    </b-row>
    <b-overlay
      :show="overlayTB"
      rounded="sm"
    >
      <b-table v-show="table.data.length > 0"
        striped
        hover
        responsive
        :items="table.data"
        :fields="table.fields"
        :busy="table.isBusy"
      >
        <template v-slot:cell(image)="course">
          <span class="">
            <img :src="getImg(course.item.image)" style="width: 150px;" alt="">
          </span>
        </template>
        <template v-slot:table-busy>
          <div class="text-center text-secondary my-2">
            <b-spinner class="align-middle me-2"></b-spinner>
            <strong>Loading...</strong>
          </div>
        </template>



        <template v-slot:cell(created)="course">
          <span>{{ course.item.created | formatDate}}</span>
        </template>
        <template v-slot:cell(creator)="course">
          <span class="d-flex flex-column">
            <span>{{ course.item.user.name }} </span>
            <span class="text-muted">({{ course.item.user.email }})</span>
            <span class="text-max"><a :href="course.item.link">{{ course.item.link }} </a></span>
          </span>
        </template>
        <template v-slot:cell(price)="course">
          <span class="fw-bold">{{course.item.price | formatNumber}}</span>
        </template>
        <template v-slot:cell(studyTime)="course">
          <span class="fw-bold">{{course.item.studyTime }} Giờ</span>
        </template>
        <template v-slot:cell(action)="course">
          <b-button
            class="mr-5"
            variant="outline-danger"
            @click="onRemoveCourse(course.item)"
            ><b-icon icon="trash-fill" aria-hidden="true"></b-icon>Hủy</b-button
          >
          <b-button v-show="btHide" variant="outline-info" @click="mdShowCourse(course.item)"
            ><b-icon icon="check-lg" aria-hidden="true"></b-icon>Chấp nhận</b-button
          >
          <b-button v-show="!btHide" variant="outline-info" @click="mdAddVideo(course.item)"
            ><b-icon icon="add" aria-hidden="true"></b-icon>Thêm bài giảng</b-button
          >
        </template>
      </b-table>
      <div class="h-100" v-show="!table.data.length > 0">
        <div class="d-flex h-100">
          <span class="m-auto text-muted fs-3 fw-blod">Không có gì</span>
        </div>
      </div>
    </b-overlay>

    <nav
      v-show="table.data.length > 0"
      id="nav-pag"
      aria-label="Page navigation"
      size="sm"
      class="float-right"
    >
      <b-pagination
        v-model="currentPage"
        :total-rows="1"
        :per-page="1"
        limit="1"
      >
      </b-pagination>
    </nav>
    <b-overlay class="w-100" :show="busy" no-wrap>
      <template class="" v-slot:overlay>
        <div
          ref="dialog"
          tabindex="-1"
          role="dialog"
          aria-modal="false"
          class="text-center p3"
        >
          <p
            ><strong id="form-confirm-label"
              >Lý do <span class="text-danger">hủy</span> yêu cầu khóa học
              <span class="text-bold"> {{ courseDelete.name }}</span></strong
            >?</p
          >
          <div class="mb-3">
            <ckeditor v-model="mail.content" ></ckeditor>
          </div>
          <div style="justify-content: center">
            <b-button variant="outline-default" class="mr-3" @click="onCancel"
              >Đóng</b-button
            >
            <b-button
              variant="outline-danger"
              class="mr-3"
              @click="notAccept"
              >Gửi mail</b-button
            >
          </div>
        </div>
      </template>
    </b-overlay>

    <b-modal
      id="modal-show-course"
      size="sm"
      centered
      scrollable
      hide-backdrop
      hide-header-close
    >
      <template v-slot:modal-title>Xác nhận  </template>
      <b-form>
        Bạn muốn chấp nhận khóa học <b class="text-bold">{{ showCourse.name }}</b>?
      </b-form>
      <template v-slot:modal-footer>
        <b-button type="button" variant="danger" @click="$bvModal.hide('modal-show-course')">
          Đóng</b-button
        >
        <b-button type="button" variant="success" @click="accept">
          Xác nhận</b-button
        >
      </template>
    </b-modal>
    <b-modal
      id="modal-add-video"
      size="xl"
      centered
      scrollable
      hide-backdrop
      hide-header-close
    >
      <template v-slot:modal-title>Thêm bài giảng </template>
      <div class="row">
        <div class="col-12 col-lg-8 col-xl-8">
          <b-table
            striped
            hover
            responsive
            :items="videos.data"
            :fields="videos.fields"
          >
            <template v-slot:cell(title)="video">
              <div class="d-flex">
                <div class="flex-column d-flex">
                  <span class="text-max">{{ video.item.title }}</span>
                  <span > <a class="text-max" :href="video.item.link">{{ video.item.link }}</a> </span>
                </div>
                <div class="ms-auto my-auto">
                  <b-button
                      class="mr-5"
                      variant="outline-danger"
                      @click="deleteVideo(video.item.id)"
                      ><b-icon icon="trash-fill" aria-hidden="true"></b-icon>xóa</b-button
                    >
                    <b-button  variant="outline-warning" @click="showEditVideo(video.item)"
                      ><b-icon icon="pen" aria-hidden="true"></b-icon>Sửa</b-button
                    >
                </div>
              </div>
            </template>
          </b-table>
        </div>
        <div class="col-12 col-lg-4 col-xl-4" >
          <div v-if="thongBao.isAddShow" class="bg-success py-2 px-1 d-flex" :class="'text-white'">
            <span class="my-auto me-auto" >Xác nhận thêm bài giảng {{ videos.data.length + 1 }} cho khóa học {{ showCourse.name }}</span>
            <b-button class="my-auto p-0 me-1" @click="addVideo" >Ok</b-button>
            <i class="bi bi-x my-auto" @click="thongBao.isAddShow = false"></i>
          </div>
          <div v-if="thongBao.isUpdateShow" class="bg-warning py-2 px-1 d-flex" :class="'text-white'">
            <span class="my-auto me-auto" >Xác nhận sửa bài giảng khóa học {{showCourse.name}}</span>
            <b-button class="my-auto p-0 me-1" @click="putVideo">Ok</b-button>
            <i class="bi bi-x my-auto" @click="thongBao.isUpdateShow = false"></i>
          </div>
          <div v-if="thongBao.isDeleteShow" class="bg-danger py-2 px-1 d-flex" :class="'text-white'">
            <span class="my-auto me-auto" >Xác nhận xóa bài giảng  cho khóa học {{showCourse.name}}</span>
            <b-button class="my-auto p-0 me-1">Ok</b-button>
            <i class="bi bi-x my-auto" @click="thongBao.isDeleteShow = false"></i>
          </div>
          <b-form>
            <b-form-group>
              <label for="">Tiêu đề bài giảng</label>
              <b-input v-model="video.title" type="text" placeholder="Tiêu đề bài giảng" />
            </b-form-group>
            <b-form-group>
              <label for="">Link</label>
              <b-input v-model="video.link" type="text" placeholder="Link" />
            </b-form-group>
          </b-form>
          <b-button v-if="btEditVideo" type="button" variant="success" @click="thongBao.isAddShow = true">
            Thêm video</b-button
          >
          <b-button v-else type="button" variant="warning" @click="thongBao.isUpdateShow = true">
            Sửa video</b-button
          >
        </div>
      </div>
      <template v-slot:modal-footer>
        <div></div>
      </template>
    </b-modal>

  </div>
</template>

<style>
  .w-100 > div  {
    width: 100%;
  }

.text-max{
  display: block;
  	display: -webkit-box;
  	-webkit-line-clamp: 1;  /* số dòng hiển thị */
  	-webkit-box-orient: vertical;
  	overflow: hidden;
  	text-overflow: ellipsis;
}
</style>
