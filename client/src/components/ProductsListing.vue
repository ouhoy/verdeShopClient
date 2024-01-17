<script setup lang="ts">

import {onMounted, ref} from "vue";
import type {Product} from "../../types";
import MainButton from "@/components/buttons/MainButton.vue";

const products = ref<Product>()

onMounted(async ()=>{
  const data = await fetch("http://localhost:8080/v1/products/");
  products.value = await data.json();
})

</script>

<template>

    <div class="w-full mb-20 " data-v0-t="card">
      <div class="flex  space-y-1.5 justify-between items-center p-6">
        <h3 class="text-2xl font-semibold whitespace-nowrap leading-none tracking-tight">Products</h3>
                  <router-link :to="{name: 'postProduct'}">
            <MainButton>New Product</MainButton>
          </router-link>

      </div>
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
                <router-link class="hover:underline-offset-2 hover:underline" :to="{name: 'updateProduct', params: {id: product.id}}">

                {{product.name}}
                </router-link>

              </td>

              <td class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0">${{product.price}}</td>
              <td class="p-4 text-sm align-middle [&amp;:has([role=checkbox])]:pr-0">{{product.colors.toString().replaceAll(",", ", ")}}</td>
              <td class="p-4 text-sm align-middle [&amp;:has([role=checkbox])]:pr-0">{{product.sizes.toString().toLowerCase().replaceAll(",", ", ")}}</td>

            </tr>

            </tbody>
          </table>
        </div>
      </div>
    </div>

</template>
