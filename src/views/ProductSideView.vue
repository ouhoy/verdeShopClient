<script setup lang="ts">


</script>

<template>

  <div class="flex flex-col gap-4">
    <div class="flex items-center space-x-2">
      <button
          type="button"
          role="checkbox"
          aria-checked="false"
          data-state="unchecked"
          value="on"
          class="peer shrink-0 ring-offset-background focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:cursor-not-allowed disabled:opacity-50 data-[state=checked]:bg-primary data-[state=checked]:text-primary-foreground peer rounded-full h-6 w-6 border-2 border-red-500 peer-checked:border-red-700 peer-checked:bg-red-500"
          id="red"
      ></button>
      <label
          class="text-sm font-medium leading-none peer-disabled:cursor-not-allowed peer-disabled:opacity-70"
          for="red"
      >
        Red
      </label>
    </div>
    </div>


  <!--
// v0 by Vercel.
// https://v0.dev/t/6SNfJ89Ophp
-->
  <div class="flex flex-col gap-4 relative"><div class="flex items-center justify-center relative"><button type="button" role="checkbox" aria-checked="false" data-state="unchecked" value="on" class="peer h-4 w-4 shrink-0 rounded-sm border border-primary ring-offset-background focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:cursor-not-allowed disabled:opacity-50 data-[state=checked]:bg-primary data-[state=checked]:text-primary-foreground z-10 absolute opacity-0" id="red"></button><label class="text-sm font-medium leading-none peer-disabled:cursor-not-allowed peer-disabled:opacity-70 w-6 h-6 rounded-full bg-red-500 peer-checked:border-2 peer-checked:border-black absolute top-0 left-1/2 transform -translate-x-1/2 -translate-y-1/2 z-20" for="red"></label></div><div class="flex items-center justify-center relative"><button type="button" role="checkbox" aria-checked="false" data-state="unchecked" value="on" class="peer h-4 w-4 shrink-0 rounded-sm border border-primary ring-offset-background focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:cursor-not-allowed disabled:opacity-50 data-[state=checked]:bg-primary data-[state=checked]:text-primary-foreground z-10 absolute opacity-0" id="green"></button><label class="text-sm font-medium leading-none peer-disabled:cursor-not-allowed peer-disabled:opacity-70 w-6 h-6 rounded-full bg-green-500 peer-checked:border-2 peer-checked:border-black absolute top-0 left-1/2 transform -translate-x-1/2 -translate-y-1/2 z-20" for="green"></label></div><div class="flex items-center justify-center relative"><button type="button" role="checkbox" aria-checked="false" data-state="unchecked" value="on" class="peer h-4 w-4 shrink-0 rounded-sm border border-primary ring-offset-background focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:cursor-not-allowed disabled:opacity-50 data-[state=checked]:bg-primary data-[state=checked]:text-primary-foreground z-10 absolute opacity-0" id="blue"></button><label class="text-sm font-medium leading-none peer-disabled:cursor-not-allowed peer-disabled:opacity-70 w-6 h-6 rounded-full bg-blue-500 peer-checked:border-2 peer-checked:border-black absolute top-0 left-1/2 transform -translate-x-1/2 -translate-y-1/2 z-20" for="blue"></label></div></div>

</template>

<style scoped>

</style>