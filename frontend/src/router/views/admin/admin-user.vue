<script>
import axios from '@/node_modules/axios'
import store from '@/src/state/store'
const url="/api/"

export default {

  created(){
    this.fileRender = new FileReader()
    this.fileRender.onload = (Even) => {
        this.imageSelected = Even.target.result
    }

    this.getUser(2)
    this.getChildCate()
  },
  data() {
    return {
    imageUP:null,
    imageSelected :"",
    currentUser:Object.assign({},store.state.auth.currentUser),
    fileRender:{},
      users: {
        isBusy: false,
        data: [],
        fields: [
          { key: 'image', label: 'img', sortable: true },
          { key: 'name', label: 'Họ và tên' },
          { key: 'username', label: 'Tài khoản' },
          { key: 'email', label: 'Email', sortable: true },
          { key: 'phone', label: 'SDT', sortable: true },
          { key: 'email', label: 'Email', sortable: true },
          { key: 'created', label: 'Ngày tạo' },
          { key: 'address', label: 'Địa chỉ' },
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
      formAddUser: {
        username: '',
        password: "",
        image:"",
        phone:"",
        email:"",
        created:"",
        address:"",
        name:"",
        usersRoles:[],
        status: 0,
      },
      userDelete: {},
      formEditUser: {},
      currentPage: 1,
      usersPerPage: 5,
      usersBackup: [],
      txtSearch: '',
      overlayTB:null,
      debounceSearch:null,
      role:1,
      categories:[]
    }
  },


  methods: {
    getUser(idRole){
      this.overlayTB = true
      if(idRole === 2) this.role = 1
      if(idRole === 3) this.role = 2
      if(idRole === 4) this.role = 3
      if(idRole === 5) this.role = 4
      axios.get(`${url}user/getByRole/${idRole}`)
      .then((res)=>{
        this.users.data = res.data
      })
      .finally(()=>{
        this.overlayTB = false
      })
    },
    validate(){
      if (this.formAddUser.username === '') {
        console.log(this.formAddUser)
        alert('Vui lòng nhập tên tài khoản')
        return
      }
      if (this.formAddUser.password === '') {
        alert('Vui lòng nhập mật khẩu')
        return
      }
      if (this.formAddUser.email === '') {
        alert('Vui lòng nhập email')
        return
      }
      if (this.formAddUser.phone === '') {
        alert('Vui lòng nhập số điện thoại')
        return
      }
      if (this.formAddUser.address === '') {
        alert('Vui lòng nhập địa chỉ')
        return
      }
      if (this.formAddUser.name === '') {
        return alert('Vui lòng nhập họ tên')
      }
    },
    chooseImg(even) {
      this.imageSelected = even.target.files[0]
      this.imageUP= this.imageSelected
      this.fileRender.readAsDataURL(this.imageSelected)
    },
    getChildCate(){
      axios.get(`/api/category/get/childs`)
      .then(res=>{
        this.categories= res.data
      })
    },
    mdEditUser(item) {
      this.formEditUser = Object.assign({}, item)
      console.log(this.formEditUser);
      this.$bvModal.show('modal-edit-user')
    },
    onAddUser() {
      const roleStudent = {role:{id:5}}
      const roleLecturers = {role:{id:3}}
      const roleStaff = {role:{id:4}}
      const roleAdmin = {role:{id:2}}
      switch (this.role) {
        case 1:
          this.formAddUser.usersRoles.push(roleAdmin)
          break;
        case 2:
          this.formAddUser.usersRoles.push(roleLecturers)
          break;
        case 3:
          this.formAddUser.usersRoles.push(roleStaff)
          break;
        default:
          this.formAddUser.usersRoles.push(roleStudent)
          break;
      }
      this.validate()
      const dataForm= new FormData()
      dataForm.append("json", JSON.stringify(this.formAddUser))
      if(this.imageUP !== null) dataForm.append("file",this.imageUP)
      console.log(this.imageUP);
      axios.post(`${url}user/add`, dataForm,{
          headers:{
            'Content-Type': 'multipart/form-data',
            'Accept':'application/json'
          },
        }
      )
      .then(res => {
        this.users.data.push(res.data)
        this.imageSelected={}
        delete this.formAddUser.category
      })

      this.formAddUser.usersRoles = []
      this.usersBackup = this.users.data
      this.$bvModal.hide('modal-add-user')
    },

    onEditUser() {
      this.validate()
      const dataForm= new FormData()
      dataForm.append("json", JSON.stringify(this.formEditUser))
      if(this.imageUP !== null) dataForm.append("file",this.imageUP)
      axios.put(`${url}user/update`,dataForm,{
        headers:{
            'Content-Type':'multipart/form-data',
            'Accept':'application/json'}
      }).then(_=>{
        for (const obj of this.users.data) {
          if (obj.id === this.formEditUser.id) {
            obj.username = this.formEditUser.username
            obj.email = this.formEditUser.email
            obj.phone = this.formEditUser.phone
            obj.address = this.formEditUser.address
            obj.category = this.formEditUser.category
            obj.image = _.data.image
            break
          }
          delete this.formEditUser.category
        }
      })
      this.$bvModal.hide('modal-edit-user')
    },

    onRemoveUser(item) {
      this.userDelete = item
      this.busy = true
      // this.users.data = this.users.data.filter((e) => e.id !== id)
    },
    onCancel() {
      this.busy = false
      this.userDelete = {}
    },

    tryRemoveUser() {
      this.users.data = this.users.data.filter(
        (e) => e.id !== this.userDelete.id
      )

      axios.delete(`${url}user/delete/${this.userDelete.id}`)
      this.busy = false
      this.userDelete = {}
    },
    getImg(name){
      console.log(name);
      return `${axios.defaults.baseURL}/api/image/get/${name}`
    }
  },
  filters:{
    formatNumber:function(value){
      return new Intl.NumberFormat().format(value)+"đ"
    },

  },
  watch:{

  }
}
</script>

<template>
  <div>
    <b-row class="mb-10">
      <b-col>
        <ul class="nav nav-tabs" id="myTab" role="tablist">
          <li class="nav-item" role="presentation">
            <button class="nav-link active" id="home-tab" data-bs-toggle="tab" @click="getUser(2)" data-bs-target="#home-tab-pane" type="button" role="tab" aria-controls="home-tab-pane" aria-selected="true">Quản lý</button>
          </li>
          <li class="nav-item" role="presentation">
            <button class="nav-link" id="profile-tab" data-bs-toggle="tab" @click="getUser(4)" data-bs-target="#profile-tab-pane" type="button" role="tab" aria-controls="profile-tab-pane" aria-selected="false">Nhân viên</button>
          </li>
          <li class="nav-item" role="presentation">
            <button class="nav-link" id="contact-tab" data-bs-toggle="tab" @click="getUser(3)" data-bs-target="#contact-tab-pane" type="button" role="tab" aria-controls="contact-tab-pane" aria-selected="false">Giảng viên</button>
          </li>
          <li class="nav-item" role="presentation">
            <button class="nav-link" id="contact-tab" data-bs-toggle="tab" @click="getUser(5)" data-bs-target="#contact-tab-pane" type="button" role="tab" aria-controls="contact-tab-pane" aria-selected="false">Học viên</button>
          </li>
        </ul>
      </b-col>
      <b-col>
        <button
          class="btn btn-success mb-2 float-right"
          @click="$bvModal.show('modal-add-user')"
          ><b-icon icon="patch-plus" aria-hidden="true"></b-icon> Thêm
          user</button
        >
      </b-col>
    </b-row>
    <b-overlay
      :show="overlayTB"
      rounded="sm"
    >
      <b-table
        striped
        hover
        responsive
        :items="users.data"
        :fields="users.fields"
        :busy="users.isBusy"
      >
        <template  v-slot:cell(image)="user">
          <span >
            <img :src="getImg(user.item.image)" style="width: 50px;" alt="">
          </span>
        </template>
        <template v-slot:cell(name)="user">
          <div class="d-flex flex-column">
            <span class="fw-bold">{{ user.item.name }}</span>
            <span v-if=" user.item.usersRoles.map(userRole => userRole.role.id).includes(3)">
              Chuyên môn:
              <span v-if="user.item.category" class="">{{ user.item.category.name }}</span>
              <span v-else class="text-muted">Chưa có</span>
            </span>
          </div>
        </template>
        <template v-slot:table-busy>
          <div class="text-center text-secondary my-2">
            <b-spinner class="align-middle me-2"></b-spinner>
            <strong>Loading...</strong>
          </div>
        </template>
        <template v-slot:cell(created)="user">
          <span>
            {{ user.item.created | formatDate}}
          </span>
        </template>
        <template v-slot:cell(admin_id)="user">
          <span v-if="user.item.admin_id === 1" class="text-bold"> Admin </span>
          <span v-if="user.item.admin_id === 2" class="text-bold"> Manager </span>
        </template>
        <template  v-slot:cell(action)="user">
         <div>
          <b-button
            v-if="currentUser.usersRoles.map(userRole => userRole.role.id).includes(2)"
            class="mr-5"
            variant="outline-danger"
            @click="onRemoveUser(user.item)"
            ><b-icon icon="trash-fill" aria-hidden="true"></b-icon> Xoá</b-button
          >
          <b-button variant="outline-info" @click="mdEditUser(user.item)"
            ><b-icon icon="pen" aria-hidden="true"></b-icon> Sửa</b-button
          >
         </div>
        </template>
      </b-table>
    </b-overlay>

    <nav
      id="nav-pag"
      aria-label="Page navigation"
      size="sm"
      class="float-right"
    >
      <b-pagination
        v-model="currentPage"
        :total-rows="1"
        :per-page="usersPerPage"
        limit="1"
      >
      </b-pagination>
    </nav>
    <b-modal
      id="modal-add-user"
      size="lg"
      centered
      scrollable
      hide-backdrop
      hide-header-close
    >
      <template v-slot:modal-title> Thêm user </template>
      <b-form>
        <b-row>
          <b-col sm="9">
            <b-row>
              <b-col>
                <b-form-group label="Tài khoản" label-for="role-name">
                  <b-form-input
                    id="role-name"
                    v-model="formAddUser.username "
                    required
                    type="text"
                  ></b-form-input>
                </b-form-group>
              </b-col>
              <b-col>
                <b-form-group label="Mật khẩu" label-for="role-name">
                  <b-form-input
                    id="role-name"
                    v-model="formAddUser.password"
                    required
                    type="password"
                  ></b-form-input>
                </b-form-group>
              </b-col>
            </b-row>
            <b-row>
              <b-col>
                <b-form-group label="SDT" label-for="role-name">
                  <b-form-input
                    id="role-name"
                    v-model="formAddUser.phone"
                    required
                    type="text"
                  ></b-form-input>
                </b-form-group>

              </b-col>
              <b-col>
                <b-form-group label="Email" label-for="role-name">
                  <b-form-input
                    id="role-name"
                    v-model="formAddUser.email"
                    required
                    type="email"
                  ></b-form-input>
                </b-form-group>
              </b-col>
            </b-row>
            <b-row>
              <b-col>
                <b-form-group label="Họ và tên" label-for="role-name">
                  <b-form-input
                    id="full-name"
                    v-model="formAddUser.name"
                    required
                    type="text"
                  ></b-form-input>
                </b-form-group>
              </b-col>
            </b-row>
            <b-row>
            <b-row v-if="this.role === 2">
              <b-col>
                <label for="">Chuyên môn</label>
                <select class="form-select" v-model = "formAddUser.category" name="" id="">
                  <option v-for="ct in categories" :key = "ct.id" :value="ct">{{ ct.name }}</option>
                </select>
              </b-col>

            </b-row>

              <b-col>
                <b-form-group label="Địa chỉ" label-for="role-name">
                  <b-form-textarea v-model="formAddUser.address">
                  </b-form-textarea>
                </b-form-group>
              </b-col>
            </b-row>
          </b-col>
          <b-col class="d-flex flex-column">
            <b-form-group label="Img" label-for="role-name">
              <input type="file" @change="chooseImg" class="form-control">
            </b-form-group>
            <img v-if="imageSelected" name="file" :src="imageSelected" class="m-auto w-100 shadow border " alt="">

          </b-col>
        </b-row>

      </b-form>
      <template v-slot:modal-footer>
        <b-button type="button" variant="success" @click="onAddUser">
          Thêm</b-button
        >
      </template>
    </b-modal>

    <b-modal
      id="modal-edit-user"
      size="lg"
      centered
      scrollable
      hide-backdrop
      hide-header-close
    >
      <template v-slot:modal-title> Sửa user </template>
      <b-form>
        <b-row>
          <b-col sm="9">
            <b-row>
              <b-col>
                <b-form-group label="Tài khoản" label-for="role-name">
                  <b-form-input
                    id="role-name"
                    v-model="formEditUser.username "
                    required
                    type="text"
                  ></b-form-input>
                </b-form-group>
              </b-col>
              <b-col>
                <b-form-group label="Mật khẩu" label-for="role-name">
                  <b-form-input
                    id="role-name"
                    v-model="formEditUser.password"
                    required
                    type="password"
                  ></b-form-input>
                </b-form-group>
              </b-col>
            </b-row>
            <b-row>
              <b-col>
                <b-form-group label="SDT" label-for="role-name">
                  <b-form-input
                    id="role-name"
                    v-model="formEditUser.phone"
                    required
                    type="text"
                  ></b-form-input>
                </b-form-group>

              </b-col>
              <b-col>
                <b-form-group label="Email" label-for="role-name">
                  <b-form-input
                    id="role-name"
                    v-model="formEditUser.email"
                    required
                    type="email"
                  ></b-form-input>
                </b-form-group>
              </b-col>
            </b-row>
            <b-row>
              <b-col>
                <b-form-group label="Họ và tên" label-for="role-name">
                  <b-form-input
                    id="full-name"
                    v-model="formEditUser.name"
                    required
                    type="text"
                  ></b-form-input>
                </b-form-group>
              </b-col>
            </b-row>
            <b-row v-if="this.role === 2">
              <b-col>
                <label for="">Chuyên môn</label>
                <select class="form-select" v-model = "formEditUser.category" name="" id="">
                  <option v-for="ct in categories" :key = "ct.id" :value="ct">{{ ct.name }}</option>
                </select>
              </b-col>
            </b-row>
            <b-row>
              <b-col>
                <b-form-group label="Địa chỉ" label-for="role-name">
                  <b-form-textarea v-model="formEditUser.address">
                  </b-form-textarea>
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
        <b-button type="button" variant="success" @click="onEditUser">
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
              <span class="text-bold"> {{ userDelete.username }}</span></strong
            ></p
          >
          <div style="justify-content: center">
            <b-button variant="outline-default" class="mr-3" @click="onCancel"
              >Huỷ</b-button
            >
            <b-button
              variant="outline-danger"
              class="mr-3"
              @click="tryRemoveUser"
              >Xoá</b-button
            >
          </div>
        </div>
      </template>
    </b-overlay>
  </div>
</template>

<style scoped>

.w-70px{
  width: 70px !important;
}
</style>
