<template>
  <div
    class="main-container"
    style="position: absolute; height: 100vmin; top: 0%"
  >
    <img
      src="https://user-images.githubusercontent.com/77240765/235085316-343b04d2-037f-46ab-b2f2-bda4048c0484.jpeg"
      class="d-block w-100 h-100"
      style="object-fit: cover"
      alt="top1"
    />
    <a>gd</a>
  </div>
</template>

<script>
export default {
  name: "TheIndex",
};
</script>

<style scoped>
.navbar {
  background-color: transparent;
  z-index: 99;
}
#footer {
  position: absolute;
  bottom: 0%;
}
.footer {
  position: absolute;
  bottom: 0%;
}
.container {
  background-image: url("https://user-images.githubusercontent.com/77240765/235085316-343b04d2-037f-46ab-b2f2-bda4048c0484.jpeg");
  width: 100%;
}
.slide {
  height: 83vmin;
}
.carousel-inner {
  height: 86vmin;
}
footer {
  position: fixed;
  bottom: 0;
}
.image-container {
  position: relative;
}

.image-text {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  /* Additional styles for the text */
}
</style>
