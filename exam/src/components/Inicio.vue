<template>
    <div >
        
    <b-breadcrumb :items="items"></b-breadcrumb>
        <h1>Estas en el inicio para navegar arriba esta el breadcrumb</h1>
  </div>
</template>

<script>
export default {
  data() {
    return {
      items: [
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
          to: "page2"
        },
      ],
    };
  },
};
</script>

<style>

</style>