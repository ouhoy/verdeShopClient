export function isAlphabetic(name: string) {
    return name.toString().replace(/\s/g, "").match(/^[a-zA-Z]+$/) !== null;
}
export function validateName(name: string, inputName: string) {

    // @ts-ignore
    if (!name.replaceAll(" ", "") || name.replaceAll(" ", "").length === 0) {
        return `${inputName} is required.`;

    }

    if (!isAlphabetic(name)) {
        return `${inputName} should only container alphabetical characters.`
    }

    return false
}