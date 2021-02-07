import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

const User = { template: '<div>user</div>' }
// const Home = { template: '<div><h2>home</h2></div>' }
const Room = { template: '<div>room</div>' }


export default new Router({
    routes: [
        {
            path: '/',
            name: 'Home',
            component: () => import("./Home")
        },
        {
            path: '/room',
            name: 'RoomDetails',
            // component: () => import("./RoomDetails")
            component: Room
        },
        {
            path: '/user ',
            name: 'User',
            component: User
        }
    ]
})
// const router = new Router({
//     routes: [
//         {
//             path: '/details ',
//             name: 'RoomDetails',
//             // component: () => import("./RoomDetails")
//             component: Room
//         }
//     ]
// })
// const User = {
//     template: '<div>User</div>'
// }
//
// const router = new VueRouter({
//     routes: [
//         // dynamic segments start with a colon
//         { path: '/user/:id', component: User }
//     ]
// })
