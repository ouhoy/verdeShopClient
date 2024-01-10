<script setup lang="ts">



const products = [
  {
    id: 1,
    name: 'Throwback Hip Bag',
    href: '#',
    color: 'Salmon',
    price: '$90.00',
    quantity: 1,
    imageSrc: 'https://tailwindui.com/img/ecommerce-images/shopping-cart-page-04-product-01.jpg',
    imageAlt: 'Salmon orange fabric pouch with match zipper, gray zipper pull, and adjustable hip belt.',
  },
  {
    id: 2,
    name: 'Medium Stuff Satchel',
    href: '#',
    color: 'Blue',
    price: '$32.00',
    quantity: 1,
    imageSrc: 'https://tailwindui.com/img/ecommerce-images/shopping-cart-page-04-product-02.jpg',
    imageAlt:
        'Front of satchel with blue canvas body, black straps and handle, drawstring top, and front zipper pouch.',
  },
  // More products...
]

</script>

<template>



  <main class="mt-20 mx-auto max-w-7xl px-4 sm:px-6 lg:px-8 flex gap-20">
    <div class="pointer-events-auto w-screen max-w-md">
      <div class="flex h-full flex-col bg-white border-primary-light-active border rounded	">
        <div class="flex-1 overflow-y-auto px-4 py-6 sm:px-6">
          <div class="flex items-start justify-between">
            <div class="text-lg font-medium text-gray-900">Order summary</div>

          </div>

          <div class="mt-8">
            <div class="flow-root">
              <ul role="list" class="-my-6 divide-y divide-gray-200">
                <li v-for="product in products" :key="product.id" class="flex py-6">
                  <div class="h-24 w-24 flex-shrink-0 overflow-hidden rounded-md border border-gray-200">
                    <img :src="product.imageSrc" :alt="product.imageAlt"
                         class="h-full w-full object-cover object-center"/>
                  </div>

                  <div class="ml-4 flex flex-1 flex-col">
                    <div>
                      <div class="flex justify-between text-base font-medium text-gray-900">
                        <h3>
                          <a :href="product.href">{{ product.name }}</a>
                        </h3>
                        <p class="ml-4">{{ product.price }}</p>
                      </div>
                      <p class="mt-1 text-sm text-gray-500">{{ product.color }}</p>
                    </div>
                    <div class="flex flex-1 items-end justify-between text-sm">
                      <p class="text-gray-500">Qty {{ product.quantity }}</p>

                      <div class="flex">
                        <button type="button" class="font-medium text-indigo-600 hover:text-indigo-500">
                          Remove
                        </button>
                      </div>
                    </div>
                  </div>
                </li>
              </ul>
            </div>
          </div>
        </div>

        <div class="border-t border-gray-200 px-4 py-6 sm:px-6">

          <div class="flex justify-between text-base font-sm text-gray-900">
            <p>Subtotal</p>
            <p class="font-medium">$122.00</p>
          </div>

          <div class="flex mt-5 justify-between text-base font-sm text-gray-900">
            <p>Shipping</p>
            <p class="font-medium">$5.00</p>
          </div>




        </div>

        <div class="border-t border-gray-200 px-4 py-6 sm:px-6">
          <div class="flex justify-between text-base font-medium text-gray-900">
            <p>Total</p>
            <p >$127.00</p>
          </div>
        </div>

        <div  class="border-t border-gray-200 px-4 py-1 sm:px-6">
          <div class="mt-6">
            <a href="#"
               class="flex items-center justify-center rounded-md border border-transparent bg-indigo-600 px-6 py-3 text-base font-medium text-white shadow-sm hover:bg-indigo-700">Confirm order</a>
          </div>
        </div>



      </div>
    </div>


  </main>
</template>

<style scoped>

</style>