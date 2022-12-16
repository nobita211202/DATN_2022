<script>
import axios from '@/node_modules/axios'
const url="http://localhost:8080/api/"

export default {

  created(){
    this.fileRender = new FileReader()
    this.fileRender.onload = (Even) => {
        this.imageSelected = Even.target.result
    }
    var getUser=()=>{
      axios.get(`${url}user/get`)
      .then((res)=>{
        this.users.data = res.data
      })
    }
    getUser()
  },
  data() {
    return {
    imageUP:{},
    imageSelected :"",
    fileRender:{},
      users: {
        isBusy: false,
        data: [
          {
            id: 1,
            username: 'Manager',
            password: 1,
            email:"hoang@c.cp",
            image:"",
            phone:"0961389115",
            address:"Hà nội",

            creator: 'admin',
            created: '1970-01-01',
            status: 1,
          },
        ],
        fields: [
          { key: 'image', label: 'img', sortable: true },
          { key: 'username', label: 'Tài khoản' },
          { key: 'email', label: 'Email', sortable: true },
          { key: 'phone', label: 'SDT', sortable: true },
          { key: 'email', label: 'Email', sortable: true },
          { key: 'created', label: 'Ngày tạo' },
          { key: 'status', label: 'Trạng thái', sortable: true },
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
        status: 0,
      },
      userDelete: {},
      formEditUser: {},
      currentPage: 1,
      usersPerPage: 5,
      currentUser: {
        id: 1,
        name: 'admin',
      },
      optionsStatus: [
        { value: 0, text: 'Inactive' },
        { value: 1, text: 'Active' },
      ],
      optionsAdminId: [
        { value: 1, text: 'Admin' },
        { value: 2, text: 'Manager' },
      ],
      usersBackup: [],
      txtSearch: '',
    }
  },

  watch: {
    txtSearch() {
      if (this.txtSearch !== '' && this.txtSearch !== null) {
        this.users.data = this.usersBackup.filter((e) =>
          e.role_name.toLowerCase().includes(this.txtSearch.toLowerCase())
        )
      } else {
        this.users.data = this.usersBackup
      }
    },
  },


  methods: {
    chooseImg(even) {
      this.imageSelected = even.target.files[0]
      this.imageUP= this.imageSelected
      this.fileRender.readAsDataURL(this.imageSelected)
    },
    mdEditUser(item) {
      this.formEditUser = Object.assign({}, item)
      console.log(this.formEditUser);
      this.$bvModal.show('modal-edit-user')
    },
    onAddUser() {
      const obj = Object.assign({}, this.formAddUser)
      const isExist = this.users.data.find((e) => e.username === obj.username)
      if (isExist !== undefined) {
        alert('Username đã tồn tại')
        return
      }
      const dataForm= new FormData()
      dataForm.append("json", JSON.stringify(this.formAddUser))
      dataForm.append("file",this.imageUP)
      axios.post(`${url}user/add`, dataForm,{
          headers:{
            'Content-Type': 'multipart/form-data'
          },
          // transformRequest:""
        }
      )


      this.usersBackup = this.users.data
      this.$bvModal.hide('modal-add-user')
    },

    onEditUser() {
      for (const obj of this.users.data) {
        if (obj.id === this.formEditUser.id) {
          obj.username = this.formEditUser.username
          obj.status = this.formEditUser.status
          break
        }
      }
      const dataForm= new FormData()
      dataForm.append("json", JSON.stringify(this.formEditUser))
      dataForm.append("file",this.imageUP)
      axios.put(`${url}user/update`,dataForm,{
        headers:{
            'Content-Type':'multipart/form-data',
            'Accept':'application/json'}
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
      return `${url}image/get/${name}`
    }
  },
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
          @click="$bvModal.show('modal-add-user')"
          ><b-icon icon="patch-plus" aria-hidden="true"></b-icon> Thêm
          user</button
        >
      </b-col>
    </b-row>
    <b-table
      striped
      hover
      responsive
      :items="users.data"
      :fields="users.fields"
      :busy="users.isBusy"
    >
      <template  v-slot:cell(image)="role">
        <span >
          <img :src="getImg(role.item.image)" style="width: 50px;" alt="">
        </span>
      </template>
      <template v-slot:table-busy>
        <div class="text-center text-secondary my-2">
          <b-spinner class="align-middle me-2"></b-spinner>
          <strong>Loading...</strong>
        </div>
      </template>
      <template v-slot:cell(status)="role">
        <span v-if="role.item.status === 0" class="text-danger text-bold">
          Inactive
        </span>
        <span v-if="role.item.status === 1" class="text-success text-bold">
          Active
        </span>
      </template>

      <template v-slot:cell(admin_id)="role">
        <span v-if="role.item.admin_id === 1" class="text-bold"> Admin </span>
        <span v-if="role.item.admin_id === 2" class="text-bold"> Manager </span>
      </template>
      <template v-slot:cell(action)="role">
        <b-button
          class="mr-5"
          variant="outline-danger"
          @click="onRemoveUser(role.item)"
          ><b-icon icon="trash-fill" aria-hidden="true"></b-icon> Xoá</b-button
        >
        <b-button variant="outline-info" @click="mdEditUser(role.item)"
          ><b-icon icon="pen" aria-hidden="true"></b-icon> Sửa</b-button
        >
      </template>
    </b-table>
    <nav
      id="nav-pag"
      aria-label="Page navigation"
      size="sm"
      class="float-right"
    >
      <b-pagination
        v-model="currentPage"
        :total-rows="users.meta.pagination.total"
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
                <b-form-group label="Address" label-for="role-name">
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
                <b-form-group label="Address" label-for="role-name">
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
