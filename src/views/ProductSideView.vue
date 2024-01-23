<template>
  <main class="mt-20 mb-20  mx-auto max-w-7xl px-4 sm:px-6 lg:px-8 flex flex-col lg:flex-col gap-20">

    <div>
      <div class="mb-5">
        <p class="font-semibold text-2xl">Order #{{order?.id}}</p>
      </div>
      <h3 class="text-base font-semibold leading-7 text-gray-900">Order information</h3>
      <p class="mt-1 mb-6 max-w-2xl text-sm leading-6 text-gray-500">Details about the products ordered.</p>
      <Order v-if="order" :order="order"/>
    </div>

    <div class="w-full">
      <div class="px-4 sm:px-0">
        <h3 class="text-base font-semibold leading-7 text-gray-900">Address information</h3>
        <p class="mt-1 max-w-2xl text-sm leading-6 text-gray-500">Personal details and delivery address.</p>
      </div>
      <div class="mt-6 border-t border-gray-100">
        <dl class="divide-y divide-gray-100">

          <div v-for="(value, key) in address" class="px-4 py-6 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-0">
            <dt class="text-sm font-medium leading-6 text-gray-900 capitalize">{{ key.toLowerCase() }}</dt>
            <dd class="mt-1 text-sm leading-6 text-gray-700 sm:col-span-2 sm:mt-0">{{ value }}</dd>
          </div>

        </dl>
      </div>

    </div>


  </main>
</template>

<script setup lang="ts">
import {onMounted, ref} from "vue";
import type {Address, CheckoutOrder, OrderedProduct} from "../../types";
import Order from "@/components/tables/Order.vue";
import {SERVER_URL} from "@/production";

const address = ref<Address>();
const order = ref<CheckoutOrder>();
onMounted(async () => {
  const data = await fetch(`${SERVER_URL}/v1/orders/1`);
  const result = await data.json();
  const parsedAddress = await JSON.parse(result.address) as Address;
  const parsedProducts = await JSON.parse(result.products) as OrderedProduct[];
  address.value = parsedAddress;

  order.value = {
    id: result.id,
    userId: result.userId,
    products: parsedProducts,
    address: parsedAddress,
    date: result.date,
    total: result.total

  };



})
</script>