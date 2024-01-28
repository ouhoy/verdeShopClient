<template>
  <div class="bg-white">
    <div class="mb-12">
      <h2 class="text-base font-semibold leading-7 text-gray-900">My order history</h2>
      <p class="mt-1 mb-6 text-sm leading-6 text-gray-600">Explore your order history for a comprehensive view of past
        purchases and transaction details.</p>

      <div v-if="orders" v-for="order in orders"
           class="mt-6 grid grid-cols-1 gap-x-6 gap-y-10 sm:grid-cols-2 lg:grid-cols-2 xl:gap-x-8">
        <div v-for="product in order.products" :key="product.id" class="group relative">
          <div
              class="aspect-h-1 aspect-w-1 w-full overflow-hidden rounded-md bg-gray-200 lg:aspect-none group-hover:opacity-75 lg:h-80">
            <img :src="product.imageSrc" :alt="product.name"
                 class="h-full w-full object-cover object-center lg:h-full lg:w-full"/>
          </div>
          <div class="mt-4 flex justify-between">
            <div>
              <h3 class="text-sm text-gray-700">
                <router-link :to="{name: 'product', params: {id: product.id}}" :href="product.href">
                  <span aria-hidden="true" class="absolute inset-0"/>
                  {{ product.name }}
                </router-link>
              </h3>
              <div class="flex justify-start gap-2 items-center w-full">
                <span class="mt-1 text-sm text-gray-500 capitalize">{{ product.color }} {{ product.size }},</span>
                <span class="mt-1 text-sm text-gray-500 capitalize">Qyt {{ product.quantity }}</span>

              </div>
            </div>
            <p class="text-sm font-medium text-gray-900">${{ product.price }}</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import {onMounted} from "vue";
import {SERVER_URL} from "@/production";
import {auth} from "@/firebase/config";
import {ref} from "vue";
import {CheckoutOrder} from "../../../types";

const products = [
  {
    id: 1,
    name: 'Basic Tee',
    href: '#',
    imageSrc: 'https://tailwindui.com/img/ecommerce-images/product-page-01-related-product-01.jpg',
    imageAlt: "Front of men's Basic Tee in black.",
    price: '$35',
    color: 'Black',
  },
  {
    id: 2,
    name: 'Basic Tee',
    href: '#',
    imageSrc: 'https://tailwindui.com/img/ecommerce-images/product-page-01-related-product-01.jpg',
    imageAlt: "Front of men's Basic Tee in black.",
    price: '$35',
    color: 'Black',
  },
  {
    id: 2,
    name: 'Basic Tee',
    href: '#',
    imageSrc: 'https://tailwindui.com/img/ecommerce-images/product-page-01-related-product-01.jpg',
    imageAlt: "Front of men's Basic Tee in black.",
    price: '$35',
    color: 'Black',
  },

]
const orders = ref<CheckoutOrder[]>([])


onMounted(async () => {
  try {
    const userData = await fetch(`${SERVER_URL}/v1/users/${auth.currentUser?.email}`);
    const userResult = await userData.json();

    const userId = userResult.id;
    const orderData = await fetch(`${SERVER_URL}/v1/orders/orderHistory/${userId}`);
    const orderResult = await orderData.json() as CheckoutOrder[];


    orderResult.forEach((order: any) => {

      const serializedOrder = {
        id: order.id,
        userId: order.userId,
        products: JSON.parse(order.products),
        address: JSON.parse(order.address),
        date: order.date,
        total: order.total

      };


      orders.value.push(serializedOrder)
      console.log(orders.value[0].products)
    })
  } catch (error) {

  }
})
</script>