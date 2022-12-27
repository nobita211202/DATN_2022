<template>
    <div class="row">
        <div class="col-6" ><img class="col-12" :src="src" ref="img"></div>
        <div class="col-6 d-flex ">
            <img class="rounded m-auto shadow w-50" :src="urlImg" alt="">
        </div>
    </div>

</template>

<style >

    .shadow{
        box-shadow: 10px 10px 40px rgba(156, 156, 156, 0.731) !important;
    }

</style>

<script>
import Cropper from 'cropperjs';
import 'cropperjs/dist/cropper.css';
export default{
    name:"CropImg",
    data:()=>{
        return{
            image:{},
            crop:{},
            urlImg:"",
            fileImg:{}
        }
    },
    props:{
        src:String
    },
    mounted(){
        this.image=this.$refs.img
        this.crop = new Cropper(this.image,{
        aspectRatio: 1,
        initialAspectRatio:1,

        zoomable:false,
        scalable:false,
        minCropBoxHeight:100,
        minCropBoxWidth:100,
        cropend:()=>{
            const canva= this.crop.getCroppedCanvas()
            this.urlImg= canva.toDataURL("image.png")
            console.log(this.urlImg);
            canva.toBlob((blob)=>{
            blob.lastModifiedDate = new Date();
            blob.name = "img";
            this.$emit("setImgCrop",new File([blob],"file.jpg"))
        })
        }
        })
   },
}

</script>
