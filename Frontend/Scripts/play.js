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

// Initialize
document.addEventListener("DOMContentLoaded", () => {
    renderBoxes(aktuelleItems, "aktuelle-grid");
    renderBoxes(tidligereItems, "tidligere-grid");
});
