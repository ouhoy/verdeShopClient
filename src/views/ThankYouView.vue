<script setup lang="ts">

</script>

<template>
  <div class="flex flex-col items-center justify-center min-h-screen">
    <svg
        xmlns="http://www.w3.org/2000/svg"
        width="24"
        height="24"
        viewBox="0 0 24 24"
        fill="none"
        stroke="currentColor"
        stroke-width="2"
        stroke-linecap="round"
        stroke-linejoin="round"
        class="h-12 w-12 text-green-500 dark:text-green-400"
    >
      <path d="M22 11.08V12a10 10 0 1 1-5.93-9.14"></path>
      <polyline points="22 4 12 14.01 9 11.01"></polyline>
    </svg>
    <h1 class="text-2xl font-bold text-gray-800 dark:text-gray-200 mt-4">Thank you for shopping with us!</h1>
    <p class="text-gray-600 dark:text-gray-400 mt-2">Your product will be shipped within 2 business days.</p>
    <router-link :to="{name: 'home'}"
        class="mt-6 inline-flex h-10 items-center justify-center rounded-md bg-primary-darker px-8 text-sm font-medium text-gray-50 shadow transition-colors hover:bg-primary-dark-hover focus-visible:outline-none focus-visible:ring-1 focus-visible:ring-gray-950 disabled:pointer-events-none disabled:opacity-50 dark:bg-gray-50 dark:text-gray-900 dark:hover:bg-gray-50/90 dark:focus-visible:ring-gray-300"
        href="#"
    >
      Back to Home
    </router-link>
  </div>
</template>

<style scoped>

</style>