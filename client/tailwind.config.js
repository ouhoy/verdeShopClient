/** @type {import('tailwindcss').Config} */
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



      },
      fontFamily: {
        'kaisei-decol': ['Kaisei Decol', 'sans-serif'],
      },
    },
  },
  plugins: [      require('@tailwindcss/aspect-ratio'),require("@tailwindcss/forms")
  ],
}

