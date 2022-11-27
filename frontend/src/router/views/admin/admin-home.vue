<script>
import Layout from '@layouts/main.vue'
import LeftAdmin from '@layouts/left-admin.vue'
import AdminRole from '@views/admin/admin-role.vue'
import AdminCategory from '@views/admin/admin-cateogry.vue'
import { bus } from '@src/main'
export default {
  page: {
    title: 'Quản trị hệ thống',
  },

  components: {
    Layout,
    LeftAdmin,
    AdminRole,
    AdminCategory,
  },

  data() {
    return {
      activeTab: 'admin-role',
    }
  },

  created() {
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
</style>

<template>
  <Layout>
    <div class="row justify-content-md-center mt-3">
      <div class="col-12">
        <b-col class="col-12 col-lg-3 col-xl-2 float-left">
          <LeftAdmin :active="activeTab" />
        </b-col>
        <b-col class="col-12 col-lg-9 col-xl-10 float-left">
          <div class="card">
            <div class="card-header">
              <h3 v-if="activeTab === 'admin-role'" class="card-title"
                >Quản lý role</h3
              >
              <h3 v-if="activeTab === 'admin-category'" class="card-title"
                >Quản lý danh mục</h3
              >
              <h3 v-if="activeTab === 'admin-users'" class="card-title"
                >Quản lý tài khoản</h3
              >
              <h3 v-if="activeTab === 'admin-tools'" class="card-title"
                >Quản lý tool cho thuê</h3
              >
              <h3 v-if="activeTab === 'admin-transaction'" class="card-title"
                >Quản lý giao dịch</h3
              >
              <h3 v-if="activeTab === 'admin-statistic'" class="card-title"
                >Thống kê</h3
              >
            </div>
            <div class="card-body">
              <AdminRole v-if="activeTab === 'admin-role'" />
              <AdminCategory v-if="activeTab === 'admin-category'" />
            </div>
          </div>
        </b-col>
      </div>
    </div>
  </Layout>
</template>
