// import './assets/main.css'
//
// import { createApp } from 'vue'
// import App from './App.vue'
// import router from './router'
// import { createPinia } from 'pinia'
//
//
// const app = createApp(App)
// const pinia = createPinia()
//
// app.use(pinia)
// app.use(router)
//
// app.mount('#app')

import './assets/main.css';

import { createApp } from 'vue';
import App from './App.vue';
import { auth } from '@/firebase/config';
import { onAuthStateChanged } from 'firebase/auth';
import router from './router';
import { createPinia } from 'pinia';

let app;

const pinia = createPinia();

onAuthStateChanged(auth, () => {
    if (!app) {
        // Ensure Pinia store is created before creating the app
        app = createApp(App).use(pinia).use(router).mount('#app');
    }
});
