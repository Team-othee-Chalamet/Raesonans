(async function loadHeader() {
    const header = document.getElementById("header");
    if (!header) return; 
    const html = await fetch("../Pages/header.html").then(r => r.text());
    header.innerHTML = html;
     if (isAdmin === 'true') {
        const adminHtml = await fetch("../Pages/adminHeader.html").then(r => r.text());
        header.innerHTML = adminHtml;
        const logoutBtn = document.getElementById("logout-btn");
        logoutBtn.addEventListener("click", handleLogout);
    }
})();

(async function loadFooter() {
    const footer = document.getElementById("footer");
    if (!footer) return; 
    const html = await fetch("../Pages/footer.html").then(r => r.text());
    footer.innerHTML = html;
})();

const isAdmin = localStorage.getItem("isAdmin");
import { handleLogout } from "../Scripts/logout.js";

// Should be one exported function that others can import
// into their script to keep a smooth structure