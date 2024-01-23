<script setup lang="ts">

import {onMounted, ref} from "vue";
import type {Product} from "../../types";
import MainButton from "@/components/buttons/MainButton.vue";
import {EllipsisVerticalIcon} from "@heroicons/vue/20/solid";
import {Menu, MenuButton, MenuItem, MenuItems} from "@headlessui/vue";
import DangerDialog from "@/components/dialogs/DangerDialog.vue";
import axios from "axios";
import {SERVER_URL} from "@/production";

const products = ref<Product[]>()

let PRODUCT_ID: number;
onMounted(async () => {
  const data = await fetch(`${SERVER_URL}/v1/products/`);
  products.value = await data.json() as Product[];



})

const dangerDialogOpen = ref(false)

function updateDialogOpenState(isOpen:boolean) {
  dangerDialogOpen.value = isOpen
}

async function handleDelete() {
  try {
    await axios.delete(`${SERVER_URL}/v1/products/${PRODUCT_ID}`)
    const data = await fetch(`${SERVER_URL}/v1/products/`);
    products.value = await data.json();


  } catch (error) {
    console.log("Error deleting product.")
    console.log(error)
  }
}

function handleDeleteClick(id: number) {
  dangerDialogOpen.value = true;
  PRODUCT_ID = id;
}

</script>

<template>

  <DangerDialog @takeAction="handleDelete" @toggle="updateDialogOpenState" title="Delete Product"
                :is-open="dangerDialogOpen" action="Delete">
    Are you sure you want to delete this product? All of
    your product data will be permanently removed. This action cannot be undone.
  </DangerDialog>
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

            <th class="h-12 px-4 text-left align-middle font-medium text-muted-foreground [&amp;:has([role=checkbox])]:pr-0">
              Options
            </th>
          </tr>
          </thead>

          <tbody class="[&amp;_tr:last-child]:border-0">

          <tr v-for="product in products"
              class="border-b transition-colors hover:bg-muted/50 data-[state=selected]:bg-muted">

            <td class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0 hidden md:table-cell">
              <img
                  :src=product.thumbnail alt="Product image"
                  class="aspect-square rounded-md object-cover">
            </td>

            <td class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0 font-medium">
              <router-link class="hover:underline-offset-2 hover:underline"
                           :to="{name: 'product', params: {id: product.id}}">

                {{ product.name }}
              </router-link>

            </td>

            <td class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0">${{ product.price }}</td>
            <td class="p-4 text-sm align-middle [&amp;:has([role=checkbox])]:pr-0">

              <div class="flex gap-1 flex-wrap w-32">

                <span v-for="(color, index) in product.colors" :key="index" class="inline-flex gap-1 items-center rounded-md bg-gray-50 px-2 py-1 text-xs font-medium text-gray-600 ring-1 ring-inset ring-gray-500/10">
                    {{ color }}
                </span>

              </div>
            </td>
            <td class="p-4 text-sm align-middle [&amp;:has([role=checkbox])]:pr-0">
              <div class="flex gap-1  flex-wrap w-32 " >

                <span v-for="size in product.sizes" class="inline-flex items-center rounded-md bg-gray-50 px-2 py-1 text-xs font-medium text-gray-600 ring-1 ring-inset ring-gray-500/10">{{ size }}</span>

              </div>
            </td>
            <td class="p-4 text-sm align-middle [&amp;:has([role=checkbox])]:pr-0">
              <Menu as="div" class="relative inline-block text-left">
                <div>
                  <MenuButton
                      class="inline-flex w-full justify-center gap-x-1.5 rounded-md bg-white px-3 py-2 text-sm font-semibold text-gray-900 hover:bg-gray-50">
                    <EllipsisVerticalIcon class="-mr-1 h-5 w-5 text-gray-400" aria-hidden="true"/>
                  </MenuButton>
                </div>

                <transition enter-active-class="transition ease-out duration-100"
                            enter-from-class="transform opacity-0 scale-95"
                            enter-to-class="transform opacity-100 scale-100"
                            leave-active-class="transition ease-in duration-75"
                            leave-from-class="transform opacity-100 scale-100"
                            leave-to-class="transform opacity-0 scale-95">
                  <MenuItems
                      class="absolute right-0 z-10 mt-2 w-56 origin-top-right rounded-md bg-white shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none">
                    <div class="py-1">
                      <MenuItem v-slot="{ active }">
                        <router-link :to="{name: 'product', params: {id: product.id}}"
                                     :class="[active ? 'bg-gray-100 text-gray-900' : 'text-gray-700', 'block px-4 py-2 text-sm']">
                          View
                        </router-link>
                      </MenuItem>
                      <MenuItem v-slot="{ active }">
                        <router-link :to="{name: 'updateProduct', params: {id: product.id}}"
                                     :class="[active ? 'bg-gray-100 text-gray-900' : 'text-gray-700', 'block px-4 py-2 text-sm']">
                          Edit
                        </router-link>
                      </MenuItem>
                      <MenuItem v-slot="{ active }">
                        <button @click="handleDeleteClick(product.id)"
                                :class="[active ? 'bg-red-50 text-red-700' : 'text-red-600', 'block w-full px-4 py-2 text-left text-sm']">
                          Delete
                        </button>
                      </MenuItem>

                    </div>
                  </MenuItems>
                </transition>
              </Menu>
            </td>

          </tr>

          </tbody>
        </table>
      </div>
    </div>
  </div>

</template>
