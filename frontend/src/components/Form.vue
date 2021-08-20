<template>
    <v-row :key="rowId" :id="rowId">
      <v-col cols>
        <v-select
            :items="ALL_CRITERION"
            v-model="criteriaId"
            @change="getTypes(criteriaId); getComponent(criteriaId)"
            item-text="criteriaName"
            item-value="id"
            label="Criteria"
            required
        ></v-select>
      </v-col>
      <v-col>
        <v-select
            :items="types"
            v-model="typeId"
            @change="submit()"
            item-text="typeName"
            item-value="id"
            label="Types"
            required
        ></v-select>
      </v-col>
      <v-col>
        <component :rowId="rowId"
            v-bind:is="component"
           ></component>
      </v-col>

    </v-row>
</template>

<script>
import axios from "axios";
import AmountForm from "@/components/AmountForm";
import TitleForm from "@/components/TitleForm";
import DateForm from "@/components/DateForm";
import {mapGetters, mapActions, mapMutations} from "vuex";

export default {
  name: "Form",
  props: ['rowId'],

  components: {
    AmountForm,
    TitleForm,
    DateForm
  },
  data: () => ({
    types: [],
    component: AmountForm,
    criteriaId: 1,
    typeId: ''
  }),

  async mounted() {
    console.log('Form mounted: ', this.rowId)
    await this.FETCH_CRITERIES();
    this.getTypes(this.criteriaId);
  },

  computed: mapGetters(['ALL_CRITERION', 'GET_NEW_FILTER']),

  methods: {
    getTypes(id) {
      axios.get(this.host + '/type/' + id)
      .then(response => {
        this.types = response.data
      })
    },

    getComponent(id) {
      if (id === 1) {
        this.component = 'amount-form'
      } else if (id === 2) {
        this.component = 'title-form'
      } else if (id === 3) {
        this.component = 'date-form'
      }
    },
    ...mapActions(['FETCH_CRITERIES']),
    ...mapMutations(['CREATE_FILTER']),

    submit(){
      this.CREATE_FILTER({
        id: this.rowId,
        criteria: this.criteriaId,
        type: this.typeId,
      })
    }
  },



}
</script>

<style scoped>

</style>