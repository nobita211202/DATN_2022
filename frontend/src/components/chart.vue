<template>
  <div>
    <canvas id="planet-chart"></canvas>
  </div>
</template>

<script>
import Chart from 'chart.js'

export default {
  name: 'PlanetChart',
  props:{
    data:Array,
    labels:Array
  },
  data(){
    return{
      chartjs:{},
      planetChartData :{
        type: "line",
        data: {
          labels: this.labels,
          datasets: [
            {
              label: "Doanh số",
              data: this.data,
              backgroundColor: "rgba(71, 183,132,.5)",
              borderColor: "#47b784",
              borderWidth: 3
            }
          ]
        },
        options: {
          responsive: true,
          lineTension: 1,
          scales: {
            yAxes: [
              {
                ticks: {
                  beginAtZero: true,
                  padding: 25
                }
              }
            ]
          }
        }
      }
    }
  },
  methods:{
    loga(){
      this.labels = ['a','c']

      console.log(this.label);
    }
  },
  mounted(){
    console.log("tk");
      // console.log(this.labels);
      // console.log(this.data);
      // console.log(this.planetChartData);
      const ctx = document.getElementById('planet-chart');
      this.chartjs = new Chart(ctx, this.planetChartData);

  },
  watch:{
    labels(){
      console.log("watch");
      console.log(this.labels);
      this.chartjs.data.datasets.forEach((dataset) => {
        dataset.data= []
        dataset.data.push(...this.data);
      });
      this.planetChartData.data.labels = []
      this.planetChartData.data.labels.push(...this.labels)
      this.chartjs.update()
    }
  }
}
</script>
