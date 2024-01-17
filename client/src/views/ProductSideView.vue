<script setup lang="ts">
import { PhotoIcon, UserCircleIcon } from '@heroicons/vue/24/solid'
import {ref, watch} from "vue";


const colors = ref<any[]>([])

colors.value = [];
const availableColors = ["green", "red", "blue", "purple", "orange", "orewood", "brown", "pink", "mauve", "black", "white"]
availableColors.forEach((availableColor, index) => {
  colors.value.push({name: `${availableColor}`, class: `bg-${availableColor}-600`, selectedClass: `ring-${availableColor}-600`, selected:false,id: index})

})

function handleColorChange(state:boolean) {

  if(state) return

  let selectedColors = 0;
  colors.value.forEach((color,index)=>{
    if(color.selected) selectedColors ++
  })

  if(selectedColors === 4) {
    for(let i =0; i< colors.value.length; i++) {
      if(colors.value[i].selected) {
        colors.value[i].selected = false;
        break;
      }
    }
  }
}

</script>

<template>
  <main class="mt-20 mx-auto max-w-7xl px-4 sm:px-6 lg:px-8 flex gap-20">
    <div class="mt-10">
      <div class="flex items-center justify-between">
        <h3 class="text-sm font-medium text-gray-900">Color</h3>
      </div>
      <div class="mt-4">
        <label id="headlessui-label-4" class="sr-only" role="none">Choose a color</label>
        <div class="grid grid-cols-4 gap-4 sm:grid-cols-8 lg:grid-cols-7 ">
          <div v-for="color in colors" :key="color.id">
            <label @click="handleColorChange(color.selected)"
                :class="[color.selected ? `${color.selectedClass} ${color.class} ring-offset-1 ring-2 text-white ` : ` ${color.class} text-gray-900`, 'group relative flex items-center justify-center rounded-full border  text-sm font-medium uppercase  focus:outline-none sm:flex-1 h-8 w-8  cursor-pointer']">
              <input type="checkbox" v-model="color.selected" class="hidden"/>

            </label>

          </div>
        </div>

      </div>

    </div>
  </main>
</template>
