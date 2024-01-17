<template>

  <Cart @click="handleClick"  :is-open="cartOpen" @toggle="updateCartState"/>


  <div class="bg-white">
    <!-- Mobile menu -->
    <TransitionRoot as="template" :show="open">
      <Dialog as="div" class="relative z-40 lg:hidden" @close="open = false">
        <TransitionChild as="template" enter="transition-opacity ease-linear duration-300" enter-from="opacity-0"
                         enter-to="opacity-100" leave="transition-opacity ease-linear duration-300"
                         leave-from="opacity-100" leave-to="opacity-0">
          <div class="fixed inset-0 bg-black bg-opacity-25"/>
        </TransitionChild>

        <div class="fixed inset-0 z-40 flex">
          <TransitionChild as="template" enter="transition ease-in-out duration-300 transform"
                           enter-from="-translate-x-full" enter-to="translate-x-0"
                           leave="transition ease-in-out duration-300 transform" leave-from="translate-x-0"
                           leave-to="-translate-x-full">
            <DialogPanel class="relative flex w-full max-w-xs flex-col overflow-y-auto bg-white pb-12 shadow-xl">

              <div class="flex items-center justify-between px-4 py-2">
                <h1 class="text-gray-950 font-kaisei-decol text-2xl font-bold	pb-1">Verde</h1>

                <button type="button"
                        @click="open = false"
                        class="-mr-2 flex h-10 w-10 items-center justify-center rounded-md bg-white p-2 text-gray-400">
                  <span class="sr-only">Close menu</span>
                  <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
                       stroke="currentColor" aria-hidden="true" data-slot="icon" class="h-6 w-6">
                    <path stroke-linecap="round" stroke-linejoin="round" d="M6 18 18 6M6 6l12 12"></path>
                  </svg>
                </button>
              </div>

              <div class="space-y-6 border-t border-gray-200 px-4 py-6">
                <div class="flow-root">
                  <a class="-m-2 block p-2 font-medium text-gray-900">Women</a>
                  <a class="-m-2 block p-2 font-medium text-gray-900">Men</a>
                  <router-link :to="{name: 'collection'}" class="-m-2 block p-2 font-medium text-gray-900">Collection
                  </router-link>
                  <a href="#" class="-m-2 block p-2 font-medium text-gray-900">Story</a>
                  <a href="#" class="-m-2 block p-2 font-medium text-gray-900">Track order</a>
                </div>
              </div>

              <div class="space-y-6 border-t border-gray-200 px-4 py-6">
                <div class="flow-root">
                  <a href="#" class="-m-2 block p-2 font-medium text-gray-900">Sign in</a>
                </div>
                <div class="flow-root">
                  <a href="#" class="-m-2 block p-2 font-medium text-gray-900">Create account</a>
                </div>
              </div>


            </DialogPanel>
          </TransitionChild>
        </div>
      </Dialog>
    </TransitionRoot>

    <header class="relative bg-white">

<!--      <div class="w-full bg-gray-50 h-10 flex items-center">-->
<!--        <div class="flex justify-end items-center w-full mx-auto max-w-7xl px-4 sm:px-6 lg:px-8">-->
<!--          <div class="flex  justify-center items-center gap-2">-->
<!--            <p class="text-gray-950 font-medium text-sm ">Hi, {{auth.currentUser.displayName.split(" ")[0]}}</p>-->
<!--            <UserIcon/>-->
<!--          </div>-->
<!--        </div>-->
<!--      </div>-->
<!--      <p class="flex h-10 items-center justify-center bg-primary-dark px-4 text-sm font-medium text-white sm:px-6 lg:px-8">-->
<!--        Get free delivery on orders over $100</p>-->

      <nav aria-label="Top" class="mx-auto max-w-7xl px-4 sm:px-6 lg:px-8">
        <div class="border-b border-gray-200">
          <div class="flex h-16 items-center">
            <button type="button" class="relative rounded-md bg-white p-2 text-gray-400 lg:hidden" @click="open = true">
              <span class="absolute -inset-0.5"/>
              <span class="sr-only">Open menu</span>
              <Bars3Icon class="h-6 w-6" aria-hidden="true"/>
            </button>

            <!-- Logo -->

            <div class="ml-4 flex lg:ml-0">
              <router-link :to="{name: 'home'}">
                <span class="sr-only">Verde</span>
                <h1 class="text-gray-950 font-kaisei-decol text-2xl font-bold	pb-1">Verde</h1>
              </router-link>
            </div>

            <!-- Flyout menus -->
            <PopoverGroup class="hidden lg:ml-8 lg:block lg:self-stretch">
              <div class="flex h-full space-x-8">
                <a href="#" class="flex items-center text-sm font-medium text-gray-900 hover:text-gray-800">Men</a>
                <a href="#" class="flex items-center text-sm font-medium text-gray-900 hover:text-gray-800">Women</a>
                <router-link class="flex items-center text-sm font-medium text-gray-900 hover:text-gray-800"
                             :to="{name: 'collection'}">Collection
                </router-link>
                <a href="#" class="flex items-center text-sm font-medium text-gray-900 hover:text-gray-800">Story</a>
                <a href="#" class="flex items-center text-sm font-medium text-gray-900 hover:text-gray-800">Track order</a>
              </div>

            </PopoverGroup>

            <div  class="ml-auto flex items-center">
              <div v-if="!auth.currentUser" class="hidden lg:flex lg:flex-1 lg:items-center lg:justify-end lg:space-x-6">

                <router-link :to="{name: 'login'}" class="text-sm font-medium text-gray-700 hover:text-gray-800">
                  Sign in
                </router-link>
                <span class="h-6 w-px bg-gray-200" aria-hidden="true" />

                <router-link :to="{name: 'signup'}" class="text-sm font-medium text-gray-700 hover:text-gray-800">
                  Create Account
                </router-link>
              </div>

              <!-- Cart -->
              <UserProfileIcon/>

              <div @click="toggleCart" class="ml-4 flow-root lg:ml-6 ">
                <a href="#" class="group -m-2 flex items-center p-2">
                  <ShoppingBagIcon class="h-6 w-6 flex-shrink-0 text-primary-darker group-hover:text-gray-500 "
                                   aria-hidden="true"/>
                  <span class="ml-2 text-sm font-medium text-gray-700 group-hover:text-gray-800">{{storeCartProducts.cart.length}}</span>
                  <span class="sr-only">items in cart, view bag</span>
                </a>
              </div>


            </div>
          </div>
        </div>
      </nav>
    </header>
  </div>
</template>

<script setup>
import {ref} from 'vue'
import {
  Dialog,
  DialogPanel,
  PopoverGroup,
  TransitionChild,
  TransitionRoot,
  DialogTitle
} from '@headlessui/vue'
import {Bars3Icon, ShoppingBagIcon, XMarkIcon} from '@heroicons/vue/24/outline'
import Cart from "@/components/Cart.vue";

import {useCartStore} from "@/stores/cart";

import {auth} from "@/firebase/config";
import UserIcon from "@/assets/icons/UserIcon.vue";
import UserProfileIcon from "@/components/UserProfileIcon.vue";

console.log("Current User: ",auth.currentUser)

const storeCartProducts = useCartStore();


const open = ref(false)
const cartOpen = ref(false);

const toggleCart = () => {
  cartOpen.value = !cartOpen.value;
};

const updateCartState = (isOpen) => {
  cartOpen.value = isOpen;
};

function handleClick(){
  cartOpen.value = false;
}

</script>