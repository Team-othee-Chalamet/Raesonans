import { post } from "../Scripts/fetchUtil.js";

const BASE_URL = "http://localhost:8080/api/plays";

let credits = []; // lcreddit array

// Create new Play 
export async function createPlay(BASE_URL, playDto) {
    return post(BASE_URL, playDto, { "Content-Type": "application/json" });
}


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
        alert("Udfyld både job og navn");
        return;
    }

    credits.push({ job, name });
    
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
    //Poster
    try {
        const response = createPlay(BASE_URL, playDto);
        console.log(response);
    } catch (error) {
        console.error("ERROR creating play:", error);
    }
    window.location.replace("../Pages/play.html")
}
