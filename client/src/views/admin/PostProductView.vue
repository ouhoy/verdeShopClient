<script setup lang="ts">

import {reactive, ref} from "vue";
import type {AvailableColor} from "../../../types";
import {availableColors, sizes} from "@/availableStockData/availableStock";
import MainButton from "@/components/buttons/MainButton.vue";
import axios from "axios";
import LoadingIcon from "@/assets/icons/LoadingIcon.vue";
import CheckMarkIcon from "@/assets/icons/CheckMarkIcon.vue";

const errors = reactive({title: "", price: "", description: "", highlights: "", thumbnail: "", images: "", colors: ""})

const title = ref("");
const price = ref(0);
const description = ref("");
const highlights = ref("");
const thumbnail = ref("");
const images = ref("")
const selectedGender = ref("men");
const selectedProductType = ref("clothing")
const colors = ref<AvailableColor[]>([])

const isPending = ref(false);
const productPosted = ref(false);
// Add colors
colors.value = [];
availableColors.forEach((availableColor: string, index: number) => {
  colors.value.push({
    name: `${availableColor}`,
    class: `bg-${availableColor}-600`,
    selectedClass: `ring-${availableColor}-600`,
    selected: false,
    id: index
  })

})

const selectedColorsList = ref<string[]>([])

function handleColorChange(state: boolean) {

  if (state) return

  let selectedColors = 0;
  selectedColorsList.value = []
  colors.value.forEach((color, index) => {
    if (color.selected) {
      selectedColors++
      selectedColorsList.value.push(color.name)
    }
  })

  if (selectedColors === 4) {
    for (let i = 0; i < colors.value.length; i++) {
      if (colors.value[i].selected) {
        colors.value[i].selected = false;
        break;
      }
    }
  }


}


function handleSubmit() {


  // TODO: Validate form
  isPending.value = true;
  const imageSrc = images.value.split("\n");

  const selectedColors: string[] = []
  const selectedSizes: string[] = []

  colors.value.forEach((color) => {
    if (color.selected) selectedColors.push(color.name)

  })

  sizes.value.forEach((size) => {
    if (size.selected) selectedSizes.push(size.name)
  })

  const product = {
    name: title.value,
    price: price.value,
    description: description.value,
    highlights: highlights.value.split("\n"),
    details: "N/A",
    thumbnail: thumbnail.value,
    imageSrc: [imageSrc[2], imageSrc[3], imageSrc[1], imageSrc[0]],
    imageAlt: title.value,
    colors: selectedColors,
    gender: selectedGender.value.toUpperCase(),
    sizes: selectedSizes,
    options: [...selectedSizes, selectedGender.value.toUpperCase(), ...selectedColors],
    type: selectedProductType.value.toUpperCase()

  }


  axios.post("http://localhost:8080/v1/products/", product).then(result => {
    console.log("OK");
    isPending.value = false;
    productPosted.value = true

  }).catch(error => {
    console.log("Something went wrong")
    console.log(error)
    isPending.value = false;
    productPosted.value = false


  })

}

</script>

