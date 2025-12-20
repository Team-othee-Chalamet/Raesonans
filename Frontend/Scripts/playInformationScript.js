import { fetchPlayById, updatePlay } from "../API/playApi.js";

// --- STATE ---
const urlParams = new URLSearchParams(window.location.search);
const playId = urlParams.get("id");
const isAdmin = localStorage.getItem("isAdmin") === "true";

let currentPlay = null; 
let isEditing = false;  

// --- LOAD ---
async function loadPlay() {
    try {
        const play = await fetchPlayById(playId);
        currentPlay = play;
        
        renderPlay();

        // Kald admin setup hvis brugeren er admin
        if (isAdmin) {
            setupAdminInterface();
        }
    } catch (error) {
        console.error("Fejl:", error);
    }
}

// --- RENDER ---
function renderPlay() {
    const play = currentPlay;
    
    const titleEl = document.getElementById("playTitleMiddle");
    const descEl = document.getElementById("playDescription");

    // LOGIK: Vis Inputs ELLER Vis Tekst
    if (isEditing) {
        // Redigerings-mode
        // Vi styler inputs så de ligner overskriften/teksten lidt
        titleEl.innerHTML = `<input id="editTitle" type="text" value="${play.title}" style="font-size:inherit; font-weight:bold; text-align:left; width:100%; border:1px solid #ccc; padding:5px;">`;
        descEl.innerHTML = `<textarea id="editDesc" style="width:100%; min-height:150px; font-family:inherit; font-size:inherit; border:1px solid #ccc; padding:10px;">${play.description || ""}</textarea>`;
    } else {
        // Visnings-mode
        titleEl.textContent = play.title;
        descEl.textContent = play.description || "Ingen beskrivelse";
    }

    // Credits & Reviews
    const credits = play.credits || play.creditDtos || [];
    displayCredits(credits);

    const reviews = play.reviewDtos || [];
    if (reviews.length > 0 || isEditing) {
        displayReviews(reviews);
    } else {
        const revContainer = document.getElementById("reviewsContainer");
        if(revContainer) revContainer.innerHTML = "<p>Ingen anmeldelser tilgængelige.</p>";
    }
}

// --- CREDITS LISTE ---
function displayCredits(credits) {
    const container = document.getElementById("creditsList");
    if (!container) return;
    container.innerHTML = ''; 

    const ul = document.createElement('ul');
    ul.classList.add('credit-list-style'); // Husk at have denne klasse i CSS hvis du vil fjerne bullets

    credits.forEach((credit, index) => {
        const li = document.createElement('li');
        li.style.marginBottom = "10px"; // Lidt luft mellem hver person

        if (isEditing) {
            li.innerHTML = `
                <div style="display:flex; align-items:center; gap:5px;">
                    <input class="edit-role" data-index="${index}" value="${credit.role}" placeholder="Rolle" style="width:80px; padding:4px;">
                    : 
                    <input class="edit-name" data-index="${index}" value="${credit.name}" placeholder="Navn" style="padding:4px;">
                    <button onclick="deleteCredit(${index})" style="background:red; color:white; border:none; cursor:pointer; width:25px; height:25px; border-radius:50%; display:flex; align-items:center; justify-content:center;">&times;</button>
                </div>`;
        } else {
            li.innerHTML = `
                <span class="credit-role" style="font-weight:bold;">${credit.role}: </span>
                <span class="credit-name">${credit.name}</span>`;
        }
        ul.appendChild(li);
    });

    container.appendChild(ul);

    if (isEditing) {
        const addBtn = document.createElement("button");
        addBtn.textContent = "+ Tilføj Person";
        addBtn.style.marginTop = "10px";
        addBtn.style.cursor = "pointer";
        addBtn.style.padding = "5px 10px";
        addBtn.onclick = addCredit;
        container.appendChild(addBtn);
    }
}

