import {ref} from "vue";
import {auth} from "@/firebase/config";

import {createUserWithEmailAndPassword, updateProfile} from "firebase/auth"

const errorMessage = ref("");
const isPending = ref(false);


async function signup(firstname: string, lastname:string, email: string, password: string) {
    errorMessage.value = ""
    isPending.value = true

    try {
        const res = await createUserWithEmailAndPassword(auth, email, password)
        if (!res) {
            throw new Error('Could not complete signup please try again')
        }


        // Set the user's displayName
        await updateProfile(res.user, {displayName: `${firstname} ${lastname}`})

        errorMessage.value = ""
        isPending.value = false
    } catch (err) {
        errorMessage.value = (err as Error).message
        isPending.value = false
    }
}

const useSignup = () => {
    return {errorMessage, isPending, signup}
}

export default useSignup