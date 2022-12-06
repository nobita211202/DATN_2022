<script>
import axios from '@/node_modules/axios';
import Layout from '@layouts/main.vue'
export default {
  page: {
    title: 'Danh sách khoá học',
  },

  components: {
    Layout,
  },
  data() {
    return {
      listCouses: {
        data: [
          {
            id: 1,
            title: '69 Tuyệt Chiêu Chốt Sale Trực Tiếp',
            teacher: {
              teacher_id: 1,
              teacher_name: 'Hán Quang Dự',
            },
            rate: {
              star: 4,
              half_star: 1,
              non_star: 0,
              total_rate: 32,
            },
            price: '459.000',
            old_price: '599.000',
            img: 'https://static.unica.vn/upload/images/2019/04/69_tuyet_chieu_chot_sale_truc_tiep_m_1555564902.jpg',
          },
          {
            id: 1,
            title: '69 Tuyệt Chiêu Chốt Sale Trực Tiếp',
            teacher: {
              teacher_id: 1,
              teacher_name: 'Hán Quang Dự',
            },
            rate: {
              star: 4,
              half_star: 1,
              non_star: 0,
              total_rate: 32,
            },
            price: '459.000',
            old_price: '599.000',
            img: 'https://static.unica.vn/upload/images/2019/04/69_tuyet_chieu_chot_sale_truc_tiep_m_1555564902.jpg',
          },
          {
            id: 1,
            title: '69 Tuyệt Chiêu Chốt Sale Trực Tiếp',
            teacher: {
              teacher_id: 1,
              teacher_name: 'Hán Quang Dự',
            },
            rate: {
              star: 4,
              half_star: 1,
              non_star: 0,
              total_rate: 32,
            },
            price: '459.000',
            old_price: '599.000',
            img: 'https://static.unica.vn/upload/images/2019/04/69_tuyet_chieu_chot_sale_truc_tiep_m_1555564902.jpg',
          },
          {
            id: 1,
            title: '69 Tuyệt Chiêu Chốt Sale Trực Tiếp',
            teacher: {
              teacher_id: 1,
              teacher_name: 'Hán Quang Dự',
            },
            rate: {
              star: 4,
              half_star: 1,
              non_star: 0,
              total_rate: 32,
            },
            price: '459.000',
            old_price: '599.000',
            img: 'https://static.unica.vn/upload/images/2019/04/69_tuyet_chieu_chot_sale_truc_tiep_m_1555564902.jpg',
          },
          {
            id: 1,
            title: '69 Tuyệt Chiêu Chốt Sale Trực Tiếp',
            teacher: {
              teacher_id: 1,
              teacher_name: 'Hán Quang Dự',
            },
            rate: {
              star: 4,
              half_star: 1,
              non_star: 0,
              total_rate: 32,
            },
            price: '459.000',
            old_price: '599.000',
            img: 'https://static.unica.vn/upload/images/2019/04/69_tuyet_chieu_chot_sale_truc_tiep_m_1555564902.jpg',
          },
          {
            id: 1,
            title: '69 Tuyệt Chiêu Chốt Sale Trực Tiếp',
            teacher: {
              teacher_id: 1,
              teacher_name: 'Hán Quang Dự',
            },
            rate: {
              star: 4,
              half_star: 1,
              non_star: 0,
              total_rate: 32,
            },
            price: '459.000',
            old_price: '599.000',
            img: 'https://static.unica.vn/upload/images/2019/04/69_tuyet_chieu_chot_sale_truc_tiep_m_1555564902.jpg',
          },
        ]
      },categoryParent : [],
    }
  },
  created() {
    this.getCategoryParent();
  }
  ,methods: {
    getCategoryParent(){
      axios.get('/api/category/get/parent').then((response) => {
        this.categoryParent = response.data
      }).catch((error) => {
        console.log(error)
      })

    },getCategoryChildByParentId(id){
      axios.get('/api/category-attribute/find-by-category-id/'+id).then((response) => {
        return response.data;
      }).catch((error) => {
        console.log(error)
      })
    }
  }
}
</script>

<style>
#main-container {
  margin: auto;
}
#main-title {
  margin-top: 20px;
  text-align: center;
}
.card {
  border-radius: 5px !important;
}
.product-grid6 .title {
  font-size: 16px !important;
  margin-bottom: 2px !important;
}
.product-grid6 .teacher-title {
  font-size: 15px !important;
  font-weight: 400 !important;
}
.list-group-item {
  margin-bottom: 10px !important;
}
</style>