// --- REVIEWS LISTE ---
function displayReviews(reviews) {
    const container = document.getElementById("reviewsContainer");
    if (!container) return;
    container.innerHTML = ''; 

    reviews.forEach((review, index) => {
        const reviewBox = document.createElement('div');
        reviewBox.style.marginBottom = "20px";
        reviewBox.style.padding = "15px";
        reviewBox.style.border = "1px solid #eee";
        reviewBox.style.borderRadius = "5px";
        reviewBox.style.backgroundColor = "#fff"; // Sikrer hvid baggrund i boksen

        if (isEditing) {
            reviewBox.innerHTML = `
                <div style="margin-bottom:10px;">
                    <label style="font-size:12px; font-weight:bold; display:block;">Titel:</label> 
                    <input class="edit-rev-title" value="${review.title}" style="width:100%; padding:5px;">
                </div>
                <div style="margin-bottom:10px; display:flex; gap:10px;">
                    <div>
                        <label style="font-size:12px; font-weight:bold; display:block;">Score:</label> 
                        <input class="edit-rev-score" type="number" value="${review.actualScore}" style="width:50px; padding:5px;">
                    </div>
                    <div>
                        <label style="font-size:12px; font-weight:bold; display:block;">Max:</label> 
                        <input class="edit-rev-max" type="number" value="${review.maxScore}" style="width:50px; padding:5px;">
                    </div>
                </div>
                <div style="margin-bottom:10px;">
                    <label style="font-size:12px; font-weight:bold; display:block;">Tekst:</label>
                    <textarea class="edit-rev-text" style="width:100%; height:60px; padding:5px;">${review.reviewText}</textarea>
                </div>
                <button onclick="deleteReview(${index})" style="background:#ff4d4d; color:white; border:none; padding:5px 10px; border-radius:3px; cursor:pointer;">Slet Anmeldelse</button>
            `;
        } else {
            reviewBox.innerHTML = `
                <h4 style="margin-top:0;">${review.title || 'Anmeldelse'}</h4>
                <div class="star-rating" style="color:gold; margin-bottom:5px;">${generateStarRating(review.actualScore, review.maxScore)}</div>
                <p style="font-size:14px; margin-bottom:5px;">${review.reviewText}</p>
                ${review.sourceLink ? `<a href="${review.sourceLink}" target="_blank" style="font-size:12px; color:#6a3dad;">Læs hele anmeldelsen</a>` : ''}
            `;
        }
        container.appendChild(reviewBox);
    });

    if (isEditing) {
        const addBtn = document.createElement("button");
        addBtn.textContent = "+ Tilføj Anmeldelse";
        addBtn.style.marginTop = "10px";
        addBtn.style.cursor = "pointer";
        addBtn.style.padding = "5px 10px";
        addBtn.onclick = addReview;
        container.appendChild(addBtn);
    }
}

// --- ADMIN KNAP (RETTET PLACERING) ---
function setupAdminInterface() {
    // 1. Tjek om knappen allerede findes for at undgå dubletter
    if (document.getElementById("adminEditBtn")) return;

    // 2. Find "Description" elementet, så vi kan lægge knappen under det
    const descEl = document.getElementById("playDescription");
    if (!descEl) {
        console.warn("Kunne ikke finde playDescription elementet til admin knappen");
        return;
    }

    // 3. Opret knappen
    const btn = document.createElement("button");
    btn.id = "adminEditBtn";
    btn.textContent = "Rediger Teaterstykke"; // Rettet tekst
    
    // Styling (Du kan flytte dette til CSS hvis du foretrækker)
    btn.style.marginTop = "25px";
    btn.style.padding = "10px 20px";
    btn.style.cursor = "pointer";
    btn.style.backgroundColor = "#222"; 
    btn.style.color = "#fff";
    btn.style.border = "none";
    btn.style.borderRadius = "5px";
    btn.style.fontSize = "14px";
    btn.style.fontWeight = "bold";
    btn.style.transition = "background 0.3s";

    // Hover effekt
    btn.onmouseover = () => btn.style.backgroundColor = "#ff59c7";
    btn.onmouseout = () => btn.style.backgroundColor = isEditing ? "#4CAF50" : "#222";

    // Klik logik
    btn.onclick = async () => {
        if (!isEditing) {
            // Skift til REDIGER
            isEditing = true;
            btn.textContent = "Gem Ændringer";
            btn.style.backgroundColor = "#4CAF50"; // Grøn
            renderPlay();
        } else {
            // GEM
            await saveChanges();
            isEditing = false;
            btn.textContent = "Rediger Teaterstykke";
            btn.style.backgroundColor = "#222";
            renderPlay();
        }
    };

    // 4. Indsæt knappen LIGE EFTER description elementet
    descEl.insertAdjacentElement('afterend', btn);
}

