<template>
  <v-text-field
      type="text"
      label="Title"
      v-model="title"
      @input="submit()"
  ></v-text-field>
</template>

<script>
import {mapMutations} from "vuex";

export default {
  name: "TitleForm",

  data: () => ({
    title: ''
  }),
  props:['rowId'],

  methods:{
    ...mapMutations(['ADD_TITLE']),
    submit(){
      this.ADD_TITLE({
        title: this.title,
        rowId: this.rowId
      })
    },
  }
}
</script>