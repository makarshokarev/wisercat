<template>
  <div class="home">
    <FiltersList/>
  </div>
</template>

<script>
// @ is an alias to /src
import FiltersList from '@/components/FiltersList.vue'

export default {
  name: 'Home',
  components: {
    FiltersList: FiltersList,
  }
}
</script>
