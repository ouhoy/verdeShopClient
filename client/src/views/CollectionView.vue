<template>
  <div class="bg-white">
    <div>
      <!-- Mobile filter dialog -->
      <TransitionRoot as="template" :show="mobileFiltersOpen">
        <Dialog as="div" class="relative z-40 lg:hidden" @close="mobileFiltersOpen = false">
          <TransitionChild as="template" enter="transition-opacity ease-linear duration-300" enter-from="opacity-0"
                           enter-to="opacity-100" leave="transition-opacity ease-linear duration-300"
                           leave-from="opacity-100" leave-to="opacity-0">
            <div class="fixed inset-0 bg-black bg-opacity-25"/>
          </TransitionChild>

          <div class="fixed inset-0 z-40 flex">
            <TransitionChild as="template" enter="transition ease-in-out duration-300 transform"
                             enter-from="translate-x-full" enter-to="translate-x-0"
                             leave="transition ease-in-out duration-300 transform" leave-from="translate-x-0"
                             leave-to="translate-x-full">
              <DialogPanel
                  class="relative ml-auto flex h-full w-full max-w-xs flex-col overflow-y-auto bg-white py-4 pb-12 shadow-xl">
                <div class="flex items-center justify-between px-4">
                  <h2 class="text-lg font-medium text-gray-900">Filters</h2>
                  <button type="button"
                          class="-mr-2 flex h-10 w-10 items-center justify-center rounded-md bg-white p-2 text-gray-400"
                          @click="mobileFiltersOpen = false">
                    <span class="sr-only">Close menu</span>
                    <XMarkIcon class="h-6 w-6" aria-hidden="true"/>
                  </button>
                </div>

                <!-- Filters -->
                <form class="mt-4 border-t border-gray-200">
                  <h3 class="sr-only">Categories</h3>
                  <ul role="list" class="px-2 py-3 font-medium text-gray-900">
                    <li v-for="category in subCategories" :key="category.name">
                      <a :href="category.href" class="block px-2 py-3">{{ category.name }}</a>
                    </li>
                  </ul>

                  <Disclosure as="div" v-for="section in filters" :key="section.id"
                              class="border-t border-gray-200 px-4 py-6" v-slot="{ open }">
                    <h3 class="-mx-2 -my-3 flow-root">
                      <DisclosureButton
                          class="flex w-full items-center justify-between bg-white px-2 py-3 text-gray-400 hover:text-gray-500">
                        <span class="font-medium text-gray-900">{{ section.name }}</span>
                        <span class="ml-6 flex items-center">
                          <PlusIcon v-if="!open" class="h-5 w-5" aria-hidden="true"/>
                          <MinusIcon v-else class="h-5 w-5" aria-hidden="true"/>
                        </span>
                      </DisclosureButton>
                    </h3>
                    <DisclosurePanel class="pt-6">
                      <div class="space-y-6">
                        <div v-for="(option, optionIdx) in section.options" :key="option.value"
                             class="flex items-center">
                          <input :id="`filter-mobile-${section.id}-${optionIdx}`" :name="`${section.id}[]`"
                                 :value="option.value" type="checkbox" :checked="option.checked"
                                 class="h-4 w-4 rounded border-gray-300 text-indigo-600 focus:ring-indigo-500"/>
                          <label :for="`filter-mobile-${section.id}-${optionIdx}`"
                                 class="ml-3 min-w-0 flex-1 text-gray-500">{{ option.label }}</label>
                        </div>
                      </div>
                    </DisclosurePanel>
                  </Disclosure>
                </form>
              </DialogPanel>
            </TransitionChild>
          </div>
        </Dialog>
      </TransitionRoot>

      <main class="mx-auto max-w-7xl px-4 sm:px-6 lg:px-8">
        <div class="flex items-baseline justify-between border-b border-gray-200 pb-6 pt-24">
          <h1 class="text-4xl font-bold tracking-tight text-gray-900">New Arrivals</h1>

          <div class="flex items-center">
            <Menu as="div" class="relative inline-block text-left">
              <div>
                <MenuButton
                    class="group inline-flex justify-center text-sm font-medium text-gray-700 hover:text-gray-900">
                  Sort
                  <ChevronDownIcon class="-mr-1 ml-1 h-5 w-5 flex-shrink-0 text-gray-400 group-hover:text-gray-500"
                                   aria-hidden="true"/>
                </MenuButton>
              </div>

              <transition enter-active-class="transition ease-out duration-100"
                          enter-from-class="transform opacity-0 scale-95"
                          enter-to-class="transform opacity-100 scale-100"
                          leave-active-class="transition ease-in duration-75"
                          leave-from-class="transform opacity-100 scale-100"
                          leave-to-class="transform opacity-0 scale-95">
                <MenuItems
                    class="absolute right-0 z-10 mt-2 w-40 origin-top-right rounded-md bg-white shadow-2xl ring-1 ring-black ring-opacity-5 focus:outline-none">
                  <div class="py-1">
                    <MenuItem v-for="option in sortOptions" :key="option.name" v-slot="{ active }">
                      <a @click="handleSortOptionClick(option.name)" :href="option.href"
                         :class="[option.current ? 'font-medium text-gray-900' : 'text-gray-500', active ? 'bg-gray-100' : '', 'block px-4 py-2 text-sm']">{{
                          option.name
                        }}</a>
                    </MenuItem>
                  </div>
                </MenuItems>
              </transition>
            </Menu>

            <button type="button" class="-m-2 ml-5 p-2 text-gray-400 hover:text-gray-500 sm:ml-7">
              <span class="sr-only">View grid</span>
              <Squares2X2Icon class="h-5 w-5" aria-hidden="true"/>
            </button>
            <button type="button" class="-m-2 ml-4 p-2 text-gray-400 hover:text-gray-500 sm:ml-6 lg:hidden"
                    @click="mobileFiltersOpen = true">
              <span class="sr-only">Filters</span>
              <FunnelIcon class="h-5 w-5" aria-hidden="true"/>
            </button>
          </div>
        </div>

        <section aria-labelledby="products-heading" class="pb-24 pt-6">
          <h2 id="products-heading" class="sr-only">Products</h2>

          <div class="grid grid-cols-1 gap-x-8 gap-y-10 lg:grid-cols-4">
            <!-- Filters -->

            <form class="hidden lg:block">
              <h3 class="sr-only">Categories</h3>
              <ul role="list" class="space-y-4 border-b border-gray-200 pb-6 text-sm font-medium text-gray-900">
                <li v-for="category in subCategories" :key="category.name">
                  <a :href="category.href">{{ category.name }}</a>
                </li>
              </ul>

              <Disclosure as="div" v-for="section in filters" :key="section.id" class="border-b border-gray-200 py-6"
                          v-slot="{ open }">
                <h3 class="-my-3 flow-root">
                  <DisclosureButton
                      class="flex w-full items-center justify-between bg-white py-3 text-sm text-gray-400 hover:text-gray-500">
                    <span class="font-medium text-gray-900">{{ section.name }}</span>
                    <span class="ml-6 flex items-center">
                      <PlusIcon v-if="!open" class="h-5 w-5" aria-hidden="true"/>
                      <MinusIcon v-else class="h-5 w-5" aria-hidden="true"/>
                    </span>
                  </DisclosureButton>
                </h3>
                <DisclosurePanel class="pt-6">
                  <div class="space-y-4">
                    <div v-for="(option, optionIdx) in section.options" :key="option.value" class="flex items-center">
                      <input @change="handleProductTypeChange(section.id, option.value)"
                             :id="`filter-${section.id}-${optionIdx}`"
                             :name="`${section.id}[]`" :value="option.value" type="checkbox" :checked="option.checked"
                             class="h-4 w-4 rounded border-gray-300 text-indigo-600 focus:ring-indigo-500"/>
                      <label :for="`filter-${section.id}-${optionIdx}`"
                             class="ml-3 text-sm text-gray-600">{{ option.label }}</label>
                    </div>
                  </div>
                </DisclosurePanel>
              </Disclosure>
            </form>

            <!-- Product grid -->
            <div class="lg:col-span-3">
              <!-- Your content -->
              <div class="bg-white">
                <div class="mx-auto max-w-2xl px-4 py-16 sm:px-6 sm:py-1 lg:max-w-7xl lg:px-8">
                  <h2 class="sr-only">Products</h2>
                  <div
                      class="grid grid-cols-1 gap-x-6 gap-y-10 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-3 xl:gap-x-8">
                    <a v-for="product in filteredProducts" :key="product.id" :href="product.href" class="group">
                      <div
                          class="aspect-h-1 aspect-w-1 w-full overflow-hidden rounded-lg bg-gray-200 xl:aspect-h-8 xl:aspect-w-7">
                        <img :src="product.imageSrc" :alt="product.imageAlt"
                             class="h-full w-full object-cover object-center group-hover:opacity-75"/>
                      </div>
                      <h3 class="mt-4 text-sm text-gray-700">{{ product.name }}</h3>
                      <p class="mt-1 text-lg font-medium text-gray-900">{{ product?.price }}</p>
                    </a>
                  </div>
                </div>
              </div>
              <!-- End Your content -->

            </div>
          </div>
        </section>
      </main>
    </div>
  </div>
