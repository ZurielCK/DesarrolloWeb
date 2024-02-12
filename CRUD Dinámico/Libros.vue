<template>
    <div style="padding-top: 30px; padding-left: 20px">
      

      <b-button pill size="lg" v-b-modal.modal-1 style="background-color: green"
        >+</b-button
      >
  
      <b-modal id="modal-1" title="BootstrapVue" hide-footer>
        <b-form @submit="onSubmit">
          <b-form-group label="Nombre:" label-for="input-1">
            <b-form-input
              id="input-1"
              v-model="form.nombre"

              placeholder="El principito"
              required
            >
            </b-form-input>
          </b-form-group>
  
          <b-form-group label="Autor:" label-for="input-2">
            <b-form-input
              id="input-2"
              v-model="form.autor"

              placeholder="Zuriel Rios Aguilar"
              required
            >
            </b-form-input>
          </b-form-group>
  
          <b-form-group label="Genero:" label-for="input-3">
            <b-form-select v-model="form.genero" :options="options"></b-form-select>
          </b-form-group>
  
          <b-form-group label="Año:" label-for="input-4">
              <b-form-input
            id="input-4"
            v-model="form.añoPub"
            type="number"
            placeholder="2003"
            required
          ></b-form-input>
          </b-form-group>
          
  
  
          <b-button type="submit"  variant="primary" style="margin-top: 10px">Enviar</b-button>
        </b-form>
      </b-modal>

      <b-card-group deck class="cards">

        <b-spinner v-if="showSpinner" variant="primary" style="width: 8rem; height: 8rem;"></b-spinner>

      <b-card v-for="(libro, index) in libros" :key="index">

        <template #header>{{ libro.nombre }}</template>
        <b-card-text>
          <p><strong>Autor:</strong> {{ libro.autor }}</p>
          <p><strong>Género:</strong> {{ libro.genero }}</p>
          <p><strong>Año de Publicación:</strong> {{ libro.añoPub }}</p>
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
          { value: "suspenso", text: "SUSPENSO"},
        ],
       form:{},
       libros: [],
       showSpinner: false,
      };
    },
    mounted() {
    this.spinner(); // Llamar a la función para obtener los libros al montar el componente
  },
    methods: {
      onSubmit() {
        console.log("Formulario enviado!");
        this.$bvModal.hide("modal-1");
  
        var url = "http://localhost:8080/api/libros/add";
        var data = this.form ;
        
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
    fetch("http://localhost:8080/api/libros")
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
  }
  },
};
  </script>
  
  <style>

.cards{
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  grid-gap: 20px; /* Espacio entre las cards */
  margin-top: 20px;
}
  </style>