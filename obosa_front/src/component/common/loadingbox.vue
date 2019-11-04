<template>
  <div id="loader">
    <div id="box"></div>
    <div id="shadow"></div>
  </div>
</template>

<script>
export default {
  name: "loadingbox",
  props: ["height", "width"],
  mounted() {
    $("#loader").css("height", this.height);
    $("#loader").css("width", this.width);
    const mt = this.height / 2 + 25;
    $("#box").css("margin-top", mt);
  }
};
</script>

<style>

</style>