</template>

<script setup lang="ts">
import {ref} from 'vue'
import {
  Dialog,
  DialogPanel,
  Disclosure,
  DisclosureButton,
  DisclosurePanel,
  Menu,
  MenuButton,
  MenuItem,
  MenuItems,
  TransitionChild,
  TransitionRoot,
} from '@headlessui/vue'
import {XMarkIcon} from '@heroicons/vue/24/outline'
import {ChevronDownIcon, FunnelIcon, MinusIcon, PlusIcon, Squares2X2Icon} from '@heroicons/vue/20/solid'

const sortOptions = ref([

  {name: 'Newest', href: '#', current: true},
  {name: 'Price: Low to High', href: '#', current: false},
  {name: 'Price: High to Low', href: '#', current: false},
])


const subCategories = [
  {name: 'Clothing', href: '#'},
  {name: 'Shoes', href: '#'},
  {name: 'Accessories', href: '#'},
]
const filters = ref([
  {
    id: 'gender',
    name: 'Gender',
    options: [
      {value: 'unisex', label: 'Unisex', checked: false},
      {value: 'men', label: 'Men', checked: false},
      {value: 'women', label: 'Women', checked: false},
    ],
  },
  {
    id: 'colors',
    name: 'Colors',
    options: [
      {value: 'white', label: 'White', checked: false},
      {value: 'green', label: 'Green', checked: false},
      {value: 'beige', label: 'Beige', checked: false},
      {value: 'blue', label: 'Blue', checked: false},
      {value: 'brown', label: 'Brown', checked: false},
      {value: 'purple', label: 'Purple', checked: false},
    ],
  },
  {
    id: 'size',
    name: 'Size',
    options: [
      {value: 'xxs', label: 'XXS', checked: false},
      {value: 'xs', label: 'XS', checked: false},
      {value: 's', label: 'S', checked: false},
      {value: 'm', label: 'M', checked: false},
      {value: 'l', label: 'L', checked: false},
      {value: 'xl', label: 'XL', checked: false},
      {value: '2xl', label: '2XL', checked: false},
      {value: '3xl', label: '3XL', checked: false},
    ],
  },
])

