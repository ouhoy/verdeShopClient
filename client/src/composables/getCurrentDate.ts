export function getCurrentDate(): string {
    const currentDate = new Date();

    const options = { year: 'numeric', month: 'long', day: 'numeric' };

    // @ts-ignore
    return  currentDate.toLocaleDateString('en-US', options);


}
