<template>

  <main class="mx-auto max-w-7xl mt-16">
  <DangerDialog @takeAction="handleDelete" @toggle="updateDialogOpenState" :isOpen=dangerDialogOpen title="Delete Product" action="Delete">Are you sure you want to delete the product? All of
    your product data will be permanently removed. This action cannot be undone.
  </DangerDialog>
<MainButton @click="handleClick">Open</MainButton>
  </main>


</template>

<script setup lang="ts">
import DangerDialog from "@/components/dialogs/DangerDialog.vue";
import {ref} from "vue";
import MainButton from "@/components/buttons/MainButton.vue";

const dangerDialogOpen =ref(false)

function updateDialogOpenState(isOpen) {
  dangerDialogOpen.value = isOpen
}

function handleDelete(id) {
  console.log(id)
}

function handleClick() {
  dangerDialogOpen.value = true;
}

</script>