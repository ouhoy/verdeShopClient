<script setup lang="ts">
import WarningIcon from "@/assets/icons/WarningIcon.vue";

const { label, type, placeholder, error, modelValue } = defineProps<{
  label: string;
  type: string;
  placeholder: string;
  error: string;
  modelValue: string | number;
}>();

const emit = defineEmits();
const onInput = (event: Event) => {
  const target = event.target as HTMLInputElement;
  emit('update:modelValue', target.value);
};

</script>

<template>

  <div>
    <label :for="label.trim().replace(' ', '').toLowerCase()" class="block text-sm font-medium leading-6 text-gray-900">{{ label }}</label>
    <div class="mt-2">
      <input  :value="modelValue"
              @input="onInput"
              :type="type"
              :name="label.trim().replace(' ', '').toLowerCase()"
              :id="label.trim().replace(' ', '').toLowerCase()"
              :placeholder="placeholder"

              class="outline-0 block w-full rounded-md border-0 pl-2 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-primary-darker sm:text-sm sm:leading-6" />
      <p v-if="error" class="mt-2 flex justify-start gap-1 items-center text-sm text-red-600 dark:text-red-500"> <span>{{error}}</span></p>

    </div>
  </div>

</template>

<style scoped>

</style>