<template>
  <Layout>
    <b-col id="main-container" cols="11">
      <h2 id="main-title">DANH SÁCH KHOÁ HỌC</h2>
      <div class="row">
        <div class="col-3">
          <div class="card">
            <div class="card-header">
              <div class="card-title">Chuyên ngành</div>
            </div>
            <div class="card-body">
              <ul class="list-group" >
                <li  v-for="item,index2 in categoryParent" :key="index2" class="list-group-item border-0 p-0" >
                  <a  href="#collapse"
                  data-toggle="collapse"  role="button" aria-expanded="false" aria-controls="collapseExample" >
                  <i class="fe fe-chevron-right"></i> {{item.name}} </a
                  ><span class="product-label">22</span>
                  <div class="" id="collapse">
                    <div v-for="item2,index in getCategoryChildByParentId(item.id)" :key="index" class="card card-body" >
                      {{item2.name}}
                    </div>
                  </div>
                </li>
              </ul>
            </div>
          </div>
          <div class="card">
            <div class="card-header">
              <div class="card-title">Đánh giá</div>
            </div>
            <div class="card-body">
              <ul class="list-group">
                <li class="list-group-item border-0 p-0">
                  <a href="javascript:void(0)"
                    ><i class="fe fe-chevron-right"></i> 1 sao </a
                  ><span class="product-label">22</span>
                </li>
                <li class="list-group-item border-0 p-0">
                  <a href="javascript:void(0)"
                    ><i class="fe fe-chevron-right"></i> 2 sao</a
                  ><span class="product-label">15</span>
                </li>
                <li class="list-group-item border-0 p-0">
                  <a href="javascript:void(0)"
                    ><i class="fe fe-chevron-right"></i> 3 sao </a
                  ><span class="product-label">10</span>
                </li>
                <li class="list-group-item border-0 p-0">
                  <a href="javascript:void(0)"
                    ><i class="fe fe-chevron-right"></i> 4 sao </a
                  ><span class="product-label">88</span>
                </li>
                <li class="list-group-item border-0 p-0">
                  <a href="javascript:void(0)"
                    ><i class="fe fe-chevron-right"></i> 5 sao </a
                  ><span class="product-label">88</span>
                </li>
              </ul>
            </div>
          </div>
        </div>
        <div class="col-9">
          <div class="row">
            <div class="col-xl-12">
              <div class="card p-0">
                <div class="card-body p-4">
                  <div class="row">
                    <div class="col-xl-5 col-lg-8 col-md-8 col-sm-8">
                      <div class="input-group d-flex w-100 float-start">
                        <input
                          type="text"
                          class="form-control border-end-0 my-2"
                          placeholder="Tìm kiếm"
                        />
                        <button
                          class="btn input-group-text bg-transparent border-start-0 text-muted my-2"
                        >
                          <i
                            class="fe fe-search text-muted"
                            aria-hidden="true"
                          ></i>
                        </button>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="row">
            <div
              v-for="course in listCouses.data"
              :key="course.id"
              class="col-xl-4 col-md-6 col-sm-6"
            >
              <div class="card">
                <div class="product-grid6">
                  <div class="product-image6 p-5">
                    <a :href="`course/${course.id}`" class="bg-light">
                      <img
                        class="img-fluid br-7 w-100"
                        :src="course.img"
                        alt="img"
                      />
                    </a>
                  </div>
                  <div class="card-body pt-0">
                    <div class="product-content">
                      <h1 class="title fw-bold fs-20"
                        ><a :href="`course/${course.id}`"
                          >69 Tuyệt chiêu chốt sale trực tiếp</a
                        ></h1
                      >
                      <h1 class="teacher-title fw-bold fs-20"
                        ><a :href="`teacher/${course.teacher.teacher_id}`">{{
                          course.teacher.teacher_name
                        }}</a></h1
                      >
                      <div class="mb-2 text-warning">
                        <i
                          v-for="star in course.rate.star"
                          :key="star"
                          class="fa fa-star text-warning"
                        ></i>
                        <i
                          v-for="star in course.rate.half_star"
                          :key="star"
                          class="fa fa-star-half-o text-warning"
                        ></i>
                        <i
                          v-for="star in course.rate.non_star"
                          :key="star"
                          class="fa fa-star-o text-warning"
                        ></i>
                        <span class="text-muted ml-2"
                          >({{ course.rate.total_rate }})</span
                        >
                      </div>
                      <div class="price"
                        >{{ course.price }} <sup>đ</sup>
                        <span class="ms-4"
                          >{{ course.old_price }} <sup>đ</sup></span
                        >
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <div class="mb-5">
              <div class="float-end">
                <ul class="pagination">
                  <li class="page-item page-prev disabled">
                    <a class="page-link" href="javascript:void(0)" tabindex="-1"
                      >Trước</a
                    >
                  </li>
                  <li class="page-item active"
                    ><a class="page-link" href="javascript:void(0)">1</a></li
                  >
                  <li class="page-item"
                    ><a class="page-link" href="javascript:void(0)">2</a></li
                  >
                  <li class="page-item"
                    ><a class="page-link" href="javascript:void(0)">3</a></li
                  >
                  <li class="page-item"
                    ><a class="page-link" href="javascript:void(0)">4</a></li
                  >
                  <li class="page-item"
                    ><a class="page-link" href="javascript:void(0)">5</a></li
                  >
                  <li class="page-item page-next">
                    <a class="page-link" href="javascript:void(0)">Sau</a>
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
    </b-col>
  </Layout>
</template>
