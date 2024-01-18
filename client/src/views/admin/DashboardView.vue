<script setup lang="ts">
import {ref} from "vue";
import MainButton from "@/components/buttons/MainButton.vue";
import ProductsListing from "@/components/ProductsListing.vue";

const selected = ref("products");

</script>

<template>
  <main class="mt-20 mx-auto max-w-7xl px-4 sm:px-6 lg:px-8 flex justify-center items-center flex-col gap-20">

    <div class="flex w-full sm:justify-center sm:items-center md:justify-start flex-col md:flex-row md:items-start gap-6">

      <div @click="selected='products'" :class="{'bg-gray-50': selected==='products'}" class="border w-full cursor-pointer hover:bg-gray-50 p-2 rounded-lg">
        <h2 class="text-base  font-semibold leading-7 text-gray-900">Products</h2>
        <p class="mt-1 text-sm leading-6 text-gray-600">Create new products, modify existing or delete.</p>
      </div>
      <div @click="selected='shipping-address'" :class="{'bg-gray-50': selected==='shipping-address'}" class="border w-full cursor-pointer hover:bg-gray-50 p-2 rounded-lg">
        <h2 class="text-base  font-semibold leading-7 text-gray-900">Orders</h2>
        <p class="mt-1 text-sm leading-6 text-gray-600">Track and manage orders.</p>
      </div>
      <div @click="selected='order-history'" :class="{'bg-gray-50': selected==='order-history'}" class="border w-full cursor-pointer hover:bg-gray-50 p-2 rounded-lg" >
        <h2 class="text-base font-semibold leading-7 text-gray-900">Shoppers</h2>
        <p class="mt-1 text-sm leading-6 text-gray-600">Manage shoppers accounts.</p>
      </div>
    </div>



      <ProductsListing v-if="selected === 'products'"/>



  </main>
</template>

<style scoped>

</style>