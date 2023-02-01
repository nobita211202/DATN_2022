<script>
import Layout from '@layouts/main.vue'
import LeftAdmin from '@layouts/left-admin.vue'
import AdminThongKe from '@/src/router/views/admin/admin-thongke.vue'
import AdminCategory from '@views/admin/admin-cateogry.vue'
import adminUser from '@/src/router/views/admin/admin-user.vue'
import adminSourse from '@/src/router/views/admin/admin-sourse.vue'
import adminContactVue from '@views/admin/admin-contact.vue'
import courseUser from '@/src/router/views/admin/course-user.vue'
import { bus } from '@src/main'
export default {
  page: {
    title: 'Quản trị hệ thống',
  },

  components: {
    Layout,
    LeftAdmin,
    AdminThongKe,
    AdminCategory,
    adminUser,
    adminSourse,
    adminContactVue,
    courseUser,
  },

  data() {
    return {
      activeTab: '',
    }
  },
  props:{
    role: Array
  },
  created() {
    if (this.role.includes(2)) this.activeTab = 'thong-ke'
    if (this.role.includes(3)) this.activeTab = 'thong-ke'
    if (this.role.includes(4)) this.activeTab = 'admin-category'
    bus.$on('switchTab', (data) => {
      this.activeTab = data
    })
  },
}
</script>

<style>
body {
  overflow: auto !important;
  font-family: 'Calibri', sans-serif !important;
}

.float-left {
  float: left !important;
}

.groupItem {
  float: left;
  width: 100% !important;
  padding: 1rem 0.5rem !important;
  overflow: hidden;
  border-right: none !important;
  border-bottom: 1px solid #e1e1e1;
}
.groupItem .fa {
  color: #000 !important;
}
.groupItem:hover {
  color: #05c3fb !important;
}
.groupItem b {
  font-weight: 500 !important;
  text-transform: none !important;
}
@media only screen and (max-width: 991px) {
  .col-12 {
    padding-right: 0 !important;
    padding-left: 0 !important;
    margin-right: 0 !important;
    margin-left: 0 !important;
  }
  .row {
    padding-right: 0 !important;
    padding-left: 0 !important;
    margin-right: 0 !important;
    margin-left: 0 !important;
  }
  .container-fluid {
    padding-right: 0 !important;
    padding-left: 0 !important;
    margin-right: 0 !important;
    margin-left: 0 !important;
  }
}
.h600px{
  height: 670px;
}
</style>

<template>
  <Layout>
    <div class="container-fluid  justify-content-md-center mt-3">
      <div class="col-12">
        <b-col class="col-12 col-lg-3 col-xl-2 float-left">
          <LeftAdmin :active="activeTab" />
        </b-col>
        <b-col class="col-12 col-lg-9 col-xl-10 float-left">
          <div class="card">
            <div class="card-header">
              <h3 v-if="activeTab === 'thong-ke'" class="card-title"
                >Thống kê</h3
              >
              <h3  v-if="activeTab === 'admin-category'" class="card-title"
                >Quản lý danh mục</h3
              >
              <h3 v-if="activeTab === 'admin-users'" class="card-title"
                >Quản lý tài khoản</h3
              >
              <h3 v-if="activeTab === 'admin-course'" class="card-title"
                >Quản lý Khóa học</h3
              >
              <h3 v-if="activeTab === 'admin-contact'" class="card-title"
                >Quản lý liên hệ</h3
              >
              <h3 v-if="activeTab === 'course-user'" class="card-title"
                >Gửi duyệt khóa học</h3
              >

            </div>
            <div class="h600px p-3" style=" overflow-y: scroll ; overflow-x: hidden;">
             <!-- <KeepAlive> -->
                <AdminThongKe v-if="activeTab === 'thong-ke'" />
                <AdminCategory v-if="activeTab === 'admin-category'" />
                <adminUser v-if="activeTab === 'admin-users'" />
                <adminSourse v-if="activeTab === 'admin-course'"/>
                <adminContactVue v-if="activeTab === 'admin-contact'"/>
                <courseUser v-if="activeTab === 'course-user'"/>
             <!-- </KeepAlive> -->
            </div>
          </div>
        </b-col>
      </div>
    </div>
  </Layout>
</template>
