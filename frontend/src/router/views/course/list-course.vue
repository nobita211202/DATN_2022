<script>
import Layout from '@layouts/main.vue'
export default {
  page: {
    title: 'Danh sách khoá học',
  },
  components: {
    Layout,
  },
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
              <ul class="list-group">
                <li class="list-group-item border-0 p-0">
                  <a href="javascript:void(0)"
                    ><i class="fe fe-chevron-right"></i> Java </a
                  ><span class="product-label">22</span>
                </li>
                <li class="list-group-item border-0 p-0">
                  <a href="javascript:void(0)"
                    ><i class="fe fe-chevron-right"></i> Đồ hoạ</a
                  ><span class="product-label">15</span>
                </li>
                <li class="list-group-item border-0 p-0">
                  <a href="javascript:void(0)"
                    ><i class="fe fe-chevron-right"></i> Web </a
                  ><span class="product-label">10</span>
                </li>
                <li class="list-group-item border-0 p-0">
                  <a href="javascript:void(0)"
                    ><i class="fe fe-chevron-right"></i> CSDL </a
                  ><span class="product-label">88</span>
                </li>
                <li class="list-group-item border-0 p-0">
                  <a href="javascript:void(0)"
                    ><i class="fe fe-chevron-right"></i> C# </a
                  ><span class="product-label">88</span>
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
            <div class="col-xl-4 col-md-6 col-sm-6">
              <div class="card">
                <div class="product-grid6">
                  <div class="product-image6 p-5">
                    <a href="shop-description.html" class="bg-light">
                      <img
                        class="img-fluid br-7 w-100"
                        src="https://static.unica.vn/upload/images/2019/04/69_tuyet_chieu_chot_sale_truc_tiep_m_1555564902.jpg"
                        alt="img"
                      />
                    </a>
                  </div>
                  <div class="card-body pt-0">
                    <div class="product-content">
                      <h1 class="title fw-bold fs-20"
                        ><a href="shop-description.html"
                          >69 Tuyệt chiêu chốt sale trực tiếp</a
                        ></h1
                      >
                      <h1 class="teacher-title fw-bold fs-20"
                        ><a href="shop-description.html">Hán Quang Dự</a></h1
                      >
                      <div class="mb-2 text-warning">
                        <i class="fa fa-star text-warning"></i>
                        <i class="fa fa-star text-warning"></i>
                        <i class="fa fa-star text-warning"></i>
                        <i class="fa fa-star-half-o text-warning"></i>
                        <i class="fa fa-star-o text-warning"></i>
                        <span class="text-muted ml-2">(32)</span>
                      </div>
                      <div class="price"
                        >459.000 <sup>đ</sup>
                        <span class="ms-4">599.000 <sup>đ</sup></span>
                      </div>
                    </div>
                  </div>
                  <!-- <div class="card-footer text-center">
                    <a href="cart.html" class="btn btn-primary mb-1"
                      ><i class="fe fe-shopping-cart me-2"></i>Add to cart</a
                    >
                    <a href="wishlist.html" class="btn btn-outline-primary mb-1"
                      ><i class="fe fe-heart me-2 wishlist-icon"></i>Add to
                      wishlist</a
                    >
                  </div> -->
                </div>
              </div>
            </div>
            <div class="col-xl-4 col-md-6 col-sm-6">
              <div class="card">
                <div class="product-grid6">
                  <div class="product-image6 p-5">
                    <a href="shop-description.html" class="bg-light">
                      <img
                        class="img-fluid br-7 w-100"
                        src="https://static.unica.vn/upload/images/2019/04/69_tuyet_chieu_chot_sale_truc_tiep_m_1555564902.jpg"
                        alt="img"
                      />
                    </a>
                  </div>
                  <div class="card-body pt-0">
                    <div class="product-content">
                      <h1 class="title fw-bold fs-20"
                        ><a href="shop-description.html"
                          >69 Tuyệt chiêu chốt sale trực tiếp</a
                        ></h1
                      >
                      <h1 class="teacher-title fw-bold fs-20"
                        ><a href="shop-description.html">Hán Quang Dự</a></h1
                      >
                      <div class="mb-2 text-warning">
                        <i class="fa fa-star text-warning"></i>
                        <i class="fa fa-star text-warning"></i>
                        <i class="fa fa-star text-warning"></i>
                        <i class="fa fa-star-half-o text-warning"></i>
                        <i class="fa fa-star-o text-warning"></i>
                        <span class="text-muted ml-2">(32)</span>
                      </div>
                      <div class="price"
                        >459.000 <sup>đ</sup>
                        <span class="ms-4">599.000 <sup>đ</sup></span>
                      </div>
                    </div>
                  </div>
                  <!-- <div class="card-footer text-center">
                    <a href="cart.html" class="btn btn-primary mb-1"
                      ><i class="fe fe-shopping-cart me-2"></i>Add to cart</a
                    >
                    <a href="wishlist.html" class="btn btn-outline-primary mb-1"
                      ><i class="fe fe-heart me-2 wishlist-icon"></i>Add to
                      wishlist</a
                    >
                  </div> -->
                </div>
              </div>
            </div>
            <div class="col-xl-4 col-md-6 col-sm-6">
              <div class="card">
                <div class="product-grid6">
                  <div class="product-image6 p-5">
                    <a href="shop-description.html" class="bg-light">
                      <img
                        class="img-fluid br-7 w-100"
                        src="https://static.unica.vn/upload/images/2019/04/69_tuyet_chieu_chot_sale_truc_tiep_m_1555564902.jpg"
                        alt="img"
                      />
                    </a>
                  </div>
                  <div class="card-body pt-0">
                    <div class="product-content">
                      <h1 class="title fw-bold fs-20"
                        ><a href="shop-description.html"
                          >69 Tuyệt chiêu chốt sale trực tiếp</a
                        ></h1
                      >
                      <h1 class="teacher-title fw-bold fs-20"
                        ><a href="shop-description.html">Hán Quang Dự</a></h1
                      >
                      <div class="mb-2 text-warning">
                        <i class="fa fa-star text-warning"></i>
                        <i class="fa fa-star text-warning"></i>
                        <i class="fa fa-star text-warning"></i>
                        <i class="fa fa-star-half-o text-warning"></i>
                        <i class="fa fa-star-o text-warning"></i>
                        <span class="text-muted ml-2">(32)</span>
                      </div>
                      <div class="price"
                        >459.000 <sup>đ</sup>
                        <span class="ms-4">599.000 <sup>đ</sup></span>
                      </div>
                    </div>
                  </div>
                  <!-- <div class="card-footer text-center">
                    <a href="cart.html" class="btn btn-primary mb-1"
                      ><i class="fe fe-shopping-cart me-2"></i>Add to cart</a
                    >
                    <a href="wishlist.html" class="btn btn-outline-primary mb-1"
                      ><i class="fe fe-heart me-2 wishlist-icon"></i>Add to
                      wishlist</a
                    >
                  </div> -->
                </div>
              </div>
            </div>
            <div class="col-xl-4 col-md-6 col-sm-6">
              <div class="card">
                <div class="product-grid6">
                  <div class="product-image6 p-5">
                    <a href="shop-description.html" class="bg-light">
                      <img
                        class="img-fluid br-7 w-100"
                        src="https://static.unica.vn/upload/images/2019/04/69_tuyet_chieu_chot_sale_truc_tiep_m_1555564902.jpg"
                        alt="img"
                      />
                    </a>
                  </div>
                  <div class="card-body pt-0">
                    <div class="product-content">
                      <h1 class="title fw-bold fs-20"
                        ><a href="shop-description.html"
                          >69 Tuyệt chiêu chốt sale trực tiếp</a
                        ></h1
                      >
                      <h1 class="teacher-title fw-bold fs-20"
                        ><a href="shop-description.html">Hán Quang Dự</a></h1
                      >
                      <div class="mb-2 text-warning">
                        <i class="fa fa-star text-warning"></i>
                        <i class="fa fa-star text-warning"></i>
                        <i class="fa fa-star text-warning"></i>
                        <i class="fa fa-star-half-o text-warning"></i>
                        <i class="fa fa-star-o text-warning"></i>
                        <span class="text-muted ml-2">(32)</span>
                      </div>
                      <div class="price"
                        >459.000 <sup>đ</sup>
                        <span class="ms-4">599.000 <sup>đ</sup></span>
                      </div>
                    </div>
                  </div>
                  <!-- <div class="card-footer text-center">
                    <a href="cart.html" class="btn btn-primary mb-1"
                      ><i class="fe fe-shopping-cart me-2"></i>Add to cart</a
                    >
                    <a href="wishlist.html" class="btn btn-outline-primary mb-1"
                      ><i class="fe fe-heart me-2 wishlist-icon"></i>Add to
                      wishlist</a
                    >
                  </div> -->
                </div>
              </div>
            </div>
            <div class="col-xl-4 col-md-6 col-sm-6">
              <div class="card">
                <div class="product-grid6">
                  <div class="product-image6 p-5">
                    <a href="shop-description.html" class="bg-light">
                      <img
                        class="img-fluid br-7 w-100"
                        src="https://static.unica.vn/upload/images/2019/04/69_tuyet_chieu_chot_sale_truc_tiep_m_1555564902.jpg"
                        alt="img"
                      />
                    </a>
                  </div>
                  <div class="card-body pt-0">
                    <div class="product-content">
                      <h1 class="title fw-bold fs-20"
                        ><a href="shop-description.html"
                          >69 Tuyệt chiêu chốt sale trực tiếp</a
                        ></h1
                      >
                      <h1 class="teacher-title fw-bold fs-20"
                        ><a href="shop-description.html">Hán Quang Dự</a></h1
                      >
                      <div class="mb-2 text-warning">
                        <i class="fa fa-star text-warning"></i>
                        <i class="fa fa-star text-warning"></i>
                        <i class="fa fa-star text-warning"></i>
                        <i class="fa fa-star-half-o text-warning"></i>
                        <i class="fa fa-star-o text-warning"></i>
                        <span class="text-muted ml-2">(32)</span>
                      </div>
                      <div class="price"
                        >459.000 <sup>đ</sup>
                        <span class="ms-4">599.000 <sup>đ</sup></span>
                      </div>
                    </div>
                  </div>
                  <!-- <div class="card-footer text-center">
                    <a href="cart.html" class="btn btn-primary mb-1"
                      ><i class="fe fe-shopping-cart me-2"></i>Add to cart</a
                    >
                    <a href="wishlist.html" class="btn btn-outline-primary mb-1"
                      ><i class="fe fe-heart me-2 wishlist-icon"></i>Add to
                      wishlist</a
                    >
                  </div> -->
                </div>
              </div>
            </div>
            <div class="col-xl-4 col-md-6 col-sm-6">
              <div class="card">
                <div class="product-grid6">
                  <div class="product-image6 p-5">
                    <a href="shop-description.html" class="bg-light">
                      <img
                        class="img-fluid br-7 w-100"
                        src="https://static.unica.vn/upload/images/2019/04/69_tuyet_chieu_chot_sale_truc_tiep_m_1555564902.jpg"
                        alt="img"
                      />
                    </a>
                  </div>
                  <div class="card-body pt-0">
                    <div class="product-content">
                      <h1 class="title fw-bold fs-20"
                        ><a href="shop-description.html"
                          >69 Tuyệt chiêu chốt sale trực tiếp</a
                        ></h1
                      >
                      <h1 class="teacher-title fw-bold fs-20"
                        ><a href="shop-description.html">Hán Quang Dự</a></h1
                      >
                      <div class="mb-2 text-warning">
                        <i class="fa fa-star text-warning"></i>
                        <i class="fa fa-star text-warning"></i>
                        <i class="fa fa-star text-warning"></i>
                        <i class="fa fa-star-half-o text-warning"></i>
                        <i class="fa fa-star-o text-warning"></i>
                        <span class="text-muted ml-2">(32)</span>
                      </div>
                      <div class="price"
                        >459.000 <sup>đ</sup>
                        <span class="ms-4">599.000 <sup>đ</sup></span>
                      </div>
                    </div>
                  </div>
                  <!-- <div class="card-footer text-center">
                    <a href="cart.html" class="btn btn-primary mb-1"
                      ><i class="fe fe-shopping-cart me-2"></i>Add to cart</a
                    >
                    <a href="wishlist.html" class="btn btn-outline-primary mb-1"
                      ><i class="fe fe-heart me-2 wishlist-icon"></i>Add to
                      wishlist</a
                    >
                  </div> -->
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
