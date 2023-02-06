<script>
import { bus } from '@src/main'
import store from '@/src/state/store'

export default {
  props: {
    active: {
      type: String,
      default: '',
    },
  },

  data() {
    return {
      leftContentAdminHeight: 700,
      user :Object.assign({}, store.state.auth.currentUser),
      menuList: [],
      menuListAdmin: [
        { key: 'thong-ke', title: 'Thống kê', icon: 'bi bi-pie-chart-fill' },
        { key: 'admin-category', title: 'Quản lý danh mục', icon: 'fa-folder' },
        { key: 'admin-users', title: 'Quản lý tài khoản', icon: 'fa-user' },
        { key: 'admin-course', title: 'Quản lý Khóa học', icon: 'bi bi-table' },
        { key: 'admin-contact', title: 'Quản lý liên hệ', icon: 'bi bi-telephone-fill' },
      ],
      menuListStaff: [
        { key: 'admin-category', title: 'Quản lý danh mục', icon: 'fa-folder' },
        { key: 'admin-users', title: 'Quản lý tài khoản', icon: 'fa-user' },
        { key: 'admin-course', title: 'Quản lý Khóa học', icon: 'bi bi-table' },
        { key: 'admin-contact', title: 'Quản lý liên hệ', icon: 'bi bi-telephone-fill' },
        { key: 'course-user', title: 'Gửi duyệt khóa học', icon: 'bi bi-table' },
      ],
      menuListTeacher: [
      { key: 'thong-ke', title: 'Thống kê', icon: 'bi bi-pie-chart-fill' },
        { key: 'course-user', title: 'Gửi duyệt khóa học', icon: 'bi bi-table' },
      ],
    }
  },
  created(){
    if(this.user.usersRoles.map(userRole => userRole.role.id).includes(2)){
      this.menuList = this.menuListAdmin
      return
    }
    if(this.user.usersRoles.map(userRole => userRole.role.id).includes(3)){
      this.menuList = this.menuListTeacher
      return
    }
    if(this.user.usersRoles.map(userRole => userRole.role.id).includes(4)){
      this.menuList = this.menuListStaff
    }
  },

  methods: {
    switchTab(tab) {
      bus.$emit('switchTab', tab)
    },
  },
}
</script>

<style>
.selectedGroup {
  border-bottom: 2px solid #e1e1e1 !important;
  border-left: 3px solid #09ad95 !important;
}
</style>

<template>
  <div class="card">
    <div class="groupHeader card-header">
      <h3 class="card-title">Administator</h3>
    </div>
    <div
      class="list-group list-group-transparent mb-0 file-manager file-manager-border"
    >
      <div v-for="page in menuList" :key="page.key" class="group">
        <a
          href="#"
          class="list-group-item d-flex align-items-center px-0 groupItem"
          :class="[{ selectedGroup: active === page.key }]"
          @click="switchTab(page.key)"
        >
          <i class="fa fs-18 me-2 text-info p-2" :class="page.icon"></i>
          <b>{{ page.title }}</b>
        </a>
      </div>
    </div>
  </div>
</template>
