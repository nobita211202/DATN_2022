<template>
  <div>
  <div>
    <button
          class="btn ps-4 btn-warning mb-2 float-right"
          @click="$bvModal.show('modal-diagram')"
          ><b-icon icon="diagram3" aria-hidden="true"></b-icon> Sơ đồ danh mục</button>
  </div>
  <b-modal
      id="modal-diagram"
      size="xl"
      centered
      scrollable
      hide-header-close
      hide-backdrop
    >
      <template v-slot:modal-title> Sơ đồ danh mục </template>
        <b-row>
          <b-col >
            <div

            class="mt-2 p-0">
            <VoBasic
            class="h-100 w-100"
            style="overflow: scroll;"
            :data="chartData"></VoBasic>
           </div>
          </b-col>
        </b-row>
      <template v-slot:modal-footer>
        <div></div>
      </template>
    </b-modal>
  </div>
</template>

<script>
import { VoBasic } from 'vue-orgchart'
export default {
  components:{
    VoBasic
  },
  props:{
    diagramCate:Array
  },
  data(){
    return{
      chartData : {
        name: 'Danh mục',
        children: this.diagramCate
      }
    }
  },
  methods: {
    handleClick: e => {
      console.log(e);
      alert(e);
    }
  },
  watch:{
    diagramCate(){
      console.log("abc");
      console.log(
        this.diagramCate
      );
      this.chartData.children=this.diagramCate
    }
  }
}
</script>

<style >


</style>
