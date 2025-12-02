import { get, put, del } from "../Scripts/fetchUtil.js"; // Import the utility functions

const API_URL = "http://localhost:8080/api/play";

// Mock data
const aktuelleItems = [
    { title: "NEMT", description: "Hvordan bliver presset til at presse det stort...", image: "path/to/image1.jpg" },
    { title: "Sendebud fra Armenien", description: "Hvordan man overlever som sandebud...", image: "path/to/image2.jpg" },
];

const tidligereItems = [
    { title: "RODET", description: "Livet kan være rødt, og nogle gange...", image: "path/to/image3.jpg" },
    { title: "MIKKEL", description: "Mikkel Mørk er i Ballerup, og han vil ikke...", image: "path/to/image4.jpg" },
];

// Check if user is logged in (replace with backend check)
function isLoggedIn() {
    return true; // or false
}

// Render boxes
function renderBoxes(items, gridId) {
    const grid = document.getElementById(gridId);
    items.forEach(item => {
        const box = document.createElement("div");
        box.className = `box ${isLoggedIn() ? 'logged-in' : ''}`;
        box.innerHTML = `
            <img src="${item.image}" alt="${item.title}">
            <button class="rediger-btn">Rediger</button>
            <div class="box-content">
                <h3>${item.title}</h3>
                <p>${item.description}</p>
            </div>
        `;
        grid.appendChild(box);
    });
}

// Read (GET) all plays
async function fetchPlays() {
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
        fetchPlays(); // Refresh the list
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
        fetchPlays(); // Refresh the list
    } catch (error) {
        console.error("Error deleting play:", error);
    }
}

// Render plays to the DOM
function renderPlays(plays) {
    const container = document.getElementById("plays-container");
    container.innerHTML = "";

    plays.forEach(play => {
        const playElement = document.createElement("div");
        playElement.className = "play";
        playElement.innerHTML = `
            <h3>${play.title}</h3>
            <p>${play.description}</p>
            ${isLoggedIn() ? `
                <button onclick="updatePlay(${play.id}, { title: 'Updated Title', description: 'Updated Description' })">Update</button>
                <button onclick="deletePlay(${play.id})">Delete</button>
            ` : ""}
        `;
        container.appendChild(playElement);
    });
}

// Initialize
document.addEventListener("DOMContentLoaded", () => {
    renderBoxes(aktuelleItems, "aktuelle-grid");
    renderBoxes(tidligereItems, "tidligere-grid");
});
