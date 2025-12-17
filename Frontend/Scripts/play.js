import { fetchAllPlays, deletePlay } from "../API/playApi.js"; 

// Tjek login status via localStorage
const isAdmin = localStorage.getItem("isAdmin") === "true";

// Slet funktion
async function handleDelete(id) {
    if (!isAdmin) return;

    // Bekræftelses boks
    const confirmDelete = confirm("Er du sikker på, at du vil slette dette stykke?");
    if (!confirmDelete) return;

    try {
        await deletePlay(id);
        alert("Teaterstykke slettet");
        // Reload siden for at opdatere listen
        window.location.reload(); 
    } catch (error) {
        console.error("Error deleting play:", error);
        alert("Kunne ikke slette: " + error.message);
    }
}

// Render plays to the DOM
function renderPlays(plays, gridId) {
    const container = document.getElementById(gridId);
    if(!container) return; // Safety check
    
    container.innerHTML = "";
    
    plays.forEach(play => {
        const box = document.createElement("div");
        box.classList.add("play-box");

        // HTML indhold (Titel og beskrivelse)
        // Bemærk: Vi indsætter IKKE slet knappen her i stringen, men manuelt nedenfor
        // for lettere at styre event listeners.
        box.innerHTML = `
            <h3>${play.title}</h3>
            <p>${play.description || ""}</p>
        `;

        // 1. ADMIN LOGIK: Tilføj slet knap hvis admin
        if (isAdmin) {
            const delBtn = document.createElement("button");
            delBtn.innerText = "X"; // Eller et ikon
            delBtn.classList.add("delete-play-btn");
            delBtn.title = "Slet forestilling";

            // VIGTIGT: stopPropagation forhindrer at vi navigerer til info-siden
            delBtn.addEventListener("click", (e) => {
                e.stopPropagation(); 
                handleDelete(play.id);
            });

            box.appendChild(delBtn);
        }

        // 2. Redirect ved klik på selve boksen
        box.addEventListener("click", () => {
            window.location.href = `playInformation.html?id=${play.id}`;
        });

        container.appendChild(box);
    });
}


function filterAktuellePlays(plays) {
    // Tjek din DTO om det hedder isActive eller playActive
    return plays.filter(p => p.isActive === true || p.playActive === true); 
}

function filterTidligerePlays(plays) {
    return plays.filter(p => p.isActive === false || p.playActive === false);
}

function renderAdminButton() {
    // 1. Tjek om logget ind
    if (!isAdmin) return;

    // 2. Find containeren
    const mainContainer = document.querySelector(".boxWithCoolBackGround");
    if (!mainContainer) return;

    // 3. Lav container til knappen
    const btnContainer = document.createElement("div");
    btnContainer.classList.add("admin-btn-container");

    // 4. Lav selve knappen
    const btn = document.createElement("button");
    btn.textContent = "+ Opret Nyt Teaterstykke";
    btn.classList.add("create-play-btn");

    // 5. Link til createPlay.html
    btn.addEventListener("click", () => {
        window.location.href = "createPlay.html";
    });

    btnContainer.appendChild(btn);
    
    // Indsæt FØR overskrifterne. 
    // Vi sætter den ind før den første .section
    const firstSection = document.querySelector(".section");
    if (firstSection) {
        mainContainer.insertBefore(btnContainer, firstSection);
    } else {
        mainContainer.prepend(btnContainer);
    }
}


// Initialize
document.addEventListener("DOMContentLoaded", async () => {
    try {
        // 1. Hent ALLE plays
        const allPlays = await fetchAllPlays(); 
        
        // 2. Filtrér lokalt
        const aktuelleItems = filterAktuellePlays(allPlays);
        const tidligereItems = filterTidligerePlays(allPlays);

        // 3. Render
        renderPlays(aktuelleItems, "aktuelle-grid");
        renderPlays(tidligereItems, "tidligere-grid");

        // 4. Render admin knap hvis logget ind
        renderAdminButton();
        
    } catch (e) {
        console.error("Fejl ved hentning af plays:", e);
    }
});