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
    const { day, month } = formatDate(performance);

    // ${performance.playPreviewDto.splashImg} for image source when available
    performanceCard.innerHTML = `
    <div class="performance-top">
        <img src="null" class="performance-image" />

    <div class="performance-info">
        <h3 class="performance-title">${performance.playPreviewDto.title}</h3>
        <p class="performance-location">${performance.location}</p>
    </div>

    <div class="divider"></div>

    <div class="performance-date">
        <span class="day">${day}</span>
        <span class="month">${month}</span>
        <span class="time">${performance.time}</span>
    </div>
    </div>

    <button class="toggle-btn" aria-expanded="false">⬇</button>
    <div class="performance-details" aria-hidden="true">
        <p>${performance.playPreviewDto.description}</p>
        <a href="${performance.ticketLink}" class="ticket-btn">Køb Billetter</a>
    </div>
    
    `;

    const toggleBtn = performanceCard.querySelector(".toggle-btn");
    const details = performanceCard.querySelector(".performance-details");

    toggleBtn.addEventListener("click", () => {
        const isExpanded = performanceCard.classList.toggle("expanded");
        // opdatér accessibility attributes
        toggleBtn.setAttribute("aria-expanded", isExpanded ? "true" : "false");
        details.setAttribute("aria-hidden", isExpanded ? "false" : "true");
    });

    container.appendChild(performanceCard);

}

function formatDate(performance) {
    const date = new Date(performance.performanceDate);

    const day = date.getDate();
    const month = date.toLocaleString('dk-DK', { month: 'short' });

    return { day, month };
}