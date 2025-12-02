
const performer_BASE = "http://localhost:8080/api/performers";


init();

function init(){
    fetchPerformer();
}





const form = document.getElementById("createPlayForm");


form.addEventListener("submit", async (e) => {
    e.preventDefault();

const formData = new FormData(form);

console.log("form data submitted")
});

//hi
const plusButton = document.getElementById("plusKnap");

form.addEventListener("click", async (e) => {
e.preventDefault();





})





async function fetchPerformer(){
const response = await fetch(performer_BASE);
if (!response.ok) throw new Error(`FEJLHTTP : ${response.status}`);

return response.json;
}

function renderPerformers(){

}


