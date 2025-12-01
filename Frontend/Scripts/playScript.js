


const form = document.getElementById("createPlayForm");


form.addEventListener("submit", async (e) => {
    e.preventDefault();

const formData = new FormData(form);

console.log(Object.fromEntries(formData.entries()) + "form data submitted")
});
