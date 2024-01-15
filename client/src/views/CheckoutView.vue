<script setup>

import {useCartStore} from "@/stores/cart.ts";
// import {OrderedProduct} from "../../types/index.ts";

const storeCartProducts = useCartStore();

function handleRemove(product){

  storeCartProducts.removeProduct(product);

}

</script>

<template>
  <main class="mt-20 mx-auto max-w-7xl px-4 sm:px-6 lg:px-8 flex gap-20">
    <form>
      <div class="space-y-12">

        <div class="border-b border-gray-900/10 pb-12">
          <h2 class="text-base font-semibold leading-7 text-gray-900">Contact Information</h2>
          <p class="mt-1 text-sm leading-6 text-gray-600">Use a permanent address where you can receive mail.</p>

          <div class="mt-10 grid grid-cols-1 gap-x-6 gap-y-8 sm:grid-cols-6">
            <div class="sm:col-span-3">
              <label for="first-name" class="block text-sm font-medium leading-6 text-gray-900">First name</label>
              <div class="mt-2">
                <input type="text" name="first-name" id="first-name" autocomplete="given-name" class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6" />
              </div>
            </div>

            <div class="sm:col-span-3">
              <label for="last-name" class="block text-sm font-medium leading-6 text-gray-900">Last name</label>
              <div class="mt-2">
                <input type="text" name="last-name" id="last-name" autocomplete="family-name" class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6" />
              </div>
            </div>

            <div class="sm:col-span-4">
              <label for="email" class="block text-sm font-medium leading-6 text-gray-900">Email address</label>
              <div class="mt-2">
                <input id="email" name="email" type="email" autocomplete="email" class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6" />
              </div>
            </div>

            <div class="sm:col-span-3">
              <label for="country" class="block text-sm font-medium leading-6 text-gray-900">Country</label>
              <div class="mt-2">
                <select id="country" name="country" autocomplete="country-name" class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:max-w-xs sm:text-sm sm:leading-6">
                  <option>United States</option>
                  <option>Canada</option>
                  <option>Mexico</option>
                </select>
              </div>
            </div>

            <div class="col-span-full">
              <label for="street-address" class="block text-sm font-medium leading-6 text-gray-900">Street address</label>
              <div class="mt-2">
                <input type="text" name="street-address" id="street-address" autocomplete="street-address" class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6" />
              </div>
            </div>

            <div class="sm:col-span-2 sm:col-start-1">
              <label for="city" class="block text-sm font-medium leading-6 text-gray-900">City</label>
              <div class="mt-2">
                <input type="text" name="city" id="city" autocomplete="address-level2" class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6" />
              </div>
            </div>

            <div class="sm:col-span-2">
              <label for="region" class="block text-sm font-medium leading-6 text-gray-900">State / Province</label>
              <div class="mt-2">
                <input type="text" name="region" id="region" autocomplete="address-level1" class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6" />
              </div>
            </div>

            <div class="sm:col-span-2">
              <label for="postal-code" class="block text-sm font-medium leading-6 text-gray-900">ZIP / Postal code</label>
              <div class="mt-2">
                <input type="text" name="postal-code" id="postal-code" autocomplete="postal-code" class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6" />
              </div>
            </div>
          </div>
        </div>


        <div class="border-b border-gray-900/10 pb-12">
          <h2 class="text-base font-semibold leading-7 text-gray-900">Delivery method</h2>
          <p class="mt-1 text-sm leading-6 text-gray-600">We'll always let you know about important changes, but you pick what else you want to hear about.</p>

          <div class="mt-0 space-y-10">
            <fieldset>
              <div class="mt-6 space-y-6">
                <div class="relative flex gap-x-3">
                  <div class="flex h-6 items-center">
                    <input id="standard" checked name="delivery-method" type="radio" class="h-4 w-4  border-gray-300 text-indigo-600 focus:ring-indigo-600" />
                  </div>

                  <div class="text-sm leading-6">
                    <label for="standard"  class="font-medium text-gray-900">Standard: <b>$5</b> </label>
                    <p class="text-gray-500">4-10 business days</p>
                  </div>
                </div>

                <div class="relative flex gap-x-3">
                  <div class="flex h-6 items-center">
                    <input id="candidates" name="delivery-method"  type="radio" class="h-4 w-4  border-gray-300 text-indigo-600 focus:ring-indigo-600" />
                  </div>
                  <div class="text-sm leading-6">
                    <label for="candidates" class="font-medium text-gray-900">Express: <b>$15</b></label>
                    <p class="text-gray-500">2-5 business days</p>
                  </div>
                </div>


              </div>
            </fieldset>
          </div>
        </div>


        <div class="border-b border-gray-900/10 pb-12">
          <h2 class="text-base font-semibold leading-7 text-gray-900">Payment method</h2>

          <div class="mt-0 space-y-10">

          </div>
        </div>


      </div>


    </form>

    <div class="pointer-events-auto w-screen max-w-md">
      <div class="flex  flex-col bg-white border-gray-200 border rounded	">
        <div class="flex-1 overflow-y-auto px-4 py-6 sm:px-6">
          <div class="flex items-start justify-between">
            <div class="text-lg font-medium text-gray-900">Order summary</div>

          </div>

          <div class="mt-8">
            <div class="flow-root">
              <ul role="list" class="-my-6 divide-y divide-gray-200">
                <li v-for="product in storeCartProducts.cart" :key="product.id" class="flex py-6">
                  <div class="h-24 w-24 flex-shrink-0 overflow-hidden rounded-md border border-gray-200">
                    <img :src="product.imageSrc" :alt="product.name"
                         class="h-full w-full object-cover object-center"/>
                  </div>

                  <div class="ml-4 flex flex-1 flex-col">
                    <div>
                      <div class="flex justify-between text-base font-medium text-gray-900">
                        <h3>
                          <router-link :to="{name: 'product', params: {id: product.id}}" >{{ product.name }}</router-link>
                        </h3>
                        <p class="ml-4">{{ product.price }}</p>
                      </div>
                      <p class="mt-1 text-sm text-gray-500" style="text-transform: capitalize;">{{ product.color }}, {{product.size}}</p>
                    </div>
                    <div class="flex flex-1 items-end justify-between text-sm">
                      <p class="text-gray-500">Qty {{ product.quantity }}</p>

                      <div class="flex">
                        <button @click="handleRemove(product)" type="button" class="font-medium text-indigo-600 hover:text-indigo-500">
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
            <p class="font-medium">${{storeCartProducts.getTotalPrice}}</p>
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

        <div  class="border-t border-gray-200 px-4 py-6 sm:px-6">
          <div >
            <a href="#"
               class="flex items-center justify-center rounded-md border border-transparent bg-indigo-600 px-6 py-3 text-base font-medium text-white shadow-sm hover:bg-indigo-700">Confirm order</a>
          </div>
        </div>



      </div>
    </div>

  </main>
</template>

