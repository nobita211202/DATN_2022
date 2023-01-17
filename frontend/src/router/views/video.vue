<template>
  <Main>
    <div class="pt-4 bg-dark">
      <div class="container-xxl text-white py-3">
        <div class="d-flex flex-column">
          <span class="fs-1 fw-bold">{{ course.name }}</span>
          <span class="fs-4">{{ video.title }}</span>
        </div>
      </div>
    </div>
    <div class="container-xxl pt-4">
      <div class="row">
        <div class="col-12 col-xl-9 col-lg-8  col" >
          <b-embed
            style="height: 500px;"
            type="iframe"
            :src="video.link"
            allowfullscreen
          ></b-embed>
        </div>
        <div class="col-12 col-xl-3 col-lg-4  border" >
          <div class="py-2 fs-4 border-bottom border-1" >
            Bài giảng
          </div>
          <div class="h-100 px-0" style=" overflow-y: scroll;">
            <span v-for="video,index in videos" class="fs-6 px-2 text-max hover" :key="index + 'video'" @click="setVideo(video)" >Bài {{ index + 1 }}: {{ video.title }}</span>
          </div>
        </div>
      </div>
    </div>
  </Main>
</template>

<script>
import axios from '@/node_modules/axios';
import Main from '@layouts/main.vue';
export default {
  components:{
    Main
  },
  data(){
    return{
      videos:[],
      video:{
        title:"",
        link:""
      },
      course:{}
    }
  },
  created(){
    this.getVideos()
    this.getCourse()
  },
  methods:{
    getCourse(){
      axios.get(`/api/course/get/${this.$route.params.idCourse}`)
      .then(res=>{
        this.course = res.data
      })
    },
    getVideos(){
      console.log(this.$route.params.idCourse);
      axios.get(`/api/video/getByCourseId/${this.$route.params.idCourse}`)
      .then(res =>{
        this.videos = res.data
        this.video = this.videos[0]

      })
    },
    setVideo(video){
      this.video=video
    }
  }
}
</script>

<style >
.text-max{
  display: block;
  	display: -webkit-box;
  	-webkit-line-clamp: 1;
  	-webkit-box-orient: vertical;
  	overflow: hidden;
    line-height: 50px;
  	text-overflow: ellipsis;
    cursor: pointer;
}
.hover:hover{
  background-color: rgba(128, 128, 128, 0.348);

}
</style>
