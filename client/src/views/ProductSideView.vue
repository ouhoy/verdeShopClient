<script lang="ts" setup>
import {onMounted, ref} from 'vue'
import {StarIcon} from '@heroicons/vue/20/solid'
import {RadioGroup, RadioGroupLabel, RadioGroupOption} from '@headlessui/vue'
import {useRoute} from "vue-router";
import type {ColorSelection, Product} from "../../types";

const route = useRoute();

const product = ref<Product>()

const sizes = ref([
  {name: 'XXS', inStock: false},
  {name: 'XS', inStock: false},
  {name: 'S', inStock: false},
  {name: 'M', inStock: false},
  {name: 'L', inStock: false},
  {name: 'XL', inStock: false},
  {name: 'XXL', inStock: false},
  {name: 'XXXL', inStock: false},
])

const colors = ref<ColorSelection[]>([])

const breadcrumbs = [
  {id: 1, name: 'Men', href: '#'},
  {id: 2, name: 'Clothing', href: '#'},
]

const reviews = {href: '#', average: 4, totalCount: 117}

const selectedColor = ref(colors.value[0])
const selectedSize = ref(sizes.value[0])

onMounted(async () => {

  const data = await fetch(`http://localhost:8080/1`);
  const result = await data.json();

  product.value = result;

  console.log(result)

  sizes.value.forEach((size, index) => {

    result.sizes.forEach((currentSize: string) => {
      if (size.name === currentSize) {
        sizes.value[index].inStock = true

        selectedSize.value = sizes.value[index]
      }

    })
  })

  // TODO: Put this in a function
  colors.value.push({name: `black`, class: `bg-brown-600`, selectedClass: `ring-brown-600`})
  colors.value.push({name: `black`, class: `bg-red-600`, selectedClass: `ring-red-600`})
  colors.value.push({name: `black`, class: `bg-blue-600`, selectedClass: `ring-blue-600`})
  colors.value.push({name: `black`, class: `bg-orewood-600`, selectedClass: `ring-orewood-600`})
  colors.value.push({name: `black`, class: `bg-pink-600`, selectedClass: `ring-pink-600`})
  colors.value.push({name: `black`, class: `bg-mauve-600`, selectedClass: `ring-mauve-600`})
  colors.value.push({name: `black`, class: `bg-purple-600`, selectedClass: `ring-purple-600`})
  colors.value.push({name: `black`, class: `bg-orange-600`, selectedClass: `ring-orange-600`})

  colors.value = []


  result.colors.forEach((color: ColorSelection, index: number) => {

    colors.value.push({name: `${color}`, class: `bg-${color}-600`, selectedClass: `ring-${color}-600`})
  })

})


</script>

<style>

</style>


