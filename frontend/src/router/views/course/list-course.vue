<script>
import axios from '@/node_modules/axios';
import Layout from '@layouts/main.vue'
import Carousel from "@/src/components/lstcourse.vue"
export default {
  page: {
    title: 'Danh sách khoá học',
  },

  components: {
    Layout,
    Carousel
  },
  data() {
    return {
      listCouses: {
        data: [

        ]
      },categoryParent : [],
    }
  },
  created() {
    this.getCategoryParent();
    this.getCourse();
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
    },getCourse(){
      axios.get(`/api/course/getCourseAndLike/${15}`).then((response) => {
        this.listCouses.data = response.data
        console.log(response.data);
      }).catch((error) => {
        console.log(error)
      })
    },
    getImg(name){
      return `${axios.defaults.baseURL}/api/image/get/${name}`
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

.before::before{
  content: "";
  background-position: center center;
  background-repeat: no-repeat;
  background-size: cover;
  position: absolute;
  opacity: 0.25;
  height: 100%;
  width: 100%;
  background-image: url(https://www.ox.ac.uk/sites/files/oxford/field/field_image_main/learning.jpg);
  background-attachment: fixed;
}
.bg-fixed{
  background-attachment: fixed;
}

.fw-blod{
  font-weight: 700 !important;
}

</style>

<template>
  <Layout>
    <div class="bg-dark  w-100" >
      <div class="h-1000 before position-relative ">
        <!-- <div  class="w-100 h-100 position-absolute bg-dark opacity-75">
        </div> -->
        <div class="d-flex flex-column w-100 h-100">
            <div class="container-xxl text-white mt-5">
              <div class="border-bottom border-white py-2">
                <a  v-for="c,index in categoryParent"  :key="index" href="#" class="text-white">
                {{ c.name }}
                  <span v-show="index < ( categoryParent.length - 1)" class="px-2">/</span>
                </a>
              </div>
              <div>
                <span class="fs-1 fw-blod">Top 8 khóa học bán chạy trong tuần</span>
              </div>
            </div>
            <div class="my-auto container-xxl">
              <b-row>
                <b-col sm="12">
                  <Carousel :classtext="'text-white'" class="text-white" :listCourse="listCouses.data" />
                </b-col>
              </b-row>
            </div>
        </div>
      </div>
    </div>
    <div class="container-xxl mt-5">
      <div>
        <span class="fs-1 fw-blod">Khóa học được học nhiều nhất</span>
      </div>
      <!-- <Carousel :classtext="'text-dark'" class="text-dark" :listCourse="listCouses.data" /> -->

    </div>
  </Layout>
</template>
