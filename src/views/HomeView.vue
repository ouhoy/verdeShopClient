<script setup lang="ts">
</script>

<template>
  <div class="pb-28  container w-full h-full">
    <main class="mx-auto max-w-7xl px-4 sm:px-6 lg:px-8 flex flex-col gap-20	">

      <header class="pt-28 flex flex-col gap-6 items-center justify-center ">
        <h1 class="font-kaisei-decol text-primary-darker text-center lg:text-6xl text-6xl  font-bold leading-16">Fresh Green Fitness Style</h1>
        <p class="support-text mx-auto text-center font-inter text-primary-darker text-base font-medium leading-6">Achieve all your fitness goals with a handpicked selection of new sneakers, comfortable clothes, and supportive designs.</p>
        <div class="buttons flex gap-2">
          <button class="font-medium rounded-full max-h-12 flex items-center justify-center	 py-4 px-8 bg-primary-darker text-primary-light text-md">
            Shop now
          </button>
          <button class="font-medium rounded-full max-h-12 flex items-center justify-center py-4 px-8 border-2 border-primary-darker text-primary-darker text-md">
            Know more
          </button>



        </div>
      </header>
      <section class="flex items-end gap-4">
        <div>
          <img src="@/assets/images/img-1.png" alt="" srcset="">

        </div>
        <div class="flex flex-col items-center justify-center gap-2">
          <img src="@/assets/images/img-2.png" alt="" srcset="">
          <img src="@/assets/images/img-3.png" alt="" srcset="">
        </div>
        <div>
          <img src="@/assets/images/img-4.png" alt="" srcset="">

        </div>
      </section>

    </main>
  </div>


</template>
<style >
header > .support-text {
  max-width: 512px;
}

</style>