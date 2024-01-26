enum Gender {
    Men = "MEN",
    Women = "WOMEN",
    Unisex = "UNISEX",
}

export interface User {

    firstName: string,
    lastName: string,
    email: string,
    id: number

}

enum Type {
    Clothing = "CLOTHING",
    Shoes = "SHOES",
    Accessories = "ACCESSORIES"
}

type Size = "XXS" | "XS" | "S" | "M" | "L" | "XL" | "XXL" | "XXXL";

export interface Sizes {
    name: Size,
    inStock?: boolean,
    selected?: boolean
}

export type ColorItem = {
    value: string;
    label: string;
    checked: boolean;
};

export type ColorSelection = {
    name: string,
    class: string,
    selectedClass: string
    id: number
}

export type AvailableColor = {
    name: string,
    class: string,
    selectedClass: string
    id: number,
    selected: boolean
}

export interface Address {
    firstName: string,
    lastName: string,
    email: string,
    street: string,
    country: string,
    city: string,
    state: string,
    zip: string
}

export interface CheckoutOrder {
    id: number,
    userId: number,
    total: number,
    products: OrderedProduct[],
    address: Address,
    date: string,

}

export interface Product {
    id: number,
    name: string,
    price: number,
    description: string,
    highlights: string[]
    details: string,
    thumbnail: string,
    imageSrc: string[],
    imageAlt: string,
    colors: string[],
    options: string[],
    gender: Gender,
    sizes: Size [],
    type: Type

}

export interface OrderedProduct {
    id: number,
    name: string,
    price: number,
    quantity: number,
    href: string,
    imageSrc: string,
    color: string,
    size: string

}

export interface Breadcrumb {
    id: number,
    name: string,
    href: string
}