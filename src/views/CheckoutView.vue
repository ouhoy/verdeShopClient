<script setup>

import {useCartStore} from "@/stores/cart.ts";
import {auth} from "@/firebase/config.ts";
import {reactive, ref} from "vue";
import {getCurrentDate} from "@/composables/getCurrentDate.ts";
import FormInput from "@/components/form/FormInput.vue";
import axios from "axios";
// import {OrderedProduct} from "../../types/index.ts";

const shippingPrice = ref(5)
const errors = reactive({firstName: "", lastName: "", email: "", streetAddress: "", city: "", state: "", zipCode: ""})

const firstName = ref("");
const lastName = ref("");
const email = ref("");

const country = ref("Morocco");
const streetAddress = ref("");
const city = ref("");
const state = ref("");
const zipCode = ref("");

const storeCartProducts = useCartStore();

function handleRemove(product) {

  storeCartProducts.removeProduct(product);

}

async function handleSubmit() {

  const data = await fetch(`/api/v1/users/${auth.currentUser?.email}`)
  const currentUser = await data.json()
  const userId = currentUser.id;

  const address = {
    firstName: firstName.value,
    lastName: lastName.value,
    email: email.value,
    country: country.value,
    street:streetAddress.value,
    city: city.value,
    state: state.value,
    zip: zipCode.value,

  }

  const order = {
    userId,
    products: storeCartProducts.cart,
    address,
    date: getCurrentDate(),
    total: storeCartProducts.getTotalPrice + shippingPrice.value

  }
  axios.post(`/api/v1/orders/`, order).then(r=> {
    console.log("Order Posted")
  }).catch(e=> {
    console.log("Something went wrong!")
  })
  console.log(order)
}


</script>

