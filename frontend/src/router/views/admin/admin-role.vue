<script>
export default {
  data() {
    return {
      roles: {
        isBusy: false,
        data: [
          {
            id: 1,
            role_name: 'Manager',
            admin_id: 1,
            creator: 'admin',
            created: '1970-01-01',
            status: 1,
          },
          {
            id: 2,
            role_name: 'Member',
            admin_id: 2,
            creator: 'manager',
            created: '1970-01-01',
            status: 0,
          },
          {
            id: 3,
            role_name: 'Teacher',
            admin_id: 2,
            creator: 'admin',
            created: '1970-01-01',
            status: 1,
          },
          {
            id: 4,
            role_name: 'Inspector',
            admin_id: 1,
            creator: 'admin',
            created: '1970-01-01',
            status: 0,
          },
        ],
        fields: [
          { key: 'id', label: 'ID', sortable: true },
          { key: 'role_name', label: 'Tên role' },
          { key: 'admin_id', label: 'Người quản lý', sortable: true },
          { key: 'creator', label: 'Nguời tạo', sortable: true },
          { key: 'created', label: 'Ngày tạo' },
          { key: 'status', label: 'Trạng thái', sortable: true },
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
      formAddRole: {
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

  created() {
    this.rolesBackup = this.roles.data
  },

  methods: {
    mdEditRole(item) {
      this.formEditRole = Object.assign({}, item)
      this.$bvModal.show('modal-edit-role')
    },
    onAddRole() {
      const obj = Object.assign({}, this.formAddRole)
      const isExist = this.roles.data.find((e) => e.role_name === obj.role_name)
      if (isExist !== undefined) {
        alert('Tên role đã tồn tại')
      } else {
        const id = this.roles.data.length + 1
        obj.id = id
        obj.admin_id = 1
        obj.created = '1970-01-01'
        obj.creator = this.currentUser.name
        this.roles.data.push(obj)
      }
      this.rolesBackup = this.roles.data
      this.$bvModal.hide('modal-add-role')
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
      this.$bvModal.hide('modal-edit-role')
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
          @click="$bvModal.show('modal-add-role')"
          ><b-icon icon="patch-plus" aria-hidden="true"></b-icon> Thêm
          role</button
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
      id="modal-add-role"
      size="lg"
      centered
      scrollable
      hide-backdrop
      hide-header-close
    >
      <template v-slot:modal-title> Thêm role </template>
      <b-form>
        <b-form-group label="Tên Role" label-for="role-name">
          <b-form-input
            id="role-name"
            v-model="formAddRole.role_name"
            required
            type="text"
          >
          </b-form-input>
        </b-form-group>
        <b-row>
          <b-col>
            <b-form-group label="Người quản lý">
              <b-form-select
                v-model="formAddRole.admin_id"
                :options="optionsAdminId"
              ></b-form-select>
            </b-form-group>
          </b-col>
          <b-col>
            <b-form-group label="Trạng thái">
              <b-form-select
                v-model="formAddRole.status"
                :options="optionsStatus"
              ></b-form-select>
            </b-form-group>
          </b-col>
        </b-row>
      </b-form>
      <template v-slot:modal-footer>
        <b-button type="button" variant="success" @click="onAddRole">
          Thêm</b-button
        >
      </template>
    </b-modal>

    <b-modal
      id="modal-edit-role"
      size="lg"
      centered
      scrollable
      hide-backdrop
      hide-header-close
    >
      <template v-slot:modal-title> Sửa role </template>
      <b-form>
        <b-form-group label="Tên Role" label-for="role-name">
          <b-form-input
            id="role-name"
            v-model="formEditRole.role_name"
            required
            type="text"
          >
          </b-form-input>
        </b-form-group>
        <b-row>
          <b-col>
            <b-form-group label="Người quản lý">
              <b-form-select
                v-model="formEditRole.admin_id"
                :options="optionsAdminId"
              ></b-form-select>
            </b-form-group>
          </b-col>
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
