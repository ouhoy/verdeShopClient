<template>

  <div class="w-full flex flex-col gap-12 mb-12 ">
    <h3 class="text-2xl font-semibold whitespace-nowrap leading-none tracking-tight">Orders</h3>
    <Order v-for="order in orders" :order="order" :action-button="true"></Order>
  </div>
</template>

<script setup lang="ts">

import {onMounted, ref} from "vue";
import Order from "@/components/tables/Order.vue";
import type {CheckoutOrder} from "../../../types";
import {SERVER_URL} from "@/production";

const orders = ref<CheckoutOrder[]>([])

onMounted(async ()=>{
  const data = await fetch(`${SERVER_URL}/v1/orders/`)
  const results = await data.json()

  results.forEach((order: any)=> {

    const serializedOrder = {
      id: order.id,
      userId: order.userId,
      products:JSON.parse(order.products),
      address:JSON.parse(order.address),
      date: order.date,
      total: order.total

    };


    orders.value.push(serializedOrder)
  })
})

</script>