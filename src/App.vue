<template>
  <div class="container">
    <div class="row mt-5">
      <div class="col">
        <!--        <router-link to="/">Home</router-link>-->
        <router-link to="/details">
          <h1 class="text-center">Trading Platform</h1>
        </router-link>
      </div>
    </div>

    <div class="row mt-5">
      <div class="col">
        <div class="sample">
          <h2 class="text-center">
            <router-link to="/">Quotes</router-link>
            <router-link :to="'/room'">Rooms</router-link>
            <router-link :to="'/user'">Community</router-link>
          </h2>
        </div>
      </div>
    </div>

    <div class="card">
      <div class="text-center">
        <h3>Дефолтное название продукта</h3>
        <button v-on:click="$emit('enlarge-text')">
          Войти в комнату
        </button>
        <h4>Дополнительная информация о комнате</h4>
      </div>
    </div>
    <div class="card">
      <div class="text-center">
        <h3>Дефолтное название продукта</h3>
        <button v-on:click="$emit('enlarge-text')">
          Войти в комнату
        </button>
        <h4>Дополнительная информация о комнате</h4>
      </div>
    </div>
    <div class="card">
      <div class="text-center">
        <h3>Дефолтное название продукта</h3>
        <button v-on:click="$emit('enlarge-text')">
          Войти в комнату
        </button>
        <h4>Дополнительная информация о комнате</h4>
      </div>
    </div>
    <div class="card">
      <div class="text-center">
        <h3>Дефолтное название продукта</h3>
        <button v-on:click="$emit('enlarge-text')">
          Войти в комнату
        </button>
        <h4>Дополнительная информация о комнате</h4>
      </div>
    </div>



    <div id="blog-posts-events-demo">
      <div :style="{ fontSize: postFontSize + 'em' }">
        <blog-post
            v-for="post in posts"
            v-bind:key="post.id"
            v-bind:post="post"
            v-on:enlarge-text="postFontSize += $event"
        ></blog-post>
      </div>
    </div>


    <div class="row mt-5" v-if="arrPositive.length > 0">
      <div class="col">
        <h2 class="text-center">Price</h2>
        <line-chart
            :chartData="arrPositive"
            :options="chartOptions"
            :chartColors="positiveChartColors"
            label="Price"
        />
      </div>
    </div>

    <!--    <div class="row mt-5" v-if="arrHospitalized.length > 0">-->
    <!--      <div class="col">-->
    <!--        <h2 class="text-center">Number of sold items</h2>-->
    <!--        <line-chart-->
    <!--          :chartData="arrHospitalized"-->
    <!--          :options="chartOptions"-->
    <!--          :chartColors="hospitalizedChartColors"-->
    <!--          label="Number of sold items"-->
    <!--        />-->
    <!--      </div>-->
    <!--    </div>-->

    <!--    <div class="row mt-5" v-if="arrInIcu.length > 0">-->
    <!--      <div class="col">-->
    <!--        <h2 class="text-center">market offers</h2>-->
    <!--        <line-chart-->
    <!--          :chartData="arrInIcu"-->
    <!--          :options="chartOptions"-->
    <!--          :chartColors="inIcuColors"-->
    <!--          label="market offers"-->
    <!--        />-->
    <!--      </div>-->
    <!--    </div>-->

  </div>
</template>

<!--<template>-->
<!--  <div id="app">-->
<!--      <router-link to="/">Home</router-link>-->
<!--      <router-link to="/room">Room</router-link>-->
<!--      <router-link to="/details">Details</router-link>-->
<!--  </div>-->
<!--</template>-->

<!--<script src="https://unpkg.com/vue-router/dist/vue-router.js"></script>-->


<script>
import axios from "axios";
import moment from "moment";
import Vue from "vue";

// import LineChart from "./components/LineChart";

Vue.component('blog-post', {
  props: ['post'],
  template: `
    <div class="blog-post">
    <h3>{{ post.title }}</h3>
    <button v-on:click="$emit('enlarge-text')">
      Увеличить размер текста
    </button>
    <div v-html="post.content"></div>
    </div>
  `
})


export default {
  components: {
    // LineChart
  },
  data() {
    return {
      arrPositive: [],
      positiveChartColors: {
        borderColor: "#077187",
        pointBorderColor: "#0E1428",
        pointBackgroundColor: "#AFD6AC",
        backgroundColor: "#74A57F"
      },
      arrHospitalized: [],
      hospitalizedChartColors: {
        borderColor: "#251F47",
        pointBorderColor: "#260F26",
        pointBackgroundColor: "#858EAB",
        backgroundColor: "#858EAB"
      },
      arrInIcu: [],
      inIcuColors: {
        borderColor: "#190B28",
        pointBorderColor: "#190B28",
        pointBackgroundColor: "#E55381",
        backgroundColor: "#E55381"
      },
      arrOnVentilators: [],
      onVentilatorsColors: {
        borderColor: "#784F41",
        pointBorderColor: "#784F41",
        pointBackgroundColor: "#BBE5ED",
        backgroundColor: "#BBE5ED"
      },
      arrRecovered: [],
      recoveredColors: {
        borderColor: "#4E5E66",
        pointBorderColor: "#4E5E66",
        pointBackgroundColor: "#31E981",
        backgroundColor: "#31E981"
      },
      arrDeaths: [],
      deathColors: {
        borderColor: "#E06D06",
        pointBorderColor: "#E06D06",
        pointBackgroundColor: "#402A2C",
        backgroundColor: "#402A2C"
      },
      chartOptions: {
        responsive: true,
        maintainAspectRatio: false
      }
    };
  },
  async created() {
    const {data} = await axios.get("https://covidtracking.com/api/us/daily");

    data.forEach(d => {
      const date = moment(d.date, "YYYYMMDD").format("MM/DD");
      const {
        positive,
        hospitalizedCurrently,
        inIcuCurrently,
        onVentilatorCurrently,
        recovered,
        death
      } = d;

      this.arrPositive.push({date, total: positive});
      this.arrHospitalized.push({date, total: hospitalizedCurrently});
      this.arrInIcu.push({date, total: inIcuCurrently});
      this.arrOnVentilators.push({date, total: onVentilatorCurrently});
      this.arrRecovered.push({date, total: recovered});
      this.arrDeaths.push({date, total: death});
    });
  }
};
</script>

<style>
#app a {
  font-weight: bold;
  color: #2c3e50;
}

div.card {
  margin-left: auto;
  margin-right: auto;
  /*width: 8em;*/
  width: 500px;
  border: 1px solid blue;
  border-radius: 10px;
  /*margin: 10px;*/
  padding: 10px;
}
div.sample {
  padding: 10px;
}
</style>

