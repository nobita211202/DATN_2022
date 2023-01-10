<template>
  <div>
  <div>
    <b-button variant="success" @click="mdTrue"><i :class="showDemoIcon"></i> Chọn icon</b-button>

  </div>
  <b-modal
      id="modal-edit-icon"
      size="xl"
      centered
      scrollable
      hide-header-close
      hide-backdrop
    >
      <template v-slot:modal-title> Chọn icon </template>
        <b-row>
          <b-col>
            <b-form-input
              v-model="txtSearch"
              placeholder="Tìm kiếm Icon"
              ></b-form-input>
            <div
            class="mt-2 p-0">

           </div>
          </b-col>

        </b-row>
      <template v-slot:modal-footer>
      </template>
    </b-modal>
  </div>
</template>

<script>
import { bus } from '@src/main'
export default {
  data(){
    const fontIcons=require("@assets/fontIcon.json")
    console.log(fontIcons);
    return{
      fontIcons:fontIcons,
      showDemoIcon:"",
      typeIcon:"",
      txtSearch:"",

    }
  },

  methods:{
    mdTrue(){
      this.$bvModal.show("modal-edit-icon")
    },
    mdFalse(font){
      this.showDemoIcon = font.icon
      bus.$emit('setIcon',this.showDemoIcon)
      this.$bvModal.hide("modal-edit-icon")
    }
  }
}
</script>

<style  >

.focus:hover{
   background-color: rgb(1, 127, 108) !important;
   color: white !important;
}

</style>
