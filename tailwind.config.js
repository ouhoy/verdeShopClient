/** @type {import('tailwindcss').Config} */
const colors = require('tailwindcss/colors')

export default {
  content: [],
  purge: ['./index.html', './src/**/*.{vue,js,ts,jsx,tsx}'],
  theme: {
    extend: {
      gridTemplateRows: {
        '[auto,auto,1fr]': 'auto auto 1fr',
      },
      colors:{
        'primary-light': "#f6f6f4",
        "primary-light-hover": "#f1f2ee",
        "primary-light-active": "#e2e4dc",
        "primary": "#a3a78d",
        "primary-hover": "#93967f",
        "primary-active": "#828671",
        "primary-dark": "#7a7d6a",
        "primary-dark-hover": "#626455",
        "primary-dark-active": "#494b3f",
        "primary-darker": "#393a31",
        "orewood-600": "#d6d3d1",
        "orewood-500": "#d6d3d1",
        "orewood-50": "#eaeaea",
        "mauve-600": "#9d174d",
        "mauve-500": "#9d174d",
        "mauve-50": "#ffc5dc",
        "pink-600": "#ec4899",
        "pink-500": "#ec4899",
        "pink-50": "#ffc3df",
        "brown-600": "#431407",
        "brown-50": "#ffcec0",
        "brown-500": "#622817",
        "black-600": "#000",
        ...colors,



      },
      fontFamily: {
        'kaisei-decol': ['Kaisei Decol', 'sans-serif'],
      },
    },
  },
  plugins: [      require('@tailwindcss/aspect-ratio'),require("@tailwindcss/forms")
  ],
}

