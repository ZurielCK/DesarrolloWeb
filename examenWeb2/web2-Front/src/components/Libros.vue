<template>
  <div >
    <div  align="center">
      <b-row class="limiteCarrusel">
      <b-carousel
      
      v-show="showElement"  
        id="carousel-1"
        :interval="4000"
        controls
        background="#ababab"
        style="text-shadow: 1px 1px 2px #333  max-width: 1024px; max-height: 480px; overflow: hidden;"
      >
        <b-carousel-slide v-for="(myDataa, index) in myData" :key="index" :img-src="myDataa.imagen"  >

        </b-carousel-slide>
      </b-carousel>
      
    </b-row>
    
    </div>
    <div class="row">
    <div class="col gen2"
    >



    <!-- Modal -->
    <b-modal id="modal-1" title="Registro Libro" hide-footer>
        <b-form @submit="onSubmit">
            <b-form-input
              id="input-1"
              style="margin-bottom: 10px;"
              v-model="form.nombre"
              label="Nombre:"
              placeholder="Titulo del Libro"
              required>
            </b-form-input>

            <b-form-input
              id="input-2"
              v-model="form.autor"
              style="margin-bottom: 10px;"
              placeholder="Nombre del Autor"
              required>
            </b-form-input>


          <label for="example-datepicker">Año de publicación:</label>
          <b-form-datepicker id="example-datepicker" v-model="form.fechaPub" class="mb-2"></b-form-datepicker>
          
          <b-form-input
              id="input-4"
              v-model="form.imagen"
              placeholder="Dirección URL de imagen"
       
            ></b-form-input>
          
  
  
          <b-button type="submit"  variant="primary" style="margin-top: 10px">Enviar</b-button>
        </b-form>
      </b-modal>
    <!-- Modal2  -->
    <b-modal id="modal-2" title="Modificar Libro" hide-footer v-model="showModal">
        <b-form @submit="onSubmitUpdate">
          
          <b-form-input
          
              id="input-1"
              v-model="form.id"
              hidden>
            </b-form-input>

            <b-form-input
              id="input-1"
              style="margin-bottom: 10px;"
              v-model="form.nombre"
              label="Nombre:"
              placeholder="Titulo del Libro"
              required>
            </b-form-input>

            <b-form-input
              id="input-2"
              v-model="form.autor"
              placeholder="Nombre del autor"
              required>
            </b-form-input>

            <b-form-group label="Año de publicación:" label-for="input-4">
          <b-form-datepicker id="example-datepicker" v-model="form.fechaPub" class="mb-2"></b-form-datepicker>
          </b-form-group> 
          <b-form-input
              id="input-4"
              v-model="form.imagen"
              placeholder="Dirección URL de imagen"
            ></b-form-input>
  
          <b-button type="submit"  variant="primary" style="margin-top: 10px">Enviar</b-button>
        </b-form>
      </b-modal>





<!-- botones -->
        <div class="row" style="justify-content: center;">
            <b-button class="buttons" @click="buscarPor('anio')">Publicacion</b-button>
            <b-button class="buttons" @click="buscarPor('autor')">Autor</b-button>
            <b-button class="buttons" @click="buscarPor('foto')">Foto</b-button>
        </div>

<!-- zona de cards -->
        <div>
          <transition-group name="bounce" tag="div" class="cards">
                

                <b-card draggable @dragstart="startDragEdit($event, myDataa)" v-for="(myDataa, index) in myData" :key="index" >

                <template #header>{{ myDataa.nombre }}</template>
                <b-card-text>
                  <b-card-img :src="myDataa.imagen" alt="Imagen del libro" fluid></b-card-img>
                    <p><strong>Autor:</strong> {{ myDataa.autor }}</p>
                    <p><strong>fechaPub:</strong> {{ myDataa.fechaPub }}</p>
                </b-card-text>
                </b-card>
               

              </transition-group>
        </div>
    </div>



    <div class="gen3" align="center">
        <!-- Agregar -->
        <b-button style="width: 100px; height: 40px; background-color: green; margin-bottom: 10px;" @click="insertLibro">Agregar</b-button>

        <!-- Editar -->
        <div class="editZone" 
        @drop="onDropEdit($event)"
        @dragover.prevent
        @dragenter.prevent>Editar</div>

        <!-- Eliminar -->
        <div class="eliminarZone"
        @drop="onDropDelete($event)"
        @dragover.prevent
        @dragenter.prevent>Eliminar</div>
    </div>
     </div>
  </div>
