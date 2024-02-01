import VueRouter from 'vue-router'
import Vue from 'vue'

Vue.use(VueRouter);

const routes = [
    //la ruta base
    {
        path: '/',
        component:()=> import('../components/Inicio.vue')
    },

    {
        path: '/',
        component: {
            render(c) {
                return c('router-view');
            },
        },
        children: [
            {
                path: '/inicio',
                name: 'inicio',
                component: () => import('../components/Inicio.vue')
            },
            {
                path: '/form',
                name: 'form',
                component: () => import('../components/Formulario.vue')
            },
            {
                path: '/page2',
                name: 'page2',
                component: () => import('../components/Page2.vue')
            },
            
        ]
    }
]

const router = new VueRouter({ routes, })
export default router;