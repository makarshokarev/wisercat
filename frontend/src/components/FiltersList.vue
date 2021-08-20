<template>
  <v-container class="text-center " >
    <v-row class="text-center">
      <v-col class="mb-4">
        <v-btn height="50" width="200" color="success" dark @click.stop="showAddNewFilterForm=true">Add new product</v-btn>
        <AddNewFilter v-model="showAddNewFilterForm"/>
      </v-col>
    </v-row>

    <div v-for="(item, k) in ALL_FILTERS.slice().reverse()" :key="k" class="elevation-5 mb-6 grey lighten-3"  >
        <v-container>Filter Name: {{ item.filterName }}</v-container>
        <v-container>
          <v-data-table
              :headers="headers"
              :items="item.filters"
              class="elevation-5"
              hide-default-footer
          ></v-data-table>
        </v-container>
      </div>
  </v-container>
</template>

<script>
import {mapGetters, mapActions} from 'vuex'

import AddNewFilter from "@/components/AddNewFilter";

export default {
  name: 'FiltersList',
  components: {
    AddNewFilter
  },

  computed: mapGetters(['ALL_FILTERS']),

  methods: {
    ...mapActions(['FETCH_FILTERS']),
  },

  mounted() {
    this.FETCH_FILTERS();
  },

  data: () => ({
    headers:[
      {text:'Criteria', value:'criteria'},
      {text:'Type', value:'type'},
      {text:'Value', value: 'value'}
    ],
    showAddNewFilterForm: false
  }),
}

</script>
