<script>
import axios from '@/node_modules/axios'
import store from "@state/store"
const url="/api/"
const user = Object.assign({},store.state.auth.currentUser)
export default {
  components:{
  },
  created(){
    this.fileRender = new FileReader()
    this.fileRender.onload = (Even) => {
        this.imageSelected = Even.target.result
    }
    console.log(this.courses);
    var getCourse=()=>{
      axios.get(`${url}course/getCourseByUserId/${user.id}`)
      .then((res)=>{
        this.courses.data= res.data
        console.log(res.data);
      })
    }
    // var getCourseAttr=()=>{
    //   axios.get(`${url}course/get`)
    //   .then((res)=>{
    //     this.courses= res.data
    //   })
    // }
    getCourse()
    console.log(this.course);
  },
  data() {
    return {
      debounceSearch:null,
      overlayTB:null,
      imageUP:null,
      content:"",
    imageSelected :"",
    fileRender:{},
    courseAtt:[],
    courses: {
        isBusy: false,
        data: [
          {
            image: 1,
            name: 'Manager',
            studyTime: 1,
            price:423,
            address:"Hà nội",
            like:"22",
            creator: 'admin',
            created: '1970-01-01',
            status: 1,
            describe:"hehe",
          },
        ],
        fields: [
          { key: 'image', label: 'img', sortable: true },
          { key: 'name', label: 'Tên khóa học' },
          { key: 'studyTime', label: 'Học trong', sortable: true },
          { key: 'price', label: 'Giá ban', sortable: true },
          { key: 'created', label: 'Ngày tạo' },
          { key: 'status', label: 'Trạng thái' },
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
      formAddCourse: {
        categoryAttr:{
          id:1
        },
        user:{
          id:user.id
        }
      },
      courseDelete: {},
      formEditCourse: {},
      currentPage: 1,
      rolesPerPage: 5,
      txtSearch: '',
    }
  },


  methods: {
    chooseImg(even) {

      this.imageSelected = even.target.files[0]
      this.imageUP=this.imageSelected
      this.fileRender.readAsDataURL(this.imageSelected)
    },
    mdEditCourse(item) {
      this.formEditCourse = Object.assign({}, item)
      this.formEditCourse.user = {id : user.id}
      this.$bvModal.show('modal-edit-course')
    },
    onAddCourse() {
      const dataForm= new FormData()
      dataForm.append("json", JSON.stringify(this.formAddCourse))
      if(this.imageUP !== null) dataForm.append("file",this.imageUP)
      axios.post(`${url}course/add`,dataForm,{
        headers:{
            'Content-Type':'multipart/form-data',
            'Accept':'application/json'}
      }).then((res)=>{
        this.courses.data.push(res.data)
      })

      this.rolesBackup = this.courses.data
      this.$bvModal.hide('modal-add-course')
    },

    onEditCourse() {
      const dataForm= new FormData()
      dataForm.append("json", JSON.stringify(this.formEditCourse))
      if(this.imageUP !== null) dataForm.append("file",this.imageUP)
      axios.put(`${url}course/update`,dataForm,{
        headers:{
            'Content-Type':'multipart/form-data',
            'Accept':'application/json'}
      }).then((res)=>{
        this.formEditCourse.image = res.data.image
        for (const obj of this.courses.data) {
          if (obj.id === this.formEditCourse.id) {
            obj.name = this.formEditCourse.name
            obj.status = this.formEditCourse.status
            obj.link = this.formEditCourse.link
            obj.image = res.data.image
            break
          }
        }
      })
      this.$bvModal.hide('modal-edit-course')
    },

    onRemoveCourse(item) {

      this.courseDelete = item
      this.busy = true
      // this.courses.data = this.roles.data.filter((e) => e.id !== id)
    },
    onCancel() {
      this.busy = false
      this.courseDelete = {}
    },

    tryRemoveCourse() {

      this.courses.data = this.courses.data.filter(
        (e) => e.id !== this.courseDelete.id
      )
      axios.delete(`${url}course/delete/${this.courseDelete.id}`)
      this.busy = false
      this.courseDelete = {}
    },
    getImg(name){
      return `${axios.defaults.baseURL}${url}image/get/${name}`
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
          this.courses.data = []
          axios.get(`/api/course/search/${this.txtSearch}`)
          .then((res)=>{
            console.log(res.data);
            this.courses.data = res.data
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
        <b-form-input v-model="txtSearch" placeholder="Tìm kiếm"></b-form-input>
      </b-col>
      <b-col>
        <button
          class="btn btn-success mb-2 float-right"
          @click="$bvModal.show('modal-add-course')"
          ><b-icon icon="patch-plus" aria-hidden="true"></b-icon> Thêm
          Khóa học</button
        >
      </b-col>
    </b-row>
    <b-overlay
      :show="overlayTB"
      rounded="sm"
    >
      <b-table
        v-if="courses.data.length > 0"
        striped
        hover
        responsive
        :items="courses.data"
        :fields="courses.fields"
        :busy="courses.isBusy"
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
        <template v-slot:cell(status)="course">
          <span class="text-muted" v-if="course.item.status === 1">Chờ xét duyệt</span>
          <span class="text-danger" v-if="course.item.status === 2">Không chấp thuận</span>
          <span class="text-success" v-if="course.item.status === 3">Đã chấp thuận</span>
          <!-- <span class="text-muted" v-if="course.item.status === 4">Đang được bán</span> -->
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
            ><b-icon icon="trash-fill" aria-hidden="true"></b-icon> Hủy</b-button
          >
          <b-button variant="outline-warning" v-if="course.item.status === 1" @click="mdEditCourse(course.item)"
            ><b-icon icon="pen" aria-hidden="true"></b-icon> Sửa</b-button
          ><b-button variant="outline-primary" v-else @click="mdEditCourse(course.item)"
            ><b-icon icon="pen" aria-hidden="true"></b-icon>Thêm video</b-button
          >
        </template>
      </b-table>
      <div class="h-100" v-show="!courses.data.length > 0">
        <div class="d-flex h-100">
          <span class="m-auto text-muted fs-3 fw-blod">Không có gì</span>
        </div>
      </div>
    </b-overlay>

    <nav
      v-if="courses.data.length > 0"
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
    <b-modal
      id="modal-add-course"
      size="xl"
      centered
      scrollable
      hide-backdrop
      hide-header-close
    >
      <template v-slot:modal-title> Thêm khóa hoc </template>
      <b-form>
        <b-row>
          <b-col sm="9">
            <b-row>
              <b-col>
                <b-form-group label="Tên khóa học" label-for="role-name">
                  <b-form-input
                    id="role-name"
                    v-model="formAddCourse.name "
                    required
                    type="text"
                  ></b-form-input>
                </b-form-group>
              </b-col>
              <b-col>
                <b-form-group label="Thời gian học(Giờ)" label-for="role-name">
                  <b-form-input
                    id="role-name"
                    v-model="formAddCourse.studyTime"
                    required
                    type="number"
                  ></b-form-input>
                </b-form-group>
              </b-col>
            </b-row>
            <b-row>
              <b-col>
                <b-form-group label="Giá bán" label-for="role-name">
                  <b-form-input
                    id="role-name"
                    v-model="formAddCourse.price"
                    required
                    type="text"
                  ></b-form-input>
                </b-form-group>

              </b-col>
              <b-col>
                <b-form-group label="Link tài liệu" label-for="role-name">
                  <b-form-input
                    id="role-name"
                    v-model="formAddCourse.link"
                    required
                    type="text"
                  ></b-form-input>
                </b-form-group>
              </b-col>
            </b-row>
            <b-row>
              <b-col>
                <b-form-group label="Thông tin" label-for="role-name">
                  <ckeditor v-model="formAddCourse.describe"></ckeditor>
                </b-form-group>
              </b-col>
            </b-row>
          </b-col>
          <b-col class="d-flex flex-column">
            <b-form-group label="Img" label-for="role-name">
              <input type="file" @change="chooseImg" class="form-control">
            </b-form-group>
            <img v-if="imageSelected" :src="imageSelected" class="m-auto w-100 shadow border " alt="">

          </b-col>
        </b-row>

      </b-form>
      <template v-slot:modal-footer>
        <b-button type="button" variant="success" @click="onAddCourse">
          Thêm</b-button
        >
      </template>
    </b-modal>

    <b-modal
      id="modal-edit-course"
      size="xl"
      centered
      scrollable
      hide-backdrop
      hide-header-close
    >
      <template v-slot:modal-title> Sửa khóa hoc </template>
      <b-form>
        <b-row>
          <b-col sm="9">
            <b-row>
              <b-col>
                <b-form-group label="Tên khóa học" label-for="role-name">
                  <b-form-input
                    id="role-name"
                    v-model="formEditCourse.name "
                    required
                    type="text"
                  ></b-form-input>
                </b-form-group>
              </b-col>
              <b-col>
                <b-form-group label="Thời gian học(Giờ)" label-for="role-name">
                  <b-form-input
                    id="role-name"
                    v-model="formEditCourse.studyTime"
                    required
                    type="number"
                  ></b-form-input>
                </b-form-group>
              </b-col>
            </b-row>
            <b-row>
              <b-col>
                <b-form-group label="Giá bán" label-for="role-name">
                  <b-form-input
                    id="role-name"
                    v-model="formEditCourse.price"
                    required
                    type="text"
                  ></b-form-input>
                </b-form-group>

              </b-col>
              <b-col>
                <b-form-group label="Link tài liệu" label-for="role-name">
                  <b-form-input
                    id="role-name"
                    v-model="formEditCourse.link"
                    required
                    type="text"
                  ></b-form-input>
                </b-form-group>

              </b-col>

            </b-row>
            <b-row>
              <b-col>
                <b-form-group label="Thông tin" label-for="role-name">
                  <ckeditor v-model="formEditCourse.describe"></ckeditor>

                </b-form-group>
              </b-col>
            </b-row>
          </b-col>
          <b-col class="d-flex flex-column">
            <b-form-group label="Img" label-for="role-name">
              <input type="file" @change="chooseImg" class="form-control">
            </b-form-group>
            <img v-if="imageSelected" :src="imageSelected" class="m-auto w-100 shadow border " alt="">

          </b-col>
        </b-row>

      </b-form>
      <template v-slot:modal-footer>
        <b-button type="button" variant="success" @click="onEditCourse">
          Sửa</b-button
        >
      </template>
    </b-modal>
    <b-overlay :show="busy" no-wrap>
      <template v-slot:overlay>
        <div
          ref="dialog"
          tabindex="-1"
          role="dialog"
          aria-modal="false"
          class="text-center p3"
        >
          <p
            ><strong id="form-confirm-label"
              >Bạn có chắc muốn <span class="text-danger">xoá</span>
              <span class="text-bold"> {{ courseDelete.name }}</span></strong
            ></p
          >
          <div style="justify-content: center">
            <b-button variant="outline-default" class="mr-3" @click="onCancel"
              >Huỷ</b-button
            >
            <b-button
              variant="outline-danger"
              class="mr-3"
              @click="tryRemoveCourse"
              >Xoá</b-button
            >
          </div>
        </div>
      </template>
    </b-overlay>
  </div>
</template>

<style >

</style>
