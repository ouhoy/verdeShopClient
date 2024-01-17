<script setup lang="ts">
import { PhotoIcon, UserCircleIcon } from '@heroicons/vue/24/solid'
import {ref, watch} from "vue";
import ShippingAddress from "@/components/form/ShippingAddress.vue";
import UserDetailsForm from "@/components/form/UserDetailsForm.vue";
const selected = ref("profile");

watch(selected, ()=>{
  console.log("Changed!")
})

</script>

<template>
  <main class="mb-24 mx-auto max-w-7xl px-4 sm:px-6 lg:px-8 flex gap-20">
    <div class="w-full relative">
      <!--   COVER   -->
      <div class="w-full h-64 bg-gradient-to-r from-purple-400 via-pink-500 to-red-500 p-6"></div>

      <!--   USER PROFILE   -->
      <div class="profile-icon rounded-full bg-gray-200 w-32 h-32 absolute"></div>
      <div>
        <p class="text-center text-lg font-medium text-gray-950 mt-24">Abdallah DAHMOU</p>

      </div>
    </div>


  </main>
  <main class="mt-20 mx-auto max-w-7xl px-4 sm:px-6 lg:px-8 flex gap-20">

    <div class="flex flex-col justify-start items-start gap-6">

      <div @click="selected='profile'" :class="{'bg-gray-50': selected==='profile'}" class="cursor-pointer hover:bg-gray-50 p-2 rounded-lg">
        <h2 class="text-base  font-semibold leading-7 text-gray-900">Profile</h2>
        <p class="mt-1 text-sm leading-6 text-gray-600">This information can be modified on your next checkout.</p>
      </div>
      <div @click="selected='shipping-address'" :class="{'bg-gray-50': selected==='shipping-address'}" class="cursor-pointer hover:bg-gray-50 p-2 rounded-lg">
        <h2 class="text-base  font-semibold leading-7 text-gray-900">Shipping Address</h2>
        <p class="mt-1 text-sm leading-6 text-gray-600">This information can be modified on your next checkout.</p>
      </div>
      <div @click="selected='order-history'" :class="{'bg-gray-50': selected==='order-history'}" class="cursor-pointer hover:bg-gray-50 p-2 rounded-lg" >
        <h2 class="text-base font-semibold leading-7 text-gray-900">Order History</h2>
        <p class="mt-1 text-sm leading-6 text-gray-600">This information can be modified on your next checkout.</p>
      </div>
    </div>

    <UserDetailsForm v-if="selected == 'profile'" />
    <ShippingAddress v-if="selected==='shipping-address'"/>

  </main>


</template>

<style scoped>
.profile-icon {
  top: 192px;
  left: 45%;
}
</style>