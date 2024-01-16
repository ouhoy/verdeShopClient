<script setup lang="ts">

import FormInput from "@/components/form/FormInput.vue";
import {reactive, ref} from "vue";
import {validateName} from "@/composables/useStringValidaitons";
import axios from "axios";
import useSignup from "@/composables/auth/useSignup";
import {useRouter} from "vue-router";

const errors = reactive({email: "", password: "", firstname: "", lastname: ""})
const email = ref("")
const firstName = ref("")
const lastName = ref("")
const password = ref("");

const router = useRouter()


const {errorMessage, isPending, signup} = useSignup();

function handleSubmit() {

  validateName(firstName.value, "First name") ?
      (errors.firstname = validateName(firstName.value, "First name").toString()) : (errors.firstname = "");


  validateName(lastName.value, "Last name") ?
      (errors.lastname = validateName(lastName.value, "Last name").toString()) : (errors.lastname = "");


  !email.value ? (errors.email = "Email is required.") : (errors.email = "");

  (password.value.length < 6) ? (errors.password = "Password should be more than 6 characters.") : (errors.password = "")

  console.log(password.value)

  if (!errors.password && !errors.email && !errors.firstname && !errors.lastname) {

    const createdUser = {
      firstName: firstName.value,
      lastName: lastName.value,
      email: email.value,
      // TODO: This should be handled in the backend
      userType: "SHOPPER"
    }


    axios.post("http://localhost:8080/v1/users/", createdUser).then(async () => {

      isPending.value = true;

      await signup(firstName.value, lastName.value, email.value, password.value)
      await router.push('/')

    }).catch(error => {
      isPending.value = false;
      console.error('Error sending data to the backend:', error);
    });
  }
}

</script>

<template>

  <div class="flex min-h-full flex-1 flex-col justify-center px-6 py-12 lg:px-8">
    <div class="sm:mx-auto sm:w-full sm:max-w-sm">
      <router-link :to="{name: 'home'}">
        <img class="mx-auto  w-auto" src="../../assets/images/logo.svg" alt="Your Company"/>
      </router-link>
      <h2 class="mt-10 text-center text-2xl font-medium leading-9 tracking-tight text-gray-900 ">Create your
        account</h2>
    </div>

    <div class="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">

      <form @submit.prevent="handleSubmit" class="space-y-6">

        <div class="flex gap-3">
          <FormInput v-model="firstName" label="First name" placeholder="" type="text" :error="errors.firstname"/>
          <FormInput v-model="lastName" label="Last name" placeholder="" type="text" :error="errors.lastname"/>
        </div>
        <FormInput v-model="email" label="Email" placeholder="" type="email" :error="errors.email"/>
        <FormInput v-model="password" label="Password" placeholder="" type="password" :error="errors.password"/>

        <div>
          <button type="submit"
                  class="flex w-full justify-center rounded-md bg-primary-darker px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-primary-dark-hover focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-primary-dark">
            {{isPending? 'Signing Up': 'Sign Up'}}
          </button>
        </div>
      </form>
      <p class="mt-10 text-center text-sm text-gray-500">
        Already a member?
        {{ ' ' }}
        <router-link :to="{name: 'login'}"
                     class="font-semibold leading-6  text-primary-dark-active hover:text-primary-dark-hover">Login to
          your account
        </router-link>
      </p>

    </div>

  </div>


</template>

<style scoped>

</style>