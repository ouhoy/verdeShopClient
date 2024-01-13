enum Gender {
    Men = "MEN",
    Women = "WOMEN",
    Unisex = "UNISEX",
}

enum Type {
    Clothing = "CLOTHING",
    Shoes = "SHOES",
    Accessories = "ACCESSORIES"
}
type Size = "XXS" | "XS" | "S" | "M" | "L" | "XL" | "XXL" | "XXXL";

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

export interface Product {
    id:number,
    name: string,
    price: number,
    description: string,
    highlights: string[]
    details: string,
    imageSrc: string[],
    imageAlt: string,
    colors: string[],
    options: string[],
    gender: Gender,
    sizes:Size [],
    type: Type

}