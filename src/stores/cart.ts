import {defineStore} from 'pinia'
import type {OrderedProduct} from "../../types";

// @ts-ignore
import Cookies from 'js-cookie';

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
            this.saveCartToCookie();
        },
        removeProduct(productToRemove: OrderedProduct) {


            this.cart.forEach((product, index) => {

                const sameId = productToRemove.id === product.id;
                const sameColor = productToRemove.color === product.color;
                const sameSize = productToRemove.size === product.size;
                const sameQuantity = productToRemove.quantity === product.quantity;

                if (sameId && sameColor && sameSize && sameQuantity) {
                    this.cart.splice(index, 1)

                }
            })

            this.saveCartToCookie();


        },
        saveCartToCookie() {
            Cookies.set('cart', JSON.stringify(this.cart), { expires: 7 }); // Adjust the expiry as needed
        },
        loadCartFromCookie() {
            const cartData = Cookies.get('cart');
            if (cartData) {
                this.cart = JSON.parse(cartData);
            }
        },
    }
    ,
    getters: {
        getTotalPrice: (state) => {
            return state.cart.reduce((total, product) => total + product.price, 0);
        }
    },


})