const mobileFiltersOpen = ref(false)

const products = ref([
  {
    id: 1,
    name: 'Earthen Bottle',
    href: '#',
    price: '$48',
    imageSrc: 'https://tailwindui.com/img/ecommerce-images/category-page-04-image-card-01.jpg',
    imageAlt: 'Tall slender porcelain bottle with natural clay textured body and cork stopper.',
    colors: ["red", "green"],
    gender: ["unisex"],
    options: ["red", "green", "unisex", "2l"],
    size: ["2l"],
    type: "clothing"
  },
  {
    id: 2,
    name: 'Nomad Tumbler',
    href: '#',
    price: '$35',
    imageSrc: 'https://tailwindui.com/img/ecommerce-images/category-page-04-image-card-02.jpg',
    imageAlt: 'Olive drab green insulated bottle with flared screw lid and flat top.',
    colors: ["green"],
    gender: ["unisex", "men"],
    size: ["2xl", "l", "m"],
    options: ["green", "unisex", "men", "2xl", "l", "m"],
    type: "clothing"
  },
  {
    id: 3,
    name: 'Focus Paper Refill',
    href: '#',
    price: '$89',
    imageSrc: 'https://tailwindui.com/img/ecommerce-images/category-page-04-image-card-03.jpg',
    imageAlt: 'Person using a pen to cross a task off a productivity paper card.',
    colors: ["blue"],
    gender: ["men"],
    options: ["blue", "men", "s", "l", "m"],
    size: ["s", "l", "m"],
    type: "clothing"
  },
  {
    id: 4,
    name: 'Machined Mechanical Pencil',
    href: '#',
    price: '$35',
    imageSrc: 'https://tailwindui.com/img/ecommerce-images/category-page-04-image-card-04.jpg',
    imageAlt: 'Hand holding black machined steel mechanical pencil with brass tip and top.',
    colors: ["white"],
    gender: ["women"],
    size: ["s", "l", "m"],
    options: ["white", "women", "s", "l", "m"],
    type: "clothing"
  },

])
const filteredProducts = ref<any>(products.value);


