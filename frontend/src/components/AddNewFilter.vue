<template>
  <div>
    <v-dialog v-model="show" width="1000px" scrollable>
      <v-card max-height="600px">
        <v-card-title>
          <span class="headline">Add new Filter</span>
        </v-card-title>
        <v-card-text>
          <v-text-field
              v-model="filterName"
              :rules="[() => !!filterName || 'This field is required']"
              @change="submit()"
              label="Filter name"
              required
          >
          </v-text-field>
          <form>
            <v-row v-for="(item) in items" :key="item.id">
              <v-col cols="11">
                <Form :key="item.id" :rowId="item.id" v-bind:is="item.form">
                </Form>
              </v-col>
              <v-col cols="1">
                <v-btn :key="item.id" class="mx-2" fab dark small color="primary" @click="deleteRow(item.id)">
                  <v-icon dark>
                    mdi-minus
                  </v-icon>
                </v-btn>
              </v-col>
            </v-row>
          </form>
        </v-card-text>
        <div class="my-2 text-center">
          <v-btn color="warning" dark @click="addRow()">Add row</v-btn>
        </div>
        <v-divider class="mt-12"></v-divider>
        <v-card-actions>
          <v-btn text @click.stop="show=false">
            Cancel
          </v-btn>
          <v-spacer></v-spacer>
          <v-btn color="success" dark @click.stop="addFilter()">
            Save Product
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>

import Form from "@/components/Form";


import axios from "axios";
import {mapActions, mapGetters, mapMutations} from "vuex";

export default {
  name: "AddNewFilter",

  data: () => ({
    dialog: false,
    items: [],
    filterName: '',
    rowId: 0
  }),

  methods: {
    ...mapActions(['FETCH_FILTERS']),
    ...mapMutations(['ADD_FILTER_NAME', 'DELETE_ROW', 'RESET_FILTER_FORMS']),

    addFilter() {
      if (this.GET_NEW_FILTER.filters.length !== 0) {
        axios.post(this.host + '/filter', this.GET_NEW_FILTER).then(() => {
          this.FETCH_FILTERS();
          this.show = false
          this.RESET_FILTER_FORMS()
        })
      } else {
        console.log('insert one criteria')
      }
    },

    addRow() {
      this.items.push({
        id: this.rowId++,
        form: Form
      });
    },

    submit() {
      this.ADD_FILTER_NAME({
        name: this.filterName
      })
    },

    deleteRow(id) {
      console.log('row id in AddNewFilter: ', id)
      this.DELETE_ROW(
          id
      )
      const i = this.items.findIndex(f => f.id === id)
      this.items.splice(i, 1)
    }
  },

  watch: {
    show(visible) {
      if (visible) {
        // Here you would put something to happen when dialog opens up
        console.log("Dialog was opened!")
        this.addRow()

      } else {
        console.log("Dialog was closed!")
        this.items = []
        this.filterName = ''
        this.RESET_FILTER_FORMS()
      }
    }
  },
  props: {
    value: Boolean
  },

  computed: {
    ...mapGetters(['GET_NEW_FILTER', 'GET_ITEMS']),
    show: {
      get () {
        return this.value
      },
      set (value) {
        this.$emit('input', value)
      }
    }
  },
}
</script>
<style scoped>

</style>


