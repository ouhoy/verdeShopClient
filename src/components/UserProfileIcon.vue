<script setup lang="ts">

import {auth} from "@/firebase/config";
import UserIcon from "@/assets/icons/UserIcon.vue";
import {signOut} from "firebase/auth"

import {Menu, MenuButton, MenuItem, MenuItems} from '@headlessui/vue'
import {ChevronDownIcon} from '@heroicons/vue/20/solid'
import {useRouter} from "vue-router";
const router = useRouter()
async function handleLogout() {
  await signOut(auth)
  if (!auth.currentUser) {
    await router.push('/')
  }

}

</script>

<template>
  <div v-if="auth.currentUser" class="flex ml-4  justify-center items-center gap-2">
    <!--                                <p class="text-gray-950 font-medium text-sm ">Hi, {{auth.currentUser.displayName.split(" ")[0]}}</p>-->

    <Menu as="div" class="relative inline-block text-left">
      <div>
        <MenuButton
            class="inline-flex w-full justify-center gap-x-1.5 rounded-md bg-white px-3 py-2 text-sm font-semibold text-gray-900 hover:bg-gray-50">
          <UserIcon/>
          <!--          <ChevronDownIcon class="-mr-1 h-5 w-5 text-gray-400" aria-hidden="true" />-->
        </MenuButton>
      </div>

      <transition enter-active-class="transition ease-out duration-100" enter-from-class="transform opacity-0 scale-95"
                  enter-to-class="transform opacity-100 scale-100" leave-active-class="transition ease-in duration-75"
                  leave-from-class="transform opacity-100 scale-100" leave-to-class="transform opacity-0 scale-95">
        <MenuItems
            class="absolute right-0 z-10 mt-2 w-56 origin-top-right rounded-md bg-white shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none">
          <div class="py-1">
            <MenuItem v-slot="{ active }">
              <router-link :to="{name: 'profile'}"
                           :class="[active ? 'bg-gray-100 text-gray-900' : 'text-gray-700', 'block px-4 py-2 text-sm']">
                Profile
              </router-link>
            </MenuItem>
            <MenuItem v-slot="{ active }">
              <a href="#" :class="[active ? 'bg-gray-100 text-gray-900' : 'text-gray-700', 'block px-4 py-2 text-sm']">Order
                History</a>
            </MenuItem>


            <MenuItem v-slot="{ active }">
              <button @click="handleLogout" type="submit"
                      :class="[active ? 'bg-gray-100 text-gray-900' : 'text-gray-700', 'block w-full px-4 py-2 text-left text-sm']">
                Sign out
              </button>
            </MenuItem>
          </div>
        </MenuItems>
      </transition>
    </Menu>

  </div>

</template>

<style scoped>

</style>