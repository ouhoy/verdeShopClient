import {createRouter, createWebHistory} from 'vue-router'
import HomeView from '../views/HomeView.vue'

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'home',
            component: HomeView
        },
        {
            path: '/about',
            name: 'about',
            component: () => import('../views/AboutView.vue')
        }

        , {
            path: '/product',
            name: 'product',
            component: () => import('../views/ProductView.vue')
        }
        , {
            path: '/collection',
            name: 'collection',
            component: () => import('../views/CollectionView.vue')
        },
         {
            path: '/login',
            name: 'login',
            component: () => import('../views/auth/LoginView.vue')
        },
        {
            path: '/signup',
            name: 'signup',
            component: () => import('../views/auth/SignupView.vue')
        }
    ]
})

export default router
