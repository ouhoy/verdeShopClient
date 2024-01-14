import {defineStore} from 'pinia'
import type {OrderedProduct} from "../../types";
import {state} from "vue-tsc/out/shared";

export const useCartStore = defineStore('cart', {
    state: () => ({
        cart: [] as OrderedProduct[]
    }),
    actions: {
        addProduct(product: OrderedProduct) {

            const existingProductIndex = this.cart.findIndex((p) => p.id === product.id);

            if (existingProductIndex !== -1) {
                // Product with the same id already exists in the cart
                const existingProduct = this.cart[existingProductIndex];

                const matchingProduct = this.cart.find(
                    (p) => p.id === product.id && p.color === product.color && p.size === product.size
                );

                if (matchingProduct) {
                    // Product with the same id, color, and size found, increase quantity
                    matchingProduct.quantity += product.quantity;
                    matchingProduct.price += product.price * product.quantity
                } else {
                    // No matching product found, add the new product
                    this.cart.push(product);
                }
            } else {
                // Product with the same id doesn't exist at all, add it
                this.cart.push(product);
            }

        },
        removeProduct(index: number) {

            this.cart.splice(index, 1)

        }
    }
    ,
    getters: {
        getTotalPrice:(state)=> {
            return state.cart.reduce((total, product) => total + product.price, 0);
        }
    }

})