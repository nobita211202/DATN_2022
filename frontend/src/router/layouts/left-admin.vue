<script>
import { bus } from '@src/main'
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
      menuList: [
        { key: 'admin-role', title: 'Thống kê', icon: 'bi bi-pie-chart-fill' },
        { key: 'admin-category', title: 'Quản lý danh mục', icon: 'fa-folder' },
        { key: 'admin-users', title: 'Quản lý tài khoản', icon: 'fa-user' },
        { key: 'admin-course', title: 'Quản lý Khóa học', icon: 'bi bi-table' },
        { key: 'admin-contact', title: 'Quản lý liên hệ', icon: 'bi bi-telephone-fill' },
        // { key: 'admin-tools', title: 'Quản lý tool', icon: 'fa-windows' },
      ],
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
