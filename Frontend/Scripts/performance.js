import { getPerformances } from '../API/performanceApi.js';

window.addEventListener('DOMContentLoaded', initApp);

function initApp() {
    reloadAndRender();
}

async function reloadAndRender() {
    const performances = await getPerformances();
    const container = document.querySelector("#performance-container");
    performances.forEach(performance => renderPerformances(performance, container));
}

function renderPerformances(performance, container) {
    const performanceCard = document.createElement("div");
    performanceCard.className = "performance-card";

    performanceCard.innerHTML = `
    <img src="${performance.image}" class="performance-image" />

    <div class="performance-info">
        <h3 class="performance-title">${performance.title}</h3>
        <p class="performance-location">${performance.location}</p>
    </div>

    <div class="performance-date">
        <span class="day">${performance.day}</span>
        <span class="month">${performance.month}</span>
        <span class="time">${performance.time}</span>
    </div>

    <button class="ticket-btn">Køb Billetter</button>
    `;

    container.appendChild(performanceCard);

}