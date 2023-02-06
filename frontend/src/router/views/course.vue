<template>
  <Layout>
    <div class="bg bg-dark">
      <b-container >
      <b-row class="">
        <b-col sm="8" >
          <div class="py-2 lh-3 py-2">
            <p class="fs-3 fw-bolder mb-0">{{course.name}}</p>

          </div>
        </b-col>
        <b-col sm="4" class="position-relative">
          <div class="position-absolute top-50 w-100 ">
            <div class="p-5 bg-white">
              <span class="text-dark">
                <span class="fs-3 text-dark">{{course.price | formatNumber}}</span>
              </span>
              <b-button variant="success"  class=" my-2 w-100"  @click.prevent="timer(cartCourse)"><i class="fa fa-cart-plus"></i> Thêm vào giỏ</b-button>
              <span class="">
                <ul class="pt-5 pb-2  text-dark d-flex flex-column">
                    <span class="my-1"><i class="fa fa-clock-o" ></i> Thời lượng:
                        <span>{{ course.studyTime }} giờ</span>
                    </span>
                    <span class="my-1"><i class="fa fa-play-circle" aria-hidden="true"></i> Giáo trình:
                        <span>{{ course.videos.length }} bài giảng</span>
                    </span>
                </ul>
              </span>
              <!-- <b-row>
                <b-col ms="4" class="  d-flex" >
                  <a class="" href=""><i class=" fa fa-share-alt "></i> Chia sẻ</a>
                </b-col>
                <b-col ms="4" class="  d-flex" >
                  <a class="" href=""><i class=" fa fa-gift "></i> Tặng bạn</a>
                </b-col>
                <b-col ms="4" class="  d-flex" >
                  <a class="" href=""><i class=" fa fa-ticket "></i> Mã giảm giá</a>
                </b-col>
              </b-row> -->
            </div>
            <div class="p-5 mt-3 bg-white text-dark ">
              <p class="fs-6 fw-bold border-bottom ">Giảng viên</p>
              <div class="d-flex flex-column">
                <span class="w-50 mx-auto">
                  <img class="rounded shadow" src="https://dailynorthwestern.com/wp-content/uploads/2015/10/Henry_Cao-Web1.jpg" alt="">
                </span>
                <p class="fs-4 fw-bold text-center">Nguyễn Đăng Hoàng</p>
                <div class="my-2">
                  <div class=" fw-bold">
                    <i class="fa  fa-play-circle"></i>
                    8 khóa học
                  </div>
                  <div class=" fw-bold ">
                    <i class="fa  fa-users"></i>
                    12 học viên
                  </div>
                </div>
                <p>Giảng viên Hà Kế Tú - Haketu với kinh nghiệm 12 năm để chơi đàn và nghiên cứu về âm nhạc, 5 năm kinh nghiệm tổ chức các khóa học Guitar offline tại Úc và Hà Nội, đào tạo hàng trăm học viên trong những khóa: Guitar cổ điển, Fingerstyle và Đệm hát.
                    Hà Kế Tú hay còn thường gọi là Haketu là một trong những Giảng viên, Youtuber có tên</p>

                </div>
            </div>
          </div>
        </b-col>
      </b-row>

    </b-container>

    </div>
    <b-container class="mt-3 mb-5">
      <b-row>
        <b-col sm="8">
          <div  class="">
            <!-- <b-embed
              class="h-800px"
              type="iframe"
              aspect="16by9"
              src="https://www.youtube.com/embed/zpOULjyy-n8?rel=0"
              allowfullscreen
            ></b-embed> -->
            <img :src="getImg(course.image)" class="w-100" alt="">
          </div>
        </b-col>
      </b-row>
      <b-row>
        <b-col sm="8">
          <div class="my-1 bg-white p-2">
            <strong class="fs-5 fw-bolder">Giới thiệu khóa học</strong>
          </div>
          <div class="bg-white p-2" v-html="course.describe">

          </div>

          <div class="bg-white p-2 border-bottom " v-for="video,index in course.videos" :key="index">

              <div class="d-flex" >
              <a v-b-toggle.b1 class="fw-bold pe-2"><i class="fa fa-minus-square"></i></a>

                <span>Bài {{ index + 1 }}: {{ video.title }}</span>
                <span v-if="video.free" class="ms-auto my-auto px-2"><a :href="`/video/${course.id}`">Học thử</a></span>
              </div>
          </div>
          <!-- <div class="my-1 bg-white p-2 ">
            <strong class="fs-5 fw-bolder">Khóa học liên quan</strong>
          </div> -->
          <!-- <div class="bg-white ">
            <b-row class="py-3 border-bottom  px-2 ">
              <b-col sm="1" class="pe-0">
                <img class="img my-auto" src="data:image/png;base64,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"  alt="">
              </b-col>
              <b-col class="d-flex">
                <span class="d-flex">
                  <a href="#" class="my-auto">
                    <span class="h4 fw-bold ">Tên khóa học</span> <br>
                    <span class="text-danger"> 120,999đ</span>
                  </a>
                </span>
                <span class="ms-auto my-auto px-2">
                  <span>4.8/</span>
                  <i class="fa fa-star text-warning"></i>
                  <i class="fa fa-star text-warning"></i>
                  <i class="fa fa-star text-warning"></i>
                  <i class="fa fa-star text-warning"></i>
                </span>
                <span class="my-auto px-2">
                  <i class="bi bi-heart"></i>
                </span>
              </b-col>
            </b-row>
            <b-row class="py-3 border-bottom  px-2 ">
              <b-col sm="1" class="pe-0">
                <img class="img my-auto" src="data:image/png;base64,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"  alt="">
              </b-col>
              <b-col class="d-flex">
                <span class="d-flex">
                  <a href="#" class="my-auto">
                    <span class="h4 fw-bold ">Tên khóa học</span> <br>
                    <span class="text-danger"> 120,999đ</span>
                  </a>
                </span>
                <span class="ms-auto my-auto px-2">
                  <span>4.8/</span>
                  <i class="fa fa-star text-warning"></i>
                  <i class="fa fa-star text-warning"></i>
                  <i class="fa fa-star text-warning"></i>
                  <i class="fa fa-star text-warning"></i>
                </span>
                <span class="my-auto px-2">
                  <i class="bi bi-heart"></i>
                </span>

              </b-col>
              <div class="text-center">
                <a href="">Xem thêm</a>
              </div>
            </b-row>
          </div> -->
          <div class="my-1 bg-white p-2 ">
            <strong class="fs-5 fw-bolder">Đánh giá chung</strong>
          </div>
          <div class="bg-white ">
            <b-row>
              <b-col sm="4" class="d-flex flex-column">
                <span class="number m-auto">{{ Math.round(avgStar[0] * 10) / 10 }}</span>
                <p class="text-center">{{ avgStar[1] }} đánh giá</p>
              </b-col>
              <b-col sm="8">
                <div class="my-3">
                  <b-row class="d-flex my-3" v-for=" item in countCommentByStar" :key="item.stt">
                    <b-col sm="8" class="">
                      <b-progress :value="item.count"  variant="warning" :max="avgStar[1]" class=" radius  mx-0"></b-progress>
                    </b-col>
                    <b-col sm="4"  class="my-auto">
                        <i v-for="x in [1,2,3,4,5]" class="fa fa-star" :key="x + 'x'" :class="x <= item.stt ? 'text-warning' : 'text-light'"></i>
                        <span class="px-1">{{ item.count }}</span>
                    </b-col>
                  </b-row>


                </div>
              </b-col>
            </b-row>
          </div>
          <!-- <div class="my-1 bg-white p-2 ">
            <strong class="fs-5 fw-bolder">Nhận xét</strong>
          </div>
          <div class="my-1 bg-white p-2">
           <div class="d-flex">
            <img  class="wh rounded my-auto" style="width: 50px;height: 50px;" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS95t740XwECjf9UqEXOR1q3ovhxhqxlmVElw&usqp=CAU" alt="">
            <div class="mx-2 my-auto lh-1">
              <div class="mb-2 fw-bold">Hoàng</div>
              <div class="">... </div>
            </div>
            <div class="ms-auto px-2 my-auto">
                <i class="fa fa-star text-warning"></i>
                  <i class="fa fa-star text-warning"></i>
                  <i class="fa fa-star text-warning"></i>
                  <i class="fa fa-star text-warning"></i>
                  <i class="fa fa-star text-warning"></i>
              </div>
          </div>
          </div> -->
        </b-col>
      </b-row>
      <div class="col-12 col-lg-8" v-show="isComment">
        <div>
          <span class="fw-bold fs-5">Đánh giá của bạn</span>
        </div>
        <div class="d-flex border-bottom">
          <div class="me-auto my-auto fs-5">Chất lượng</div>
          <div class="my-auto ">
            <div class="ms-auto fs-3 px-2 my-auto">
                <i class="fa fa-star "  v-for="vl in [1,2,3,4,5]" :key="vl" :class="comment.numberStar >= vl ? 'text-warning' : 'text-light'" @click="comment.numberStar = vl" ></i>
            </div>
          </div>
        </div>
        <div>
          <label for="lab">Lời nhận xét</label>
           <textarea v-model="comment.commentLine" name="" class="form-control" id="lab" cols="30" rows="3">

            </textarea>
          <div class="d-flex ">
            <button @click="addComment" class="ms-auto btn btn-primary mt-3 px-5">Gửi</button>
          </div>
        </div>
      </div>
      <div class=" col-12 col-lg-8">
        <div>
          <span class="fw-bold fs-5">Đánh giá của mọi người</span>
        </div>
        <div v-if="comments.length < 1">
          <p class="text-center text-muted fs-5">Chưa có đánh giá</p>
        </div>
        <div v-for="cmt,index in comments" :key="cmt.id">
          <div class="my-1  p-2" :class="index % 2 !== 0 ? 'bg-white' : 'bg-dark text-white'">
           <div class="d-flex">
            <img  class="wh rounded my-auto" style="width: 50px;height: 50px;" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS95t740XwECjf9UqEXOR1q3ovhxhqxlmVElw&usqp=CAU" alt="">
            <div class="mx-2 my-auto lh-1">
              <div class="mb-2 fw-bold">{{cmt.user.name}}</div>
              <div class="">{{ cmt.commentLine }} </div>
            </div>
            <div class="ms-auto px-2 my-auto">
                <i class="fa fa-star " v-for="vl in [1,2,3,4,5]" :key="vl" :class="cmt.numberStar >= vl ? 'text-warning' : 'text-light'" ></i>
            </div>
            </div>
          </div>
        </div>
        <div v-if="comments.length > 0" class="text-center">
          <span @click="getComments" style="cursor: pointer;">Xem thêm</span>
        </div>
      </div>
      <div class="mb-5"></div>
    </b-container>
  </Layout>
