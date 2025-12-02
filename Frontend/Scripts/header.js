(async function loadHeader() {
    const placeholder = document.getElementById("header");
    if (!placeholder) return; 
    const html = await fetch("../Pages/header.html").then(r => r.text());
    placeholder.innerHTML = html;
})();
    