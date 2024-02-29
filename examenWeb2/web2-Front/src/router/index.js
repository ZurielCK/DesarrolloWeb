
import VueRouter from 'vue-router'
import Vue from 'vue'

Vue.use(VueRouter);

const routes = [
    //la ruta base
    {
        path: '/',
        component:()=> import('../components/Libros.vue')
    },
    
    //las rutas asociadas a los componentes
    {
        path:'*',
        component:()=> import('../components/Error404.vue')
    }
 
]

const router = new VueRouter({ routes, })
export default router;