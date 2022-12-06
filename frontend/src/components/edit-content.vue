<template>
   <div>
        <textarea id="edit" name="editor1"></textarea>
   </div>
</template>

<script>
export default{
    setup(props){
        console.log("DMMMMMMMMMMMMMMMMMMMMMMMMMMMm");
        return{
            a:""
        }
    },
    props:{
        content:{
            type:String,
            default:""
        }
    },

//     created(){
//         const ck= {}
//         console.log(window.CKEDITOR.replace("edit"));
//         // var a=()=>{
//         //     ck= window.CKEDITOR.replace("editor1");
//         //     // ck.on("change",()=>{
//         //     //     console.log(ck);
//         //     //     // this.$emit("setWord",ck.value.getData())
//         //     //     // console.log(ck.value);
//         //     // })
//         // }
//         console.log(ck);

//         // a()
//         return {
//             ck
//         }
//    },
   watch:{
    content(){
        this.ck.setData(this.content)
    }
   }
}
</script>
