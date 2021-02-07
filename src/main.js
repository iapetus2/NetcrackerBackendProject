import Vue from "vue";
import App from "./App.vue";
import router from "./router.js"
import "bootstrap/dist/css/bootstrap.css";

Vue.config.productionTip = false;

new Vue({
  render: (h) => h(App),
  router
}).$mount("#app");
// new Vue({
//   el: '#blog-posts-events-demo',
//   data: {
//     posts: [/* ... */],
//     postFontSize: 1
//   }
// })

