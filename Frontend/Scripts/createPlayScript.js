import { createPlay } from "../API/playApi.js";

let credits = []; // lcreddit array
let reviews = [];

// sørger for man kan klikke på plus knappen og submit knappen
document.addEventListener("DOMContentLoaded", () => {
    const plusBtn = document.getElementById("plusKnap");
    const form = document.getElementById("createPlayForm");

    //kalder metoder når knapperne bliver klikket
    plusBtn.addEventListener("click", addCredit);
    addReviewBtn.addEventListener("click", addReview);
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
    alert(`Tilføjede credit: ${job + " " + name}`);
    console.log(credits);
    // clear fields
    document.getElementById("job").value = "";
    document.getElementById("navn").value = "";
}
// Tilføjer et review
function addReview() {
    const title = document.getElementById("reviewTitle").value;
    const actualScore = document.getElementById("actualScore").value;
    const maxScore = document.getElementById("maxScore").value;
    const reviewText = document.getElementById("reviewText").value;
    const sourceLink = document.getElementById("sourceLink").value;

    // Simpel validering (kræver mindst en titel og score)
    if (!title || !actualScore) {
        alert("Udfyld venligst mindst Titel og Score.");
        return;
    }

    reviews.push({
        title: title,
        actualScore: actualScore, 
        maxScore: maxScore || 6,  // Default til 6 hvis tom
        reviewText: reviewText,
        sourceLink: sourceLink
    });

    console.log("Reviews added:", reviews);
    alert(`Tilføjede anmeldelse: ${title}`);

    // Clear fields
    document.getElementById("reviewTitle").value = "";
    document.getElementById("actualScore").value = "";
    document.getElementById("maxScore").value = "";
    document.getElementById("reviewText").value = "";
    document.getElementById("sourceLink").value = "";
}

// Submit form poster en play
async function submitPlay(e) {
    e.preventDefault();

    const title = document.getElementById("titel").value;
    const description = document.getElementById("beskrivelse").value;

    const playDto = {
        title,
        description,
        creditDtos: credits,
        reviewDtos: reviews
    };
    console.log(playDto);
    //Poster
    try {
        const response = createPlay(playDto);
        console.log(response);
    } catch (error) {
        console.error("ERROR creating play:", error);
    }
  //  window.location.replace("../Pages/play.html");
}
