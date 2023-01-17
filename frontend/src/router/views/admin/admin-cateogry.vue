<script>
import modalIcon from "@components/modal_choose_icon.vue"
import axios from "@/node_modules/axios"
import diagram from "@/src/components/diagramCate.vue"
const url="/api/"

export default {
  components:{
    modalIcon,
    diagram
  },

  data() {
    return {
      overlayTB:null,
      debounceSearch:null,
      parentCategory:[],
      categories: {
        isBusy: false,
        data: [
          {
            id: 1,
            name: 'Danh mục test',
            category:{ },
            like: 0,
            image: '/assets/images/brand/logo-3.png',
            created: '1970-01-01',
            status: 1,
          },
        ],
        fields: [
          { key: 'id', label: 'ID', sortable: true },
          { key: 'name', label: 'Tên danh mục', sortable: true },
          { key: 'parent', label: 'Danh mục cha', sortable: true },
          { key: 'created', label: 'Ngày tạo' },
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
      formAddCategory: { },
      formEditCategory: {},
      categoryDelete: {},
      currentPage: 1,
      categoriesPerPage: 5,
      txtSearch: '',
      diagramCate:[],
    }
  },

  watch:{
    txtSearch(){
      this.overlayTB = true
      clearTimeout(this.debounceSearch)
      this.debounceSearch = setTimeout(
        ()=>{
          this.categories.data = []
          axios.get(`/api/category/search/${this.txtSearch}`)
          .then((res)=>{
            console.log(res.data);
            this.categories.data = res.data
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
    this.categoriesBackup = this.categories.data
    axios.get(`${url}category/get`)
    .then((res)=>{
      this.categories.data = res.data
      this.parentCategory = res.data.filter(_=> _.parent === null )
    })
    axios.get('/api/category/get/parent').then(resParent => {
      this.diagramCate = resParent.data
      console.log(this.categories);
    })

  },

  methods: {
    nameOfParentCategory(id) {
      console.log(id);
      const isExist = this.categories.data.find((e) => e.id === id)
      if (isExist !== undefined) {
        return isExist.name
      } else {
        return ''
      }
    },
    tryEditCategory() {
      axios.put(`${url}category/update`,this.formEditCategory)
      .then(()=>{
        for (const obj of this.categories.data) {
        if (obj.id === this.formEditCategory.id) {
          obj.name = this.formEditCategory.name
          obj.image = this.formEditCategory.image
          obj.category = this.formEditCategory.category
          obj.status = this.formEditCategory.status
          break
        }
      }
      })


      this.categoriesBackup = this.categories.data
      this.$bvModal.hide('modal-edit-category')
    },

    tryAddCategory() {
      const obj = Object.assign({}, this.formAddCategory)
      const isExist = this.categories.data.find((e) => e.name === obj.name)
      if (isExist !== undefined) {
        alert('Tên danh mục đã tồn tại')
      } else {
        axios.post(`${url}category/add`,this.formAddCategory).then((res)=>{
          const id = this.categories.data.length + 1
          obj.id = id
          obj.like = 0
          obj.created = '1970-01-01'
          obj.creator = this.currentUser.name
          this.categories.data.push(obj)
          this.categoriesBackup = this.categories.data
        this.$bvModal.hide('modal-add-category')
        })
      }
    },
    tryRemoveCategory() {
      this.categories.data = this.categories.data.filter(
        (e) => e.id !== this.categoryDelete.id
      )
      axios.delete(`${url}category/${this.categoryDelete.id}`)
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
  filters:{
    formatNumber:function(value){
      return new Intl.NumberFormat().format(value)+"đ"
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
        <diagram :diagramCate="diagramCate"></diagram>
        <button
          class="btn btn-success mb-2 float-right"
          @click="$bvModal.show('modal-add-category')"
          ><b-icon icon="patch-plus" aria-hidden="true"></b-icon> Thêm danh
          mục</button
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
        <template v-slot:cell(parent)="cate">
          <span v-if="cate.item.parent"> {{cate.item.parent.name }} </span>
          <span v-else class="text-muted">Không có</span>
        </template>
        <template v-slot:cell(created)="cate">
          <span> {{ cate.item.created | formatDate }} </span>
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
    </b-overlay>

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
            v-model="formAddCategory.name"
            required
            type="text"
          ></b-form-input>
        </b-form-group>

        <b-row>
          <b-col>
            <b-form-group label="Danh mục cha">
              <b-form-select
                v-model="formAddCategory.parent_id"
              >
              <option v-for="ct in categories.data" :key="ct.id" :value="ct.id">{{ct.name}} </option>
              </b-form-select>
            </b-form-group>
          </b-col>
          <b-col>

          </b-col>
          <b-col>

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
      <template v-slot:modal-title>Sửa danh mục</template>
      <b-form>
        <b-form-group label="Tên danh mục">
          <b-form-input
            v-model="formEditCategory.name"
            required
            type="text"
          ></b-form-input>
        </b-form-group>
        <div>
          <modalIcon />
        </div>
        <b-row>
          <b-col>
            <b-form-group label="Danh mục cha">
              <b-form-select
                v-model="formEditCategory.category"
              >
                <option v-for="ct in categories.data" :key="ct.id" v-show="(ct.id !== formEditCategory.id)" :value="ct.id">{{ct.name}} </option>
              </b-form-select>
            </b-form-group>
          </b-col>
          <b-col>

          </b-col>
          <b-col>

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
