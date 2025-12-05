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

    <div class="performance-date">
        <span class="day">${day}</span>
        <span class="month">${month}</span>
        <span class="time">${performance.time}</span>
    </div>
    </div>

    <button class="toggle-btn">⬇</button>
    <div class="performance-details">
        <p>${performance.playPreviewDto.description}</p>
        <a href="${performance.ticketLink}" class="ticket-btn">Køb Billetter</button>
    </div>
    
    `;

    const toggleBtn = performanceCard.querySelector(".toggle-btn");
        toggleBtn.addEventListener("click", () => {
            performanceCard.classList.toggle("expanded");
        });

    container.appendChild(performanceCard);

}

function formatDate(performance) {
    const date = new Date(performance.performanceDate);

    const day = date.getDate();
    const month = date.toLocaleString('dk-DK', { month: 'short' });

    return { day, month };
}