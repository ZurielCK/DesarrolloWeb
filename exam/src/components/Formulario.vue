<template>
    <div>
     <div>
        <b-breadcrumb :items="items"></b-breadcrumb>

        <h1>Estas en el Form</h1>

    </div>
     <div>
    <b-form @submit="onSubmit" @reset="onReset" v-if="show">
    <!-- Form nombre -->
    <b-form-group id="input-group-1" label="Escribe tu nombre:" label-for="input-1" >

      <div class="nombre-form">
        <b-form-input
          id="input-1"
          v-model="form.name"
          pattern="[A-Za-z0-9]+"
          placeholder="Nombre"
          required
        ></b-form-input>
      </div>
  
    </b-form-group>

    <!-- Form fechaNAc -->
    <b-form-group id="input-group-5" label="Escribe el año:" label-for="input-5">
    <b-form-input
          id="input-5"
          v-model="form.fecha"
          type="date"
          placeholder="Nac"
          required
        ></b-form-input>
        <div v-if="formErrors.fecha" class="text-danger">{{ formErrors.fecha }}</div>
    </b-form-group>

        <!-- Form phone -->
   <!-- Form phone -->
   <b-form-group id="input-group-4" label="Numero de Serie:" label-for="input-4">
                <b-form-input 
                id="input-4" 
                v-model="form.number" 
                pattern="[A-Za-z]{4}\d{3}-\d{2}[A-Za-z]{2}"
                placeholder="XXXX000-00XX"
                required
                ></b-form-input>

            </b-form-group>


    <!-- Form email
    <b-form-group id="input-group-6" label="Email address:" label-for="input-6" description="We'll never share your email with anyone else.">
        <b-form-input
          id="input-6"
          v-model="form.email"
          type="email"
          placeholder="Enter email"
          required
        ></b-form-input>
       
        <div v-if="formErrors.email" class="text-danger">{{ formErrors.email }}</div>
    </b-form-group>
 --> 
    <!-- Form imagen 
    <div>

      <b-form-file v-model="file1" class="mt-3" plain :max-size="3000" accept=".jpg, .png, .gif"></b-form-file>

      <div class="mt-3">Selected file: {{ file1 ? file1.name : '' }}</div>

    </div>
    --> 
<br>

      <b-button type="submit" variant="primary">Submit</b-button>
      <b-button type="reset" variant="danger">Reset</b-button>
    </b-form>
    <b-card class="mt-3" header="Form Data Result">
      <pre class="m-0">{{ form }}</pre>
    </b-card>
  </div>

    </div>
</template>


<script>
  export default {
    data() {
      return {
        form: {

        },
        show: true,
        formErrors: {},
        items: [
        {
          text: "Inicio",
          href: "#",
          to: "Inicio",
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
    }
    },

    methods: {
    onSubmit(event) {
    
      this.formErrors = {};
      const fechaActual = new Date();
      const fechaForm = new Date(this.form.fecha);

      if (fechaForm.getFullYear()  >= fechaActual.getFullYear()   ) {
        this.formErrors.fecha = "La fecha debe ser anterior a la fecha actual.";
      } 
   

      if (Object.keys(this.formErrors).length > 0) {
        event.preventDefault();
        return;
      }
      event.preventDefault();
      alert(JSON.stringify(this.form));
    }
  }
}
</script>

<style scoped>
.nombre-form{
margin-bottom: 10px;
}

.direccion-form{
  display: grid;
  grid-template-columns: repeat(2, auto);
  gap: 8px;
}
</style>
