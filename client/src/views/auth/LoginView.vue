<script setup lang="ts">

import {ref} from "vue";
import FormInput from "@/components/form/FormInput.vue";
import WarningIcon from "@/assets/icons/WarningIcon.vue";



const errors = ref({email: "", password:""})
const email = ref("")
const password = ref("");


</script>

<template>
  <div class="flex min-h-full flex-1 flex-col justify-center px-6 py-12 lg:px-8">
    <div class="sm:mx-auto sm:w-full sm:max-w-sm">
      <router-link :to="{name: 'home'}">
      <img class="mx-auto  w-auto" src="../../assets/images/logo.svg" alt="Your Company" />
      </router-link>
      <h2 class="mt-10 text-center text-2xl font-medium leading-9 tracking-tight text-gray-900 ">Sign in to your account</h2>
    </div>

    <div class="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
      <form class="space-y-6" >
        <FormInput :model-value="email" label="Email" placeholder="" type="email" :error="errors.email"/>

        <div>
          <div class="flex items-center justify-between">
            <label for="password" class="block text-sm font-medium leading-6 text-gray-900">Password</label>
            <div class="text-sm">
              <a href="#" class="font-semibold text-primary-dark-active hover:text-primary-dark-hover">Forgot password?</a>
            </div>
          </div>
          <div class="mt-2">
            <input v-model="password" id="password" name="password" type="password" autocomplete="current-password" required class="outline-0 block w-full rounded-md border-0 pl-2 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-primary-darker sm:text-sm sm:leading-6" />
            <p v-if="errors.password" class="mt-2 flex justify-start gap-1 items-center text-sm text-red-600 dark:text-red-500"><WarningIcon/> <span>{{errors.password}}</span></p>

          </div>
        </div>

        <div>
          <button type="submit" class="flex w-full justify-center rounded-md bg-primary-darker px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-primary-dark-hover focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-primary-dark">Sign in</button>
        </div>
      </form>

      <p class="mt-10 text-center text-sm text-gray-500">
        Not a member?
        {{ ' ' }}
        <router-link :to="{name: 'signup'}"  class="font-semibold leading-6  text-primary-dark-active hover:text-primary-dark-hover">Creat an account</router-link>
      </p>

    </div>
  </div>
</template>

<style lang="scss" >



</style>