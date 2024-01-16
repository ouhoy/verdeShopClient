// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import {getFirestore} from 'firebase/firestore';
import {getAuth} from "firebase/auth"
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
const firebaseConfig = {
    apiKey: "AIzaSyDrCar1tQAbY1nV1991FYFHSFqWeKs09Ns",
    authDomain: "verde-shop.firebaseapp.com",
    projectId: "verde-shop",
    storageBucket: "verde-shop.appspot.com",
    messagingSenderId: "956586666500",
    appId: "1:956586666500:web:aa70bc3ba531e62ab66351"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);

const db = getFirestore()
const auth = getAuth()

export {db, auth}