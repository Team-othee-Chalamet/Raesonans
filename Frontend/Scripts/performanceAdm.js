import { fetchAllPlays } from '../API/playApi.js';
import { getPerformances, createPerformance, updatePerformance, deletePerformance } from '../API/performanceApi.js';

window.addEventListener('DOMContentLoaded', initApp);

async function initApp() {
    loadPlays();
    setUpEventListeners();
}

async function setUpEventListeners() {
    //const createForm = document.querySelector('#createPerformanceForm');
    //createForm.addEventListener('submit', savePerformance);
    const performanceContainer = document.querySelector('#performance-container');
    performanceContainer.addEventListener('click', handlePerformanceClick);
    //const openModal = document.querySelector("#show-modal");
    //openModal.addEventListener("click", showModal);
    const closeModal = document.querySelector("#close-modal");
    closeModal.addEventListener("click", hideModal);
    const updateForm = document.querySelector("#performance-form");
    updateForm.addEventListener("submit", handleFormSubmit);
}

function showModal() {
    const modal = document.querySelector("#modal");
    modal.classList.remove("hidden");
}

function hideModal() {
    const modal = document.querySelector("#modal");
    modal.classList.add("hidden");

    const form = document.querySelector("#product-form");
    form.reset();
}

async function handlePerformanceClick(event) {
    const target = event.target;

    if (target.classList.contains(".edit-button")) {
        const card = target.closest(".performance-card");
        const performanceId = card.getAttribute("id");

        const performanceToUpdate = {
            id: performanceId,
            play: card.querySelector(".performance-title").textContent,
            location: card.querySelector(".performance-location").textContent,
            performanceDate: card.querySelector(".performance-date").textContent,
            time: card.querySelector(".performance-time").textContent,
            ticketLink: card.querySelector(".ticket-link").href
        }
        fillPerformanceForm(performanceToUpdate);
        showModal();
    }
    else if (target.classList.contains(".delete-button")) {
        const card = target.closest(".performance-card");
        const performanceId = card.getAttribute("id");
        await deletePerformance(performanceId);
    }
    await reloadAndRender();
    
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
    await updatePerformance(performanceId, performance);

    form.reset();
    hideModal();
    await reloadAndRender();
}

async function savePerformance(event) {
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
    await createPerformance(performance);
    form.reset();
}

async function loadPlays() {
    const plays = await fetchAllPlays();

    const dropdown = document.querySelector('#play')
    plays.forEach(play => {
        const option = document.createElement('option');
        option.value = play.id;
        option.textContent = play.title;
        dropdown.appendChild(option);
    });
}

async function fillPerformanceForm(performance) {
    document.querySelector("#play").value = performance.play;
    document.querySelector("#location").value = performance.location;
    document.querySelector("#performance-date").value = performance.performanceDate;
    document.querySelector("#time").value = performance.time;
    document.querySelector("#ticket-link").value = performance.ticketLink;
    
}
