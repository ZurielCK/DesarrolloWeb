<template>
  <b-container>
      <div>
      <b-breadcrumb :items="items2"></b-breadcrumb>

      <h1>Estas en el Page</h1>

  </div>

  <div class="overflow-auto">
    <b-pagination
      v-model="currentPage"
      :per-page="perPage"
      :total-rows="vehiculo.length"
      aria-controls="my-table"
    ></b-pagination>

    <p class="mt-3">Current Page: {{ currentPage }}</p>

    <b-table
      id="my-table"
      :items="vehiculo"
      :per-page="perPage"

      :current-page="currentPage"
      small
      :fields="fields"
    ></b-table>
  </div>
</b-container>
</template>

<script>
import vehicleService from "./service/Service";


export default {
data() {
  return {
    items2: [
      {
        text: "Inicio",
        href: "#",
        to: "inicio",
      },
      {
        text: "Formulario",
        href: "#",
        to: "form"
      },
      {
        text: "Page2",
        href: "#",
        to: "page2"
      },
      {
        text: "",
        href: "#",
        to: ""
      },
    ],
    sortBy: "year",
    sortDesc: false,
    perPage: 3,
    currentPage: 1,
    vehiculo: [],
    fields: [
      { key: "year", label: "Uno", sortable: true },
      { key: "brand", label: "Dos", sortable: true },
      { key: "model", label: "Tres", sortable: true },
      { key: "serie", label: "Cuatro", sortable: true },
    ],
  }
},
mounted() {
  this.getVehicle();
},
methods: {

  async getVehicle() {
    try {
      const data = await vehicleService.getVehiclePagination(
        parseInt(this.currentPage),
        parseInt(this.perPage),
        this.sortBy
      )
      this.vehiculo = data.content;
    
    } catch (error) {
      console.error(error);

    }
  },
},
};
</script>

<style>

</style>