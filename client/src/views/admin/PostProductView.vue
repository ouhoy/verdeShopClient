<script setup lang="ts">

import {ref} from "vue";
import {RadioGroup, RadioGroupLabel, RadioGroupOption} from "@headlessui/vue";
import {ColorSelection} from "../../../types";

const sizes = ref([
  { name: 'XXS', inStock: true, selected: false },
  { name: 'XS', inStock: true, selected: false },
  { name: 'S', inStock: true, selected: false },
  { name: 'M', inStock: true, selected: false },
  { name: 'L', inStock: true, selected: false },
  { name: 'XL', inStock: true, selected: false },
  { name: 'XXL', inStock: true, selected: false },
  { name: 'XXXL', inStock: true, selected: false },
])


const colors = ref<ColorSelection[]>([])

const selectedColor = ref(colors.value[0])


const availableColors = ["green", "red", "blue", "purple", "orange", "orewood", "brown", "pink", "mauve", "black", "white"]
availableColors.forEach((availableColor, index) => {
  colors.value.push({name: `${availableColor}`, class: `bg-${availableColor}-600`, selectedClass: `ring-${availableColor}-600`, id: index})

})
</script>

<template>
  <main class="mt-20 mx-auto max-w-7xl px-4 sm:px-6 lg:px-8 flex flex-col md:flex-row lg:flex-row gap-20">

    <form class="mb-24">
      <div class="space-y-12">


        <div class="border-b border-gray-900/10 pb-12">
          <h2 class="text-base font-semibold leading-7 text-gray-900">Post Product</h2>
          <p class="mt-1 text-sm leading-6 text-gray-600">Make sure to fill in all the required inputs.</p>

          <div class="mt-10 grid grid-cols-1 gap-x-6 gap-y-8 sm:grid-cols-6">


            <div class="sm:col-span-4">
              <label for="title" class="block text-sm font-medium leading-6 text-gray-900">Title</label>
              <div class="mt-2">
                <input id="title" name="title" type="text"
                       class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"/>
              </div>
            </div>

            <div class="col-span-full">
              <label for="description" class="block text-sm font-medium leading-6 text-gray-900">Description</label>
              <div class="mt-2">
                <textarea id="description" name="description" rows="3"
                          class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"/>
              </div>
              <p class="mt-3 text-sm leading-6 text-gray-600">Write a few sentences about the product.</p>
            </div>

            <div class="col-span-full">
              <label for="highlights" class="block text-sm font-medium leading-6 text-gray-900">Highlights</label>
              <div class="mt-2">
                <textarea id="highlights" name="highlights" rows="3"
                          class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"/>
              </div>
              <p class="mt-3 text-sm leading-6 text-gray-600">Write a few sentences about the product seperated by a new
                line.</p>
            </div>

            <div class="col-span-full">
              <label for="images" class="block text-sm font-medium leading-6 text-gray-900">Images</label>
              <div class="mt-2">
                <textarea id="images" name="images" rows="3"
                          class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"/>
              </div>
              <p class="mt-3 text-sm leading-6 text-gray-600">You must add 4 image urls seperated by a new line.</p>
            </div>

            <div class="sm:col-span-4">
              <label for="thumbnail" class="block text-sm font-medium leading-6 text-gray-900">Thumbnail</label>
              <div class="mt-2">
                <input id="thumbnail" name="thumbnail" type="text"
                       class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"/>
              </div>
            </div>

            <div class="sm:col-span-4">
              <!-- Colors -->


              <div>
                <h3 class="text-sm font-medium text-gray-900">Color</h3>

                <RadioGroup v-model="selectedColor" class="mt-4">
                  <RadioGroupLabel class="sr-only">Choose a color</RadioGroupLabel>
                  <div class="flex items-center space-x-3">
                    <RadioGroupOption as="template" v-for="color in colors" :key="color.name" :value="color"
                                      v-slot="{ active, checked }">
                      <div
                          :class="[color.selectedClass, active && checked ? 'ring ring-offset-1' : '', !active && checked ? 'ring-2' : '', 'relative -m-0.5 flex cursor-pointer items-center justify-center rounded-full p-0.5 focus:outline-none']">
                        <RadioGroupLabel as="span" class="sr-only">{{ color.name }}</RadioGroupLabel>
                        <span aria-hidden="true"
                              :class="[color.class, 'h-8 w-8 rounded-full border border-black border-opacity-10']"/>
                      </div>
                    </RadioGroupOption>
                  </div>
                </RadioGroup>
              </div>

              <!-- Sizes -->
              <div class="mt-10">
                <div class="flex items-center justify-between">
                  <h3 class="text-sm font-medium text-gray-900">Size</h3>
                </div>

                <div class="grid grid-cols-4 gap-4 sm:grid-cols-8 lg:grid-cols-4 mt-4">
                  <div v-for="size in sizes" :key="size.name">
                    <label v-if="size.inStock"
                           :class="[size.selected ? 'bg-primary text-white hover:bg-primary-dark' : 'bg-white text-gray-900', 'group relative flex items-center justify-center rounded-md border py-3 px-4 text-sm font-medium uppercase hover:bg-gray-50 focus:outline-none sm:flex-1 sm:py-6 cursor-pointer']">
                      <input type="checkbox" v-model="size.selected" class="hidden"/>
                      {{ size.name }}
                    </label>
                    <label v-else
                           :class="['bg-gray-50 text-gray-200', 'group relative flex items-center justify-center rounded-md border py-3 px-4 text-sm font-medium uppercase hover:bg-gray-50 focus:outline-none sm:flex-1 sm:py-6 cursor-not-allowed']">
                      {{ size.name }}
                    </label>
                  </div>
                </div>
              </div>

            </div>


          </div>
          <p class="mt-1 pt-2 text-sm leading-6 text-gray-600">We'll always let you know about important changes, but
            you pick what else you want to hear about.</p>
        </div>

      </div>

      <div class="mt-6 flex items-center justify-end gap-x-6">
        <button type="button" class="text-sm font-semibold leading-6 text-gray-900">Cancel</button>
        <button type="submit"
                class="rounded-md bg-indigo-600 px-3 py-2 text-sm font-semibold text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600">
          Save
        </button>
      </div>
    </form>
  </main>

</template>

<style scoped>

</style>