<script>
export default {
  data() {
    return {
      categories: {
        isBusy: false,
        data: [
          {
            id: 1,
            name_: 'Danh mục test',
            parent_id: null,
            admin_id: 1,
            like: 0,
            image: '/assets/images/brand/logo-3.png',
            created: '1970-01-01',
            status: 1,
          },
        ],
        fields: [
          { key: 'id', label: 'ID', sortable: true },
          { key: 'name_', label: 'Tên danh mục', sortable: true },
          { key: 'parent_id', label: 'Danh mục cha', sortable: true },
          { key: 'admin_id', label: 'Người quản lý', sortable: true },
          { key: 'like', label: 'Số like', sortable: true },
          { key: 'image', label: 'Hình ảnh', sortable: true },
          { key: 'created', label: 'Ngày tạo' },
          { key: 'status', label: 'Trạng thái', sortable: true },
          { key: 'action', label: 'Hành động' },
        ],
        meta: {
          pagination: {
            total: 1,
            count: 1,
          },
        },
      },
      busy: false,
      formAddCategory: {
        admin_id: 1,
        status: 0,
        // parent_id: null,
      },
      formEditCategory: {},
      categoryDelete: {},
      currentPage: 1,
      categoriesPerPage: 5,
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
      categoriesBackup: [],
      txtSearch: '',
    }
  },
  computed: {
    listCategories() {
      const arr = []
      arr.push({ value: null, text: '' })
      this.categories.data.forEach((e) => {
        arr.push({ value: e.id, text: e.name_ })
      })
      return arr
    },
  },
  watch: {
    txtSearch() {
      if (this.txtSearch !== '' && this.txtSearch !== null) {
        this.categories.data = this.categoriesBackup.filter((e) =>
          e.name_.toLowerCase().includes(this.txtSearch.toLowerCase())
        )
      } else {
        this.categories.data = this.categoriesBackup
      }
    },
  },

  created() {
    this.categoriesBackup = this.categories.data
  },

  methods: {
    nameOfParentCategory(index) {
      const isExist = this.categories.data.find((e) => e.id === index)
      if (isExist !== undefined) {
        return isExist.name_
      } else {
        return ''
      }
    },
    tryEditCategory() {
      for (const obj of this.categories.data) {
        if (obj.id === this.formEditCategory.id) {
          obj.name_ = this.formEditCategory.name_
          obj.image = this.formEditCategory.image
          obj.parent_id = this.formEditCategory.parent_id
          obj.admin_id = this.formEditCategory.admin_id
          obj.status = this.formEditCategory.status
          break
        }
      }
      this.categoriesBackup = this.categories.data
      this.$bvModal.hide('modal-edit-category')
    },

    tryAddCategory() {
      const obj = Object.assign({}, this.formAddCategory)
      const isExist = this.categories.data.find((e) => e.name_ === obj.name_)
      if (isExist !== undefined) {
        alert('Tên danh mục đã tồn tại')
      } else {
        const id = this.categories.data.length + 1
        obj.id = id
        obj.like = 0
        obj.created = '1970-01-01'
        obj.creator = this.currentUser.name
        this.categories.data.push(obj)
      }
      this.categoriesBackup = this.categories.data
      this.$bvModal.hide('modal-add-category')
    },
    tryRemoveCategory() {
      this.categories.data = this.categories.data.filter(
        (e) => e.id !== this.categoryDelete.id
      )
      this.busy = false
      this.categoryDelete = {}
      this.categoriesBackup = this.categories.data
    },
    onRemoveCategory(item) {
      this.categoryDelete = item
      this.busy = true
    },
    onCancel() {
      this.busy = false
      this.categoryDelete = {}
    },
    mdEditCategory(item) {
      this.formEditCategory = Object.assign({}, item)
      this.$bvModal.show('modal-edit-category')
    },
  },
}
</script>

