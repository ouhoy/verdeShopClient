<script setup lang="ts">

import {onMounted, ref} from "vue";
import {SERVER_URL} from "@/production";
import type {User} from "../../../types";

const users = ref<User[]>([])

onMounted(async () => {
  const data = await fetch(`${SERVER_URL}/v1/users/`)
  users.value = await data.json() as User[];

})

</script>

<template>

  <div class="relative w-full overflow-auto">
    <table class="w-full caption-bottom text-sm">
      <thead class="[&amp;_tr]:border-b">
      <tr class="border-b transition-colors hover:bg-muted/50 data-[state=selected]:bg-muted">
        <th class="h-12 px-4 text-left align-middle font-medium text-muted-foreground [&amp;:has([role=checkbox])]:pr-0 w-[200px]">
          ID
        </th>
        <th class="h-12 px-4 text-left align-middle font-medium text-muted-foreground [&amp;:has([role=checkbox])]:pr-0 w-[300px]">
          Email
        </th>
        <th class="h-12 px-4 text-left align-middle font-medium text-muted-foreground [&amp;:has([role=checkbox])]:pr-0 w-[100px]">
          Name
        </th>
      </tr>
      </thead>
      <tbody  class="[&amp;_tr:last-child]:border-0">
      <tr v-for="user in users" class="border-b transition-colors hover:bg-muted/50 data-[state=selected]:bg-muted">
        <td class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0 font-medium">{{user.id}}</td>
        <td class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0">{{ user.email }}</td>
        <td class="p-4 align-middle [&amp;:has([role=checkbox])]:pr-0">{{user.firstName}} {{user.lastName}}</td>
      </tr>
    
      </tbody>
    </table>
  </div>
</template>

<style scoped>

</style>