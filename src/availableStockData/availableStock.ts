import {ref} from "vue";
import type {Sizes} from "../../types";

export const sizes = ref<Sizes[]>([
    {name: 'XXS', inStock: true, selected: false},
    {name: 'XS', inStock: true, selected: false},
    {name: 'S', inStock: true, selected: false},
    {name: 'M', inStock: true, selected: false},
    {name: 'L', inStock: true, selected: false},
    {name: 'XL', inStock: true, selected: false},
    {name: 'XXL', inStock: true, selected: false},
    {name: 'XXXL', inStock: true, selected: false},
])


export const availableColors = ["green", "red", "blue", "purple", "orange", "orewood", "brown", "pink", "mauve", "black", "white"]