const sortProducts = () => {
  const filtered = [...filteredProducts.value];

  // Apply sort option
  const sortOption = sortOptions.value.find((option) => option.current);

  if (sortOption && sortOption.name === 'Newest') {
    filtered.sort((a, b) => b.id - a.id);
  } else if (sortOption && sortOption.name === 'Price: Low to High') {
    filtered.sort((a, b) => parseFloat(a.price.slice(1)) - parseFloat(b.price.slice(1)));
  } else if (sortOption && sortOption.name === 'Price: High to Low') {
    filtered.sort((a, b) => parseFloat(b.price.slice(1)) - parseFloat(a.price.slice(1)));
  }


  filteredProducts.value = filtered;
  return filtered;
};

enum Section {
  Colors = "colors",
  Size = "size",
  Gender = "gender"

}

function handleProductTypeChange(section: string, option: string) {


  const index = filters.value.findIndex((filter) => filter.id === section)

  filters.value[index].options.forEach((selection, i) => {
    if (selection.value === option) {
      filters.value[index].options[i].checked = !filters.value[index].options[i].checked;
    }

  })

  // @ts-ignore
  sortProductsByType(section);


}


function sortProductsByType() {


  const checked = []

  // Get checked items
  filters.value.forEach(filter => {
    filter.options.forEach(option => {
      if (option.checked) checked.push(option.value)
    })
  })


  // TODO before cleaning look for checked boxes

  filteredProducts.value = []

  if (checked.length == 0) {
    filteredProducts.value = products.value;
  }

  products.value.forEach((product) => {

    // if product category has item type add to array
    checked.forEach((word) => {
      if (product.options.includes(word)) addUniqueValueById(filteredProducts.value, product.id, product);
    })

  })

  // TODO: If options are selected but no product show dialog

}

function addUniqueValueById(array: any[], id: number, value: any) {
  // Check if the ID already exists in the array
  const existingItem = array.find(item => item.id === id);

  // If the ID doesn't exist, add a new object to the array
  if (!existingItem) {
    array.push(value);
  }
}


function handleSortOptionClick(name: string) {

  sortOptions.value.forEach((option, index) => {
    sortOptions.value[index].current = option.name === name;
  })
  sortProducts();

}

</script>