</template>

<script>
import Layout from '@layouts/main.vue'
import axios from '@/node_modules/axios'
import store from '@/src/state/store'
const user= Object.assign({},store.state.auth.currentUser)
export default {

  components:{
      Layout
  },methods: {
    timer(callback){
      if(this.debounceAddCart !== null)
      clearTimeout(this.debounceAddCart)
      this.debounceAddCart= setTimeout(callback,900)
    },
    cartCourse(){
      var cart = {
        "user":{id:user.id},
        "courseId":this.course.id
      }
      axios.post("/api/cart/add",cart)
        .then( _ =>{
          console.log(_.data);
          this.$toast.center('<div class="px-2 py-1"><i class="text-success fs-1 mb-1 fw-bold fa-solid fa-circle-check"></i> <p>Đã thêm</p> </div>')
        }).catch(_ =>{
          console.log(_);
          this.$toast.center(`<div class="px-2 py-1"><i class="text-danger fs-1 mb-1 fw-bold bi bi-x-circle-fill"></i> <p>${_.response.data} </p> </div>`)
        })
    },
    getImg(name){
      if(name === undefined) return
      return `${axios.defaults.baseURL}/api/image/get/${name}`
    },
    addComment(){
      axios.post(`/api/comment/add`,this.comment)
      .then(res=>{
        this.comments.unshift(res.data)

      })
    },
     isCommented(){
      if(!user?.id) return
       axios.get(`/api/comment/isComment/${user.id}/${this.course.id}`)
      .then(res=>{
        this.isComment = res.data
        console.log("log "+ this.isComment);
      })
    },
    getAvgStar(){
      axios.get(`/api/comment/avgStar/${this.course.id}`)
      .then(res=>{
        this.avgStar= res.data
        console.log("avgv :"+res.data);
        console.log();
      })
    },
    getCountCommentByStar(){
      for(let i=0 ;i<5;i++){
        axios.get(`/api/comment/getCountComment/${this.course.id}/${i+1}`)
        .then(res =>{
          const data ={
            stt:i+1,
            count:res.data
          }
          this.countCommentByStar.push(data)
          this.countCommentByStar = this.countCommentByStar.sort((a,b) => a.stt - b.stt)
        })
      }
    },
    getComments(){
      axios.get(`/api/comment/get/${this.tabComment}/${this.course.id}`)
      .then(res=>{
        this.comments.push(...res.data)
        console.log("comment");
        console.log(res.data);
      })
    }
  },
  data(){
    return{
      // lstCourseLQ:[],
      course:"",
      debounceAddCart:null,
      comment:{
        user:{id:user.id},
        course:{},
        numberStar:1
      },
      comments:[],
      isComment:false,
      tabComment:0,
      avgStar:'0',
      countCommentByStar:[]
    }
  },
  filters:{
    formatNumber:function(value){
      return new Intl.NumberFormat().format(value)+"đ"
    }
  },
  created(){
    axios.get(`/api/course/get/${this.$route.params.id}`)
    .then((res)=>{
      this.course=res.data
      this.comment.course = res.data
      this.isCommented()
      this.getComments()
      this.getAvgStar()
      this.getCountCommentByStar()
    })

    // axios.get(`/api/course/get/${this.$route.params.id}`)
  },
  watch:{

  }
}
</script>


<style  >
.bg{
  color: white;
}
.hover:hover{
  background-color: rgba(155, 152, 152, 0.141) !important;
}
a{
  color: black;
}
.h-800px{
  height: 500px !important;
}
.radius{
  border-radius: 60px !important;
  border: 1px rgba(209, 208, 208, 0.327) solid;
}
.number{
  font-size: 100px;
  font-weight: 500;
}
.shadow{
  box-shadow: 10px 5px 40px rgba(156, 156, 156, 0.731) !important;
}
.img{
  width: 50px !important;
}

</style>
