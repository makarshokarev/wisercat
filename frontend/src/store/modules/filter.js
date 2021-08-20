import axios from "axios";

export default {
  state: {
    filters: [],
    criteries: [],
    newFilter: {
      filterName: '',
      filters:[]
    },
  },
  actions: {
    FETCH_FILTERS({commit}) {
      return axios.get('http://localhost:8090/filter', {
        method: "GET"
      })
      .then((filters) => {
        commit('UPDATE_FILTERS', filters.data)
      })
      .catch((error) => {
        console.log(error)
      })
    },

    FETCH_CRITERIES({commit}) {
      return axios.get('http://localhost:8090/criteries', {
        method: "GET"
      })
      .then((criteries) => {
        commit('GET_CRITERIAS', criteries.data)
      })
      .catch((error) => {
        console.log(error)
      })
    },
  },
  mutations: {
    UPDATE_FILTERS(state, filters) {
      state.filters = filters
    },

    GET_CRITERIAS(state, criteries) {
      state.criteries = criteries
    },
    CREATE_FILTER(state, newFilter){
      state.newFilter.filters.push(newFilter)
    },
    ADD_AMOUNT(state, amount){

      const i = state.newFilter.filters.findIndex(f => f.id === amount.rowId)
      state.newFilter.filters[i].value = amount.amount

       //state.newFilter.filters[amount['rowId']].value = amount.amount;

    },
    ADD_TITLE(state, title) {

      const i = state.newFilter.filters.findIndex(f => f.id === title.rowId)
      state.newFilter.filters[i].value = title.title

      // state.newFilter.filters[title['rowId']].value = title.title;
    },
    ADD_DATE(state, date) {
      const i = state.newFilter.filters.findIndex(f => f.id === date.rowId)
      state.newFilter.filters[i].value = date.date

      // state.newFilter.filters[date['rowId']].value = date.date;
    },
    ADD_FILTER_NAME(state, name) {
      state.newFilter.filterName = name.name;
    },
    DELETE_ROW(state, rowId){

      const i = state.newFilter.filters.findIndex(f => f.id === rowId)
      state.newFilter.filters.splice(i , 1)

    },
    RESET_FILTER_FORMS(state){
      state.newFilter.filterName = '';
      state.newFilter.filters = []
    },
  },

  getters: {
    ALL_FILTERS(state) {
      return state.filters
    },
    ALL_CRITERION(state) {
      return state.criteries
    },
    GET_NEW_FILTER(state) {
      return state.newFilter
    },
    GET_ITEMS(state){
      return state.items
    }
  }
}