import {ref} from "vue";
import {auth} from "@/firebase/config";

import {signInWithEmailAndPassword} from "firebase/auth"

const errorMessage = ref("");
const isPending = ref(false);


async function login(email: string, password: string) {
    errorMessage.value = ""
    isPending.value = true

    try {
        const res = await signInWithEmailAndPassword(auth, email, password)
        if (!res) {
            throw new Error('Could not login please try again')
        }
        errorMessage.value = ""
        isPending.value = false
    } catch (err) {
        errorMessage.value = (err as Error).message
        isPending.value = false
    }
}

const useLogin = () => {
    return { errorMessage, isPending, login }
}

export default useLogin