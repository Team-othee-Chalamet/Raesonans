import { get, put, del } from "../Scripts/fetchUtil.js"; // Import the utility functions

const API_URL = "http://localhost:8080/api/play";


// Check if user is logged in (replace with backend check)
function isLoggedIn() {
    return true; // or false
}


// GET all plays
async function fetchAllPlays() {
    try {
        const plays = await get(API_URL);
        renderPlays(plays);
    } catch (error) {
        console.error("Error fetching plays:", error);
    }
}

// Update (PUT) a play (only if logged in)
async function updatePlay(id, updatedPlay) {
    if (!isLoggedIn()) {
        alert("You must be logged in to update a play.");
        return;
    }

    try {
        await put(`${API_URL}/${id}`, updatedPlay);
        console.log("Play updated");
        fetchAllPlays(); // Refresh the list
    } catch (error) {
        console.error("Error updating play:", error);
    }
}

// Delete (DELETE) a play (only if logged in)
async function deletePlay(id) {
    if (!isLoggedIn()) {
        alert("You must be logged in to delete a play.");
        return;
    }

    try {
        await del(`${API_URL}/${id}`);
        console.log("Play deleted");
        fetchAllPlays(); // Refresh the list
    } catch (error) {
        console.error("Error deleting play:", error);
    }
}

// Render plays to the DOM
function renderPlays(plays, gridId) {
    const container = document.getElementById(gridId);
    container.innerHTML = "";

    plays.forEach(play => {
        const box = document.createElement("div");
        box.classList.add("play-box");

        box.innerHTML = `
            <h3>${play.title}</h3>
            <p>${play.description}</p>
        `;

        container.appendChild(box);
    });
}


//Fetch alle aktuelle plays
function fetchAllCommingPlays(){

}


//Fetch alle forrige plays
function fetchAllPastPlays(){
    
}



// Initialize
document.addEventListener("DOMContentLoaded", () => {


    // Hent alle plays

    const aktuelleItems = await fetchAktuellePlays();
    const tidligereItems = await fetchTidligerePlays();




    // Få alle plays efter dags dato og sæt dem in i aktuelleItems


    renderBoxes(aktuelleItems, "aktuelle-grid");



    // Få alle plays for dags dato og sæt dem ind i tidligereItems


    renderBoxes(tidligereItems, "tidligere-grid");

    

    
    
});
