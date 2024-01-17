import {createRouter, createWebHistory} from 'vue-router'
import HomeView from '../views/HomeView.vue'
import {auth} from "@/firebase/config";

// @ts-ignore
const requireAuth = (to, from, next) => {
    let user = auth.currentUser
    if (!user) {
        next({name: 'login'})
    } else {
        next()
    }
}

// @ts-ignore
const userAuth = (to, from, next) => {
    let user = auth.currentUser
    if (user) {
        next({name: 'home'})
    } else {
        next()
    }
}

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
            path: '/product/:id',
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
            component: () => import('../views/auth/LoginView.vue'),
            beforeEnter: userAuth,
            meta: {
                hideNavbar: true,
            }

        },
        {
            path: '/signup',
            name: 'signup',
            component: () => import('../views/auth/SignupView.vue'),
            beforeEnter: userAuth,
            meta: {
                hideNavbar: true,
            }

        }
        ,
        {
            path: '/side',
            name: 'side',
            component: () => import('../views/ProductSideView.vue')
        }

        ,
        {
            path: '/dashboard',
            name: 'dashboard',
            component: () => import('../views/admin/DashboardView.vue'),
            meta: {
                hideNavbar: true,
            }
        }

        ,
        {
            path: '/dashboard/post-product',
            name: 'postProduct',
            component: () => import('../views/admin/PostProductView.vue'),
            meta: {
                hideNavbar: true,
            }
        }
        ,
        {
            path: '/checkout',
            name: 'checkout',
            component: () => import('../views/CheckoutView.vue'),
            beforeEnter: requireAuth,

        },

        {
            path: '/profile',
            name: 'profile',
            component: () => import('../views/shopper/ShopperProfileView.vue'),
            beforeEnter: requireAuth,

        }
    ]
})

export default router