<template>


  <div class="bg-white">

    <div class="pt-6">
      <nav aria-label="Breadcrumb">
        <ol role="list" class="mx-auto flex max-w-2xl items-center space-x-2 px-4 sm:px-6 lg:max-w-7xl lg:px-8">
          <li v-for="breadcrumb in breadcrumbs" :key="breadcrumb.id">
            <div class="flex items-center">
              <a :href="breadcrumb.href" class="mr-2 text-sm font-medium text-gray-900">{{ breadcrumb.name }}</a>
              <svg width="16" height="20" viewBox="0 0 16 20" fill="currentColor" aria-hidden="true"
                   class="h-5 w-4 text-gray-300">
                <path d="M5.697 4.34L8.98 16.532h1.327L7.025 4.341H5.697z"/>
              </svg>
            </div>
          </li>
          <li class="text-sm">
            <a :href="product?.name" aria-current="page"
               class="font-medium text-gray-500 hover:text-gray-600">{{ product?.name }}</a>
          </li>
        </ol>
      </nav>

      <!-- Image gallery -->
      <div class="mx-auto mt-6 max-w-2xl sm:px-6 lg:grid lg:max-w-7xl lg:grid-cols-3 lg:gap-x-8 lg:px-8">

        <div class="aspect-h-4 aspect-w-3 hidden overflow-hidden rounded-lg lg:block">
          <img :src="product?.imageSrc[0]" :alt="product?.name" class="h-full w-full object-cover object-center"/>
        </div>

        <div class="hidden lg:grid lg:grid-cols-1 lg:gap-y-8">
          <div class="aspect-h-2 aspect-w-3 overflow-hidden rounded-lg">
            <img :src="product?.imageSrc[1]" :alt="product?.name" class="h-full w-full object-cover object-center"/>
          </div>

          <div class="aspect-h-2 aspect-w-3 overflow-hidden rounded-lg">
            <img :src="product?.imageSrc[2]" :alt="product?.name" class="h-full w-full object-cover object-center"/>
          </div>

        </div>
        <div class="aspect-h-5 aspect-w-4 lg:aspect-h-4 lg:aspect-w-3 sm:overflow-hidden sm:rounded-lg">
          <img :src="product?.imageSrc[3]" :alt="product?.name" class="h-full w-full object-cover object-center"/>
        </div>
      </div>

      <!-- Product info -->
      <div
          class="mx-auto max-w-2xl px-4 pb-16 pt-10 sm:px-6 lg:grid lg:max-w-7xl lg:grid-cols-3 lg:grid-rows-[auto,auto,1fr] lg:gap-x-8 lg:px-8 lg:pb-24 lg:pt-16">
        <div class="lg:col-span-2 lg:border-r lg:border-gray-200 lg:pr-8">
          <h1 class="text-2xl font-bold tracking-tight text-gray-900 sm:text-3xl">{{ product?.name }}</h1>
        </div>

        <!-- Options -->
        <div class="mt-4 lg:row-span-3 lg:mt-0">
          <h2 class="sr-only">Product information</h2>
          <p class="text-3xl tracking-tight text-gray-900">${{ product?.price }}</p>

          <!-- Reviews -->
          <div class="mt-6">
            <h3 class="sr-only">Reviews</h3>
            <div class="flex items-center">
              <div class="flex items-center">
                <StarIcon v-for="rating in [0, 1, 2, 3, 4]" :key="rating"
                          :class="[reviews.average > rating ? 'text-gray-900' : 'text-gray-200', 'h-5 w-5 flex-shrink-0']"
                          aria-hidden="true"/>
              </div>
              <p class="sr-only">{{ reviews.average }} out of 5 stars</p>
              <a :href="reviews.href" class="ml-3 text-sm font-medium text-primary-dark hover:text-primary-dark-hover">{{
                  reviews.totalCount
                }} reviews</a>
            </div>
          </div>

          <form class="mt-10">
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
                <a href="#" class="text-sm font-medium text-primary-dark hover:text-primary-dark-hover">Size guide</a>
              </div>

              <RadioGroup v-model="selectedSize" class="mt-4">
                <RadioGroupLabel class="sr-only">Choose a size</RadioGroupLabel>
                <div class="grid grid-cols-4 gap-4 sm:grid-cols-8 lg:grid-cols-4">
                  <RadioGroupOption as="template" v-for="size in sizes" :key="size.name" :value="size"
                                    :disabled="!size.inStock" v-slot="{ active, checked }">
                    <div
                        :class="[size.inStock ? 'cursor-pointer bg-white text-gray-900 shadow-sm' : 'cursor-not-allowed bg-gray-50 text-gray-200', active ? 'ring-2 ring-primary-active' : '', 'group relative flex items-center justify-center rounded-md border py-3 px-4 text-sm font-medium uppercase hover:bg-gray-50 focus:outline-none sm:flex-1 sm:py-6']">
                      <RadioGroupLabel as="span">{{ size.name }}</RadioGroupLabel>
                      <span v-if="size.inStock"
                            :class="[active ? 'border' : 'border-2', checked ? 'border-primary-active' : 'border-transparent', 'pointer-events-none absolute -inset-px rounded-md']"
                            aria-hidden="true"/>
                      <span v-else aria-hidden="true"
                            class="pointer-events-none absolute -inset-px rounded-md border-2 border-gray-200">
                        <svg class="absolute inset-0 h-full w-full stroke-2 text-gray-200" viewBox="0 0 100 100"
                             preserveAspectRatio="none" stroke="currentColor">
                          <line x1="0" y1="100" x2="100" y2="0" vector-effect="non-scaling-stroke"/>
                        </svg>
                      </span>
                    </div>
                  </RadioGroupOption>
                </div>
              </RadioGroup>
            </div>

            <button type="submit"
                    class="mt-10 flex w-full items-center justify-center rounded-md border border-transparent bg-primary-darker px-8 py-3 text-base font-medium text-white hover:bg-primary-dark-hover focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2">
              Add to bag
            </button>
          </form>
        </div>

        <div class="py-10 lg:col-span-2 lg:col-start-1 lg:border-r lg:border-gray-200 lg:pb-16 lg:pr-8 lg:pt-6">
          <!-- Description and details -->
          <div>
            <h3 class="sr-only">Description</h3>

            <div class="space-y-6">
              <p class="text-base text-gray-900">{{ product?.description }}</p>
            </div>
          </div>

          <div class="mt-10">
            <h3 class="text-sm font-medium text-gray-900">Highlights</h3>

            <div class="mt-4">
              <ul role="list" class="list-disc space-y-2 pl-4 text-sm">
                <li v-for="highlight in product?.highlights" :key="highlight" class="text-gray-400">
                  <span class="text-gray-600">{{ highlight }}</span>
                </li>
              </ul>
            </div>
          </div>

          <div class="mt-10">
            <h2 class="text-sm font-medium text-gray-900">Details</h2>

            <div class="mt-4 space-y-6">
              <p class="text-sm text-gray-600">{{ product?.details }}</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