<template>
  <main class="mt-20 mx-auto max-w-7xl px-4 sm:px-6 lg:px-8 flex flex-col md:flex-row lg:flex-row gap-20">

    <form @submit.prevent="handleSubmit" class="mb-24">
      <div class="space-y-12">


        <div class="border-b border-gray-900/10 pb-12">
          <h2 class="text-base font-semibold leading-7 text-gray-900">Post Product</h2>
          <p class="mt-1 text-sm leading-6 text-gray-600">Make sure to fill in all the required inputs.</p>

          <div class="mt-10 grid grid-cols-1 gap-x-6 gap-y-8 sm:grid-cols-6">


            <div class="sm:col-span-4">
              <label for="title" class="block text-sm font-medium leading-6 text-gray-900">Title</label>
              <div class="mt-2">
                <input v-model="title" id="title" name="title" type="text"
                       class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"/>
              </div>
            </div>

            <div class="sm:col-span-4">
              <label for="title" class="block text-sm font-medium leading-6 text-gray-900">Price</label>
              <div class="mt-2">
                <input id="price" v-model="price" name="price" type="number"
                       class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"/>
              </div>
            </div>

            <div class="col-span-full">
              <label for="description" class="block text-sm font-medium leading-6 text-gray-900">Description</label>
              <div class="mt-2">
                <textarea id="description" v-model="description" name="description" rows="3"
                          class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"/>
              </div>
              <p class="mt-3 text-sm leading-6 text-gray-600">Write a few sentences about the product.</p>
            </div>

            <div class="col-span-full">
              <label for="highlights" class="block text-sm font-medium leading-6 text-gray-900">Highlights</label>
              <div class="mt-2">
                <textarea v-model="highlights" id="highlights" name="highlights" rows="3"
                          class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"/>
              </div>
              <p class="mt-3 text-sm leading-6 text-gray-600">Write a few sentences about the product seperated by a new
                line.</p>
            </div>

            <div class="sm:col-span-4">
              <!-- Colors -->


              <div class="">
                <div class="flex flex-col items-start justify-between">
                  <h3 class="text-sm font-medium text-gray-900">Color</h3>
                  <p class="mt-1 text-sm leading-6 text-gray-600">The selection of the colors' order should match the
                    order of images' urls.</p>

                </div>
                <div class="mt-4">
                  <label id="headlessui-label-4" class="sr-only" role="none">Choose a color</label>
                  <div class="grid grid-cols-4 gap-4 sm:grid-cols-8 lg:grid-cols-8 ">
                    <div v-for="color in colors" :key="color.id">
                      <label @click="handleColorChange(color.selected)"
                             :class="[color.selected ? `${color.selectedClass} ${color.class} ring-offset-1 ring-2 text-white ` : ` ${color.class} text-gray-900`, 'group relative flex items-center justify-center rounded-full border  text-sm font-medium uppercase  focus:outline-none sm:flex-1 h-8 w-8  cursor-pointer']">
                        <input type="checkbox" v-model="color.selected" class="hidden"/>
                        <span v-if="color.selected">{{
                            selectedColorsList.indexOf(color.name) + 1 ? selectedColorsList.indexOf(color.name) + 1 : ''
                          }}</span>
                      </label>

                    </div>
                  </div>

                </div>

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
            <div class="sm:col-span-4">

              <fieldset>
                <legend class="text-sm font-semibold leading-6 text-gray-900">Gender</legend>
                <div class="mt-4 flex gap-6">
                  <div class="flex items-center gap-x-3">
                    <input v-model="selectedGender" value="men" id="men" name="gender" checked type="radio"
                           class="h-4 w-4 border-gray-300 text-indigo-600 focus:ring-indigo-600"/>
                    <label for="men" class="block text-sm font-medium leading-6 text-gray-900">Men</label>
                  </div>
                  <div class="flex items-center gap-x-3">
                    <input v-model="selectedGender" value="women" id="women" name="gender" type="radio"
                           class="h-4 w-4 border-gray-300 text-indigo-600 focus:ring-indigo-600"/>
                    <label for="women" class="block text-sm font-medium leading-6 text-gray-900">Women</label>
                  </div>
                  <div class="flex items-center gap-x-3">
                    <input v-model="selectedGender" value="unisex" id="unisex" name="gender" type="radio"
                           class="h-4 w-4 border-gray-300 text-indigo-600 focus:ring-indigo-600"/>
                    <label for="unisex" class="block text-sm font-medium leading-6 text-gray-900">Unisex</label>
                  </div>
                </div>
              </fieldset>

            </div>

            <div class="sm:col-span-4">

              <fieldset>
                <legend class="text-sm font-semibold leading-6 text-gray-900">Product Type</legend>
                <div class="mt-4 flex gap-6 ">
                  <div class="flex items-center gap-x-3">
                    <input v-model="selectedProductType" value="clothing" id="clothing" name="product-type" checked
                           type="radio" class="h-4 w-4 border-gray-300 text-indigo-600 focus:ring-indigo-600"/>
                    <label for="clothing" class="block text-sm font-medium leading-6 text-gray-900">Clothing</label>
                  </div>
                  <div class="flex items-center gap-x-3">
                    <input v-model="selectedProductType" value="shoes" id="shoes" name="product-type" type="radio"
                           class="h-4 w-4 border-gray-300 text-indigo-600 focus:ring-indigo-600"/>
                    <label for="shoes" class="block text-sm font-medium leading-6 text-gray-900">Shoes</label>
                  </div>
                  <div class="flex items-center gap-x-3">
                    <input v-model="selectedProductType" value="accessories" id="accessories" name="product-type"
                           type="radio" class="h-4 w-4 border-gray-300 text-indigo-600 focus:ring-indigo-600"/>
                    <label for="accessories"
                           class="block text-sm font-medium leading-6 text-gray-900">Accessories</label>
                  </div>
                </div>
              </fieldset>

            </div>

            <div class="sm:col-span-4">
              <label for="thumbnail" class="block text-sm font-medium leading-6 text-gray-900">Thumbnail</label>
              <div class="mt-2">
                <input id="thumbnail" v-model="thumbnail" name="thumbnail" type="text"
                       class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"/>
              </div>
            </div>

            <div class="col-span-full">
              <label for="images" class="block text-sm font-medium leading-6 text-gray-900">Images</label>
              <div class="mt-2">
                <textarea id="images" v-model="images" name="images" rows="3"
                          class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"/>
              </div>
              <p class="mt-3 text-sm leading-6 text-gray-600">You must add 4 image urls seperated by a new line.</p>
            </div>


          </div>

        </div>

      </div>

      <div class="mt-6 flex items-center justify-end gap-x-6">
        <p v-if="productPosted" class=" text-sm text-green-500 flex justify-start items-center gap-1">
          <CheckMarkIcon/>
          Product has been posted with success!
        </p>
        <MainButton v-if="isPending">
          <LoadingIcon/>
          Saving...
        </MainButton>
        <MainButton v-if="!isPending">Save</MainButton>
      </div>
    </form>
  </main>

</template>

<style scoped>

</style>