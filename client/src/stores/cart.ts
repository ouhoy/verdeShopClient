import {defineStore} from 'pinia'
import type {OrderedProduct} from "../../types";

export const useCartStore = defineStore('cart', {
    state: () => ({
        products: [] as OrderedProduct[]
    }),
    actions: {
        addProduct(product: OrderedProduct) {

            if (this.products.length === 0) {
                this.products.push(product)
                return
            }

            this.products.forEach((existingProduct: OrderedProduct, index: number) => {
                if (product.id === existingProduct.id) {

                    const sameColor = (product.color === existingProduct.color);
                    const sameSize = (product.size === existingProduct.size);

                    // If existing product
                    if (sameColor && sameSize) {
                        this.products[index].quantity++;
                    } else if (sameColor || sameSize) {
                        this.products[index].quantity++
                    } else {
                        this.products.push(product)
                    }

                }
            })
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