<template>
  <main class="mt-20  mx-auto max-w-7xl px-4 sm:px-6 lg:px-8 flex flex-col lg:flex-row gap-20">
    <form>
      <div class="space-y-12">

        <div class="border-b border-gray-900/10 pb-12">
          <h2 class="text-base font-semibold leading-7 text-gray-900">Contact Information</h2>
          <p class="mt-1 text-sm leading-6 text-gray-600">Use a permanent address where you can receive mail.</p>

          <div class="mt-10 grid grid-cols-1 gap-x-6 gap-y-8 sm:grid-cols-6">
            <div class="sm:col-span-3">
              <FormInput v-model="firstName" label="First name" placeholder="" type="text" :error="errors.firstName"/>

            </div>

            <div class="sm:col-span-3">
              <FormInput v-model="lastName" label="Last name" placeholder="" type="text" :error="errors.lastName"/>

            </div>

            <div class="sm:col-span-4">
              <FormInput v-model="email" label="Email Address" placeholder="" type="email" :error="errors.email"/>

            </div>

            <div class="sm:col-span-3">
              <label for="country" class="block text-sm font-medium leading-6 text-gray-900">Country</label>
              <div class="mt-2">
                <select v-model="country" id="country" name="country" autocomplete="country-name"
                        class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:max-w-xs sm:text-sm sm:leading-6">
                  <option>Morocco</option>
                  <option>United Kingdom</option>
                </select>
              </div>
            </div>

            <div class="col-span-full">
              <FormInput v-model="streetAddress" label="Street Address" placeholder="" type="text"
                         :error="errors.streetAddress"/>

            </div>

            <div class="sm:col-span-2 sm:col-start-1">
              <FormInput v-model="city" label="City" placeholder="" type="text" :error="errors.city"/>

            </div>

            <div class="sm:col-span-2">
              <FormInput v-model="state" label="State / Province" placeholder="" type="text" :error="errors.state"/>

            </div>

            <div class="sm:col-span-2">
              <FormInput v-model="zipCode" label="Zip / Postal Code" placeholder="" type="text" :error="errors.zipCode"/>

            </div>
          </div>
        </div>


        <!--        <div class="border-b border-gray-900/10 pb-12">-->
        <!--          <h2 class="text-base font-semibold leading-7 text-gray-900">Delivery method</h2>-->
        <!--          <p class="mt-1 text-sm leading-6 text-gray-600">We'll always let you know about important changes, but you-->
        <!--            pick what else you want to hear about.</p>-->

        <!--          <div class="mt-0 space-y-10">-->
        <!--            <fieldset>-->
        <!--              <div class="mt-6 space-y-6">-->
        <!--                <div class="relative flex gap-x-3">-->
        <!--                  <div class="flex h-6 items-center">-->
        <!--                    <input id="standard" checked name="delivery-method" type="radio"-->
        <!--                           class="h-4 w-4  border-gray-300 text-indigo-600 focus:ring-indigo-600"/>-->
        <!--                  </div>-->

        <!--                  <div class="text-sm leading-6">-->
        <!--                    <label for="standard" class="font-medium text-gray-900">Standard: <b>$5</b> </label>-->
        <!--                    <p class="text-gray-500">4-10 business days</p>-->
        <!--                  </div>-->
        <!--                </div>-->

        <!--                <div class="relative flex gap-x-3">-->
        <!--                  <div class="flex h-6 items-center">-->
        <!--                    <input id="candidates" name="delivery-method" type="radio"-->
        <!--                           class="h-4 w-4  border-gray-300 text-indigo-600 focus:ring-indigo-600"/>-->
        <!--                  </div>-->
        <!--                  <div class="text-sm leading-6">-->
        <!--                    <label for="candidates" class="font-medium text-gray-900">Express: <b>$15</b></label>-->
        <!--                    <p class="text-gray-500">2-5 business days</p>-->
        <!--                  </div>-->
        <!--                </div>-->


        <!--              </div>-->
        <!--            </fieldset>-->
        <!--          </div>-->
        <!--        </div>-->


        <div class="border-b border-gray-900/10 pb-12">
          <h2 class="text-base font-semibold leading-7 text-gray-900">Payment method</h2>
          <p class="mt-1 text-sm leading-6 text-gray-600">Use a permanent address where you can receive mail.</p>

          <div class="mt-10 grid grid-cols-1 gap-x-6 gap-y-8 sm:grid-cols-6">


            <div class="sm:col-span-4">
              <label for="card-number" class="block text-sm font-medium leading-6 text-gray-900">Card number</label>
              <div class="mt-2">
                <input type="text" name="street-address" id="card-number" autocomplete="street-address"
                       class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"/>
              </div>
            </div>

            <div class="sm:col-span-2 sm:col-start-1">
              <label for="expiration" class="block text-sm font-medium leading-6 text-gray-900">Expiration</label>
              <div class="mt-2">
                <input type="text" name="expiration" id="expiration"
                       class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"/>
              </div>
            </div>

            <div class="sm:col-span-2">
              <label for="cvc" class="block text-sm font-medium leading-6 text-gray-900">CVC</label>
              <div class="mt-2">
                <input type="text" name="cvc" id="cvc"
                       class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"/>
              </div>
            </div>
          </div>
        </div>
      </div>
    </form>

    <form @submit.prevent="handleSubmit" class="pointer-events-auto w-screen max-w-md">
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
                          <router-link :to="{name: 'product', params: {id: product.id}}">{{
                              product.name
                            }}
                          </router-link>
                        </h3>
                        <p class="ml-4">${{ product.price }}</p>
                      </div>
                      <p class="mt-1 text-sm text-gray-500" style="text-transform: capitalize;">{{ product.color }},
                        {{ product.size }}</p>
                    </div>
                    <div class="flex flex-1 items-end justify-between text-sm">
                      <p class="text-gray-500">Qty {{ product.quantity }}</p>

                      <div class="flex">
                        <button @click="handleRemove(product)" type="button"
                                class="font-medium text-indigo-600 hover:text-indigo-500">
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
            <p class="font-medium">${{ storeCartProducts.getTotalPrice }}</p>
          </div>

          <div class="flex mt-5 justify-between text-base font-sm text-gray-900">
            <p>Shipping</p>
            <p class="font-medium">${{shippingPrice}}</p>
          </div>


        </div>

        <div class="border-t border-gray-200 px-4 py-6 sm:px-6">
          <div class="flex justify-between text-base font-medium text-gray-900">
            <p>Total</p>
            <p>${{storeCartProducts.getTotalPrice + shippingPrice}}</p>
          </div>
        </div>

        <div class="border-t border-gray-200 px-4 py-6 sm:px-6">
          <div>
            <button type="submit"
                    class="w-full flex items-center justify-center rounded-md border border-transparent bg-indigo-600 px-6 py-3 text-base font-medium text-white shadow-sm hover:bg-indigo-700">
              Confirm order
            </button>
          </div>
        </div>


      </div>
    </form>

  </main>
</template>

