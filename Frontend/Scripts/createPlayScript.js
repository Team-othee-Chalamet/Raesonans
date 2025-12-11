import { createPlay } from "../API/playApi.js";

let credits = []; // lcreddit array

// sørger for man kan klikke på plus knappen og submit knappen
document.addEventListener("DOMContentLoaded", () => {
    const plusBtn = document.getElementById("plusKnap");
    const form = document.getElementById("createPlayForm");

    //kalder metoder når knapperne bliver klikket
    plusBtn.addEventListener("click", addCredit);
    form.addEventListener("submit", submitPlay);
});

// Tilføjer en kredit til credit[] arrayet
function addCredit() {
    const job = document.getElementById("job").value;
    const name = document.getElementById("navn").value;

    if (!job || !name) {
        alert("Udfyld både rolle og navn");
        return;
    }

    credits.push({ 
        role: job,  
        name: name
    });
    
    // clear fields
    document.getElementById("job").value = "";
    document.getElementById("navn").value = "";
}

// Submit form poster en play
async function submitPlay(e) {
    e.preventDefault();

    const title = document.getElementById("titel").value;
    const description = document.getElementById("beskrivelse").value;

    const playDto = {
        title,
        description,
        creditDtos: credits
    };
    console.log(playDto);
    //Poster
    try {
        const response = createPlay(playDto);
        console.log(response);
    } catch (error) {
        console.error("ERROR creating play:", error);
    }
    window.location.replace("../Pages/play.html");
}
