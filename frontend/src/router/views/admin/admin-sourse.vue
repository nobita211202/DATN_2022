<script>
import axios from '@/node_modules/axios'
export default {
  components:{
  },
  created(){
    this.fileRender = new FileReader()
    this.fileRender.onload = (Even) => {
        this.imageSelected = Even.target.result
    }
  },
  data() {
    return {
      editor: "ClassicEditor",
        editorData: '<p>Content of the editor.</p>',
        editorConfig: {
            // The configuration of the editor.
        },
      content:"",
    imageSelected :"",
    fileRender:{},
      roles: {
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

          },
        ],
        fields: [
          { key: 'image', label: 'img', sortable: true },
          { key: 'name', label: 'Tên khóa học' },
          { key: 'studyTime', label: 'Thời gian học', sortable: true },
          { key: 'price', label: 'Giá ban', sortable: true },
          { key: 'like', label: 'Lượt thích', sortable: true },
          { key: 'created', label: 'Ngày tạo' },
          { key: 'status', label: 'Trạng thái', sortable: true },
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
      formAddCourse: {
        role_name: '',
        admin_id: 1,
        status: 0,
      },
      roleDelete: {},
      formEditRole: {},
      currentPage: 1,
      rolesPerPage: 5,
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
      rolesBackup: [],
      txtSearch: '',
    }
  },

  watch: {
    txtSearch() {
      if (this.txtSearch !== '' && this.txtSearch !== null) {
        this.roles.data = this.rolesBackup.filter((e) =>
          e.role_name.toLowerCase().includes(this.txtSearch.toLowerCase())
        )
      } else {
        this.roles.data = this.rolesBackup
      }
    },
  },


  methods: {
    chooseImg(even) {
      console.log(this.imageSelected);
      this.imageSelected = even.target.files[0]
      console.log(this.imageSelected);
      console.log(this.fileRender);
      this.fileRender.readAsDataURL(this.imageSelected)
    },
    mdEditRole(item) {
      this.formEditRole = Object.assign({}, item)
      this.$bvModal.show('modal-edit-course')
    },
    onAddUser() {
      const obj = Object.assign({}, this.formAddCourse)
      const isExist = this.roles.data.find((e) => e.username === obj.username)
      if (isExist !== undefined) {
        alert('Username đã tồn tại')
        return
      }
      const file= new FormData()
      console.log(this.imageSelected.name);
      file.append("file",this.imageSelected)
      axios.post(`http://localhost:8080/api/user/add?file=${file}`,{
          entity:this.user
        },
        {
          headers: {
            'Content-Type': 'multipart/form-data'
          },
        }
        )


      this.rolesBackup = this.roles.data
      this.$bvModal.hide('modal-add-course')
    },

    onEditRole() {
      for (const obj of this.roles.data) {
        if (obj.id === this.formEditRole.id) {
          obj.role_name = this.formEditRole.role_name
          obj.status = this.formEditRole.status
          obj.admin_id = this.formEditRole.admin_id
          break
        }
      }
      this.$bvModal.hide('modal-edit-course')
    },

    onRemoveRole(item) {
      this.roleDelete = item
      this.busy = true
      // this.roles.data = this.roles.data.filter((e) => e.id !== id)
    },
    onCancel() {
      this.busy = false
      this.roleDelete = {}
    },

    tryRemoveRole() {
      this.roles.data = this.roles.data.filter(
        (e) => e.id !== this.roleDelete.id
      )
      this.busy = false
      this.roleDelete = {}
    },
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
          @click="$bvModal.show('modal-add-course')"
          ><b-icon icon="patch-plus" aria-hidden="true"></b-icon> Thêm
          user</button
        >
      </b-col>
    </b-row>
    <b-table
      striped
      hover
      responsive
      :items="roles.data"
      :fields="roles.fields"
      :busy="roles.isBusy"
    >
      <template v-slot:cell(image)="role">
        <b-col sm="12" class="bg-dark">
          <img :src="role.item.image" class="w-100" alt="">
        </b-col>
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
          @click="onRemoveRole(role.item)"
          ><b-icon icon="trash-fill" aria-hidden="true"></b-icon> Xoá</b-button
        >
        <b-button variant="outline-info" @click="mdEditRole(role.item)"
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
        :total-rows="roles.meta.pagination.total"
        :per-page="rolesPerPage"
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
                <b-form-group label="Thời gian học" label-for="role-name">
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

            </b-row>
            <b-row>
              <b-col>
                <b-form-group label="Thông tin" label-for="role-name">
                  <ckeditor></ckeditor>
                  <b-textarea v-model="formAddCourse.describe">

                  </b-textarea>
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
        <b-button type="button" variant="success" @click="onAddUser">
          Thêm</b-button
        >
      </template>
    </b-modal>

    <b-modal
      id="modal-edit-course"
      size="lg"
      centered
      scrollable
      hide-backdrop
      hide-header-close
    >
      <template v-slot:modal-title> Sửa role </template>
      <b-form>
        <b-form-group label="Tài khoản" label-for="role-name">
          <b-row>
            <b-form-input
            id="role-name"
            v-model="formEditRole.role_name"
            required
            type="text"
          >
          </b-form-input>

          </b-row>
        </b-form-group>
        <b-row>

          <b-col>
            <b-form-group label="Trạng thái">
              <b-form-select
                v-model="formEditRole.status"
                :options="optionsStatus"
              ></b-form-select>
            </b-form-group>
          </b-col>
        </b-row>
      </b-form>
      <template v-slot:modal-footer>
        <b-button type="button" variant="success" @click="onEditRole">
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
              <span class="text-bold"> {{ roleDelete.role_name }}</span></strong
            ></p
          >
          <div style="justify-content: center">
            <b-button variant="outline-default" class="mr-3" @click="onCancel"
              >Huỷ</b-button
            >
            <b-button
              variant="outline-danger"
              class="mr-3"
              @click="tryRemoveRole"
              >Xoá</b-button
            >
          </div>
        </div>
      </template>
    </b-overlay>
  </div>
</template>
