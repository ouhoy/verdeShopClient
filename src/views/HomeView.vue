<template>
  <main class="pb-28   mx-auto max-w-7xl px-4 sm:px-6 lg:px-8 flex flex-col gap-20	">

    <header class="pt-28 flex flex-col gap-6 items-center justify-center ">
      <h1 class="font-kaisei-decol text-primary-darker text-center lg:text-6xl text-6xl  font-bold leading-16">Fresh
        Green Fitness Style</h1>
      <p class="support-text mx-auto text-center font-inter text-primary-darker text-base font-medium leading-6">
        Achieve all your fitness goals with a handpicked selection of new sneakers, comfortable clothes, and supportive
        designs.</p>

      <div class="buttons flex gap-2">
        <router-link :to="{name:'collection'}"
                     class="font-medium rounded-full max-h-12 flex items-center justify-center	 py-4 px-8 bg-primary-darker text-primary-light text-md">
          Shop now
        </router-link>
        <router-link :to="{name: 'story'}"
            class="font-medium rounded-full max-h-12 flex items-center justify-center py-4 px-8 border-2 border-primary-darker text-primary-darker text-md">
          Our story
        </router-link>


      </div>
    </header>
    <section class="flex items-end gap-4">
      <div class="relative image-container">
        <img class="animated-image" src="@/assets/images/img-1.png" alt="" srcset="">
      </div>
      <div class="flex flex-col items-center justify-center gap-2">
        <img class="animated-image delay-1" src="@/assets/images/img-2.png" alt="" srcset="">
        <img class="animated-image delay-2" src="@/assets/images/img-3.png" alt="" srcset="">
      </div>
      <div class="image-container">
        <img class="animated-image delay-3" src="@/assets/images/img-4.png" alt="" srcset="">
      </div>
    </section>

  </main>


</template>
<style>


header > .support-text {
  max-width: 512px;
}

.image-container {
  perspective: 1000px;
}

.animated-image {
  opacity: 0.5;
  transform: translateY(50px);
  animation: tilt .8s ease-out forwards, fadeIn .8s ease-out forwards;
}

.delay-1 {
  animation-delay: 0.2s;
}

.delay-2 {
  animation-delay:.3s;
}

.delay-3 {
  animation-delay: 0.4s;
}

@keyframes tilt {
  to {
    transform: translateY(0);
  }
}

@keyframes fadeIn {
  to {
    opacity: 1;
  }
}

</style>