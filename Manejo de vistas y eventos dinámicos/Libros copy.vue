<template>
  <div style="padding-top: 30px; padding-left: 20px">

    <div class="d-flex" style="justify-content: space-between">
      <b-form inline @submit.prevent="buscarPor">

        <label class="mr-sm-2">Filtrar por:</label>
        <b-form-select class="mb-2 mr-sm-2 mb-sm-0" v-model="tipoFiltro"
          :options="[{ text: 'Todos', value: null }, 'Autor', 'Genero', 'Título', 'Año', 'Descendente']" :value="null"></b-form-select>

        
        <b-select v-if="tipoFiltro == 'Genero' && tipoFiltro !== 'Año' && tipoFiltro !== null " :options="options" v-model="valorFiltro"></b-select>

        <b-input-group class="mb-2 mr-sm-2 mb-sm-0">
          <b-form-input v-if="tipoFiltro !== 'Genero' && tipoFiltro !== 'Año' && tipoFiltro !== null && tipoFiltro !== 'Descendente'  " v-model="valorFiltro"></b-form-input>
        </b-input-group>

        <b-input-group class="mb-2 mr-sm-2 mb-sm-0">
          <b-form-datepicker v-if="tipoFiltro == 'Año' && tipoFiltro !== null " v-model="añoInicio" class="mb-2"></b-form-datepicker>
          <b-form-datepicker v-if="tipoFiltro == 'Año' && tipoFiltro !== null " v-model="añoFin" class="mb-2"></b-form-datepicker>
        </b-input-group>

        <b-button type="submit" variant="primary" style="margin-left: 20px;">Buscar</b-button>
      </b-form>
      <b-button pill size="lg" v-b-modal.modal-1 style="background-color: green; margin-right: 60px;">Agregar</b-button>

    </div>


    <!-- Modal -->
    <b-modal id="modal-1" title="BootstrapVue" hide-footer>
      <b-form @submit="onSubmit">
        <b-form-group label="Nombre:" label-for="input-1">
          <b-form-input id="input-1" v-model="form.nombre" placeholder="El principito" required>
          </b-form-input>
        </b-form-group>

        <b-form-group label="Autor:" label-for="input-2">
          <b-form-input id="input-2" v-model="form.autor" placeholder="Zuriel Rios Aguilar" required>
          </b-form-input>
        </b-form-group>

        <b-form-group label="Genero:" label-for="input-3">
          <b-form-select v-model="form.genero" :options="options"></b-form-select>
        </b-form-group>
        <b-form-group label="Año:" label-for="input-4">
          <b-form-datepicker id="example-datepicker" v-model="form.añoPub" class="mb-2"></b-form-datepicker>


        </b-form-group>



        <b-button type="submit" variant="primary" style="margin-top: 10px">Enviar</b-button>
      </b-form>
    </b-modal>

    <!-- Spinner -->
    <div class="d-flex justify-content-center align-items-center">
      <b-spinner v-if="showSpinner" variant="info" style="width: 8rem; height: 8rem; margin-top:15%;"></b-spinner>
    </div>

    <!-- Cards -->
    <b-card-group deck class="cards">

      <b-card v-for="(libro, index) in libros" :key="index">

        <template #header>{{ libro.nombre }}</template>
        <b-card-text>
          <p><strong>Autor:</strong> {{ libro.autor }}</p>
          <p><strong>Género:</strong> {{ libro.genero }}</p>
          <p><strong>Año de Publicación:</strong> {{ formatDate(libro.añoPub) }}</p>
        </b-card-text>
      </b-card>
    </b-card-group>

  </div>
</template>
  
<script>
export default {
  data() {
    return {
      options: [
        { value: null, text: "SELECCIONA UN GENERO" },
        { value: "accion", text: "ACCION" },
        { value: "aventura", text: "AVENTURA" },
        { value: "suspenso", text: "SUSPENSO" },
      ],
      tipoFiltro: null,
      valorFiltro: '',
      form: {},
      libros: [],
      showSpinner: false,
    };
  },
  mounted() {
    this.spinner(); // Llamar a la función para obtener los libros al montar el componente
  },
  methods: {
    buscarPor() {

      const tipo = this.tipoFiltro;
      const valor = this.valorFiltro;

      let url;

      switch (tipo) {
        case null:
          url = `http://localhost:8080/api/libros/`;
          break;
        case 'Autor':
          url = `http://localhost:8080/api/libros/autor/${encodeURIComponent(valor)}`;
          break;
        case 'Genero':
          url = `http://localhost:8080/api/libros/genero/${encodeURIComponent(valor)}`;
          break;
        case 'Título':
          url = `http://localhost:8080/api/libros/nombre/${encodeURIComponent(valor)}`;
          break;
        case 'Año':
          url = `http://localhost:8080/api/libros/añoPub/${encodeURIComponent(this.añoInicio)}/${encodeURIComponent(this.añoFin)}`;
          break;
        case 'Descendente':
          url = `http://localhost:8080/api/libros/ordenados`;
          break;
        default:
          console.error('Tipo de filtro no válido');
          return;
      }

      // Realizar la solicitud GET a la URL construida
      fetch(url)
        .then((response) => {
          if (!response.ok) {
            throw new Error("Network response was not ok");
          }
          return response.json();
        })
        .then((data) => {
          console.log("Success:", data);
          this.libros = data;
        })
        .catch((error) => {
          console.error("Error:", error);
        });
    },



    onSubmit() {
      console.log("Formulario enviado!");
      this.$bvModal.hide("modal-1");

      var url = "http://localhost:8080/api/libros/add";
      var data = this.form;

      console.log(data)

      fetch(url, {
        method: "POST",
        body: JSON.stringify(data),
        headers: {
          "Content-Type": "application/json",
        },
      })
        .then((res) => res.json())
        .then((response) => {
          console.log("Success:", response);
          // Recargar la página después de enviar el formulario
          location.reload();
        })
        .catch((error) => console.error("Error:", error));
    },

    spinner() {
      this.showSpinner = true;
      setTimeout(() => {
        this.showSpinner = false;
        this.getLibros();
      }, 1500);
    },

    getLibros() {
      fetch("http://localhost:8080/api/libros/")
        .then((response) => {
          if (!response.ok) {
            throw new Error("Network response was not ok");
          }
          return response.json();
        })
        .then((data) => {
          console.log("Success:", data);
          this.libros = data;
        })
        .catch((error) => {
          console.error("Error:", error);
        });
    },
    formatDate(dateString) {
      const date = new Date(dateString);
      return date.toLocaleString('default', { month: 'numeric', year: 'numeric' });
    }
  },
};
</script>
  
<style>
.cards {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  grid-gap: 20px;
  /* Espacio entre las cards */
  margin-top: 20px;
}
</style>