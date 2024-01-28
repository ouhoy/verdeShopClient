<script setup lang="ts">

import FormInput from "@/components/form/FormInput.vue";
import {reactive, ref} from "vue";
import {SERVER_URL} from "@/production";

const isPending = ref(false);
const orderNumber = ref(0);
const errors = reactive({order: ""});

const message = ref("");

function formatDateWithStatus(inputDate) {
  // Parse inputDate string to Date object
  const date = new Date(inputDate);

  // Current date
  const currentDate = new Date();

  // Calculate the difference in days
  // @ts-ignore
  const differenceInDays = Math.floor((currentDate - date) / (1000 * 60 * 60 * 24));

  // Format the date
  const formattedDate = date.toLocaleDateString('en-US', {month: 'long', day: 'numeric', year: 'numeric'});

  // Determine the status based on the criteria
  let status;
  if (differenceInDays < 1) {
    status = 'In preparation';
  } else if (differenceInDays >= 1 && differenceInDays <= 2) {
    status = 'Prepared';
  } else {
    status = 'Shipped';
  }

  // Return the formatted date with status
  return `Order placed on ${formattedDate} - ${status}`;
}

async function handleSubmit() {

  // @ts-ignore
  if (orderNumber.value === null || orderNumber.value === "") {
    errors.order = "Order number is required!";
    return
  }

  errors.order = "";
  const data = await fetch(`${SERVER_URL}/v1/orders/${orderNumber.value}`)
  const result = await data.json();

  if (result.hasOwnProperty('error')) {
    errors.order = "Order not found.";
    return;
  }
  const date = result.date;

  message.value = formatDateWithStatus(date)

}
</script>

<template>

  <div class="flex min-h-full flex-1 flex-col justify-center px-6 py-12 lg:px-8">
    <div class="sm:mx-auto sm:w-full sm:max-w-sm">

      <h2 class="mt-10 text-center text-2xl font-medium leading-9 tracking-tight text-gray-900 ">Track your order</h2>
    </div>

    <div class="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
      <div class="space-y-6">
        <FormInput v-model="orderNumber" label="Order number" placeholder="" type="number" :error="errors.order"/>


        <div>

          <button @click="handleSubmit" type="submit"
                  class="flex w-full justify-center rounded-md bg-primary-darker px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-primary-dark-hover focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-primary-dark">
            {{ isPending ? 'Looking for order...' : 'Look for order' }}
          </button>
        </div>
      </div>
      <p v-if="message" class="mt-6">{{ message }}</p>

    </div>
  </div>


</template>

<style scoped>

</style>