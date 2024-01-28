<script setup lang="ts">

import MainButton from "@/components/buttons/MainButton.vue";
import {onMounted, reactive, ref} from "vue";
import {validateName} from "@/composables/useStringValidaitons";
import FormInput from "@/components/form/FormInput.vue";
import axios from "axios";
import {auth} from "@/firebase/config";
import CheckMarkIcon from "@/assets/icons/CheckMarkIcon.vue";
import LoadingIcon from "@/assets/icons/LoadingIcon.vue";
import {SERVER_URL} from "@/production";

const errors = reactive({email: "", password: "", firstname: "", lastname: ""})


const firstName = ref("");
const lastName = ref("")
const email = ref("");
const password = ref("")
const userUpdated = ref(false);
const isPending = ref(false);

onMounted(async () => {
  userUpdated.value = false;

  try {
    const data = await fetch(`${SERVER_URL}/v1/users/${auth.currentUser?.email}`)
    const result = await data.json()
    firstName.value = result.firstName;
    lastName.value = result.lastName;
    email.value = result.email;

  } catch (error) {
    console.log("error getting user")
    console.log(error)
  }

  console.log(auth.currentUser?.email)
})

async function handleSubmit() {
  userUpdated.value = false;

  validateName(firstName.value, "First name") ?
      (errors.firstname = validateName(firstName.value, "First name").toString()) : (errors.firstname = "");


  validateName(lastName.value, "Last name") ?
      (errors.lastname = validateName(lastName.value, "Last name").toString()) : (errors.lastname = "");


  !email.value ? (errors.email = "Email is required.") : (errors.email = "");


  if (!errors.password && !errors.email && !errors.firstname && !errors.lastname) {
    const userInfo = {
      firstName: firstName.value,
      lastName: lastName.value,
      email: email.value,
      // TODO: This should be handled in the backend
      userType: "SHOPPER"
    }

    isPending.value = true;
    const data = await fetch(`${SERVER_URL}/v1/users/${auth.currentUser?.email}`)
    const currentUser = await data.json();


    await axios.put(`${SERVER_URL}/v1/users/${currentUser.id}`, userInfo).then(result => {
      userUpdated.value = true;
      isPending.value = false;

    }).catch(error => {

      console.log(error)
      userUpdated.value = false;
      isPending.value = false;

    })

  }

}


</script>

<template>
  <form class="mb-24" @submit.prevent="handleSubmit">
    <div class="space-y-12">


      <div class="border-b border-gray-900/10 pb-12">
        <h2 class="text-base font-semibold leading-7 text-gray-900">Personal Information</h2>
        <p class="mt-1 text-sm leading-6 text-gray-600">Use a permanent address where you can receive your orders.</p>

        <div class="mt-10 grid grid-cols-1 gap-x-6 gap-y-8 sm:grid-cols-6">
          <div class="sm:col-span-3">
            <FormInput v-model="firstName" label="First name" placeholder="" type="text" :error="errors.firstname"/>

          </div>

          <div class="sm:col-span-3">
            <FormInput v-model="lastName" label="Last name" placeholder="" type="text" :error="errors.lastname"/>

          </div>

          <div class="sm:col-span-4">
            <FormInput v-model="email" label="Email Address" placeholder="" type="email" :error="errors.email"/>

          </div>

          <div class="sm:col-span-4">
            <label :for="'Password'.trim().replace(' ', '').toLowerCase()"
                   class="block text-sm font-medium leading-6 text-gray-900">{{ 'Password' }}</label>
            <div class="mt-2 relative cursor-not-allowed">

              <input v-model="password" id="password" name="password" type="password" autocomplete="current-password"
                     required disabled
                     class="cursor-not-allowed outline-0 block w-full rounded-md border-0 pl-2 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-primary-darker sm:text-sm sm:leading-6"/>

              <!-- Banned icon -->
              <div class="absolute inset-y-0 right-0 flex items-center pr-2 pointer-events-none">
                <svg class="h-5 w-5 text-red-500" fill="none" stroke="currentColor" viewBox="0 0 24 24"
                     xmlns="http://www.w3.org/2000/svg">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                        d="M6 18L18 6M6 6l12 12"></path>
                </svg>
              </div>

            </div>


          </div>

        </div>
        <p class="mt-1 pt-2 text-sm leading-6 text-gray-600">Future updates will enable this feature for enhanced user
          experience and security enhancements.</p>
        <p v-if="userUpdated" class="mt-1 pt-2 text-sm text-green-500 flex justify-start items-center gap-1">
          <CheckMarkIcon/>
          Profile information have been updated!
        </p>
      </div>

    </div>

    <div class="mt-6 flex items-center justify-end gap-x-6">
      <MainButton v-if="isPending">
        <LoadingIcon/>
        Saving...
      </MainButton>
      <MainButton v-if="!isPending">Save</MainButton>
    </div>
  </form>

</template>

<style scoped>

</style>