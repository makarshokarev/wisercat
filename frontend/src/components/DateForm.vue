<template>
  <v-text-field
      type="date"
      label="Date"
      v-model="date"
      @input="submit()"
  ></v-text-field>
</template>

<script>
import {mapMutations} from "vuex";

export default {
  name: "DateForm",

  data: () => ({
    date: ''
  }),

  props:['rowId'],

  methods:{
    ...mapMutations(['ADD_DATE']),
    submit(){
      this.ADD_DATE({
        date: this.date,
        rowId: this.rowId
      })
    },
  }
}
</script>