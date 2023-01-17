<template>
  <div class="container">
    <Main>
      <div class="row">
        <div class="col-12">
          <div class="card">
            <div class="card-header">
              <h4 class="card-title">Danh sách khóa học</h4>
            </div>
            <div class="card-content">
              <div class="card-body">
                <div class="row">
                  <div class="col-12">
                    <div class="table-responsive">
                      <table class="table table-hover mb-0">
                        <thead>
                          <tr>
                            <th>STT</th>
                            <th>Tên khóa học</th>
                            <th>Giá</th>
                            <th>Thời gian</th>
                            <th>Ngày tạo</th>
                            <th>Ngày cập nhật</th>
                            <th>Trạng thái</th>
                            <th>Thao tác</th>
                          </tr>
                        </thead>
                        <tbody>
                          <tr v-for="(item,index) in lstCourse" :key="index">
                            <td>{{index + 1}}</td>
                            <td>{{item.name}}</td>
                            <td>{{item.price}}</td>
                            <td>{{item.time}}</td>
                            <td>{{item.created_at}}</td>
                            <td>{{item.updated_at}}</td>
                            <td>
                              <span v-if="item.status === 1" class="badge badge-success">Hoạt động</span>
                              <span v-else class="badge badge-danger">Không hoạt động</span>
                            </td>
                            <td>
                              <div class="btn-group" role="group" aria-label="Basic example">
                                <button type="button" class="btn btn-outline-primary">Sửa</button>
                                <button type="button" class="btn btn-outline-danger">Xóa</button>
                              </div>
                            </td>
                          </tr>
                        </tbody>
                      </table>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </Main>
  </div>
</template>

<script>
  import axios from '@/node_modules/axios';
  export default {
    data() {
      return {
        lstCourse: [],
      };
    },
    created() {
      this.getCourse();
    },
    methods: {
      getCourse() {
        axios
          .get("http://localhost:3000/course")
          .then((res) => {
            this.lstCourse = res.data;
          })
          .catch((err) => {
            console.log(err);
          });
      },
    },
  }
</script>