<template>
  <div>
    <b-row class="mb-10">
      <b-col>
        <b-form-input
          v-model="txtSearch"
          placeholder="Tìm kiếm danh mục"
        ></b-form-input>
      </b-col>
      <b-col>
        <button
          class="btn btn-success mb-2 float-right"
          @click="$bvModal.show('modal-add-category')"
          ><b-icon icon="patch-plus" aria-hidden="true"></b-icon> Thêm danh
          mục</button
        >
      </b-col>
    </b-row>
    <b-table
      striped
      hover
      responsive
      :items="categories.data"
      :fields="categories.fields"
      :busy="categories.isBusy"
    >
      <template v-slot:table-busy>
        <div class="text-center text-secondary my-2">
          <b-spinner class="align-middle me-2"></b-spinner>
          <strong>Loading...</strong>
        </div>
      </template>
      <template v-slot:cell(parent_id)="cate">
        <span> {{ nameOfParentCategory(cate.value) }} </span>
      </template>
      <template v-slot:cell(status)="cate">
        <span v-if="cate.item.status === 0" class="text-danger text-bold">
          Inactive
        </span>
        <span v-if="cate.item.status === 1" class="text-success text-bold">
          Active
        </span>
      </template>
      <template v-slot:cell(admin_id)="cate">
        <span v-if="cate.item.admin_id === 1" class="text-bold"> Admin </span>
        <span v-if="cate.item.admin_id === 2" class="text-bold"> Manager </span>
      </template>
      <template v-slot:cell(action)="cate">
        <b-button
          class="mr-5"
          variant="outline-danger"
          @click="onRemoveCategory(cate.item)"
          ><b-icon icon="trash-fill" aria-hidden="true"></b-icon> Xoá</b-button
        >
        <b-button variant="outline-info" @click="mdEditCategory(cate.item)"
          ><b-icon icon="pen" aria-hidden="true"></b-icon> Sửa</b-button
        >
      </template>
    </b-table>
    <b-modal
      id="modal-add-category"
      size="lg"
      centered
      scrollable
      hide-header-close
      hide-backdrop
    >
      <template v-slot:modal-title>Thêm danh mục</template>
      <b-form>
        <b-form-group label="Tên danh mục">
          <b-form-input
            v-model="formAddCategory.name_"
            required
            type="text"
          ></b-form-input>
        </b-form-group>
        <b-form-group label="Hình ảnh">
          <b-form-input
            v-model="formAddCategory.image"
            required
            type="text"
          ></b-form-input>
        </b-form-group>
        <b-row>
          <b-col>
            <b-form-group label="Danh mục cha">
              <b-form-select
                v-model="formAddCategory.parent_id"
                :options="listCategories"
              ></b-form-select>
            </b-form-group>
          </b-col>
          <b-col>
            <b-form-group label="Người quản lý">
              <b-form-select
                v-model="formAddCategory.admin_id"
                :options="optionsAdminId"
              ></b-form-select>
            </b-form-group>
          </b-col>
          <b-col>
            <b-form-group label="Trạng thái">
              <b-form-select
                v-model="formAddCategory.status"
                :options="optionsStatus"
              ></b-form-select>
            </b-form-group>
          </b-col>
        </b-row>
      </b-form>
      <template v-slot:modal-footer>
        <b-button type="button" variant="success" @click="tryAddCategory">
          Thêm</b-button
        >
      </template>
    </b-modal>

    <b-modal
      id="modal-edit-category"
      size="lg"
      centered
      scrollable
      hide-header-close
      hide-backdrop
    >
      <template v-slot:modal-title> Sửa danh mục </template>
      <b-form>
        <b-form-group label="Tên danh mục">
          <b-form-input
            v-model="formEditCategory.name_"
            required
            type="text"
          ></b-form-input>
        </b-form-group>
        <b-form-group label="Hình ảnh">
          <b-form-input
            v-model="formEditCategory.image"
            required
            type="text"
          ></b-form-input>
        </b-form-group>
        <b-row>
          <b-col>
            <b-form-group label="Danh mục cha">
              <b-form-select
                v-model="formEditCategory.parent_id"
                :options="listCategories"
              ></b-form-select>
            </b-form-group>
          </b-col>
          <b-col>
            <b-form-group label="Người quản lý">
              <b-form-select
                v-model="formEditCategory.admin_id"
                :options="optionsAdminId"
              ></b-form-select>
            </b-form-group>
          </b-col>
          <b-col>
            <b-form-group label="Trạng thái">
              <b-form-select
                v-model="formEditCategory.status"
                :options="optionsStatus"
              ></b-form-select>
            </b-form-group>
          </b-col>
        </b-row>
      </b-form>
      <template v-slot:modal-footer>
        <b-button type="button" variant="success" @click="tryEditCategory">
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
