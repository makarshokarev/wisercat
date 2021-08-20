<template>
  <v-text-field
      type="number"
      label="Number"
      v-model.number="number"
      @input="submit()"
  ></v-text-field>
</template>

<script>

import {mapMutations} from "vuex";

export default {
  name: "AmountForm",

  data: () => ({
    number: ''
  }),
  props:['rowId'],

  methods:{
    ...mapMutations(['ADD_AMOUNT']),
    submit(){
      this.ADD_AMOUNT({
            amount: this.number,
            rowId: this.rowId
      })
    },
  }
}
</script>