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

  (password.value.length < 6) ? (errors.password = "Password should be more than 6 characters.") : (errors.password = "")

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
            <FormInput v-model="password" label="Password" placeholder="" type="password" :error="errors.password"/>

          </div>

        </div>
        <p class="mt-1 pt-2 text-sm leading-6 text-gray-600">We'll always let you know about important changes, but you
          pick what else you want to hear about.</p>
        <p v-if="userUpdated" class="mt-1 pt-2 text-sm text-green-500 flex justify-start items-center gap-1">
          <CheckMarkIcon/>
          Profile info has been updated!
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