import { createApp } from 'vue'
import { createRouter, createWebHistory } from 'vue-router'
import './style.css'
import App from './App.vue'
import Main from './components/Main.vue'
import Editor from './components/Editor.vue'

const router = createRouter({
    history: createWebHistory(),
    routes: [
        { path: '/', component: Main},
        { path: '/editor/:sessionId', component: Editor},
    ]
})


const app = createApp(App);
app.use(router);
app.mount('#app')