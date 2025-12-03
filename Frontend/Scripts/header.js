(async function loadHeader() {
    const header = document.getElementById("header");
    if (!header) return; 
    const html = await fetch("../Pages/header.html").then(r => r.text());
    header.innerHTML = html;
})();

(async function loadFooter() {
    const footer = document.getElementById("footer");
    if (!footer) return; 
    const html = await fetch("../Pages/footer.html").then(r => r.text());
    footer.innerHTML = html;
})();