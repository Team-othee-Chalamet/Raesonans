import { fetchAllPlays } from '../API/playApi.js';
import { getPerformances, updatePerformance, deletePerformance, getUpcomingPerformances } from '../API/performanceApi.js';

window.addEventListener('DOMContentLoaded', initApp);

const isAdmin = localStorage.getItem("isAdmin");

function initApp() {
    reloadAndRender();
    loadPlays();
    setUpEventListeners();
    if (isAdmin === 'true') {
        document.getElementById("adminButton").removeAttribute("hidden");
    }
}

async function setUpEventListeners() {
    const performanceContainer = document.querySelector('#performance-container');
    performanceContainer.addEventListener('click', handlePerformanceClick);
    const closeModal = document.querySelector("#close-modal");
    closeModal.addEventListener("click", hideModal);
    const updateForm = document.querySelector("#performance-form");
    updateForm.addEventListener("submit", handleFormSubmit);
}

async function reloadAndRender() {
    const container = document.querySelector("#performance-container");
    container.innerHTML = "";
    const performances = await getPerformances();
    performances.forEach(performance => renderPerformances(performance, container));
}

function renderPerformances(performance, container) {
    const performanceCard = document.createElement("div");
    performanceCard.className = "performance-card";
    const { day, month } = formatDate(performance);

    performanceCard.setAttribute("id", performance.id);

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
        <a href="${performance.ticketLink}" class="ticket-link">Køb Billetter</a>
        
        ${isAdmin === 'true' ? `
        <button class="edit-button">Rediger</button>
        <button class="delete-button">Slet</button>`: ''} 
    </div>
    `;

    const toggleBtn = performanceCard.querySelector(".toggle-btn");
    const details = performanceCard.querySelector(".performance-details");

    toggleBtn.addEventListener("click", () => {
        const isExpanded = performanceCard.classList.toggle("expanded");
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


function showModal() {
    const modal = document.querySelector("#modal");
    modal.classList.remove("hidden");
}

function hideModal() {
    const modal = document.querySelector("#modal");
    modal.classList.add("hidden");

    const form = document.querySelector("#performance-form");
    form.reset();
}

async function handlePerformanceClick(event) {
    event.preventDefault();
    console.log("CLICK");
    const editButton = event.target.closest(".edit-button");
    const deleteButton = event.target.closest(".delete-button");

    if (editButton) {
        const card = editButton.closest(".performance-card");
        const performanceId = card.getAttribute("id");

        const performanceToUpdate = {
            id: performanceId,
            play: card.querySelector(".performance-title").textContent,
            location: card.querySelector(".performance-location").textContent,
            performanceDate: card.querySelector(".performance-date").textContent,
            time: card.querySelector(".time").textContent,
            ticketLink: card.querySelector(".ticket-link").href
        }
        fillPerformanceForm(performanceToUpdate);
        showModal();
    }
    else if (deleteButton) {
        const card = deleteButton.closest(".performance-card");
        const performanceId = card.getAttribute("id");
        await deletePerformance(performanceId);
        await reloadAndRender();
    }
    
}

async function handleFormSubmit(event) {
    event.preventDefault();
    const form = event.target;
    const formData = new FormData(form);

    const performance = {
        playPreviewDto: { id: Number(formData.get("play")) },
        location: formData.get("location"),
        performanceDate: formData.get("performance-date"),
        time: formData.get("time"),
        ticketLink: formData.get("ticket-link")
    }
    const performanceId = formData.get("id")
    console.log("ID der sendes:", performance.id);
    await updatePerformance(performanceId, performance);

    form.reset();
    hideModal();
    await reloadAndRender();
}


async function loadPlays() {
    const plays = await fetchAllPlays();

    const dropdown = document.querySelector('#play')
    dropdown.innerHTML = '';
    plays.forEach(play => {
        const option = document.createElement('option');
        option.value = play.id;
        option.textContent = play.title;
        dropdown.appendChild(option);
    });
}

async function fillPerformanceForm(performance) {
    document.querySelector("#id").value = performance.id;
    document.querySelector("#play").value = performance.play;
    document.querySelector("#location").value = performance.location;
    document.querySelector("#performance-date").value = performance.performanceDate;
    document.querySelector("#time").value = performance.time;
    document.querySelector("#ticket-link").value = performance.ticketLink;
    
}
