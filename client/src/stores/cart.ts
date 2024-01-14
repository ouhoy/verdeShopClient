import {defineStore} from 'pinia'
import type {OrderedProduct} from "../../types";

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
                } else {
                    // No matching product found, add the new product
                    this.cart.push(product);
                }
            } else {
                // Product with the same id doesn't exist at all, add it
                this.cart.push(product);
            }

        },
        removeProduct(id: number) {

            this.products.forEach((existingProduct: OrderedProduct, index: number) => {
                if (existingProduct.id === id) {
                    this.products.splice(index, 1)
                }
            })

        }
    }

})