// --- SYNC & SAVE ---
function syncInputsToState() {
    if (!isEditing) return;

    // Titel/Beskrivelse
    const tInput = document.getElementById("editTitle");
    const dInput = document.getElementById("editDesc");
    if(tInput) currentPlay.title = tInput.value;
    if(dInput) currentPlay.description = dInput.value;

    // Credits
    const roles = document.querySelectorAll(".edit-role");
    const names = document.querySelectorAll(".edit-name");
    let creds = currentPlay.creditDtos || currentPlay.credits;
    if (creds) {
        roles.forEach((input, i) => {
            if(creds[i]) {
                creds[i].role = input.value;
                creds[i].name = names[i].value;
            }
        });
    }

    // Reviews
    const rTitles = document.querySelectorAll(".edit-rev-title");
    const rScores = document.querySelectorAll(".edit-rev-score");
    const rMaxs = document.querySelectorAll(".edit-rev-max");
    const rTexts = document.querySelectorAll(".edit-rev-text");
    let revs = currentPlay.reviewDtos;
    if (revs) {
        rTitles.forEach((input, i) => {
            if(revs[i]) {
                revs[i].title = input.value;
                revs[i].actualScore = parseInt(rScores[i].value);
                revs[i].maxScore = parseInt(rMaxs[i].value);
                revs[i].reviewText = rTexts[i].value;
            }
        });
    }
}

async function saveChanges() {
    syncInputsToState();
    const btn = document.getElementById("adminEditBtn");
    if(btn) {
        btn.textContent = "Gemmer...";
        btn.disabled = true;
    }
    
    try {
        await updatePlay(playId, currentPlay);
        alert("Ændringer er gemt!");
    } catch (e) {
        console.error(e);
        alert("Fejl ved gemning: " + e.message);
    } finally {
        if(btn) btn.disabled = false;
    }
}

// --- GLOBALE FUNKTIONER ---
window.addCredit = function() {
    syncInputsToState(); 
    if (!currentPlay.creditDtos) currentPlay.creditDtos = []; 
    currentPlay.creditDtos.push({ role: "", name: "" }); 
    renderPlay();
};

window.deleteCredit = function(index) {
    syncInputsToState(); 
    let list = currentPlay.creditDtos || currentPlay.credits;
    list.splice(index, 1); 
    renderPlay();
};

window.addReview = function() {
    syncInputsToState();
    if (!currentPlay.reviewDtos) currentPlay.reviewDtos = [];
    currentPlay.reviewDtos.push({ title: "Ny Titel", actualScore: 5, maxScore: 6, reviewText: "" });
    renderPlay();
};

window.deleteReview = function(index) {
    syncInputsToState();
    currentPlay.reviewDtos.splice(index, 1);
    renderPlay();
};

// --- STJERNER ---
function generateStarRating(actualScore, maxScore) {
    const totalStars = maxScore; 
    let starHtml = '';
    const fullStar = '★';
    const emptyStar = '☆';
    
    for (let i = 1; i <= totalStars; i++) {
        starHtml += (i <= actualScore) 
            ? `<span class="full-star">${fullStar}</span>` 
            : `<span class="empty-star">${emptyStar}</span>`;
    }
    return starHtml;
}

// Start
loadPlay();