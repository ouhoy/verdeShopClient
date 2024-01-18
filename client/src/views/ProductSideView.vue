<script setup lang="ts">

import {onMounted, ref} from "vue";
import type {Product} from "../../types";
import {Menu, MenuButton, MenuItem, MenuItems} from "@headlessui/vue";
import UserIcon from "@/assets/icons/UserIcon.vue";
import { ChevronDownIcon, EllipsisVerticalIcon } from '@heroicons/vue/20/solid'



const products = ref<Product>()

onMounted(async ()=>{
  const data = await fetch("http://localhost:8080/v1/products/");
  products.value = await data.json();
})

</script>

<template>
  <main class="mt-20 mx-auto max-w-7xl px-4 sm:px-6 lg:px-8 flex gap-20">

    <div class="w-full rounded-lg border bg-card text-card-foreground shadow-sm" data-v0-t="card">
      <div class="flex flex-col space-y-1.5 p-6"><h3
          class="text-2xl font-semibold whitespace-nowrap leading-none tracking-tight">Products</h3></div>
      <div class="p-6">
        <div class="relative w-full overflow-auto">
          <table class="w-full caption-bottom text-sm">
            <thead class="[&amp;_tr]:border-b">
            <tr class="border-b transition-colors hover:bg-muted/50 data-[state=selected]:bg-muted">
              <th class="h-12 px-4 text-left align-middle font-medium text-muted-foreground [&amp;:has([role=checkbox])]:pr-0 w-[80px] hidden md:table-cell">
                Image
              </th>
              <th class="h-12 px-4 text-left align-middle font-medium text-muted-foreground [&amp;:has([role=checkbox])]:pr-0 max-w-[150px]">
                Name
              </th>
              <th class="h-12 px-4 text-left align-middle font-medium text-muted-foreground [&amp;:has([role=checkbox])]:pr-0 max-w-[150px]">
                Price
              </th>
              <th class="h-12 px-4 text-left align-middle font-medium text-muted-foreground [&amp;:has([role=checkbox])]:pr-0">
                Colors
              </th>
              <th class="h-12 px-4 text-left align-middle font-medium text-muted-foreground [&amp;:has([role=checkbox])]:pr-0">
                Sizes
              </th>

              <th class="h-12 px-4 text-left align-middle font-medium text-muted-foreground [&amp;:has([role=checkbox])]:pr-0">
               Options
              </th>
            </tr>
            </thead>

            <tbody class="[&amp;_tr:last-child]:border-0">
            <tr v-for="product in products" class="border-b transition-colors hover:bg-muted/50 data-[state=selected]:bg-muted">

              <td class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0 hidden md:table-cell">
                <img
                  :src=product.thumbnail  alt="Product image"
                  class="aspect-square rounded-md object-cover">
              </td>

              <td class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0 font-medium">
                {{product.name}}
              </td>

              <td class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0">${{product.price}}</td>
              <td class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0">{{product.colors.toString().replaceAll(",", ", ")}}</td>
              <td class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0">{{product.sizes.toString().replaceAll(",", ", ")}}</td>
              <td class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0">


              </td>

            </tr>

            </tbody>
          </table>
        </div>
      </div>
    </div>

  </main>
</template>
