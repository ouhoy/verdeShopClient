<script setup lang="ts">
import { PhotoIcon, UserCircleIcon } from '@heroicons/vue/24/solid'

</script>

<template>
  <main class="mt-20 mx-auto max-w-7xl px-4 sm:px-6 lg:px-8 flex gap-20">

    <div class="flex flex-col justify-start items-start gap-6">

      <div class="bg-gray-50 hover:bg-gray-50 p-2 rounded-lg">
        <h2 class="text-base  font-semibold leading-7 text-gray-900">Profile</h2>
        <p class="mt-1 text-sm leading-6 text-gray-600">This information can be modified on your next checkout.</p>
      </div>

      <div class="hover:bg-gray-50 p-2 rounded-lg">
        <h2 class="text-base  font-semibold leading-7 text-gray-900">Shipping Address</h2>
        <p class="mt-1 text-sm leading-6 text-gray-600">This information can be modified on your next checkout.</p>
      </div>

      <div class="hover:bg-gray-100 p-2 rounded-lg" >
        <h2 class="text-base font-semibold leading-7 text-gray-900">Order History</h2>
        <p class="mt-1 text-sm leading-6 text-gray-600">This information can be modified on your next checkout.</p>
      </div>



    </div>


  </main>


</template>

<style scoped>

</style>