<script setup lang="ts">

import MainButton from "@/components/buttons/MainButton.vue";
import type {CheckoutOrder} from "../../../types";

defineProps<{
  order: CheckoutOrder,
  actionButton?: true
}>()

</script>

<template>
  <div class="grid gap-6  w-full">
    <div class="border rounded-lg shadow-sm overflow-hidden">
      <div class="flex items-center justify-between p-4 bg-gray-100 dark:bg-gray-800">
        <div class="flex flex-col gap-1"><span class="text-sm text-gray-500 dark:text-gray-400">Order Date: {{order?.date}}</span><span
            class="font-semibold text-lg">Order #{{ order.id }}</span></div>
        <div class="flex items-center gap-4"><span class="font-semibold text-lg">${{ order.total }}</span>
          <router-link v-if="actionButton"  :to="{name: 'order', params: {id: order.id}}">
            <MainButton >View Order</MainButton>
          </router-link>
        </div>
      </div>
      <div class="relative w-full overflow-auto">
        <table class="w-full caption-bottom text-sm">
          <thead class="[&amp;_tr]:border-b">
          <tr class="border-b transition-colors hover:bg-muted/50 data-[state=selected]:bg-muted">
            <th class="h-12 px-4 text-left align-middle font-medium text-muted-foreground [&amp;:has([role=checkbox])]:pr-0">
              Image
            </th>
            <th class="h-12 px-4 text-left align-middle font-medium text-muted-foreground [&amp;:has([role=checkbox])]:pr-0">
              Title
            </th>
            <th class="h-12 px-4 text-left align-middle font-medium text-muted-foreground [&amp;:has([role=checkbox])]:pr-0">
              Price
            </th>
            <th class="h-12 px-4 text-left align-middle font-medium text-muted-foreground [&amp;:has([role=checkbox])]:pr-0">
              Quantity
            </th>
            <th class="h-12 px-4 text-left align-middle font-medium text-muted-foreground [&amp;:has([role=checkbox])]:pr-0">
              Color
            </th>
            <th class="h-12 px-4 text-left align-middle font-medium text-muted-foreground [&amp;:has([role=checkbox])]:pr-0">
              Size
            </th>

          </tr>
          </thead>
          <tbody class="[&amp;_tr:last-child]:border-0">

          <tr v-for="product in order.products"
              class="border-b transition-colors hover:bg-muted/50 data-[state=selected]:bg-muted">
            <td class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0">
              <img :src="product.imageSrc" :alt="product.name" class="w-12 h-12" width="50" height="50"
                   style="aspect-ratio: 50 / 50; object-fit: cover;">
            </td>
            <td class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0 underline-offset-1 hover:underline"><router-link :to="{name:'product', params: {id: product.id}}">{{ product.name }}</router-link></td>
            <td class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0">${{ product.price }}</td>
            <td class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0">{{ product.quantity }}</td>
            <td class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0 capitalize">{{ product.color }}</td>
            <td class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0">{{ product.size }}</td>

          </tr>

          </tbody>
        </table>
      </div>
    </div>
  </div>

</template>

<style scoped>

</style>