import { fetchAllPlays} from "../API/playApi.js"; // Import the utility functions

const API_URL = "http://localhost:8080/api/plays";


// Check if user is logged in (replace with backend check)
function isLoggedIn() {
    return true; // or false
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
    console.log(plays);
    plays.forEach(play => {
        const box = document.createElement("div");
        box.classList.add("play-box");

        box.innerHTML = `
            <h3>${play.title}</h3>
            <p>${play.description}</p>
        `;

        // Redirect on click og send play.id med
        box.addEventListener("click", () => {
            window.location.href = `playInformation.html?id=${play.id}`;
        });

        container.appendChild(box);
    });
}


function filterAktuellePlays(plays) {
    // filtrere
    return plays.filter(p => p.isActive === true); 
}

function filterTidligerePlays(plays) {
    return plays.filter(p => p.isActive === false);
}



// Initialize
document.addEventListener("DOMContentLoaded", async () => {
    
    // 1. Hent ALLE plays EEN gang (Fetch ALL plays ONCE)
    const allPlays = await fetchAllPlays(); 
    
    // 2. Filtrér lokalt (Filter locally)
    const aktuelleItems = filterAktuellePlays(allPlays);
    const tidligereItems = filterTidligerePlays(allPlays);

    // 3. Render
    renderPlays(aktuelleItems, "aktuelle-grid");
    renderPlays(tidligereItems, "tidligere-grid");
});