</template>

<script>
export default {
 data(){
    return{ 
        myData:[],
        form:{},
        showModal: false,
        lastScrollPosition: 0,
        showElement: true
}
 },
 mounted(){
    this.getData()
    window.addEventListener("scroll", this.onScroll); 
 },
 beforeDestroy() {
    window.removeEventListener("scroll", this.onScroll);
  },

 methods:{
    getData(){
        fetch("http://localhost:8080/api/libros/")
        .then((response) => {
        if (!response.ok) {
            throw new Error("Network response was not ok");
        }
        return response.json();
        })
        .then((data) => {
        console.log("Success:", data);
        this.myData = data;
        })
        .catch((error) => {
        console.error("Error:", error);
        });
    },
    buscarPor(value){

        const tipo = value;


        let url;

        switch (tipo) {
        case 'anio':
        url = `http://localhost:8080/api/libros/fecha`;
        break;
        case 'autor':
        url = `http://localhost:8080/api/libros/autor`;
        break;
        case 'foto':
        url = `http://localhost:8080/api/libros/imagen`;
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
        this.myData = data;
        })
        .catch((error) => {
        console.error("Error:", error);
        });
        }, 
    startDragEdit(evt, item) {
      evt.dataTransfer.dropEffect = "move";
      evt.dataTransfer.effectAllowed = "move";
      evt.dataTransfer.setData("formData", JSON.stringify(item));
      
    },
    insertLibro() {
      this.$bvModal.show('modal-1');
    },
    onDropEdit(evt) {
    this.showModal = true;
      const itemData = evt.dataTransfer.getData("formData");
      const item = JSON.parse(itemData);
      this.form.id = item.id
      this.form.nombre = item.nombre;
      this.form.autor = item.autor;
      this.form.fechaPub = item.fechaPub;
      this.form.imagen = item.imagen
        },
    onDropDelete(evt) {

        
        const itemData = JSON.parse(evt.dataTransfer.getData("formData"));
        
        console.log("Datos drop" + itemData);

        var id = itemData.id ;
        var url = `http://localhost:8080/api/libros/${encodeURIComponent(id)}`;

      fetch(url, {
        method: "DELETE"    
      })
      .then(() => {location.reload();})
  .catch((error) => console.error("Error:", error));
        },  
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
    onSubmitUpdate() {
        console.log("Formulario enviado!");
        this.$bvModal.hide("modal-2");
        var id = this.form.id ;
    var url = `http://localhost:8080/api/libros/${encodeURIComponent(id)}`;
      var data = this.form ;
      if (data.imagen.length < 1) {
        data.imagen = null;
      }

      fetch(url, {
        method: "PUT", 
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

    onScroll() {

const currentScrollPosition = window.pageYOffset || document.documentElement.scrollTop;

if (Math.abs(currentScrollPosition - this.lastScrollPosition) < 50) {
  return;
}

if (currentScrollPosition < this.lastScrollPosition && currentScrollPosition < 100) {
    this.showElement = true; // Mostrar el elemento cuando el usuario está en la parte superior de la página
  } else {
    this.showElement = false;
  }

  this.lastScrollPosition = currentScrollPosition;
},

 }
}
</script>

<style>

.gen2{
  background-color: rgb(240, 240, 240);
width: 80%;
}
.gen3{
    background-color: rgb(227, 227, 227);
    width: 20%;
    padding: 20px;
}


.limiteCarrusel{
  width: 400px;
  height: 200px;
  margin-top: 20px;
  margin-bottom: 20px;
  justify-content: center;
  overflow: hidden;
}
.dropZone{

background-color: rgb(255, 0, 132);
}

.editZone{
  height: 200px;
width: 200px;
margin: 10px;
background-color: rgba(36, 118, 249, 0.653);
padding-top: 30% ;
}

.eliminarZone{
    height: 200px;
width: 200px;
margin: 10px;
background-color: rgba(255, 0, 0, 0.653);
padding-top: 30% ;
}

.cards{
  display: grid;
grid-template-columns: 1fr 1fr 1fr;
grid-gap: 20px; /* Espacio entre las cards */
margin-top: 20px;
padding: 20px 20px 20px 20px;
}

.buttons{
height: 50px;
width: 100px;
margin: 20px 20px 0px 20px;
background-color: rgb(38, 0, 255);
}
</style>