<template>
  <div class="btn" @click="to('home')">
    <ul class="text">
      <li>O</li>
      <li class="ghost">I</li>
      <li class="ghost">S</li>
      <li class="ghost">O</li>
      <li class="spaced">B</li>
      <li>O</li>
      <li class="ghost">I</li>
      <li class="ghost">S</li>
      <li class="ghost">O</li>
      <li class="spaced">S</li>
      <li>A</li>
      <li class="ghost">I</li>
      <li class="ghost">S</li>
      <li class="ghost">O</li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "logo",
  mounted() {
    var text = $(".text");
    $(window).scroll(function() {
      var scroll = $(window).scrollTop();

      if (scroll >= 20) {
        text.addClass("hidden");
      } else {
        text.removeClass("hidden");
      }
    });
  },
  methods : {
    to(target){
      for (const active of $('.btn-active')) {
        $(active).removeClass('btn-active')
      }
      this.$router.push({
        name: target
      })
    }
  }
};
</script>

<style>
</style>